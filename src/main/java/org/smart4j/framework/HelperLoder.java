package org.smart4j.framework;

import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.ClassUtil;

/**
 * 加载相应的Helper类
 * @author taojiajun
 *
 */
public final class HelperLoder {
	public static void init(){
		Class<?>[] classList={
				ClassHelper.class, //加载所有的class    controller,service
				BeanHelper.class,  //为加载的class 生成对象生成Bean容器Map<Class<?>,Object>
				AopHelper.class,//加载设置 代理对象 会覆盖上一步BeanHelper.class生成的对象 要在IocHelper之前
				IocHelper.class, //为 使用Inject注解的 类，忘Bean容器的对象 字段里注入对象实例 主要是serivice
				ControllerHelper.class//为带有controller注解的类，为其带有Action注解的方法生成 根据注解值
									//初始化成<Request,Handler>
		};
		for (Class<?> cls:classList){
			ClassUtil.loadClass(cls.getName(),true);
		}
	}

}
