package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class UsuarioRespositoryTests {

	@Autowired UsuarioRepository repository;
	
	@BeforeAll
	void start() {
		repository.save(new Usuario("Gabriel", "Krafa'Khan", "123456"));
		repository.save(new Usuario("Rodrigo", "Imagine", "123456"));
		repository.save(new Usuario("Rafael", "Ola Mundo", "123456"));
		repository.save(new Usuario("Lucas", "Trapper", "123456"));
	}
	
	@Test
	@DisplayName("Primeiro Teste")
	void buscaPorUsuario() {
		Usuario usuario = repository.findByUsuario("Krafa'Khan").get();	
		
		assertTrue(usuario.getUsuario().equals("Krafa'Khan"));
	}
	
	@Test
	@DisplayName("Segundo Teste")
	void buscaPorUsuarioOptional() {
		Optional<Usuario> optional = repository.findByUsuario("Imagine");
		
		assertTrue(optional.isPresent());
	}
	
	@Test
	@DisplayName("Terceiro Teste")
	void buscarPorTodosNomes() {
		List<Usuario> list = repository.findAllByNomeContainingIgnoreCase("R");
		
		assertFalse(list.isEmpty());
	}

}
