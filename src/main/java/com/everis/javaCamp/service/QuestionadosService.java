package com.everis.javaCamp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.javaCamp.entities.*;

@Service
public class QuestionadosService {

    @Autowired
    PreguntaService preguntaService;

    public Pregunta traerPreguntaRandom() {

        List<Pregunta> todasLasPreguntas = preguntaService.traerPreguntas();
        int min = 1;
        int max = todasLasPreguntas.size();
        int random = (int) (Math.random() * ((max - min) +1)) + min;

        return todasLasPreguntas.get(random - 1);   
    }

    public boolean verificarRespuesta(Integer preguntaId, Integer respuestaId) {

        Pregunta pregunta = preguntaService.buscarPreguntaPorId(preguntaId);

        for(Respuesta respuesta : pregunta.getOpciones()) {

            if(respuesta.getRespuestaId().equals(respuestaId)) {
                return respuesta.isEsCorrecta();
            }
        }

        return false;
    }
    
}