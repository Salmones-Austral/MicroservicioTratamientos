package cl.SalmonesAustral.Tratamientos.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateTratamientoRequest (
 
    //@NotNull(message = "La jaula es obligatoria")
    //Integer jaulaId,
    //Por logica del caso hay datos de TRATAMIENTO que no deberian poder
    //actualizarse o cambiar pero se muestra el atributo escrito y validado para demostrar la implementacion 

    @NotBlank(message = "Debe ingresar la enfermedad")
    String enfermedad,

    @NotBlank(message = "El campo de medicamento es obligatorio")
    String medicamento,

    @NotBlank(message = "La dosis es obligatoria")
    String dosis,

    @NotNull(message="La duracion en dias, es obligatoria")
    @PositiveOrZero(message = "La duración no puede ser negativa")
    Integer duracionDias,

    @PositiveOrZero(message = "El periodo de resguardo no puede ser negativo")
    Integer periodoResguardo,

    String observaciones
){

}

    