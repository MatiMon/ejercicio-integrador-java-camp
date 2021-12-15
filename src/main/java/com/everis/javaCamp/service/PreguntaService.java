package com.everis.javaCamp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.javaCamp.entities.Categoria;
import com.everis.javaCamp.entities.Pregunta;
import com.everis.javaCamp.entities.Respuesta;
import com.everis.javaCamp.repositories.PreguntaRepository;

@Service
public class PreguntaService {

	@Autowired
	PreguntaRepository preguntaRepository;

	@Autowired
	CategoriaService categoriaService;

	public List<Pregunta> traerPreguntas() {
		return preguntaRepository.findAll();
	}

	public Pregunta buscarPreguntaPorId(Integer preguntaId) {
		Optional<Pregunta> resultado = preguntaRepository.findById(preguntaId);
		if (resultado.isPresent()) {
			return resultado.get();
		}
		return null;
	}

	public Pregunta crearPregunta(String enunciado, Integer categoriaId, List<Respuesta> opciones) {
		if (!existePreguntaPorPregunta(enunciado)) {
			Pregunta pregunta = new Pregunta();
			pregunta.setEnunciado(enunciado);
			Categoria categoria = categoriaService.buscarCategoriaPorId(categoriaId);
			pregunta.setCategoria(categoria);
			for (Respuesta respuesta : opciones) {
				respuesta.setPregunta(pregunta);
			}
			preguntaRepository.save(pregunta);
			return pregunta;
		}
		return null;
	}

	public boolean existePorId(int id) {
		Pregunta pregunta = preguntaRepository.findById(id);
		return pregunta != null;
	}

	public boolean existePreguntaPorPregunta(String enunciado) {
		Pregunta pregunta = preguntaRepository.findByEnunciado(enunciado);
		return pregunta != null;
	}

}
