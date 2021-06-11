package br.com.zupacademy.mateus.proposta.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zupacademy.mateus.proposta.config.exceptions.ApiErroException;


@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroDeValidacaoSaidaDto handle(MethodArgumentNotValidException exception) {

    	  List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
          List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

          return buildValidationErrors(globalErrors,
  				fieldErrors);
    }
    
    private ErroDeValidacaoSaidaDto buildValidationErrors(List<ObjectError> globalErrors,
			List<FieldError> fieldErrors) {
    	ErroDeValidacaoSaidaDto validationErrors = new ErroDeValidacaoSaidaDto();
    	
    	globalErrors.forEach(error -> validationErrors.addError(getErrorMessage(error)));
    	
    	fieldErrors.forEach(error -> {
    		String errorMessage = getErrorMessage(error);
    		validationErrors.addFieldError(error.getField(), errorMessage);
    	});
		return validationErrors;
	}
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErroDeValidacaoSaidaDto handleValidationError(BindException exception) {
    	
    	List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
    	List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
    	
    	return buildValidationErrors(globalErrors,
				fieldErrors);
    }
    
    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
    
    @ExceptionHandler(ApiErroException.class)
    public ResponseEntity<ErroPadronizado> handleApiErroException(ApiErroException apiErroException) {
        Collection<String> mensagens = new ArrayList<>();
        mensagens.add(apiErroException.getReason());

        ErroPadronizado erroPadronizado = new ErroPadronizado(mensagens);
        return ResponseEntity.status(apiErroException.getHttpStatus()).body(erroPadronizado);
    }
    
}