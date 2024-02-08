package daw.dwes.ud5.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import daw.dwes.ud5.entities.Clasificacion;
import daw.dwes.ud5.entities.Resultado;
import daw.dwes.ud5.entities.Jugador;
import daw.dwes.ud5.respositories.JugadorRepository;
import daw.dwes.ud5.respositories.ResultadoRepository;
import daw.dwes.ud5.services.ClasificacionService;
import daw.dwes.ud5.services.ResultadoService;
import jakarta.servlet.http.HttpSession;

@Controller
public class QuizController {
	
    private final ResultadoRepository resultadoRepositorio;
    private final JugadorRepository jugadorRepository;
    
    private final ResultadoService resultadoService;
    private final ClasificacionService clasificacionService;
    
    @Autowired
    public QuizController(
    		ResultadoRepository resultadoRepositorio,
    		JugadorRepository jugadorRepository,
    		ResultadoService resultadoService, 
    		ClasificacionService clasificacionService) {
    	this.resultadoRepositorio = resultadoRepositorio;
    	this.jugadorRepository = jugadorRepository;
        this.resultadoService = resultadoService;
        this.clasificacionService = clasificacionService;
    }
	
	@GetMapping("/")
	public String inicio(HttpSession session, Model model) {
    	// Reiniciar los puntos en el inicio creando nuevo objeto/modelo Resultado:
        Resultado resultado = new Resultado();
        session.setAttribute("resultado", resultado);
		return "inicio"; //se muestra la pagina inicio.	
	}//inicio
	
    @GetMapping("/pregunta1")
    public String pregunta1() {
        return "pregunta1";
    }
    
    @PostMapping("/pregunta1")
    public String Pregunta1(
    		@RequestParam(name = "respuesta") String respuesta, 
    		HttpSession session,
    		Model model) {
    	
    	int puntos = 0;
    	
    	//manejar la respuesta de la pregunta 1 (radio button)
        if (respuesta.equals("gryffindor")) {
            puntos = 4;
        } else if (respuesta.equals("hufflepuff")) {
            puntos = 1;
        } else if (respuesta.equals("slytherin")) {
            puntos = 3;
        } else if (respuesta.equals("ravenclaw")) {
            puntos = 2;
        }

        // Obtener el objeto Resultado de la sesión
        Resultado resultado = resultadoService.obtenerResultado(session);

        // Actualizar los puntos acumulados en el objeto Resultado
        resultado.setPuntos(resultado.getPuntos() + puntos);

        // Agregar el objeto Resultado actualizado al modelo
        model.addAttribute("resultado", resultado);

        return "pregunta2";
    }//preg1
    
    @PostMapping("/pregunta2")
    public String pregunta2(
    		@RequestParam (value = "opciones", required = false) 
    		String[] opciones, 
    		HttpSession session,
    		Model model) {
        int puntos = 0;
        // Gestión respuesta de la pregunta 2 (checkbox)
        // asignar puntos según las opciones seleccionadas
        if (opciones != null) {
            puntos = opciones.length; 
        }

        // Actualizar la sesión con los puntos obtenidos
        Resultado resultado = resultadoService.obtenerResultado(session);
        resultado.setPuntos(resultado.getPuntos() + puntos);
        model.addAttribute("resultado", resultado);

        return "pregunta3";
    }
    

    @PostMapping("/pregunta3")
    public String pregunta3(
    		@RequestParam(name = "respuesta") String respuesta, 
    		HttpSession session,
    		Model model) {
        int puntos = 0;
        // Gestión respuesta de la pregunta 3 (select)
        // asignar puntos según la opción seleccionada
        if (respuesta.equals("minerva")) {
            puntos = 4;
        } else if (respuesta.equals("snape")) {
            puntos = 3;
        } else if (respuesta.equals("flitwick")) {
            puntos = 2;
        } else if (respuesta.equals("sprout")) {
            puntos = 1;
        } 

        // Actualizar la sesión con los puntos obtenidos
        Resultado resultado = resultadoService.obtenerResultado(session);
        resultado.setPuntos(resultado.getPuntos() + puntos);
        model.addAttribute("resultado", resultado);

        return "pregunta4";
    }//preg3
    

    @PostMapping("/pregunta4")
    public String pregunta4(
            @RequestParam(name = "respuesta") String respuesta,
            HttpSession session,
            Model model) {
        int puntos = 0;

        // Gestión de la respuesta de la pregunta 4 (botones)
        switch (respuesta) {
            case "gloria":
                puntos = 4;
                break;
            case "poder":
                puntos = 3;
                break;
            case "sabiduria":
                puntos = 2;
                break;
            case "amor":
                puntos = 1;
                break;

            default:
                // para valores no esperados
                break;
        }

        // Actualizar la sesión con los puntos obtenidos
        Resultado resultado = resultadoService.obtenerResultado(session);
        resultado.setPuntos(resultado.getPuntos() + puntos);
        model.addAttribute("resultado", resultado);

        return "pregunta5";
    }//pregunta4
    
