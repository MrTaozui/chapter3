package aop.demo;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;
/**
 * 引入增强  对类的增强
 * @author taojiajun
 *
 */
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology{
	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		return super.invoke(arg0);
	}

	@Override
	public void saySorry(String name) {
		System.out.println("sorry "+name);
	}

}
