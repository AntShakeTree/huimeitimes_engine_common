package com.hm.engine.common.exception;

import com.hm.engine.common.BeanUtils;
import com.hm.engine.common.annotation.HmRequired;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.Collection;

public class HmAssert extends Assert {
	static Logger logger =Logger.getLogger(HmAssert.class);
	public static void notNull(Object param,String object, String message) {
		if (param == null)  {
			throw new ParametersException(object, message);
		}
	}

	public static void notNull(Object param, String parameterName) {
		if (param == null) {
			throw new ParametersException(parameterName, "is required.");
		}
	}

	/**
	 * 自动加提示信息
	 * @param clazz
	 * @param object 需要验证的对象
     * @param <T>
     */
	public static <T> void notNull(Class<T> clazz,Object object) {
		if (object == null) {
			throw new ParametersException(clazz.getSimpleName(), " is required");
		}
	}

	/**
	 *
	 * @param expression
	 * @param error
	 * @param message
     * @param <T>
     */
	public static <T> void throwRestExceptionWhenTrue(boolean expression,int error,String message){
		if(expression){
			EngineRestException restException=new EngineRestException();
			restException.setErrorCode(error);
			restException.setMessage(message);
			throw restException;
		}
	}

	/**
	 * 如果被验证上添加hm独特的验证注解此验证有效
	 * @param o
	 * @param <T>
	 */
	public static <T> void valiadateByAnotation(T o){
		notNull(o.getClass(),o);
		Field[] fields=BeanUtils.getDeclaredFields(o.getClass());
		for (Field field: fields){
			//先实现必填验证,以后丰富
			HmRequired required=field.getAnnotation(HmRequired.class);
			if(required!=null){
				try {
					notNull(BeanUtils.forceGetProperty(o,field),field.getName());
				} catch (NoSuchFieldException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}
	}

	/**
	 * 同valiadateByAnotation(T o) 被验证对象指定为集合
	 * @param os
	 * @param <T>
	 */
	public static <T> void valiadateByAnotation(Collection<T> os){
		notEmpty(os);
		for(T o:os){
			valiadateByAnotation(o);
		}
	}

}

