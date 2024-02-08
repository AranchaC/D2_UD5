package daw.dwes.ud5.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "resultado")
public class Resultado {
	
	@Enumerated(EnumType.STRING)
    private Clasificacion clasificacion;
    private int puntos;  
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "resultado_id")
	private long id;
	
    // Relación muchos a uno con Jugador.
	// 
    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    public long getId() {
		return id;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
