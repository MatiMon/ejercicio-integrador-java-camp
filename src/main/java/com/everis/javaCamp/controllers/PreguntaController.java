package com.everis.javaCamp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.everis.javaCamp.entities.*;
import com.everis.javaCamp.models.request.InfoPreguntaNueva;
import com.everis.javaCamp.models.response.GenericResponse;
import com.everis.javaCamp.service.PreguntaService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/preguntas")
public class PreguntaController {

	@Autowired
	PreguntaService service;

	@GetMapping
	public ResponseEntity<List<Pregunta>> traerPreguntas() {
		return ResponseEntity.ok(service.traerPreguntas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPreguntaPorId(@PathVariable Integer id) {
		GenericResponse respuesta = new GenericResponse();
		if (service.existePorId(id)) {
			return ResponseEntity.ok(service.buscarPreguntaPorId(id));
		} else {
			respuesta.isOk = false;
			respuesta.message = "La pregunta no existe";
			return ResponseEntity.badRequest().body(respuesta);
		}
	}

	@PostMapping
	public ResponseEntity<?> crearPregunta(@RequestBody InfoPreguntaNueva preguntaNueva) {
		GenericResponse respuesta = new GenericResponse();
		Pregunta pregunta = new Pregunta();
		if (service.crearPregunta(preguntaNueva.enunciado, preguntaNueva.categoriaId, preguntaNueva.opciones) != null) {
			respuesta.id = pregunta.getPreguntaId();
			respuesta.isOk = true;
			respuesta.message = "La pregunta fue creada con exito";
			return ResponseEntity.ok(respuesta);
		} else {
			respuesta.isOk = false;
			respuesta.message = "La pregunta ya existe";
			return ResponseEntity.badRequest().body(respuesta);
		}
	}
}
