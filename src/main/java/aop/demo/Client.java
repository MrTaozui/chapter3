package aop.demo;




import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		GreetingImpl greetingImpl=(GreetingImpl) context.getBean("greetingProxy");
		greetingImpl.sayHello("jack");
		Apology apology=(Apology)greetingImpl;//没有实现这个接口  引入增强了Alology 这个接口
		apology.saySorry("jack");
	}

}
