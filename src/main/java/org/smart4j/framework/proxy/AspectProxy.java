package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 切面代理
 * 需要在目标方法被调用的前后增减相应的逻辑
 * 抽象类 提供模板方法，提供模板方法
 * @author tjj .
 */
public abstract class AspectProxy implements Proxy{
    private static final Logger LOGGER= LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object resul=null;
        Class<?> cls=proxyChain.getTargetClass();
        Method method=proxyChain.getTargetMethod();
        Object[] params=proxyChain.getMethodParams();
        begin();
        if(intercept(cls,method,params)){
            before(cls,method);
        }


        return null;
    }
    public void  begin(){}

    public boolean intercept(Class<?> cls,Method method,Object[] params) throws Throwable{
        return true;
    }
    public void before(Class<?> cls,Method method,Object[] parsms) throws Throwable{

    }
    public void after(Class<?> cls,Method method,Object[] parsms) throws Throwable{

    }
    public void error(Class<?> cls,Method method,Object[] parsms,Throwable e){

    }
    public void end(){}

}