    @PostMapping("/pregunta5")
    public String pregunta5(
            @RequestParam(name = "respuesta") String respuesta,
            HttpSession session,
            Model model) {
        int puntos = 0;

        // pasar a minúsculas y eliminar espacios en blanco
        String respuestaReal = respuesta.toLowerCase().trim();

        // Comprobar la respuesta con las opciones válidas:
        if (respuestaReal.equals("espada")) {
            puntos = 4;
        } else if (respuestaReal.equals("varita")) {
            puntos = 3;
        } else if (respuestaReal.equals("libro")) {
            puntos = 2;
        } else if (respuestaReal.equals("escoba")) {
            puntos = 1;
        }

        // Actualizar la sesión con los puntos obtenidos
        Resultado resultado = resultadoService.obtenerResultado(session);
        resultado.setPuntos(resultado.getPuntos() + puntos);
        model.addAttribute("resultado", resultado);

        return "pregunta6";
    }//preg5
    
    @PostMapping("/pregunta6")
    public String Pregunta6(
    		@RequestParam(name = "respuesta") String respuesta, 
    		HttpSession session,
    		Model model) {
    	
    	int puntos = 0;
    	
    	//manejar la respuesta de la pregunta 6 (radio button)
        if (respuesta.equals("gryffindor")) {
            puntos = 4;
        } else if (respuesta.equals("hufflepuff")) {
            puntos = 1;
        } else if (respuesta.equals("slytherin")) {
            puntos = 3;
        } else if (respuesta.equals("ravenclaw")) {
            puntos = 2;
        }

        // Obtener el objeto Resultado de la sesión
        // y actualizo los puntos
        Resultado resultado = resultadoService.obtenerResultado(session);
        resultado.setPuntos(resultado.getPuntos() + puntos);
        model.addAttribute("resultado", resultado);

        return "pregunta7";
    }//preg6
    
    @PostMapping("/pregunta7")
    public String pregunta7(
            @RequestParam(name = "respuesta") String respuesta,
            HttpSession session,
            Model model) {
        int puntos = 0;

        // Gestión de la respuesta de la pregunta 7 (botones)
        switch (respuesta) {
            case "gryff":
                puntos = 4;
                break;
            case "slyth":
                puntos = 3;
                break;
            case "raven":
                puntos = 2;
                break;
            case "huff":
                puntos = 1;
                break;

            default:
                // para valores no esperados
                break;
        }//switch

        // Actualizar la sesión con los puntos obtenidos
        Resultado resultado = resultadoService.obtenerResultado(session);
        resultado.setPuntos(resultado.getPuntos() + puntos);
        model.addAttribute("resultado", resultado);
        
        // y actualizamos la clasificación, accediendo a los puntos:
        resultado.setClasificacion(clasificacionService.calcularClasificacion
        		(resultado.getPuntos()));

        return "paginaNombre";
    }//pregunta7
    
    @PostMapping("/paginaNombre")
    public String paginaNombre(
            @RequestParam(name = "nombre") String nombre,
            HttpSession session,
            Model model) {
    	
        // Obtener el objeto Resultado de la sesión
        Resultado resultado = resultadoService.obtenerResultado(session);
        
        // Verificar si el jugador ya existe en la base de datos
        Optional<Jugador> jugadorOptional = jugadorRepository.findByNombre(nombre);

        if (jugadorOptional.isPresent()) {
            // Si el jugador ya existe, asociar la puntuación al jugador existente:
            Jugador jugadorExistente = jugadorOptional.get();
            resultado.setJugador(jugadorExistente);
        } else {
            // Si el jugador no existe, crear un nuevo jugador y asociar la puntuación
            Jugador nuevoJugador = new Jugador();
            nuevoJugador.setNombre(nombre);
            nuevoJugador.getPuntuaciones().add(resultado);
            jugadorRepository.save(nuevoJugador);
            resultado.setJugador(nuevoJugador);
        }//if-Else
        
        // Guardar el resultado en el repositorio con .save:
        resultadoRepositorio.save(resultado);

        // Seleccionar los últimos 5 resultados (o menos si hay menos de 5)
        List<Resultado> ultimosResultados = resultadoRepositorio.Ultimos5Resultados();

        // Agregar la lista de últimos resultados al modelo
        model.addAttribute("ultimosResultados", ultimosResultados);
        model.addAttribute(resultado);        
        return "finalResultado";
    }//paginaNombre
  
}//quizzMain

