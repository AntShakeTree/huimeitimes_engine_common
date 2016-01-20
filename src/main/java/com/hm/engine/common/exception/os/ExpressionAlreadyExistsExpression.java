package com.hm.engine.common.exception.os;

import com.hm.engine.common.exception.EngineRestException;
/**
 * 
 * @description 表达式已经存在异常
 * @author lipeng
 * @date 2015年10月13日
 */
public class ExpressionAlreadyExistsExpression extends EngineRestException {

	private static final long serialVersionUID = 8619199555685215075L;
	private long errorCode=0x20001001;
	public String message="表达式已经存在";
	public long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
