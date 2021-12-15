package com.everis.javaCamp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.everis.javaCamp.entities.Categoria;
import com.everis.javaCamp.models.response.GenericResponse;
import com.everis.javaCamp.service.CategoriaService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> traerCategorias() {
		return ResponseEntity.ok(categoriaService.traerCategorias());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> traerCategoriaPorId(@PathVariable Integer id) {
		GenericResponse respuesta = new GenericResponse();
		if (categoriaService.existePorId(id)) {
			return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
		} else {
			respuesta.isOk = false;
			respuesta.message = "La categoria no existe";
			return ResponseEntity.badRequest().body(respuesta);
		}
	}

	@PostMapping
	public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
		GenericResponse respuesta = new GenericResponse();
		if (categoriaService.crearCategoria(categoria)) {
			respuesta.id = categoria.getCategoriaId();
			respuesta.isOk = true;
			respuesta.message = "Categoria creada con exito";
			return ResponseEntity.ok(respuesta);
		} else {
			respuesta.isOk = false;
			respuesta.message = "Esta categoria ya esta creada";
			return ResponseEntity.badRequest().body(respuesta);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarCategoriaPorId(@PathVariable Integer id) {
		GenericResponse respuesta = new GenericResponse();
		if (categoriaService.eliminarCategoriaPorId(id)) {
			respuesta.isOk = true;
			respuesta.message = "La categoria fue eliminada";
			return ResponseEntity.ok(respuesta);
		} else {
			respuesta.isOk = false;
			respuesta.message = "Ocurrio un error al intentar ejecutar la solicitud";
			return ResponseEntity.badRequest().body(respuesta);
		}
	}
}
