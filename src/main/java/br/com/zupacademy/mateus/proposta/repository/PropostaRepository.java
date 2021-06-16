package br.com.zupacademy.mateus.proposta.repository;

import java.util.List;
import java.util.Optional;

import br.com.zupacademy.mateus.proposta.model.SituacaoFinanceira;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.mateus.proposta.model.Proposta;


@Repository
public interface PropostaRepository extends CrudRepository<Proposta,Long>{
	
	
	Optional<Proposta> findByDocumento(String documento);

	List<Proposta> findAllBySituacaoFinanceiraAndIdCartao(SituacaoFinanceira situacaoFinanceira, String id);
}
