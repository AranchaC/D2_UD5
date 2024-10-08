package daw.dwes.ud5.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugador")
public class Jugador {
	
    private String nombre;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jugador_id")
	private long id;
	
    // Relación uno a muchos con Resultados:
	// Un jugador puede tener muchas puntuaciones.
	// mappedBy para que sea bidireccional
    @OneToMany(
    		mappedBy = "jugador", 
    		cascade = CascadeType.ALL,
    		orphanRemoval = true)
    private List<Resultado> resultados = new ArrayList<>();
    
	public List<Resultado> getResultados() {
		// TODO Auto-generated method stub
		return resultados;
	}

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



}
