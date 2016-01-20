package com.hm.engine.common.exception;

public class EngineRestException extends RuntimeException {
	public EngineRestException() {
	}

	public EngineRestException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EngineRestException(String arg0) {
		super(arg0);
	}

	public EngineRestException(Throwable arg0) {
		super(arg0);

	}

	private static final long serialVersionUID = -105239594247548881L;

	private int code;
	private String message;

	public EngineRestException(int code,String message){
		this.code=code;
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getErrorCode() {
		return code;
	}

	public void setErrorCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
