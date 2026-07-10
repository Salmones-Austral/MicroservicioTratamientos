package cl.SalmonesAustral.Tratamientos.service;


import cl.SalmonesAustral.Tratamientos.dto.CreateTratamientoRequest;
import cl.SalmonesAustral.Tratamientos.dto.JaulaEstadoUpdate;
import cl.SalmonesAustral.Tratamientos.modelo.Tratamiento;
import cl.SalmonesAustral.Tratamientos.repository.TratamientosRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import cl.SalmonesAustral.Tratamientos.mapper.TratamientoMapper;
import java.time.LocalDate;
import java.util.List;

@Service
@Validated
public class TratamientoService {
    
    private final TratamientosRepository repository;
    private final WebClient jaulaWebClient;
    private final WebClient cosechaWebClient;


    public TratamientoService(TratamientosRepository repository,
        @Qualifier("jaulasWebClient") WebClient jaulaWebClient,
        @Qualifier("cosechaWebClient") WebClient cosechaWebClient) {
        this.repository = repository;
        this.jaulaWebClient=jaulaWebClient;
        this.cosechaWebClient=cosechaWebClient;

        }

    // Crear tratamiento
    public Tratamiento create(@Valid @RequestBody CreateTratamientoRequest request) {
        try{
            jaulaWebClient.get()
            .uri("/" + request.jaulaId())
            .retrieve()
            .bodyToMono(Object.class)
            .block();

            System.out.println("Validacion de jaula exitosa.");
        }catch(Exception e) {
            throw new RuntimeException("La jaula especificada no existe en el sistema");
        }
        //Nuevo requerimiento: si se detecta "SRS", enviar orden para desactivar la jaula en su ms
        if ("SRS".equalsIgnoreCase(request.enfermedad())) {
            try {
                JaulaEstadoUpdate jaulaUpdate = new JaulaEstadoUpdate(
                    request.jaulaId().longValue(),
                    false //aqui se cambia el estado "activa" de jaula a FALSE
                );

                jaulaWebClient.put()
                .uri("/" + request.jaulaId())
                .bodyValue(jaulaUpdate)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
                System.out.println("se envió la actualizacion de estado al ms de Jaulas");
            }catch (Exception e) {
                System.out.println("ADVERTENCIA: No se pudo actualizar el estado en el ms de Jaula: " + e.getMessage());
            }
        }


        // Evitar más de un tratamiento activo por jaula
        List<Tratamiento> activos = repository.findByJaulaIdAndEstado(request.jaulaId(), "ACTIVO");
        if (!activos.isEmpty()) {
            throw new RuntimeException("Ya existe un tratamiento activo para esta jaula");
        }
        //mapeamos la entidad usando el mapper del record
        Tratamiento t = TratamientoMapper.toModel(request);
        // Fecha inicio automática
        t.setFechaInicio(LocalDate.now());
        t.setEstado("ACTIVO");
        t.setFechaFin(null);

        Tratamiento tratamientoGuardado=repository.save(t);
        //conexion a cosecha:bloquear si hay resguardo(put)

        if(tratamientoGuardado.getPeriodoResguardo()!=null) {
            if(tratamientoGuardado.getPeriodoResguardo()>0) {
                try {
                    cosechaWebClient.put()
                        .uri("/bloquear/" + request.jaulaId())
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();
                        System.out.println("Orden de bloqueo enviada con exito");
                }catch (Exception e) {
                    //mensaje para que no falle si cosecha esta apagado
                    System.out.println("ADVERTENCIA: No se pudo enviar orden de bloqueo a cosecha");
                }
            }
        }

        return tratamientoGuardado;
    }

    //  Obtener todos
    public List<Tratamiento> getAll() {
        return repository.findAll();
    }

    // Obtener por ID
    public Tratamiento getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));
    }

    // Finalizar tratamiento
    public Tratamiento finalizar(Integer id) {
        Tratamiento t = getById(id);
        if (!"ACTIVO".equals(t.getEstado())) {
            throw new RuntimeException("Solo se pueden finalizar tratamientos que estan ACTIVO");
        }

        t.setEstado("FINALIZADO");
        t.setFechaFin(LocalDate.now());

        return repository.save(t);
    }

    //  CLAVE: saber si una jaula está en resguardo
    public boolean estaEnResguardo(Integer jaulaId) {
        List<Tratamiento> activos = repository.findByJaulaIdAndEstado(jaulaId, "ACTIVO");

        if (activos.isEmpty()) {
            return false;
        }

        Tratamiento t = activos.get(0);

        // Fecha fin + periodo de resguardo+duracion
        int diasTotalesEspera = t.getDuracionDias() + (t.getPeriodoResguardo()!=null ? t.getPeriodoResguardo():0);
        LocalDate finResguardo = t.getFechaInicio().plusDays(diasTotalesEspera);

        return LocalDate.now().isBefore(finResguardo);
    }
}

