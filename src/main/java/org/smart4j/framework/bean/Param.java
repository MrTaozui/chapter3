package org.smart4j.framework.bean;
/**
 * 请求参数对象  从httpRequestServlet对象中获取值 封装起来
 * @author taojiajun
 *
 */

import java.util.Map;

import org.smart4j.framework.util.CastUtil;

public class Param {
	private Map<String,Object> paramMap;
	
	public Param(Map<String,Object> paramMap){
		this.paramMap=paramMap;
	}
	/**
	 * 根据参数名获取long型参数值
	 */
	public long getLong(String name){
		return CastUtil.castLong(paramMap.get(name));
	}
	/**
	 * 根据参数名获取String型参数值
	 */
	public String getString(String name){
		return CastUtil.castString(paramMap.get(name));
	}
	/**
	 * 获取所有字段信息
	 */
	public Map<String,Object>getMap(){
		return paramMap;
	}

}
