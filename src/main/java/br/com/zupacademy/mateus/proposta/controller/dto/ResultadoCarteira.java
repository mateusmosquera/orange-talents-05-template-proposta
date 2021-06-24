package br.com.zupacademy.mateus.proposta.controller.dto;

public class ResultadoCarteira {

    private Resultado resultado;

    private String id;

    public ResultadoCarteira(Resultado resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
