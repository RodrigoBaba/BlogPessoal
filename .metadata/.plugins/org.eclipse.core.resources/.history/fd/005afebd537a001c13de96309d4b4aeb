package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

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
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	// BUSCA todos as postagens, retornando uma lista completa
	@GetMapping("/all")
	public ResponseEntity<List<Postagem>> GetAll() {
		List<Postagem> list = repository.findAll();

		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}


	// BUSCA pelo 'id' digitado
	@GetMapping("/{id_postagem}")
	public ResponseEntity<Postagem> getById(@PathVariable(value = "id_postagem") Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp)).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Id da postagem não encontrado!");
		});
	}

	// BUSCA por palavras no titulo
	// retornando uma lista com todos os que tiverem o 'titulo' digitado
	@GetMapping("/titulo/{titulo_postagem}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable(value = "titulo_postagem") String titulo) {
		List<Postagem> list = repository.findAllByTituloContainingIgnoreCase(titulo);
		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}	

	// BUSCA por palavras no texto
	// retornando uma lista com todos os que tiverem o 'texto' digitado
	@GetMapping("/texto/{texto_postagem}")
	public ResponseEntity<List<Postagem>> getByTexto(@PathVariable(value = "texto_postagem") String texto) {
		List<Postagem> list = repository.findAllByTextoContainingIgnoreCase(texto);
		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}

	// SALVA um dado no banco de dados
	// BOAZ
	@PostMapping("/saveboaz")
	public ResponseEntity<Postagem> savePostagem(@RequestBody Postagem postagem) {
		return ResponseEntity.status(201).body(repository.save(postagem));
	}

	// SALVA um dado no banco de dados
	// MARCELO
	@PostMapping("/savemarcelo")
	public ResponseEntity<Postagem> salvarPostagem(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}

	// ALTERA o que esta no banco de dados, precisa colocar o ID junto	
	@PutMapping("/update")
	public ResponseEntity<Postagem> updatePostagem(@RequestBody Postagem postagem) {
		return repository.findById(postagem.getId())
				.map(resp -> ResponseEntity.status(200).body(repository.save(postagem))).orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado!");
				});
	}
	
	// DELETAR um dado especifico
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
