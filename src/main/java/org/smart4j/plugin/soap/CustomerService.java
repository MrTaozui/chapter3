package org.smart4j.plugin.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.bean.FileParam;
import org.smart4j.framework.helper.DatabaseHelper;
import org.smart4j.framework.helper.UploadHelper;
import java.util.List;
import java.util.Map;


/**
 * @author tjj .
 */
@Service
public class CustomerService {
    private static final Logger LOGGER= LoggerFactory.getLogger(CustomerService.class);

    /**
     * 获取用户列表
     * @param keyWord
     * @return
     */
    public  List<Customer> getCustomerList(String keyWord){     
            String sql="select * from customer";
            return DatabaseHelper.queryEntityList(Customer.class,sql);
    
    }
    /**
     * 获取客户
     */
    public Customer getCustomer(long id){
    	String sql="select * from customer where id =?";    	
        return  DatabaseHelper.queryEntity(Customer.class, sql, id);
    }
    /**
     * 创建客户
     */
    public boolean createCustomer(Map<String,Object> fieldMap){
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }
    /**
     * 更新用户
     */
    public  boolean updateCustomer(long id,Map<String,Object>fieldMap){       
        return  DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
    }
    /**
     * 删除用户
     */
    public boolean deleteCustomer(long id){
        return  DatabaseHelper.deleteEntity(Customer.class, id);
    }
    /**
     * 创建用户
     */
    @Transaction
    public boolean createCustomer(Map<String,Object> filedMap, FileParam fileParam){
        boolean result=DatabaseHelper.insertEntity(Customer.class,filedMap);
        if(result){
            UploadHelper.uploadFile("/tmp/upload/",fileParam);//当前盘符下 的位置 由于项目在D盘下 所以位置为D:/tmp/upload/
        }
        return result;
    }
}
