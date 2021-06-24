package br.com.zupacademy.mateus.proposta.repository;

import br.com.zupacademy.mateus.proposta.model.CarteiraDigital;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraDigitalRepository extends CrudRepository<CarteiraDigital,Long> {

    Optional<CarteiraDigital> findByEmissorAndCartaoId(String emissor, String id);
}
