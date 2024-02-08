package daw.dwes.ud5.respositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import daw.dwes.ud5.entities.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long>{
	
	 Optional<Jugador> findByNombre(String nombre);
	 

}
