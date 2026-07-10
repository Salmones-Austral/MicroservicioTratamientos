package cl.SalmonesAustral.Tratamientos.controller;

import cl.SalmonesAustral.Tratamientos.dto.CreateTratamientoRequest;
import cl.SalmonesAustral.Tratamientos.modelo.Tratamiento;
import cl.SalmonesAustral.Tratamientos.service.TratamientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Tratamientos", description = "Operaciones relacionadas con el control de tratamientos")
@RequestMapping("/api/tratamientos")
public class TratamientoController {

    private final TratamientoService service;

    public TratamientoController(TratamientoService service) {
        this.service = service;
    }

    // Crear tratamiento
    @PostMapping
    @Operation(summary = "Crear un nuevo tratamiento", description = "Registra un tratamiento validando" )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tratamiento creado exitosamente "),
        @ApiResponse(responseCode = "400", description = "Error en la validacion de datos ")
    })

    public ResponseEntity<Tratamiento> create(@Valid @RequestBody CreateTratamientoRequest request) {
        Tratamiento nuevoTratamiento=service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTratamiento);
    }

    // Obtener todos
    @GetMapping
    @Operation(summary = "Obtener todos los tratamientos", description = "Retorna el listado de los tratamientos registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente")
    })
    public ResponseEntity<List<Tratamiento>> getAll() {
        List<Tratamiento> tratamientos=service.getAll();
        return ResponseEntity.ok(tratamientos);
    }

    // Obtener por ID
    @GetMapping("/{id}")

    @Operation(summary = "Obtener un tratamiento por ID", description = "Busca un tratamiento específico mediante su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tratamiento encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Tratamiento no encontrado")
    })

    public ResponseEntity<Tratamiento> getById(@PathVariable Integer id) {
        Tratamiento tratamiento=service.getById(id);
        return ResponseEntity.ok(tratamiento);
    }

    // Finalizar tratamiento
    @PutMapping("/{id}/finalizar")

    @Operation(summary = "Finalizar un tratamiento activo", description = "Cambia el estado de un tratamiento a FINALIZADO y asigna la fecha de término actual")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tratamiento finalizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "El tratamiento no está activo o no se pudo procesar"),
        @ApiResponse(responseCode = "404", description = "Tratamiento no encontrado")
    })
    public ResponseEntity<Tratamiento> finalizar(@PathVariable Integer id) {
        Tratamiento tratamientoFinalizado=service.finalizar(id);
        return ResponseEntity.ok(tratamientoFinalizado);
    }

    // CLAVE: validar si una jaula está en resguardo
    @GetMapping("/resguardo/{jaulaId}")
    @Operation(summary = "Valida resguardo de una jaula", description = "Verifica si una jaula se encuentra bajo período de resguardo activo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada con éxito (retorna T or F)")
    })
    public ResponseEntity<Boolean> estaEnResguardo(@PathVariable Integer jaulaId) {
        Boolean resguardo=service.estaEnResguardo(jaulaId);
        return ResponseEntity.ok(resguardo);
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarTratamiento(@PathVariable Long id) {
        service.eliminarPorId(id); 
        return ResponseEntity.noContent().build(); 
}
}