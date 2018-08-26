package org.smart4j.plugin.soap;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.annotation.Service;


/**
 * 客户服务接口实现
 * @author taojiajun
 *
 */
@Soap
@Service
public class CustomerSoapServiceImpl implements CustomerSoapService{

	@Inject
	private CustomerService customerService;
	@Override
	public Customer getCustomer(long customerId) {

	//	return customerService.getCustomer(customerId);
		Customer customer= new Customer();
		customer.setContact("965369592@qq.com");
		customer.setName("tjj");
		return customer;
	}

}
