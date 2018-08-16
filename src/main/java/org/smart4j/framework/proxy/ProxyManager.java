package org.smart4j.framework.proxy;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理管理器
 * 输入一个目标类和一组Proxy接口实现 输出一个代理对象
 * 创建所有代理对象
 * @author taojiajun
 *
 */
public class ProxyManager {
	/**
	 * 返回代理对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createProxy(final Class<?> targetClass,final List<Proxy> proxyList){
		
		return (T) Enhancer.create(targetClass, new MethodInterceptor() {//方法的增强处理
			//targetObject  是targetClass 类创建出来的
			@Override
			public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {

				return new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList).doProxyChain();
			}
		});
	}

}
