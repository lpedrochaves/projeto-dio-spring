package com.clinica.medica.Exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
		var erros = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
	}

	// O RECORD FUNCIONA COMO UM DTO (ESTE CAMPO É COMO SE FOSSE UMA CLASSE COM UM
	// CONSTRUTOR)
	private record DadosErroValidacao(String campo, String mensagem) {
		public DadosErroValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}

	@ExceptionHandler(EspecialidadeException.class)
	public ResponseEntity<Object> handleUserNameException(EspecialidadeException ex) {
		EspecialidadeException especialidadeException = new EspecialidadeException(ex.getMessage());
		return ResponseEntity.unprocessableEntity().body(especialidadeException);
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleHttpClientErrorException(MethodArgumentTypeMismatchException ex) {
		return ResponseEntity.unprocessableEntity().body("Invalid Type");
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> DataIntegrityViolationException(DataIntegrityViolationException exc) {
		return ResponseEntity.unprocessableEntity()
				.body("Impossível realizar essa opção, violação de chave estrangeira ou violação no banco de dados !");
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> erros = new ArrayList<>();
		for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
			erros.add(erro.getField() + ":" + erro.getDefaultMessage());
		}

		ErroResposta erroResposta = new ErroResposta(status.value(),
				"Existem campos inválidos. Confira o preenchimento", LocalDateTime.now(), erros);

		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}

	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErroResposta erroResposta = new ErroResposta(status.value(),
				"Existem campos inválidos. Confira o preenchimento", LocalDateTime.now(), null);

		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}
}
