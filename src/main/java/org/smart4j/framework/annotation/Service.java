package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务类注解  作用在服务类上
 * @author tjj .
 */
@Target(ElementType.TYPE)//作用在类上
@Retention(RetentionPolicy.RUNTIME)//停留时间 运行时期
public @interface Service {
}
