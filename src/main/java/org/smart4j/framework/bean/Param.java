package org.smart4j.framework.bean;
/**
 * 请求参数对象  从httpRequestServlet对象中获取值 封装起来
 * @author taojiajun
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.smart4j.framework.util.CastUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.StringUtil;

public class Param {
//	private Map<String,Object> paramMap;

	private List<FormParam> formParamList;//form表单属性等

	private List<FileParam> fileParamList;//文件
	
	/*public Param(Map<String,Object> paramMap){
		this.paramMap=paramMap;
	}*/
	public Param(List<FormParam> formParamList){
		this.formParamList=formParamList;
	}
	public Param(List<FormParam> formParamList,List<FileParam> fileParamList){
		this.formParamList=formParamList;
		this.fileParamList=fileParamList;
	}

	/**
	 * 根据参数名获取String型参数值
	 */
	public String getString(String name){
		return CastUtil.castString(getFieldMap().get(name));
	}
	/**
	 * 根据参数名获取double型参数值
	 */
	public double getDouble(String name){
		return CastUtil.castDouble(getFieldMap().get(name));
	}
	/**
	 * 根据参数名获取long型参数值
	 */
	public long getLong(String name){
		return CastUtil.castLong(getFieldMap().get(name));
	}
	/**
	 * 根据参数名获取int型参数值
	 */
	public int getInt(String name){
		return CastUtil.castInt(getFieldMap().get(name));
	}
	/**
	 * 根据参数名获取boolean型参数值
	 */
	public boolean getBoolean(String name){
		return CastUtil.castBoolean(getFieldMap().get(name));
	}
	/**
	 * 获取所有字段信息
	 */
	/*public Map<String,Object>getMap(){
		return paramMap;
	}*/
	/**
	 * 验证参数是否为空
	 */
	public boolean isEmpty(){	
		return CollectionUtil.isEmpty(formParamList)
				&&CollectionUtil.isEmpty(fileParamList);
	}

	/**
	 * 获取请求参数映射
	 */
	public Map<String, Object> getFieldMap() {
		Map<String,Object> fieldMap=new HashMap<String,Object>();
		if(CollectionUtil.isNotEmpty(formParamList)){
			for (FormParam formParam :formParamList) {
				String fieldName=formParam.getFiledName();
				Object fieldValue=formParam.getFiledValue();
				if(fieldMap.containsKey(fieldName)){
					fieldValue=fieldMap.get(fieldName)+ StringUtil.SEPARATOR+fieldValue;
				}
				fieldMap.put(fieldName,fieldValue);
			}
		}
		return  fieldMap;
	}
	/**
	 * 获取上传文件映射
	 */
	public  Map<String,List<FileParam>> getFileMap(){
		Map<String,List<FileParam>> fileMap=new HashMap<String, List<FileParam>>();
		if(CollectionUtil.isNotEmpty(fileParamList)){
			for(FileParam fileParam:fileParamList){
				String fieldName=fileParam.getFieldName();
				List<FileParam> fileParamList;
				if(fileMap.containsKey(fieldName)){
					fileParamList=fileMap.get(fieldName);
				}else{
					fileParamList=new ArrayList<FileParam>();
				}
				fileParamList.add(fileParam);
				fileMap.put(fieldName,fileParamList);
			}
		}
		return fileMap;
	}
	/**
	 * 获取所有上传文件
	 */
	public List<FileParam> getFileList(String fileName){
		return getFileMap().get(fileName);
	}
	/**
	 * 获取唯一上传文件
	 */
	public FileParam getFile(String fileName){
		List<FileParam> fileParamList=getFileList(fileName);
		if(CollectionUtil.isNotEmpty(fileParamList)){
			return fileParamList.get(0);
		}
		return null;
	}
}
