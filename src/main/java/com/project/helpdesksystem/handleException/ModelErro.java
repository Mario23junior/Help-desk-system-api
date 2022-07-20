package com.project.helpdesksystem.handleException;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ModelErro implements Serializable {
	private static final long serialVersionUID = 1L;

	private LocalDateTime timestmap;
	private Integer status;
	private String error;
	private String message;
	private String path;

	public ModelErro() {
		// TODO Auto-generated constructor stub
	}

	public ModelErro(LocalDateTime localDateTime, Integer status, String error, String message, String path) {
		super();
		this.timestmap = localDateTime;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public LocalDateTime getTimestmap() {
		return timestmap;
	}

	public void setTimestmap(LocalDateTime timestmap) {
		this.timestmap = timestmap;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
