package br.com.zupacademy.mateus.proposta.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.mateus.proposta.model.Proposta;


@Repository
public interface PropostaRepository extends CrudRepository<Proposta,Long>{

}
