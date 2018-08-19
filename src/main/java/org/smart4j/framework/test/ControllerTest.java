package org.smart4j.framework.test;

import java.util.List;


import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

@Controller
public class ControllerTest {
	@Inject
	public ServiceTest serviceDemo;
	@Inject
	CustomerService customerService;
	@Action("get:/la")
	public Data fun(Param p){
		System.out.println(p.getString("name"));
		return new Data("lalallala");
	}
	@Action("get:/getCustomer")
	public View getCunsomerList(){
		List<Customer>   customerList=	customerService.getCustomerList("");
		
		return new View("customer.jsp").addModel("customerList", customerList);
	}

}
