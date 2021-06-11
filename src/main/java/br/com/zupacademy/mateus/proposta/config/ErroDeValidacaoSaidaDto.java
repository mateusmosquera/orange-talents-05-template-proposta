package br.com.zupacademy.mateus.proposta.config;

import java.util.ArrayList;
import java.util.List;

public class ErroDeValidacaoSaidaDto {
	private List<String> globalErrorMessages = new ArrayList<>();
    private List<ErroDeFormularioDto> fieldErrors = new ArrayList<>();

    public void addError(String mensagem) {
        globalErrorMessages.add(mensagem);
    }

    public void addFieldError(String campo, String mensagem) {
    	ErroDeFormularioDto fieldError = new ErroDeFormularioDto(campo, mensagem);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<ErroDeFormularioDto> getErrors() {
        return fieldErrors;
    }

    public int getNumberOfErrors() {
        return this.globalErrorMessages.size() + this.fieldErrors.size();
    }
}