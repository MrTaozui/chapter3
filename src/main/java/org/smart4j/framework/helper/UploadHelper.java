package org.smart4j.framework.helper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.bean.FileParam;
import org.smart4j.framework.bean.FormParam;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.FileUtil;
import org.smart4j.framework.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件上传助手类
 * @author tjj .
 *
 * ServletFileUpload负责处理上传的文件数据,并将表单中每个输入项封装成一个FileItem对象中，
 * 在使用ServletFileUpload对象解析请求时需要根据DiskFileItemFactory对象的属性sizeThreshold（临界值）
 * 和repository（临时目录）来决定将解析得到的数据保存在内存还是临时文件中，如果是临时文件，
 * 保存在哪个临时目录中？。所以，我们需要在进行解析工作前构造好DiskFileItemFactory对象，
 * 通过ServletFileUpload对象的构造方法或setFileItemFactory()方法设置ServletFileUpload对象的fileItemFactory属性。
 */
public final class UploadHelper {
    private static final Logger LOGGER= LoggerFactory.getLogger(UploadHelper.class);
    /**
     * Apache Commons FileUpload 提供的Servlet 文件上传对象
     */
    private static ServletFileUpload servletFileUpload;
    /**
     * 初始化
     */
    public static void init(ServletContext servletContext){
        File repository=(File) servletContext.getAttribute("javax.servlet.context.tempdir");
        servletFileUpload=new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.
                DEFAULT_SIZE_THRESHOLD,repository));
        int uploadLimit=ConfigHelper.getAppUploadLimit();
        if(uploadLimit!=0){
            servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
        }
    }
    /**
     * 判断请求是否为multipart  是否有上传文件
     */
    public static boolean isMultipart(HttpServletRequest request){
        return ServletFileUpload.isMultipartContent(request);
    }
    /**
     * 创建请求对象
     */
    public static Param createParam(HttpServletRequest request) throws IOException{
        List<FormParam> formParamList=new ArrayList<FormParam>();
        List<FileParam> fileParamList=new ArrayList<FileParam>();

        try {
            Map<String, List<FileItem>>  fileItemListMap=servletFileUpload.parseParameterMap(request);
            if(CollectionUtil.isNotEmpty(fileItemListMap)){
                for (Map.Entry<String,List<FileItem>> fileItemListEnrty:fileItemListMap.entrySet()) {
                    String filedName=fileItemListEnrty.getKey();
                    List<FileItem> fileItemList=fileItemListEnrty.getValue();
                    if(CollectionUtil.isNotEmpty(fileItemList)){
                        for (FileItem fileItem:fileItemList) {
                        if(fileItem.isFormField()){// isFormField方法用于判断FileItem类对象封装的数据是一个普通文本表单字段
                            String filedValue=fileItem.getString("UTF-8");
                            formParamList.add(new FormParam(filedName,filedValue));
                        }else{
                        	String fileName=FileUtil.getRealFileName(new String(fileItem.getName().getBytes(),"UTF-8"));
                        	if(StringUtil.isNotEmpty(filedName)){
                        		long fileSize=fileItem.getSize();
                        		String contentType=fileItem.getContentType();
                        		InputStream inputStream=fileItem.getInputStream();
                        		fileParamList.add(new FileParam(filedName, fileName, fileSize, contentType, inputStream));
                        	}
                        }
                        }
                    }
                }
            }


        } catch (FileUploadException e) {
           LOGGER.error("create param failure",e);
           throw new RuntimeException(e);
        }
        return new Param(formParamList,fileParamList);
    }
    /**
     * 上传文件
     */
    
    public static void uploadFile(String basePath,FileParam fileParam){
    	try {
    	if(fileParam!=null){
    		String filePath=basePath+fileParam.getFileName();
    		FileUtil.createFile(filePath);
    		InputStream inputStream=new BufferedInputStream(fileParam.getInputStream());
    		
				OutputStream outputStream=new BufferedOutputStream(new FileOutputStream(filePath));
			
    	}
    	} catch (FileNotFoundException e) {
			LOGGER.error("upload file failure",e);
			throw new RuntimeException(e);
		}
    }
    /**
     * 批量上传文件
     */
    public static void uploadFile(String basePath,List<FileParam> fileParamList) {
    	try{
		if(CollectionUtil.isNotEmpty(fileParamList)){
			for (FileParam fileParam : fileParamList) {
				uploadFile(basePath, fileParam);
			}
		}
    	}catch (Exception e) {
			LOGGER.error("upload file failure",e);
			throw new RuntimeException(e);
		}
	}
}
