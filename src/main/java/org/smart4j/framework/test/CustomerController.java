package org.smart4j.framework.test;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.FileParam;
import org.smart4j.framework.bean.Param;

import java.util.Map;

/**
 * @author tjj .
 */
@Controller
public class CustomerController {
    @Inject
    CustomerService customerService;
    @Action("post:/customer_create")
    public Data createSubmit(Param param){
        Map<String,Object> fieldMap=param.getFieldMap();
        FileParam fileParam=param.getFile("photo");
        //boolean result=customerService.createCustomer(fieldMap,fileParam);
        return new Data("");
    }
}
