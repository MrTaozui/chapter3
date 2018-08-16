package org.smart4j.framework.test;

import java.lang.reflect.Method;

import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.proxy.AspectProxy;
/**
 * 与AopTest一起测试 查看多个代理执行几次原方法
 * @author taojiajun
 *
 */
@Aspect(Controller.class)
public class AopTest2 extends AspectProxy{
	
	    @Override
	    public void before(Class<?> cls, Method method, Object[] parsms) throws Throwable {
	        System.out.println("这是一个before2");
	    }

	    @Override
	    public void after(Class<?> cls, Method method, Object[] parsms) throws Throwable {
	        System.out.println("这个是一个 after2");
	    }
}
