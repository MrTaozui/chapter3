package org.smart4j.framework.test;

import java.io.UnsupportedEncodingException;
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
		try {
			System.out.println(new String(p.getString("name").getBytes(),"ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new Data("lalallala");
	}


}
