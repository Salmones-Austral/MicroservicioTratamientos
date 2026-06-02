package cl.SalmonesAustral.Tratamientos.service;


import cl.SalmonesAustral.Tratamientos.modelo.Tratamiento;
import cl.SalmonesAustral.Tratamientos.repository.TratamientosRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TratamientoService {

    private final TratamientosRepository repository;

    public TratamientoService(TratamientosRepository repository) {
        this.repository = repository;
    }

    // Crear tratamiento
    public Tratamiento crear(Tratamiento t) {

        // Validación simple
        if (t.getJaulaId() == null) {
            throw new RuntimeException("La jaula es obligatoria");
        }

        // Evitar más de un tratamiento activo por jaula
        List<Tratamiento> activos = repository.findByJaulaIdAndEstado(t.getJaulaId(), "ACTIVO");
        if (!activos.isEmpty()) {
            throw new RuntimeException("Ya existe un tratamiento activo para esta jaula");
        }

        // Fecha inicio automática
        t.setFechaInicio(LocalDate.now());

        // Calcular fecha fin
        if (t.getDuracionDias() != null) {
            t.setFechaFin(t.getFechaInicio().plusDays(t.getDuracionDias()));
        }

        // Estado inicial
        t.setEstado("ACTIVO");

        return repository.save(t);
    }

    //  Obtener todos
    public List<Tratamiento> obtenerTodos() {
        return repository.findAll();
    }

    // Obtener por ID
    public Tratamiento obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));
    }

    // Finalizar tratamiento
    public Tratamiento finalizar(Long id) {
        Tratamiento t = obtenerPorId(id);

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

        // Fecha fin + periodo de resguardo
        LocalDate finResguardo = t.getFechaFin().plusDays(t.getPeriodoResguardo());

        return LocalDate.now().isBefore(finResguardo);
    }
}
