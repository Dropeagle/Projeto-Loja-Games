package com.generation.LojaGames.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.LojaGames.Model.Categoria;
import com.generation.LojaGames.repository.CategoriaRepositorio;

/**
 * 
 * @author George
 * @since 1.0
 */

@RestController
@RequestMapping("/api/v1/categoria")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepositorio repositorio;

	@GetMapping("/todos")
	public ResponseEntity<List<Categoria>> pegarTodos() {
		List<Categoria> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/{id_categoria}")
	public ResponseEntity<Categoria> pegarPorId(@PathVariable(value = "id_categoria") Long idCategoria) {
		Optional<Categoria> objetoOptional = repositorio.findById(idCategoria);

		if (objetoOptional.isPresent()) {
			return ResponseEntity.status(200).body(objetoOptional.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria novoCategoria) {
		return ResponseEntity.status(201).body(repositorio.save(novoCategoria));

	}

	@PutMapping("/atualizar")
	public ResponseEntity<Categoria> atualizar(@Valid @RequestBody Categoria novoCategoria) {
		return ResponseEntity.status(201).body(repositorio.save(novoCategoria));

	}

	@DeleteMapping("/deletar/{id_categoria}")
	public ResponseEntity<Categoria> deletar(@PathVariable(value = "id_categoria") Long idCategoria) {
		Optional<Categoria> objetoOptional = repositorio.findById(idCategoria);

		if (objetoOptional.isPresent()) {
			repositorio.deleteById(idCategoria);
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}

}
