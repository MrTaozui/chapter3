package org.smart4j.framework.demo.proxy;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
/**
 * 后置增强类
 * @author taojiajun
 *
 */
public class GreetingAfterAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		System.out.println("After");
		
	}

}
