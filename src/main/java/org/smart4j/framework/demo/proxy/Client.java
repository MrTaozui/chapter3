package org.smart4j.framework.demo.proxy;

import org.springframework.aop.framework.ProxyFactory;

public class Client {
	public static void main(String[] args) {
		ProxyFactory proxyFactory=new ProxyFactory();//创建代理工厂
		proxyFactory.setTarget(new GreetingImpl());//射入目标类
		/*proxyFactory.addAdvice(new GreetingBeforeAdvice());//添加前置增强
		proxyFactory.addAdvice(new GreetingAfterAdvice());//添加后置增强
*/		
		proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice());
		Greeting greeting=(Greeting) proxyFactory.getProxy();
		greeting.sayHello("jack");
	}

}
