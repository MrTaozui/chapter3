<%--
  Created by IntelliJ IDEA.
  User: taojiajun
  Date: 2018/8/3
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>客户管理 - 创建用户</title>
</head>
<body>
<h1>创建客户界面</h1>
<form id="customer_form" enctype="multipart/form-data">
<table>
<tr>
	<td>客户名称：</td>
	<td><input type="text" name="name" value="${customer.name}"/></td>
</tr>
<tr>
	<td>联系人：</td>
	<td><input type="text" name="contact" value="${customer.contact}"/></td>
</tr>
<tr>
	<td>电话号码：</td>
	<td><input type="text" name="telephone" value="${customer.telephone}"/></td>
</tr>
<tr>
	<td>邮箱地址：</td>
	<td><input type="text" name="email" value="${customer.email}"/></td>
</tr>
<tr>
	<td>照片：</td>
	<td><input type="file" name="photo" value="${customer.photo}"/></td>
</tr>
</table>
<button type="submit">保存</button>
</form>
<script src="${BASE}/js/jquery-3.3.1.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script>
	$.ajax({
		$('#customer_form').ajaxForm({
			type:'post',
			url:'${BASE}/customer',
			success: function(data){
				if(data){
					location.herf='${BASE}/customer'
				}
			}
		});
		
	});
</script>

</body>
</html>
