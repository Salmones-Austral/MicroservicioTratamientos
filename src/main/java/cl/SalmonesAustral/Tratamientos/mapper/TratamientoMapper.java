package cl.SalmonesAustral.Tratamientos.mapper;
import cl.SalmonesAustral.Tratamientos.dto.CreateTratamientoRequest;
import cl.SalmonesAustral.Tratamientos.dto.UpdateTratamientoRequest;
import cl.SalmonesAustral.Tratamientos.modelo.Tratamiento;


public class TratamientoMapper {

    public static Tratamiento toModel(CreateTratamientoRequest request) {
        return new Tratamiento(
            null,
            request.jaulaId(),
            request.veterinarioId(),
            request.enfermedad(),
            request.medicamento(),
            request.dosis(),
            request.duracionDias(),
            request.pecesTratados(),
            request.periodoResguardo(),
            request.loteMedicamento(),
            request.fechaInicio(),
            request.fechaFin(),
            request.estado(),
            request.observaciones()
        );
    }

    //convierte updateTratamiento a tratamiento (para put)

    public static Tratamiento toModel(Integer id, UpdateTratamientoRequest request) {
       return new Tratamiento(
        id,
        request.enfermedad(),
        request.medicamento(),
        request.dosis(),
        request.duracionDias(),
        request.periodoResguardo(),
        request.observaciones()

       );
    }
}
