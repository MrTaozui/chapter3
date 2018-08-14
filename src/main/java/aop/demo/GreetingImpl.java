package aop.demo;

import org.smart4j.framework.demo.proxy.Greeting;
import org.springframework.stereotype.Component;
@Component
public class GreetingImpl implements Greeting{

	@Override
	public void sayHello(String name) {
		System.out.println("hello " +name);
		
	}
	public void goodMorning(String name){
		System.out.println("Good Morning! "+name);
	}
	public void goodNight(String name){
		System.out.println("Good Night! "+name);
	}

}
