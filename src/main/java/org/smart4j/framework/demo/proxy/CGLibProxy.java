package org.smart4j.framework.demo.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * CGLib 代理
 * 而cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
 * <!-- https://mvnrepository.com/artifact/asm/asm -->
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>2.2.2</version>
</dependency>
方法级别的代理
 * @author taojiajun
 *
 */
public class CGLibProxy  implements MethodInterceptor{
	
	private static CGLibProxy instance=new CGLibProxy();
	private CGLibProxy(){}
	public static CGLibProxy getInstance(){
		return instance;
	}
	public <T> T getProxy(Class<T> cls){
		return (T) Enhancer.create(cls, this);//返回代理 但是需要的参数是被代理类的类类型  和增强的参数 通过修改字节码文件
	}
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		before();
		Object result= proxy.invokeSuper(obj, args);//被代理的 obj 以及方法参数传入
		after();
		return result;
	}
	private void before(){
		System.out.println("before");
	}
	private void after(){
		System.out.println("after");
	}
	public static void main(String[] args) {
		
		Hello helloProxy=CGLibProxy.getInstance().getProxy(HelloImpl.class);
		helloProxy.say("jack");
	}

}
