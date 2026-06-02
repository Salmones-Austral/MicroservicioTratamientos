package cl.SalmonesAustral.Tratamientos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateTratamientoRequest {


    @NotBlank(message = "La jaula es obligatoria")
    private Integer jaulaId;

    private String enfermedad;
    private String medicamento;
    private String dosis;

    @PositiveOrZero(message = "La duración no puede ser negativa")
    private Integer duracionDias;

    @PositiveOrZero(message = "El periodo de resguardo no puede ser negativo")
    private Integer periodoResguardo;

    private String observaciones;

    public CreateTratamientoRequest() {
    }

    public Integer getJaulaId() {
        return jaulaId;
    }

    public void setJaulaId(Integer jaulaId) {
        this.jaulaId = jaulaId;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public Integer getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(Integer duracionDias) {
        this.duracionDias = duracionDias;
    }

    public Integer getPeriodoResguardo() {
        return periodoResguardo;
    }

    public void setPeriodoResguardo(Integer periodoResguardo) {
        this.periodoResguardo = periodoResguardo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }






}
