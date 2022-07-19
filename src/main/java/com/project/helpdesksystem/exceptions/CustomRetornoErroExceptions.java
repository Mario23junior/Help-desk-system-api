package com.project.helpdesksystem.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRetornoErroExceptions {

	@ExceptionHandler(ObjectNotFoundExceptionValues.class)
	ResponseEntity<ModelErro> objectResponseErroCustom(ObjectNotFoundExceptionValues ex, HttpServletRequest request) {
		ModelErro error = new ModelErro(
		  LocalDateTime.now(),
		  HttpStatus.NOT_FOUND.value(),
		  "Objeto não encontrado",
		  ex.getMessage(),
		  request.getRequestURI()); {
			  
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
 	}
}
