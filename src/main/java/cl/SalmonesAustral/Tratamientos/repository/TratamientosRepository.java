package cl.SalmonesAustral.Tratamientos.repository;

import cl.SalmonesAustral.Tratamientos.modelo.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TratamientosRepository extends JpaRepository<Tratamiento, Long> {

    // Buscar tratamientos por jaula
    List<Tratamiento> findByJaulaId(Integer jaulaId);

    // Buscar tratamientos por estado (ACTIVO, FINALIZADO)
    List<Tratamiento> findByEstado(String estado);

    // 🔥 CLAVE: saber si hay tratamientos activos en una jaula
    List<Tratamiento> findByJaulaIdAndEstado(Integer jaulaId, String estado);
}
