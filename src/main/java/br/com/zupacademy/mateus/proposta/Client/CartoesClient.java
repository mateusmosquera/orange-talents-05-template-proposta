package br.com.zupacademy.mateus.proposta.Client;

import br.com.zupacademy.mateus.proposta.controller.dto.ResultadoBloqueio;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoAnalise;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoBloqueio;
import br.com.zupacademy.mateus.proposta.model.Cartao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "cartoes", url="${cartao.host}")
public interface CartoesClient {

    @RequestMapping(method = RequestMethod.GET, value = "/cartoes")
    List<Cartao> cartoes();

    @RequestMapping(method = RequestMethod.POST)
    Cartao cadastrarCartao(SolicitacaoAnalise solicitacaoAnalise);

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/bloqueios")
    ResultadoBloqueio bloquear(@PathVariable("id") String id, SolicitacaoBloqueio solicitacaoBloqueio);

}