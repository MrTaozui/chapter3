package org.smart4j.framework.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy2 implements InvocationHandler{
	/**
	 * 代理的目标类
	 */
	private Object target;
	
	public DynamicProxy2(Object targe){
		this.target=targe;
	}
/**
 * 代理方法的 实现  增强方法的具体实现逻辑
 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
/**
 * 
 * @return
 */
	@SuppressWarnings("unchecked")
	public <T> T getProxy(){
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this);//目标类 和代理类 需要参数
	}
	public static void main(String[] args) {
		DynamicProxy2 dynamicProxy2=new DynamicProxy2(new HelloImpl());
		Hello helloProxy=dynamicProxy2.getProxy();
		helloProxy.say("jack");
	}
}
