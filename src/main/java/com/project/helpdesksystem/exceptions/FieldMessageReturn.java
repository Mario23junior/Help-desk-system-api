package com.project.helpdesksystem.exceptions;

import java.io.Serializable;

public class FieldMessageReturn implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String message;

	public FieldMessageReturn(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public FieldMessageReturn() {
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
