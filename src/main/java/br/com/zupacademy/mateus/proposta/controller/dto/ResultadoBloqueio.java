package br.com.zupacademy.mateus.proposta.controller.dto;

public class ResultadoBloqueio {

    private String resultado;

    @Deprecated
    public ResultadoBloqueio(){

    }

    public ResultadoBloqueio(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
