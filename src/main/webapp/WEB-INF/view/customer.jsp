<%--
  Created by IntelliJ IDEA.
  User: taojiajun
  Date: 2018/8/3
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<c:out value="${BASE}"/>
<html>
<head>
    <title>客户管理</title>
</head>
<body>
<h1>客户列表</h1>

<table>
	<tr>
		<th>客户名称</th>
		<th>联系人</th>
		<th>电话号码</th>
		<th>邮箱地址</th>
		<th>操作</th>
	</tr>
<c:forEach var="customer" items="${customerList}">
	<tr>
		<td>${customer.name}</td>
		<td>${customer.contact}</td>
		<td>${customer.telephone}</td>
		<td>${customer.email}</td>
	</tr>
</c:forEach>
</table>
</body>
</html>
