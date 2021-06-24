package br.com.zupacademy.mateus.proposta.controller.dto;

public class SolicitacaoInclusaoCarteira {

    private String email;

    private String carteira;

    public SolicitacaoInclusaoCarteira(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
