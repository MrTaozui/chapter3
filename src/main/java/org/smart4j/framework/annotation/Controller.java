package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制器注解   在控制器类上可以使用此注解
 * @author tjj .
 */
@Target(ElementType.TYPE)//注解的类型 接口，类
@Retention(RetentionPolicy.RUNTIME)//注解 存在的周期 运行时 ，编译时    Retention:保留
public @interface Controller {
}
