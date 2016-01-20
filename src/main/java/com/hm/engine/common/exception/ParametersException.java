package com.hm.engine.common.exception;

import com.hm.engine.common.Utils;

public class ParametersException extends RuntimeException {
	private String parameterName;
	private String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4204997137986254764L;
	
	public ParametersException(String parameterName,String message){
		this.parameterName=parameterName;
		this.message=message;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getMessage() {
		return this.parameterName +" "+message;
	}
	public int error(){
		return (int)Utils.hashCode(this.parameterName)&0x5fffffff;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
