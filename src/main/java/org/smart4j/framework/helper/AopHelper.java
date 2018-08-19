package org.smart4j.framework.helper;

import java.lang.annotation.Annotation;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.proxy.AspectProxy;
import org.smart4j.framework.proxy.Proxy;
import org.smart4j.framework.proxy.ProxyManager;
import org.smart4j.framework.proxy.TransactionProxy;

public final class AopHelper {
	private static final Logger LOGGER= LoggerFactory.getLogger(AopHelper.class);
	/**
	 * 获取Ascept 注解中设置的 注解类
	 */
	private static  Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{
		Set<Class<?>> targetClassSet=new HashSet<Class<?>>();
		Class<? extends Annotation> annotation=aspect.value();//获取Aspect注解中的 设置拦截的注解类
		if(annotation!=null&&!annotation.equals(Aspect.class)){//不包括 Ascept本身注解
			targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));//获取所有 带有annotation 注解的类
		}
		return targetClassSet;
	}
	/**
	 * 获取代理类及其目标类集合之间的映射关系 一个代理类对应一个或多个目标类 这里的代理类指切面
	 */
	private static Map<Class<?>,Set<Class<?>>> createProxyMap()throws Exception{
		Map<Class<?>,Set<Class<?>>> proxyMap=new HashMap<Class<?>, Set<Class<?>>>();
		/*//获取 包名下所有父类为AspectProxy的class
		Set<Class<?>> proxyClassSet=ClassHelper.getClassSetBySuper(AspectProxy.class);
		for (Class<?> proxyClass :proxyClassSet) {
			if(proxyClass.isAnnotationPresent(Aspect.class)){//如果带有注解Aspect注解的
				Aspect aspect=proxyClass.getAnnotation(Aspect.class);//获得 Aspect注解类 的值 也就是切点
				Set<Class<?>> targetClassSet=createTargetClassSet(aspect);
				proxyMap.put(proxyClass,targetClassSet);
			}
		}*/
		addAspectProxy(proxyMap);
		addTransactionProxy(proxyMap);//添加数据库事务代理
		return proxyMap;//返回<代理类 , 和被代理的目标类的set>
	}
	/**
	 * 代理类需要扩展AspectProxy 抽象类 还需要带有Ascept注解，只有满足这两条件 才能根据Aspect注解中所定义
	 * 的注解属性去获取该注解所对应的目标类集合，然后才能建立 代理类与目标类集合之间的关系，最终返货这个设置关系
	 * createProxyMap() 这个方法所作的事情
	 *
	 * 一旦获取了代理类与目标类集合之间的关系，就能根据这个关系分析出目标类与代理对象列表之间的关系
	 * 如下
	 */
	private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws  Exception{
		Map<Class<?>,List<Proxy>> targetMap=new HashMap<Class<?>, List<Proxy>>();//代理类 目标类对象集合
		for (Map.Entry<Class<?>,Set<Class<?>>> proxyEntry:proxyMap.entrySet()) {
			Class<?> proxyClass=proxyEntry.getKey();//代理类
			Set<Class<?>> targetClassSet=proxyEntry.getValue();//通过代理类设置的拦截的切点 获得的被代理的class set
			for (Class<?> targetClass:targetClassSet) {
				Proxy proxy=(Proxy)proxyClass.newInstance();//创建代理类
				if(targetMap.containsKey(targetClass)){//是不是已经添加过了 targetClass 持续添加proxy
					targetMap.get(targetClass).add(proxy);
				}else {
					List<Proxy> proxyList=new ArrayList<Proxy>();//这个list
					proxyList.add(proxy);
					targetMap.put(targetClass,proxyList);
				}
			}
		}
		return targetMap;// <被代理的类, 代理集合list<代理对象>> 一个类可能会被多个代理
	}
	
	private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception{
		Set<Class<?>> proxyClassSet=ClassHelper.getClassSetBySuper(AspectProxy.class);
		for (Class<?> proxyClass : proxyClassSet) {
			if(proxyClass.isAnnotationPresent(Aspect.class)){
				Aspect aspect=proxyClass.getAnnotation(Aspect.class);
				Set<Class<?>> targetClassSet=createTargetClassSet(aspect);
				proxyMap.put(proxyClass, targetClassSet);
				
			}
		}
	}
	
	private static void addTransactionProxy(Map<Class<?>,Set<Class<?>>> proxyMap){
		Set<Class<?>> serviceClassSet=ClassHelper.getClassSetByAnnotation(Service.class);
		proxyMap.put(TransactionProxy.class, serviceClassSet);// 代理类 被代理类
	}

	/**
	 * 静态代码块 来初始化整个AOP框架
	 */
	static{
		try {
			Map<Class<?>,Set<Class<?>>> proxyMap= createProxyMap();
			Map<Class<?>,List<Proxy>> targetMap=createTargetMap(proxyMap);
			for (Map.Entry<Class<?>,List<Proxy>> targetEntry : targetMap.entrySet()) {
				Class<?>  targetClass=targetEntry.getKey();
				List<Proxy> proxyList=targetEntry.getValue();
				Object proxy= ProxyManager.createProxy(targetClass,proxyList);// 返回代理对象 代理对象里面有方法拦截和方法增强
				BeanHelper.setBean(targetClass,proxy);// 代理对象重新放入BeanMap中  前面已经加载过BEAN_MAP这里被覆盖
			}
		}catch (Exception e){
			LOGGER.error("aop failure",e);
		}

	}
}
