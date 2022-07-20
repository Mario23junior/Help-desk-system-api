package com.project.helpdesksystem.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.project.helpdesksystem.handleException.ModelErro;

public class ValidationError extends ModelErro {
	private static final long serialVersionUID = 1L;

	private List<FieldMessageReturn> erros = new ArrayList<>();

	public ValidationError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidationError(LocalDateTime localDateTime, Integer status, String error, String message, String path) {
		super(localDateTime, status, error, message, path);
	}

	public List<FieldMessageReturn> getErros() {
		return erros;
	}

	public void addError(String fieldName, String message) {
		this.erros.add(new FieldMessageReturn(fieldName,message));
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
