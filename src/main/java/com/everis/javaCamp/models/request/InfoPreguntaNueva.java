package com.everis.javaCamp.models.request;

import java.util.List;

import com.everis.javaCamp.entities.Respuesta;

public class InfoPreguntaNueva {
	public String enunciado;
	public List<Respuesta> opciones;
	public Integer categoriaId;
}
