package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
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
@RequestMapping("/postagens")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	@GetMapping("/all")
	public ResponseEntity<List<Postagem>> GetAll() {
		List<Postagem> list = repository.findAll();

		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}

	@GetMapping("/{id_postagem}")
	public ResponseEntity<Postagem> getById(@PathVariable(value = "id_postagem") Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp)).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Postagem não encontrado!");
		});
	}

	@GetMapping("/titulo/{titulo_postagem}")
	public ResponseEntity<List<Postagem>> getByTitle(@PathVariable(value = "titulo_postagem") String titulo) {
		List<Postagem> list = repository.findAllByTituloContainingIgnoreCase(titulo);
		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}

	@GetMapping("/texto/{texto_postagem}")
	public ResponseEntity<List<Postagem>> getByText(@PathVariable(value = "texto_postagem") String texto) {
		List<Postagem> list = repository.findAllByTextoContainingIgnoreCase(texto);
		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}

	@PostMapping
	public ResponseEntity<Postagem> savePostagem(@Valid @RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}

	@PutMapping
	public ResponseEntity<Postagem> updatePostagem(@Valid @RequestBody Postagem postagem) {
		return repository.findById(postagem.getId())
				.map(resp -> ResponseEntity.status(200).body(repository.save(postagem))).orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado!");
				});
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/delete/{id_postagem}")
	public ResponseEntity deletePostagem(@PathVariable(value = "id_postagem") Long id) {
		Optional<Postagem> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).build();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado!");
		}
	}

}
