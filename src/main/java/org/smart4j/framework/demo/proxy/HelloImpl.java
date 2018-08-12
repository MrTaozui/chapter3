package org.smart4j.framework.demo.proxy;

public class HelloImpl implements Hello{

	@Override
	public void say(String name) {
		System.out.println("Hello "+name);
		
	}

	@Override
	public void say() {
		System.out.println("say no args");
		
	}

}
