package br.com.zupacademy.mateus.proposta.repository;

import br.com.zupacademy.mateus.proposta.model.Cartao;
import br.com.zupacademy.mateus.proposta.model.Proposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends CrudRepository<Cartao,String> {

}
