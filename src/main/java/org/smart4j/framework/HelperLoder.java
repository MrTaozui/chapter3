package org.smart4j.framework;

import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;

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
				IocHelper.class, //为 使用Inject注解的 类，忘Bean容器的对象 字段里注入对象实例 主要是serivice
				ControllerHelper.class//为带有controller注解的类，为其带有Action注解的方法生成 根据注解值
									//初始化成<Request,Handler>
		};
	}

}
