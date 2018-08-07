package org.smart4j.framework.bean;
/**
 * 封装action信息
 * 
 * 把action 对应的请求 Controller类和 Method 封装进一个Handler中
 * @author taojiajun
 *
 */

import java.lang.reflect.Method;

public class Handler {
	/**
	 * Controller 类
	 */
	private Class<?> controllerClass;
	/**
	 * Action 方法
	 */
	private Method actionMethod;
	/**
	 * 构造方法
	 */
	public Handler(Class<?> controllerClass,Method actionMethod){
		this.controllerClass=controllerClass;
		this.actionMethod=actionMethod;
	}
	public Class<?> getControllerClass() {
		return controllerClass;
	}
	public void setControllerClass(Class<?> controllerClass) {
		this.controllerClass = controllerClass;
	}
	public Method getActionMethod() {
		return actionMethod;
	}
	public void setActionMethod(Method actionMethod) {
		this.actionMethod = actionMethod;
	}
	
}
