package br.com.zupacademy.mateus.proposta.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.mateus.proposta.model.Usuario;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long>{
	
	
	Optional<Usuario> findByDocumento(String documento);
}
