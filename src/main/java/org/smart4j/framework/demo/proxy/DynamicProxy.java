package org.smart4j.framework.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * jdk  动态代理   但是被代理的类要有接口
 * java动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
 * @author taojiajun
 *
 */
public class DynamicProxy implements InvocationHandler {
	/**
	 * 代理的目标类
	 */
	private Object target;
	
	public DynamicProxy(Object targe){
		this.target=targe;
	}
/**
 * 代理方法的 实现  增强方法的具体实现逻辑
 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//实现代理的方法
		before();
		//proxy=target;
		Object result=method.invoke(target, args);
		after();
		return result;
	}
	private void before(){
		System.out.println("before");
		
	}
	private void after(){
		System.out.println("after");
	}
	public static void main(String[] args) {
		Hello hello=new HelloImpl();
		DynamicProxy dynamicProxy=new DynamicProxy(hello);
		Hello helloProxy=(Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(),
				hello.getClass().getInterfaces(), dynamicProxy);
		helloProxy.say("jack");
		helloProxy.say();
	}
}
