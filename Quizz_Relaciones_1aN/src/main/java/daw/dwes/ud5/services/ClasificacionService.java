package daw.dwes.ud5.services;

import org.springframework.stereotype.Service;

import daw.dwes.ud5.entities.Clasificacion;

@Service
public class ClasificacionService {
	
    public Clasificacion calcularClasificacion(int puntos) {
        // determinar la clasificación según los puntos
        if (puntos >= 20) {
            return Clasificacion.GRYFFINDOR;
        } else if (puntos >= 15) {
            return Clasificacion.RAVENCLAW;
        } else if (puntos >= 10) {
            return Clasificacion.SLYTHERIN;
        } else {
            return Clasificacion.HUFFLEPUFF;
        }
    }//calcularClasif

}
