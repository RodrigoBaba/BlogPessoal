package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tema")
public class TemaController {

	@Autowired
	private TemaRepository repository;

	// BUSCAS
	// BUSCA TODOS
	@GetMapping
	public ResponseEntity<List<Tema>> getAll() {
		List<Tema> list = repository.findAll();

		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}

	// BUSCA ID
	@GetMapping("/{id_tema}")
	public ResponseEntity<Tema> getById(@PathVariable(value = "id_tema") Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp)).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Id do tema não encontrado");
		});
	}

	// BUSCA por NOME
	@GetMapping("/nome/{nome_tema}")
	public ResponseEntity<List<Tema>> getByName(@PathVariable(value = "nome_tema")  String nome) {
		List<Tema> list = repository.findAllByDescricaoContainingIgnoreCase(nome);
		if(list.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Lista vazia!");
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}

	// SAVE
	@PostMapping
	public ResponseEntity<Tema> saveTema(@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}

	// UPDATE -> Alteracao
	@PutMapping
	public ResponseEntity<Tema> updateTema(@RequestBody Tema tema) {
		return repository.findById(tema.getId())
				.map(resp -> ResponseEntity.status(200).body(repository.save(tema))).orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado!");
				});
	}

	// DELETE
	@DeleteMapping("/delete/{id_tema}")
	public ResponseEntity<?> deleteTema(@PathVariable(value = "id_tema") Long id) {
		Optional<Tema> optional = repository.findById(id);
		if(optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).build();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado!");
		}
	}

}