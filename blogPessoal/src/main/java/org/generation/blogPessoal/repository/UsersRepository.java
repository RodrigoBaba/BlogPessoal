package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Usuario, Long>{

	public List<Usuario> findAllByNameContainingIgnoreCase();
	
	public List<Usuario> findAllByUserContainingIgnoreCase();
	
	public Usuario findByName(String name);
	
	public Usuario findByUser(String user);
}
