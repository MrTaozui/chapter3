package org.smart4j.plugin.soap;


public interface CustomerSoapService {
	/**
	 * 根据客户id 获取客户对象
	 */
	Customer getCustomer(long customerId);

}
