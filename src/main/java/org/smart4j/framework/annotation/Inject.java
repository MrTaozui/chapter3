package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 依赖注入注解
 * @author tjj .
 */
@Target(ElementType.FIELD)//作用在字段上
@Retention(RetentionPolicy.RUNTIME)//停留时间 运行时
public @interface Inject {
}
