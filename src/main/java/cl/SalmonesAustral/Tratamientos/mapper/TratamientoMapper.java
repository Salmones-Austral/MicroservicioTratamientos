package cl.SalmonesAustral.Tratamientos.mapper;
import cl.SalmonesAustral.Tratamientos.dto.CreateTratamientoRequest;
import cl.SalmonesAustral.Tratamientos.dto.UpdateTrataminetoRequest;
import cl.SalmonesAustral.Tratamientos.modelo.Tratamiento;


public class TratamientoMapper {

    public static Tratamiento toModel(CreateTratamientoRequest request) {
        Tratamiento t = new Tratamiento();
        t.setJaulaId(request.getJaulaId());
        t.setEnfermedad(request.getEnfermedad());
        t.setMedicamento(request.getMedicamento());
        t.setDosis(request.getDosis());
        t.setDuracionDias(request.getDuracionDias());
        t.setPeriodoResguardo(request.getPeriodoResguardo());
        t.setObservaciones(request.getObservaciones());
        return t;
    }


    public static Tratamiento toModel(Long id, UpdateTrataminetoRequest request) {
        Tratamiento t = new Tratamiento();
        t.setId(id);
        t.setJaulaId(request.getJaulaId());
        t.setEnfermedad(request.getEnfermedad());
        t.setMedicamento(request.getMedicamento());
        t.setDosis(request.getDosis());
        t.setDuracionDias(request.getDuracionDias());
        t.setPeriodoResguardo(request.getPeriodoResguardo());
        t.setObservaciones(request.getObservaciones());
        return t;
    }








}
