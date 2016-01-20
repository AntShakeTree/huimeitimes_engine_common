package com.hm.engine.common.annotation;

import java.lang.annotation.*;

/**
 * Created by ant_shake_tree on 15/11/12.
 * 参数校验
 */
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HmRequired {
}
