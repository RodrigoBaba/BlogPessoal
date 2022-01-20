package org.generation.blogPessoal.controller;

import java.util.List;

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

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	// BUSCA todos as postagens, retornando uma lista completa
	@GetMapping("/all")
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	// BUSCA pelo 'id' digitado
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	// BUSCA por palavras no titulo
	// retornando uma lista com todos os que tiverem o 'titulo' digitado
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	// BUSCA por palavras no titulo
		// retornando uma lista com todos os que tiverem o 'titulo' digitado
		@GetMapping("/titulocompleto/{titulo}")
		public ResponseEntity<Postagem> getByTituloOnlyOne(@PathVariable String titulo) {
			return ResponseEntity.ok(repository.findByTitulo(titulo));
		}
	
	//BUSCA por palavras no texto
	// retornando uma lista com todos os que tiverem o 'texto' digitado
	@GetMapping("/texto/{texto}")
	public ResponseEntity<List<Postagem>> getByTexto(@PathVariable String texto){
		return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));
	}
	

	// SALVA um dado no banco de dados
	// BOAZ
	@PostMapping("/save")
	public ResponseEntity<Postagem> savePostagem(@RequestBody Postagem postagem) {
		return ResponseEntity.status(201).body(repository.save(postagem));
	}

	// SALVA um dado no banco de dados
	// MARCELO
	@PostMapping
	public ResponseEntity<Postagem> post(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}

	// ALTERA o que esta no banco de dados, precisa colocar o ID junto
	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	// DELETAR um dado especifico
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
