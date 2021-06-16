package br.com.zupacademy.mateus.proposta.config;

import br.com.zupacademy.mateus.proposta.Client.CartoesClient;
import br.com.zupacademy.mateus.proposta.controller.PropostaController;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoAnalise;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoResponse;
import br.com.zupacademy.mateus.proposta.model.Cartao;
import br.com.zupacademy.mateus.proposta.model.Proposta;
import br.com.zupacademy.mateus.proposta.model.SituacaoFinanceira;
import br.com.zupacademy.mateus.proposta.repository.CartaoRepository;
import br.com.zupacademy.mateus.proposta.repository.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private CartoesClient cartoesClient;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @Scheduled(fixedRate = 60000)
    public void procurarPropostasPendentes() {
        logger.info("Inicio da Schedule de propostas pendentes sem cartao");
        List<Proposta> propostasPendentes = propostaRepository.findAllBySituacaoFinanceiraAndIdCartao(SituacaoFinanceira.ELEGIVEL,null);
        if(propostasPendentes.isEmpty()){
            logger.info("Não foi encontrado nenhuma proposta elegível pendente de cartão");
        } else{
            for(Proposta proposta : propostasPendentes){
                SolicitacaoAnalise solic =  new SolicitacaoAnalise(proposta.getDocumento(),proposta.getNome(),proposta.getId());
                Cartao cartao = cartoesClient.cadastrarCartao(solic);
                proposta.setIdCartao(cartao.getId());
                logger.info("Proposta teve seu cartão cadastrado! documento={}", proposta.getDocumento());

                cartaoRepository.save(cartao);
                propostaRepository.save(proposta);


            }
            logger.info("Processo de cadastramento encerrado, Proposta(s) cadastrada(s): "+propostasPendentes.size());
        }
    }

}
