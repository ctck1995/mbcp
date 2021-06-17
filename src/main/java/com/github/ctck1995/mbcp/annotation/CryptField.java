package com.github.ctck1995.mbcp.annotation;

import com.github.ctck1995.mbcp.executor.CryptExecutor;
import com.github.ctck1995.mbcp.executor.NoneCryptExecutor;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface CryptField {

    boolean encrypt() default false;

    boolean decrypt() default true;

    Class<? extends CryptExecutor> cryptExecutorCls() default NoneCryptExecutor.class;
}

