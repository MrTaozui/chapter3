package org.smart4j.framework.util;
/**
 * 文件操作工具类
 * @author taojiajun
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import  org.apache.commons.io.FilenameUtils;

public final class FileUtil {
	private static final Logger LOGGER=LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * 获取真实文件名  （自动去掉文件路径）
	 */
	public static String getRealFileName(String fileName){
		return FilenameUtils.getName(fileName);
	}
	/**
	 * 创建文件
	 */
	public static File createFile(String filePath){
		File file;
		try {
		file=new File(filePath);
		File parentDir=file.getParentFile();
		if(!parentDir.exists()){		
				FileUtils.forceMkdir(parentDir);////创建一个目录，可以递归创建，只要不为null
			
		}
		} catch (IOException e) {
			LOGGER.error("create file failure",e);
			throw new RuntimeException(e);
		}
		return file;
	}

}
