package org.smart4j.framework;

import javax.servlet.annotation.WebServlet;

/**
 * 请求转发器
 * @author taojiajun
 *
 */
@WebServlet(urlPatterns="/*",loadOnStartup=0)//当值为0或者大于0时，表示容器在应用启动时就加载这个servlet；
//拦截/* 所有请求
public class DispatcherServlet {

}
