package cl.SalmonesAustral.Tratamientos.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer jaulaId;
    private String enfermedad;
    private String medicamento;
    private String dosis;

    private Integer duracionDias;
    private Integer periodoResguardo;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private String estado;
    private String observaciones;

    public Tratamiento() {
    }

    public Long getId() {
        return id;
    }

    public Integer getJaulaId() {
        return jaulaId;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
