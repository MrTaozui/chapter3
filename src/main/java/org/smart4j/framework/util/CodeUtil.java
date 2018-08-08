package org.smart4j.framework.util;
/**
 * 编码与解码操作工具类
 * @author taojiajun
 *
 */

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CodeUtil {
	private static final Logger LOGGER=LoggerFactory.getLogger(CodeUtil.class);
	/**
	 * 将URL编码
	 */
	public static String encodeURL(String source){
		String target;
		try {
			target=URLEncoder.encode(source,"UTF-8");//url 在浏览器时传输时 编码 然后会自动解码
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("encode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
	}
	/**
	 * 将URL解码
	 */
	public static String decodeURL(String source){
		String target;
		try {
			target=URLDecoder.decode("source", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("decode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
	}

}
