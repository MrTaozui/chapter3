package org.smart4j.framework.demo.proxy;
/**
 * 静态代理
 * @author taojiajun
 *
 */
public class HelloProxy implements Hello{
	private Hello hello;
	public HelloProxy(){
		hello=new HelloImpl();
	}

	@Override
	public void say(String name) {
		before();
		hello.say(name);
		after();
		
	}
	private void before(){
		System.out.println("before");
		
	}
	private void after(){
		System.out.println("after");
	}
	
	public static void main(String[] args) {
		HelloProxy helloProxy=new HelloProxy();
		helloProxy.say("Jack");
	}

	@Override
	public void say() {
		
		
	}
}
