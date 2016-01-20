package com.hm.engine.common.exception.os;

public enum RuleExpressionExceptionCodeMsg {
	RuleError(0x20001002, "规则错误"),
	RuleAlreadyExists(0x20001001,"表达式已经存在");
	private int error;
	private String msg;

	private RuleExpressionExceptionCodeMsg(int error, String msg) {
		this.error = error;
		this.msg = msg;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
