package br.com.zupacademy.mateus.proposta.repository;

import br.com.zupacademy.mateus.proposta.model.AvisoViagem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoViagemRepository extends CrudRepository<AvisoViagem,Long> {
}
