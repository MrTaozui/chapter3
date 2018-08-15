package org.smart4j.framework.helper;

import org.smart4j.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean助手类
 * @author tjj .
 */
public final class BeanHelper {
    /**
     * 定义Bean映射（用于存放Bean类与Bean实例关系的映射关系）
     */
    private static final Map<Class<?>,Object> BEAN_MAP=new HashMap<Class<?>,Object>();

    static {//把Class类 service controller和对象那放入Map中 K-V 方式 bean容器构造完成
        //
        Set<Class<?>> beanLClassSet=ClassHelper.getBeanClassSet();
        for(Class<?> beanClass:beanLClassSet){
            Object obj= ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass,obj);
        }
    }
    /**
     * 获取Bean 映射
     */
    public static Map<Class<?>,Object>getBeanMap(){
        return  BEAN_MAP;
    }
    /**
     * 获取Bean实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class:"+cls);
        }
        return(T)BEAN_MAP.get(cls);
    }
    /**
     * 设置bean实例
     */
    public static void setBean(Class<?>cls,Object obj ){
    	BEAN_MAP.put(cls, obj);
    }
}
