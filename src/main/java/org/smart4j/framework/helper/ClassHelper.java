package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * 类操作助手类
 * 用于获取应用包名下的所有类，Service Contrlooer
 * Service Contrlooer所产生的对象理解为Smart所管理的bean
 * @author tjj .
 */
public final class ClassHelper {
    /**
     * 定义类集合  用于存放所加载的类
     */
    private static final Set<Class<?>>CLASS_SET;
    static {
        //初始化加载所有类
        String basePackage=ConfigHelper.getAppBasePackage();
        CLASS_SET= ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用包名下的所有类
     */
    public static  Set<Class<?>> getClassSet(){
        return  CLASS_SET;
    }
    /**
     * 获取应用包名下的所有Service类
     */
    public static Set<Class<?>> getServiceClassSet(){
    Set<Class<?>>  classSet=new HashSet<Class<?>>();
    for(Class<?> cls:CLASS_SET){
        if(cls.isAnnotationPresent(Service.class)){//如果指定类型的注释存在于此元素上 true,否则返回false
        	classSet.add(cls);
        }
    }
    return classSet;
    }
    /**
     * 获取应用包名下的所有Controller类
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        for(Class<?> cls:CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class)){
            classSet.add(cls);
            }
        }
    return classSet;
    }
    /**
     * 获取应用包名下的所有Bean类包括Service,Controller等
     */
    public static Set<Class<?>>getBeanClassSet(){
        Set<Class<?>> beanClassSet=new HashSet<Class<?>>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }
    /**
     * 获取应用包名下某父类（或接口）的所有子类（或实现类）
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
    	Set<Class<?>> classSet=new HashSet<Class<?>>();
    	for (Class<?> cls : CLASS_SET) {
    		//isAssignableFrom   是用来判断一个类Class1和另一个类Class2是否相同或是另一个类的超类或接口。
			if(superClass.isAssignableFrom(cls)&&!superClass.equals(cls)){//!superClass.equals(cls)去除本身
				//  instanceof   是用来判断一个对象实例是否是一个类或接口的或其子类子接口的实例。  
				classSet.add(cls);
			}
		}
    	return classSet;
    }
    /**
     * 获取应用包名下带有某注解的所有类
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass ){
    	Set<Class<?>> classSet=new HashSet<Class<?>>();
    	for (Class<?> cls : CLASS_SET) {
			if(cls.isAnnotationPresent(annotationClass)){
				classSet.add(cls);
			}
		}
    	return classSet;
    }
    
}
