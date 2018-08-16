package org.smart4j.framework.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.MethodProxy;
/**
 * 代理链
 * @author taojiajun
 *
 */
public class ProxyChain {
	/*private final Class<?> targetClass;
	private final Object targetObject;
	private final Method targetMethod;
	private final MethodProxy methodProxy;
	private final Object[] methodParams;*/
	
	private  Class<?> targetClass;//目标类
	private  Object targetObject;//目标对象
	private  Method targetMethod;//目标方法
	private MethodProxy methodProxy;//方法代理  拦截方法 对方法做增强处理

	private  Object[] methodParams;//方法参数
	
	private List<Proxy> proxyList=new ArrayList<Proxy>();//代理列表
	private int proxyIndex=0;//代理索引  代理对象计数器
	
	public ProxyChain(Class<?> targetClass,Object targetObject,Method targetMethod,MethodProxy methodProxy,
			Object[] methodParams,List<Proxy> proxyList){
		this.targetClass=targetClass;
		this.targetObject=targetObject;
		this.targetMethod=targetMethod;
		this.methodProxy=methodProxy;
		this.methodParams=methodParams;
		this.proxyList=proxyList;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public Method getTargetMethod() {
		return targetMethod;
	}

	public Object[] getMethodParams() {
		return methodParams;
	}
	public Object doProxyChain()throws Throwable{//
		Object methodResult;
		if(proxyIndex<proxyList.size()){
			methodResult=proxyList.get(proxyIndex++).doProxy(this);//执行代理链子  
			//各个代理对象执行代理增强方法   只有 before 和aftre
		}else{
			methodResult=methodProxy.invokeSuper(targetObject, methodParams);//执行元方法
		}
		return methodResult;
	}
	
	
}
