package com.everis.javaCamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.javaCamp.entities.Pregunta;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {

	Pregunta findById(int id);

	Pregunta findByEnunciado(String enunciado);
}
