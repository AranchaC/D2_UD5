package daw.dwes.ud5.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import daw.dwes.ud5.entities.Resultado;
import daw.dwes.ud5.respositories.ResultadoRepository;

@RestController
public class ResultadoController {
	
    private final ResultadoRepository resultadoRepositorio;

    @Autowired
    public ResultadoController(ResultadoRepository resultadoRepositorio) {
        this.resultadoRepositorio = resultadoRepositorio;
    }
    
// MÉTODOS CONSULTAS SQL //
    
    @GetMapping("/resultados")
    public List<Resultado> obtenerTodosLosResultados(){
    	return resultadoRepositorio.findAll();
    }//ObtenerTodos
    
    @GetMapping("/resultados/{id}")
    public Optional<Resultado> buscarResultadoId(@PathVariable ("id") Long id){
    	return resultadoRepositorio.findById(id);
    }//BuscarId
    
    
    @PostMapping("/resultados")
    public Resultado agregarResultado(@RequestBody Resultado nuevoResultado) {
        return resultadoRepositorio.save(nuevoResultado);
    }//agregar
    
    @DeleteMapping("/resultados/{id}")
    public ResponseEntity<Object> eliminarResultado(@PathVariable ("id") Long id) {
        Optional<Resultado> resultadoOptional = resultadoRepositorio.findById(id);

        if (!resultadoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        resultadoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }//eleminar
    
    @PutMapping("/resultados/{id}")
    public ResponseEntity<Object> actualizarResultado(
    		@PathVariable (name="id") Long id, 
    		@RequestBody Resultado resultadoActualizado) {
        Optional<Resultado> resultadoOptional = resultadoRepositorio.findById(id);

        if (!resultadoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        resultadoActualizado.setId(id); 
        resultadoRepositorio.save(resultadoActualizado);
        return ResponseEntity.noContent().build();
    }//actualizar
    
    @GetMapping("/resultados/count")
    public ResponseEntity<?> contarResultados() {
        long totalResultados = resultadoRepositorio.count();
        return ResponseEntity.ok("Número de resultados en la BBDD: " 
        		+ totalResultados);
    }//contar


}
