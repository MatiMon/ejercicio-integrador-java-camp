package com.everis.javaCamp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.javaCamp.entities.Categoria;
import com.everis.javaCamp.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public List<Categoria> traerCategorias() {
		return categoriaRepository.findAll();
	}

	public Categoria buscarCategoriaPorId(Integer categoriaId) {
		Optional<Categoria> resultado = categoriaRepository.findById(categoriaId);
		Categoria categoria = null;
		if (resultado.isPresent()) {
			categoria = resultado.get();
		}
		return categoria;
	}

	/*
	 * public Categoria buscarCategoriaV2(Integer categoriaId) {
	 * 
	 * Categoria categoria = repo.findById(categoriaId.intValue());
	 * 
	 * return categoria; }
	 */

	public boolean crearCategoria(Categoria categoria) {
		if (existe(categoria.getNombre())) {
			return false;
		}
		categoriaRepository.save(categoria);
		return true;
	}

	public boolean existePorId(int id) {
		Categoria categoria = categoriaRepository.findById(id);
		return categoria != null;
	}

	public boolean existe(String nombre) {
		Categoria categoria = categoriaRepository.findByNombre(nombre);
		return categoria != null;
	}

	/*
	 * public boolean existeV2(String nombre) { return repo.existsByNombre(nombre);
	 * }
	 */

	public boolean eliminarCategoriaPorId(Integer id) {
		boolean res = false;
		if (existePorId(id)) {
			categoriaRepository.deleteById(id);
			res = (!existePorId(id));
		}
		return res;
	}

}
