package br.com.b3teste.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.b3teste.exception.UsuarioDuplicadoException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { UsuarioDuplicadoException.class })
	protected ResponseEntity<Object> exception(UsuarioDuplicadoException ex, WebRequest request) {
		return new ResponseEntity<>("Este email já está cadastrado para o companyId informado.", HttpStatus.NOT_FOUND);
	}
}