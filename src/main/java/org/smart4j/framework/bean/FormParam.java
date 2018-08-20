package org.smart4j.framework.bean;

/**
 * 封装表单参数
 * @author tjj .
 */
public class FormParam {
    private String filedName;//表单项名
    private Object filedValue;//字段值

    public FormParam(String filedName,Object filedValue){
        this.filedName=filedName;
        this.filedValue=filedValue;
    }

    public String getFiledName() {
        return filedName;
    }

    public Object getFiledValue() {
        return filedValue;
    }
}
