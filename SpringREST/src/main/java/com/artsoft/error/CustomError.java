package com.artsoft.error;

import java.io.Serializable;



public class CustomError implements Serializable{
	
	private static final long serialVersionUID = 2405273195976698567L;
	private boolean hasError;
	private String errorOnField;
	private String errorMessage;
	
	public boolean isHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	public String getErrorOnField() {
		return errorOnField;
	}
	public void setErrorOnField(String errorOnField) {
		this.errorOnField = errorOnField;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

	
}
