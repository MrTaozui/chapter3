package org.smart4j.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CodeUtil;
import org.smart4j.framework.util.JsonUtil;
import org.smart4j.framework.util.ReflectionUtil;
import org.smart4j.framework.util.StreamUtil;
import org.smart4j.framework.util.StringUtil;

/**
 * 请求转发器
 * 来处理所有的请求 从controllerHelper中获取Acton方法 
 * 然后使用反射技术调用Action方法 传入参数 拿到返回值并判断返回值的类型 进行
 * 相应的处理
 * @author taojiajun
 *
 */
@WebServlet(urlPatterns="/*",loadOnStartup=0)//当值为0或者大于0时，表示容器在应用启动时就加载这个servlet；
//拦截/* 所有请求
public class DispatcherServlet extends HttpServlet{
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {//项目启动的时候运行
		//初始化相关Helper类
		HelperLoder.init();
		//获取ServletContext对象  (用于注册Servlet)  因为拦截了 所有的请求 所以把原生的给注入进去 成自己的路径下的
		ServletContext servletContext=servletConfig.getServletContext();
		//注册处理JSP的Servlet
		ServletRegistration jspServlet=servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");//设置请求的路径
		//注册处理静态资源默认的Servlet
		ServletRegistration defaultServlet=servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssertPath()+"*");
	}
	// 要访问servlet的时候 执行
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求方法与请求路径
		String requestMethod=request.getMethod().toLowerCase();//get  post
		String requestPath=request.getPathInfo();
		//获取Action处理器
		Handler handler=ControllerHelper.getHandler(requestMethod, requestPath);
		if(handler!=null){
			//获取Controller 类及其Bean实例
			Class<?> controllerClass=handler.getControllerClass();
			Object controllerBean=BeanHelper.getBean(controllerClass);
			//创建请求参数对象
			Map<String, Object> paramMap=new HashMap<String, Object>();
			Enumeration<String>paramNames=request.getParameterNames();//获取参数 name
			while(paramNames.hasMoreElements()){
				String paramName=paramNames.nextElement();
				String paramValue=request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			String body=CodeUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
			if(StringUtil.isNotEmpty(body)){
				String params[] =StringUtil.splitString(body, "&");
				if(ArrayUtil.isNotEmpty(params)){
					for (String param : params) {
						String[] array=StringUtil.splitString(param, "=");
						if(ArrayUtil.isNotEmpty(array)&&array.length==2){
							String paramName=array[0];
							String paramValue=array[1];
							paramMap.put(paramName, paramValue);
						}
					}
				}
				
			}
			Param param=new Param(paramMap);
			Object result;
			//调用 Action方法
			Method actionMethod=handler.getActionMethod();//获取 Controller 中的 Action注解方法
			if(param.isEmpty()){
				result=ReflectionUtil.invokeMethod(controllerBean, actionMethod);
			}else{
			result=ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);//传入的 是Param 对象
			}
			//处理Action方法返回值
			if(result instanceof View){
				//返回jsp页面
				View view=(View)result;
				String path=view.getPath();
				if(StringUtil.isNotEmpty(path)){
					if(path.startsWith("/")){ //  "/xxx/xx"
						response.sendRedirect(request.getContextPath()+path);//  得到  /项目名称 +path 重定向
					}else{// "xxxxxxxxxxx"
						Map<String,Object> model=view.getModel();
						for(Map.Entry<String, Object>entry:model.entrySet()){
							request.setAttribute(entry.getKey(), entry.getValue());
						}
						request.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(request, response);
						//转发到  jsp路径下
					}
				}
			}else if(result instanceof Data){
				//返回JSON数据
				Data data=(Data)result;
				Object model=data.getModel();
				if(model!=null){
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					PrintWriter writer=response.getWriter();
					String json=JsonUtil.toJson(model);
					writer.write(json);
					writer.flush();
					writer.close();
				}
			}
		}
		
	}

}
