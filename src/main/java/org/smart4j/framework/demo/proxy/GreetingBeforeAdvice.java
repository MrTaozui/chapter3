package org.smart4j.framework.demo.proxy;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
/**
 * 前置增强类
 * @author taojiajun
 *
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
	System.out.println("Before");		
	}

}
