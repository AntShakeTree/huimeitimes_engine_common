package com.hm.engine.common.exception.os;

import org.apache.commons.lang3.StringUtils;

import com.hm.engine.common.exception.EngineRestException;
/**
 * 药物作用类型与用药类别冲突异常(某些作用类别下不能填写现在用药类别)
 * @description
 * @author lipeng
 * @date 2015年10月13日
 */
public class DrugUseClassAndTypeConflictExpression extends EngineRestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3222817187811046045L;
	private long errorCode=0x20001002;
	public String message;
	
	public DrugUseClassAndTypeConflictExpression(String drugUserClass) {
		if(StringUtils.isBlank(drugUserClass)){
			this.message = "填写了药物类别时，必须选择作用类别";
		}else{
			this.message = drugUserClass+"类型时，不能填写药物类型";
		}
	}
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
