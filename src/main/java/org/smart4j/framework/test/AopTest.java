package org.smart4j.framework.test;

import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * @author tjj .
 */
@Aspect(Service.class)
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
