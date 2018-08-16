package org.smart4j.framework.test;

import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * @author tjj .
 */
@Aspect(Controller.class) //加上此代理类后所有的  Bean 容器中的Controller类对应的
//bean  都会被替换成代理类 然后DispatcherServlet 执行的ControllerBean之中的方法的时候
//会执行被代替之后的Proxybean 代理类的方法 就会有此增强操作  具体增强逻辑看ProxyManager里面的返回的代理类的
//方法拦截
public class AopTest extends AspectProxy{
    @Override
    public void before(Class<?> cls, Method method, Object[] parsms) throws Throwable {
        System.out.println("这是一个before");
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] parsms) throws Throwable {
        System.out.println("这个是一个 after");
    }
}
