package daw.dwes.ud5.services;

import org.springframework.stereotype.Service;

import daw.dwes.ud5.entities.Resultado;
import jakarta.servlet.http.HttpSession;

@Service
public class ResultadoService {
	
    public Resultado obtenerResultado(HttpSession session) {
    	Resultado resultado = (Resultado) session.getAttribute("resultado");
        // Si no existe en la sesión, crear uno nuevo 
    	//y se guarda en la sesión:
    	if (resultado == null) {
            resultado = new Resultado();
            session.setAttribute("resultado", resultado);
        }//if
        return resultado;
    }//obtenerResultado

}//Service
