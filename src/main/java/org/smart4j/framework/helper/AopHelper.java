package org.smart4j.framework.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import org.smart4j.framework.annotation.Aspect;

public final class AopHelper {
	/**
	 * 获取Ascept 注解中设置的 注解类
	 */
	private static  Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{
		Set<Class<?>> targetClassSet=new HashSet<Class<?>>();
		Class<? extends Annotation> annotation=aspect.value();//获取Aspect注解中的 设置拦截的注解类
		if(annotation!=null&&!annotation.equals(Aspect.class)){//不包括 Ascept本身注解
			targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));//获取所有
		}
		return targetClassSet;
	}

}
