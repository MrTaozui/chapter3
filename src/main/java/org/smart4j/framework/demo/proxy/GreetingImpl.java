package org.smart4j.framework.demo.proxy;


public class GreetingImpl implements Greeting{

	@Override
	public void sayHello(String name) {
		
		System.out.println("hello "+name);
			
	}

	

}
