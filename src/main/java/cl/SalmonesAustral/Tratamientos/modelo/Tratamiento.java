package cl.SalmonesAustral.Tratamientos.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tabla_tratamiento")
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "jaula_id", nullable = false)
    private Integer jaulaId;

    @Column(name = "veterinario_id", nullable = false)
    private Integer veterinarioId;

    @Column(name = "enfermedad", nullable = false )
    private String enfermedad;

    @Column(name = "medicamento", nullable = false)
    private String medicamento;

    @Column(name = "lote_medicamento")
    private String loteMedicamento;

    @Column(name = "dosis", nullable = false)
    private String dosis; //se calcula por biomasa(peso total de peces a tratar)

    @Column(name = "duracion_dias", nullable = false)
    private Integer duracionDias;

    @Column(name = "peces_tratados", nullable = false)
    private Integer pecesTratados;

    @Column(name = "periodo_resguardo")
    private Integer periodoResguardo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "estado", nullable = false)
    private String estado; // en progreso, completado, suspendido

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    public Tratamiento() {}

//constructor para createTR)
    public Tratamiento(Integer id, Integer jaulaId, Integer veterinarioId, String enfermedad, String medicamento,
        String dosis, Integer duracionDias, Integer pecesTratados, Integer periodoResguardo, String loteMedicamento,
        LocalDate fechaInicio, LocalDate fechaFin, String estado, String observaciones) {
            this.id=id;
            this.jaulaId=jaulaId;
            this.veterinarioId=veterinarioId;
            this.enfermedad=enfermedad;
            this.medicamento=medicamento;
            this.dosis=dosis;
            this.duracionDias=duracionDias;
            this.pecesTratados=pecesTratados;
            this.periodoResguardo=periodoResguardo;
            this.loteMedicamento=loteMedicamento;
            this.fechaInicio=fechaInicio;
            this.fechaFin=fechaFin;
            this.estado=estado;
            this.observaciones=observaciones;
        }
//constructor para updateTR)
    public Tratamiento(Integer id, String enfermedad, String medicamento, String dosis,
            Integer duracionDias, Integer periodoResguardo, String observaciones) {
                this.id=id;
                this.enfermedad=enfermedad;
                this.medicamento=medicamento;
                this.dosis=dosis;
                this.duracionDias=duracionDias;
                this.periodoResguardo=periodoResguardo;
                this.observaciones=observaciones;
            }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id=id;
    }

    public Integer getJaulaId() {
        return jaulaId;
    }

    public void setJaulaId(Integer jaulaId) {
        this.jaulaId = jaulaId;
    }

    public Integer getVeterinarioId() {
        return veterinarioId;
    }
    public void setVeterinarioId(Integer veterinarioId) {
        this.veterinarioId=veterinarioId;
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
    public String getLoteMedicamento() {
        return loteMedicamento;
    }
    public void setLoteMedicamento(String loteMedicamento) {
        this.loteMedicamento=loteMedicamento;
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
    public Integer getPecesTratados() {
        return pecesTratados;
    }
    public void setPecesTratados(Integer pecesTratados) {
        this.pecesTratados = pecesTratados;
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
