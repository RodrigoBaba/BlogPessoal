package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsersController {

	@Autowired
	private UsersRepository repository;

	// BUSCAS
	// Um nome só
	@GetMapping("/nomecompleto/{name}")
	public ResponseEntity<Usuario> getByNameOnlyOne(@PathVariable String name) {
		return ResponseEntity.ok(repository.findByName(name));
	}

	// Todos os nomes
	/*@GetMapping("/nomes")
	public ResponseEntity<List<Usuario>> getByAllName() {
		return ResponseEntity.ok(repository.findAllByNameContainingIgnoreCase());
	}*/

	// Um usuario só
	@GetMapping("/usuariocompleto/{user}")
	public ResponseEntity<Usuario> getByUserOnlyOne(@PathVariable String user) {
		return ResponseEntity.ok(repository.findByUser(user));
	}

	// Todos usuarios
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAllUser() {
		return ResponseEntity.ok(repository.findAllByUserContainingIgnoreCase());
	}

	// SALVAR
	@PostMapping("/save")
	public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario user) {
		return ResponseEntity.status(201).body(repository.save(user));
	}

	// ALTERAR
	@PutMapping
	public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario user) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(user));
	}

	// DELETAR
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
