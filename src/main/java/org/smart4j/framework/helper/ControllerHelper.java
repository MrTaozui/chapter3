package org.smart4j.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;

/**
 * 控制器助手类  初始化 存放request和handler之间的映射关系
 * 
 * 遍历使用Controller注解的class 遍历class的方法 使用Action注解的获取 value() 
 * 从value中提取出来请求方法和请求路径 实例化一个Request(methodName,mappingPath)对象 
 * 和一个Handler(contrllerClass,method)对象 然后request为key handler为value放入
 * action map之中
 * @author taojiajun
 *
 */
public final class ControllerHelper {
	/**
	 * 用于存放请求与处理器之间的关系 简称 Action Map
	 */
	private static final Map<Request, Handler> ACTION_MAP=new HashMap<Request, Handler>();
	
	static{
		//获取所有的Controller类
		Set<Class<?>> controllerClassSet=ClassHelper.getControllerClassSet();
		if(CollectionUtil.isNotEmpty(controllerClassSet)){
			//遍历这些Controller类
			for(Class<?> controller:controllerClassSet){
				//获取controller类中的方法
				Method []methods=controller.getDeclaredMethods();
				if(ArrayUtil.isNotEmpty(methods)){
				//遍历这些Controller类中的方法   
				for(Method method:methods){
					// 判断当前方法是否带有Action注解
					if(method.isAnnotationPresent(Action.class)){
						//从Action注解中获取URL映射规则  从当前方法中或得
						Action action=method.getAnnotation(Action.class);
						String mapping=action.value();
						//验证URL映射规则  xxx:/xxx
						if(mapping.matches("\\w+:/\\w*")){
							String[] array=mapping.split(":");
							if(ArrayUtil.isNotEmpty(array)&&array.length==2){
								//获取请求方法与请求路径
								String requestMethod=array[0];
								String requestPath=array[1];
								Request request=new Request(requestMethod, requestPath);
								Handler handler=new Handler(controller, method);
								//初始化 Action map
								ACTION_MAP.put(request, handler);
							}
						}
					}
				}
				}
			}
		}
	}
/**
 * 获取Handler
 */
	public static Handler getHandler(String requestMethod,String requestPath){
		Request request=new Request(requestMethod, requestPath);//重写了 hashCode() 和equals()
		return ACTION_MAP.get(request);
	}
}
