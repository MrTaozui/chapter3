package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法注解   用在控制器的方法上
 * @author tjj .
 */
@Target(ElementType.METHOD)//作用在方法上
@Retention(RetentionPolicy.RUNTIME)//保留的时期
public @interface Action {
    /**
     * 请求类型与路径     method:/mapping_path
     */
    String value();
}
