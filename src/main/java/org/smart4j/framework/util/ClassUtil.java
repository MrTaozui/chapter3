package org.smart4j.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类操作工具类
 * @author taojiajun
 *
 */
public class ClassUtil {
	private static final Logger LOGGER=LoggerFactory.getLogger(CastUtil.class);
	
	/**
	 * 获取类加载器
	 */
	public static ClassLoader getClassLoder(){
		//当发布到tomcat之中的时候 用别的类加载器可能会导致 生成的class对象不一样
	/*	一个WEB程序，发布到Tomcat里面运行。
		首先是执行Tomcat org.apache.catalina.startup.Bootstrap类，这时候的类加载器是ClassLoader.getSystemClassLoader()。
		而我们后面的WEB程序，里面的jar、resources都是由Tomcat内部来加载的，所以你在代码中动态加载jar、资源文件的时候，首先应该是使用Thread.currentThread().getContextClassLoader()。
		如果你使用Test.class.getClassLoader()，可能会导致和当前线程所运行的类加载器不一致（因为Java天生的多线程）。
		Test.class.getClassLoader()一般用在getResource，因为你想要获取某个资源文件的时候，这个资源文件的位置是相对固定的。*/
		return Thread.currentThread().getContextClassLoader();
	}
	/**
	 * 加载类
	 */
	public static Class<?> loadClass(String className,boolean isInitialized){
		Class<?> cls;
		try {
		// className 参数名称 isInitialized是指Class被loading后是不是必须被初始化 使用哪个类加载器
			cls=Class.forName(className,isInitialized,getClassLoder());
		} catch (ClassNotFoundException e) {
			LOGGER.error("load class failure",e);
			throw new RuntimeException(e);
		}
		return cls;
	}
	/**
	 * 获取指定包名下的所有类   com.org....
	 */
	public static Set<Class<?>>getClassSet(String packageName){
		Set<Class<?>>classSet=new HashSet<Class<?>>();
		try {
			Enumeration<URL> urls=getClassLoder().getResources(packageName.replace(".", "/"));//  com/org/
			while(urls.hasMoreElements()){
				URL url=urls.nextElement();
				if(url!=null){
					/*URL可以分为如下几个部分。protocol://host:port/path?query#fragment
					protocol(协议)可以是 HTTP、HTTPS、FTP 和 File ,jar -> JarHandler，port 为端口号，path为文件路径及文件名。
					HTTP 协议的 URL 实例如:http://www.runoob.com/index.html?language=cn#j2se*/	
				String protocol=url.getProtocol();//文件的类型
					if(protocol.equals("file")){//如果是文件协议 
						String packagePath=url.getPath().replaceAll("%20", "");
						addClass(classSet, packagePath, packageName);//如果
					}else if(protocol.equals("jar")){//根据协议protocol不同，对应的URL的处理方式也是不同的，
						//streamHandler即：与当前URL对应的处理对象  jar -> JarHandler   jar文件比 zip文件多了个META-INF/MANIFEST.MF 文件
						JarURLConnection jarURLConnection=(JarURLConnection)url.openConnection();
						if(jarURLConnection!=null){
							JarFile jarFile=jarURLConnection.getJarFile();
							if(jarFile!=null){
								Enumeration<JarEntry> jarEntries=jarFile.entries();
								while(jarEntries.hasMoreElements()){
									JarEntry jarEntry=jarEntries.nextElement();
									String jarEntryName=jarEntry.getName();
									if(jarEntryName.endsWith(".class")){
										String className=jarEntryName.substring(0, jarEntryName.lastIndexOf("."))
										.replaceAll("/", ".");
										doAddClass(classSet, className);
									}
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error("get class set failure",e);
			throw new RuntimeException(e);
		}
		return classSet;
		
	}
	private static void addClass(Set<Class<?>> classSet,String packagePath,String packageName){
		File[] files=new File(packagePath).listFiles(new FileFilter() {	//返回 文件路径下的.class文件和文件夹		
			@Override
			public boolean accept(File file) {//定义返回的文件或文件夹
				return(file.isFile()&&file.getName().endsWith(".class"))||file.isDirectory();
			}
		});
		for(File file:files){
			String fileName=file.getName();//获取文件名
			if(file.isFile()){//如果 是class 文件 拼接全路径并加载这个class 类
				String className=fileName.substring(0, fileName.lastIndexOf("."));
				if(StringUtil.isNotEmpty(packageName)){
					className=packageName+"."+className;
				}
				doAddClass(classSet, className);//加载class 文件
			}else{//不是文件 即文件夹
				String subPackagePath=fileName;//子文件夹名字
				if(StringUtil.isNotEmpty(packagePath)){
					subPackagePath=packagePath+"/"+subPackagePath;//子文件夹路径
				}
				String subPackageName=fileName;
				if(StringUtil.isNotEmpty(packageName)){
					subPackageName=packageName+"."+subPackageName;
				}
				addClass(classSet, subPackagePath, subPackageName);//查找 子文件夹下的class 文件以及路径
			}
		}
	}
	private static void doAddClass(Set<Class<?>> classSet,String className){
		Class<?>cls =loadClass(className, false);//加载类
		classSet.add(cls);
	}
}
