package org.smart4j.framework.test;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Param;

@Controller
public class ControllerTest {
	@Inject
	ServiceTest serviceDemo;
	@Action("get:/la")
	public Data fun(Param param){
		//serviceDemo.print();
		return new Data("lalallala");
	}

}
