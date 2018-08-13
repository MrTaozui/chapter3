package org.smart4j.framework.demo.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
/**
 * 环绕增强
 * @author taojiajun
 *
 */

public class GreetingAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		before();
		Object result=invocation.proceed();
		after();
		return result;
	}

	private void after() {
		System.out.println("After");
		
	}

	private void before() {
		System.out.println("Before");
		
	}
public static void main(String[] args) {
	ProxyFactory proxyFactory=new ProxyFactory();
	proxyFactory.setTarget(new GreetingImpl());
	proxyFactory.addAdvice(new GreetingAroundAdvice());
	Greeting greeting=(Greeting) proxyFactory.getProxy();
	greeting.sayHello("jack");
}
}
