package org.smart4j.framework.util;
/**
 * JSON工具类
 * @author taojiajun
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {
	private static final Logger LOGGER=LoggerFactory.getLogger(JsonUtil.class);
	
	private static final ObjectMapper OBJECT_MAPPER=new ObjectMapper();
	/**
	 * 将POJO 转为JSON
	 */
	public static <T>String toJson(T obj){
		String json;
		try {
			json=OBJECT_MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			LOGGER.error("convert POJO to JSON failure",e);
			throw new RuntimeException(e);
		}
		return json;
	}
	/**
	 * 将JSON 转POJO
	 */
	public static <T>T fromJson(String json,Class<T> type){
		T pojo;
		try{
		pojo=OBJECT_MAPPER.readValue(json, type);
		}catch (Exception e) {
			LOGGER.error("convert JSON to POJO failure",e);
			throw new RuntimeException(e);
		}
		return pojo;
	}

}
