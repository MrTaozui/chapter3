package org.smart4j.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 封装请求信息
 * 获取带注解Action的信息 封装成一个Request对象
 * @author taojiajun
 *
 */
public class Request {
	/**
	 * 请求方法
	 */
	private String requestMethod;
	/**
	 * 请求路径
	 */
	private String requetPath;
	/**
	 * 构造方法
	 */
	public Request(String requestMethod,String requestPath){
		this.requestMethod=requestMethod;
		this.requetPath=requestPath;		
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public String getRequetPath() {
		return requetPath;
	}
	public void setRequetPath(String requetPath) {
		this.requetPath = requetPath;
	}
	@Override
	public int hashCode() {
		//如果hashCode取决于该class的所有filed时需要使用反射机制来产生一个hashCode,根据字段名==
		return HashCodeBuilder.reflectionHashCode(this);
	}
	@Override
	public boolean equals(Object obj) {
		//如果两个对象相等当且仅当每个属性值都相等  equals
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
}
