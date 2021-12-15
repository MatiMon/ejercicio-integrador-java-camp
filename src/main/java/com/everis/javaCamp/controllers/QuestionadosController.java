package com.everis.javaCamp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.everis.javaCamp.entities.Pregunta;
import com.everis.javaCamp.models.request.RespuestaAVerificar;
import com.everis.javaCamp.models.response.RespuestaVerificada;
import com.everis.javaCamp.service.QuestionadosService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/questionados")
public class QuestionadosController {

	@Autowired
	QuestionadosService questionadosService;

	@GetMapping("/next")
	public ResponseEntity<Pregunta> traerPreguntaRandom() {
		Pregunta proximaPregunta = questionadosService.traerPreguntaRandom();
		return ResponseEntity.ok(proximaPregunta);
	}

	@PostMapping("/verificaciones")
	public ResponseEntity<RespuestaVerificada> verificarRespuesta(
			@RequestBody RespuestaAVerificar respuestaAVerificar) {
		RespuestaVerificada respuestaVerificada = new RespuestaVerificada();
		if (questionadosService.verificarRespuesta(respuestaAVerificar.preguntaId, respuestaAVerificar.respuestaId)) {
			respuestaVerificada.esCorrecta = true;
		} else {
			respuestaVerificada.esCorrecta = false;
		}
		return ResponseEntity.ok(respuestaVerificada);
	}

}
