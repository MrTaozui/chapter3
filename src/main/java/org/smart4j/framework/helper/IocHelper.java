package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 * 依赖注入 即 控制反转 new 对象不是开发者决定的 而是反转给框架
 *
 * 解决Controller 中使用注解注入Service 实例等
 * @author tjj .
 */
public final class IocHelper {
    /**
     * 遍历bean容器（含有key(class类型),value(对象实例) service 和controller）
     * 发现class 类的成员变量中含有Inject注解的，获取此变量的类型，从Bean get(变量类型)
     * 容器中取出实例 然后使用反射 此class 类对应的实例的成员变量赋值。
     */
    static {
        Map<Class<?>,Object>beanMap=BeanHelper.getBeanMap();//获取bean容器
        if(CollectionUtil.isNotEmpty(beanMap)){
            //遍历bean map
            for (Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet()){
                //从bean map中获取bean类 与bean实例
                Class<?> beanClass=beanEntry.getKey();
                Object beanInstance=beanEntry.getValue();
                //获取Bean类定义的所有成员变量 bean Field
                Field[] beanFields=beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(beanFields)){
                    //遍历 bean field
                    for(Field beanField:beanFields){
                        //判断当前Bean field是否带有Inject注解
                        if(beanField.isAnnotationPresent(Inject.class)){
                            //在 bean map 中获取bean field对应的实例
                            Class<?> beanFieldClass=beanField.getType();//获取成员的类型
                            Object beanFieldInstance=beanMap.get(beanFieldClass);
                            if(beanFieldInstance!=null){
                                //通过反射初始化BeanField
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }

    }
}
