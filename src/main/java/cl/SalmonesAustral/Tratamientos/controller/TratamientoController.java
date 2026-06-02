package cl.SalmonesAustral.Tratamientos.controller;

import cl.SalmonesAustral.Tratamientos.modelo.Tratamiento;
import cl.SalmonesAustral.Tratamientos.service.TratamientoService;
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
    public ResponseEntity<Tratamiento> crear(@RequestBody Tratamiento tratamiento) {
        return ResponseEntity.ok(service.crear(tratamiento));
    }

    // Obtener todos
    @GetMapping
    public ResponseEntity<List<Tratamiento>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tratamiento> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    // Finalizar tratamiento
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Tratamiento> finalizar(@PathVariable Long id) {
        return ResponseEntity.ok(service.finalizar(id));
    }

    // CLAVE: validar si una jaula está en resguardo
    @GetMapping("/resguardo/{jaulaId}")
    public ResponseEntity<Boolean> estaEnResguardo(@PathVariable Integer jaulaId) {
        return ResponseEntity.ok(service.estaEnResguardo(jaulaId));
    }
}