package com.hm.engine.common.exception;

import java.io.EOFException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * EngineExceptionHandle 异常处理类
 * 
 * @author ant_shake_tree
 */
public class EngineExceptionHandle {
	static Logger logger = Logger.getLogger(EngineRestException.class);

	public static EngineRestException throwEngineException(Exception e) {
		return changeCustomerExceptionCode(e);
	}

	public static EngineRestException changeCustomerExceptionCode(Exception e) {
		if (e instanceof EngineRestException) {
			return (EngineRestException) e;
		}
		
		EngineExceptionCode ec;
		if (e instanceof NullPointerException
				|| e instanceof java.lang.ClassCastException
				|| e instanceof java.lang.ClassNotFoundException
				|| e instanceof java.lang.NoSuchFieldException
				|| e instanceof java.lang.IndexOutOfBoundsException) {
			logger.error(e.getMessage(), e);
			ec = EngineExceptionCode.EngineAbnormalException;
		} else if (e instanceof EOFException
				|| e instanceof JsonProcessingException) {
			ec = EngineExceptionCode.EngineBadRequestException;
		} else if (e instanceof EngineResourceNoFoundException) {
			ec = EngineExceptionCode.EngineResourceNoFoundException;
		} else if (e instanceof EngineDatabaseException) {
			ec = EngineExceptionCode.EngineDatabaseException;
		} else if (e instanceof IllegalArgumentException) {
			ec = EngineExceptionCode.EngineBadRequestException;
		} else if (e instanceof JsonMappingException) {
			ec = EngineExceptionCode.EngineBadRequestException;
		}else if(e instanceof ParametersException){
			EngineRestException restException = new EngineRestException(e);
			ParametersException parametersException =(ParametersException)e;
			restException.setErrorCode(parametersException.error());
			restException.setMessage(parametersException.getMessage());
			return restException;
		}else if(e instanceof HttpRequestMethodNotSupportedException){
			ec =EngineExceptionCode.EngineMethodNotSupportedException;
		}
		else {
			ec = EngineExceptionCode.EngineUnknowException;
		}
		EngineRestException restException = new EngineRestException(e);
		restException.setErrorCode(ec.getErrorCode());
		return restException;
	}
	public static int generalError(int model,int error){
		//第一个8位项目 cdss remind 项目为2;
		int pro=2<<28;
		System.out.println(Integer.toBinaryString(pro));
		//第二个8为是模块id
		int m=model<<16;
		//16位为错误码
		return (pro|m)|error;
	}


}
