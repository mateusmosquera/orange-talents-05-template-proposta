package br.com.zupacademy.mateus.proposta.Client;

import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoAnalise;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "proposta", url = "${solicitacao.host}")
public interface PropostaClient {

    @RequestMapping(method = RequestMethod.POST)
    SolicitacaoResponse solicitarDados(SolicitacaoAnalise solicitacaoAnalise);

}
