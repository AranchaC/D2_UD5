package daw.dwes.ud5.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import daw.dwes.ud5.entities.Resultado;

public interface ResultadoRepository extends JpaRepository<Resultado, Long>{
	
    @Query("SELECT r FROM Resultado r ORDER BY r.fecha DESC LIMIT 5")
    List<Resultado> Ultimos5Resultados();
    
    List<Resultado> findByJugadorNombre(String nombre);	
}
