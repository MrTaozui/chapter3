package org.smart4j.plugin.soap;
/**
 * 客户SOAP服务单元测试
 * @author taojiajun
 *
 */

import org.junit.Test;

public class CustomerSoapServiceTest {
	@Test
	public void getCustomerTest(){
		String wsdl="http://localhost:8080/chapter3/soap/CustomerSoapService";
		CustomerSoapService customerSoapService=SoapHelper.createClinet(wsdl,CustomerSoapService.class);
		Customer customer=customerSoapService.getCustomer(1);
		System.out.println("结果为："+customer);
		System.out.println(customer.getName());
	}
	
}
