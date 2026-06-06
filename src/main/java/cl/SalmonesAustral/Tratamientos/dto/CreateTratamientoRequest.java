package cl.SalmonesAustral.Tratamientos.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateTratamientoRequest (

    @NotNull(message = "El ID de la jaula es obligatorio")
    Integer jaulaId,

    @NotNull(message = "El ID de veterinario es obligatorio")
    Integer veterinarioId,

    @NotBlank(message = "La enfermedad no puede estar vacia")
    String enfermedad,

    @NotBlank(message = "El nombre del medicamento es obligatorio")
    String medicamento,

    @NotBlank(message = "La dosis a usar, es obligatoria")
    String dosis,

    @NotNull(message = "La duracion en dias, es obligatoria")
    @Positive(message = "La duracion debe ser un numero positivo")
    Integer duracionDias,

    @NotNull(message = "La cantidad de peces tratados es obligatoria")
    @Positive(message = "La cantidad de peces debe ser un numero positivo")
    Integer pecesTratados,

    @PositiveOrZero(message = "El periodo de resguardo no puede ser negativo")
    Integer periodoResguardo,

    String loteMedicamento,

    LocalDate fechaInicio,

    LocalDate fechaFin,

    String estado,

    String observaciones
){



}
