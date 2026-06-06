package cl.SalmonesAustral.Tratamientos.controller;

import cl.SalmonesAustral.Tratamientos.dto.CreateTratamientoRequest;
import cl.SalmonesAustral.Tratamientos.modelo.Tratamiento;
import cl.SalmonesAustral.Tratamientos.service.TratamientoService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
public class TratamientoController {

    private final TratamientoService service;

    public TratamientoController(TratamientoService service) {
        this.service = service;
    }

    // Crear tratamiento
    @PostMapping
    public ResponseEntity<Tratamiento> create(@Valid @RequestBody CreateTratamientoRequest request) {
        Tratamiento nuevoTratamiento=service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTratamiento);
    }

    // Obtener todos
    @GetMapping
    public ResponseEntity<List<Tratamiento>> getAll() {
        List<Tratamiento> tratamientos=service.getAll();
        return ResponseEntity.ok(tratamientos);
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tratamiento> getById(@PathVariable Integer id) {
        Tratamiento tratamiento=service.getById(id);
        return ResponseEntity.ok(tratamiento);
    }

    // Finalizar tratamiento
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Tratamiento> finalizar(@PathVariable Integer id) {
        Tratamiento tratamientoFinalizado=service.finalizar(id);
        return ResponseEntity.ok(tratamientoFinalizado);
    }

    // CLAVE: validar si una jaula está en resguardo
    @GetMapping("/resguardo/{jaulaId}")
    public ResponseEntity<Boolean> estaEnResguardo(@PathVariable Integer jaulaId) {
        Boolean resguardo=service.estaEnResguardo(jaulaId);
        return ResponseEntity.ok(resguardo);
    }
}