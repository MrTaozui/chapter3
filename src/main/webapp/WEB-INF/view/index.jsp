<%--
  Created by IntelliJ IDEA.
  User: taojiajun
  Date: 2018/8/22
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="/security" %>
<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<%--<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>首页</h1>

<%--<shiro:guest>
    <p>身份：游客</p>
    <a href="<c:url value="/login/"/>">登陆</a>
    <a href="<c:url value="/register"/>">注册</a>
</shiro:guest>
<shiro:user>
    <p>身份：<shiro:principal/></p>
    <a href="<c:url value="/space"/>">空间</a>
    <a href="<c:url value="/logout"/>">退出</a>
</shiro:user>--%>
    <security:guest>
        <p>身份：游客</p>
        <a href="<c:url value="/login/"/>">登陆</a>
    </security:guest>
    <security:user>
        <p>身份：<security:principal/></p>
        <ul>
            <li><a href="${BASE/customer}">客户管理</a></li>
        </ul>
        <a href="<c:url value="/logout"/>">注销</a>
    </security:user>
</body>
</html>
