package com.project.helpdesksystem.handleException;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.project.helpdesksystem.exceptions.ValidationError;

import org.springframework.dao.DataIntegrityViolationException;

@RestControllerAdvice
public class CustomRetornoErroExceptions {

	@ExceptionHandler(ObjectNotFoundExceptionValues.class)
	ResponseEntity<ModelErro> objectResponseErroCustom(ObjectNotFoundExceptionValues ex, HttpServletRequest request) {
		ModelErro error = new ModelErro(
		  LocalDateTime.now(),
		  HttpStatus.NOT_FOUND.value(),
		  "Objeto não encontrado",
		  ex.getMessage(),
		  request.getRequestURI());
			  
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
 	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	ResponseEntity<ModelErro> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
		ModelErro error = new ModelErro(
		  LocalDateTime.now(),
		  HttpStatus.NOT_FOUND.value(),
		  "Já existe um registro com este cpf",
		  ex.getMessage(),
		  request.getRequestURI());
			  
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);	
 	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ValidationError> valdationErros(MethodArgumentNotValidException ex, WebRequest request) {
		ValidationError erroField = new ValidationError(
			    LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Erro na validacao dos campos",
				"Erro encontrado",
				request.getDescription(true));
		
		for(FieldError x : ex.getBindingResult().getFieldErrors()) {
			erroField.addError(x.getField(), x.getDefaultMessage());
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroField);	
 	}
}
