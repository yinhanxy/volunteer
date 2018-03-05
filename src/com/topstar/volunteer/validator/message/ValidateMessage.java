package com.topstar.volunteer.validator.message;

import java.io.Serializable;

public class ValidateMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5494615312785759243L;

	private String propertyName;
	
	private String message;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ValidateMessage [propertyName=" + propertyName + ", message="
				+ message + "]";
	}
	
	
}
