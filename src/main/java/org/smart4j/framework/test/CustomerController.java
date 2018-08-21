package org.smart4j.framework.test;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.FileParam;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

import java.util.List;
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
        boolean result=customerService.createCustomer(fieldMap,fileParam);
        return new Data(result);
    }
    @Action("get:/customer")
    public View getCunsomerList(){
        List<Customer> customerList=	customerService.getCustomerList("");

        return new View("customer.jsp").addModel("customerList", customerList);
    }
    @Action("get:/toCreate")
    public View toCreateEntityPage(){
        return new View("customer_create.jsp");
    }
}
