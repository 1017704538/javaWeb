<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.lyq.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>用户信息管理</title>
<style type="text/css">
td{
	font-size: 14px;
}
</style>
</head>
<body>
	<table align="center" border="1" width="400">
		<tr>
			<td align="center" colspan="3">
				<h2>用户信息管理</h2>
			</td>
		</tr>
		<tr align="center" style="font-weight: bold;">
			<td>姓名</td>
			<td>性别</td>
			<td>家庭住址</td>
		</tr>
		<%
			List<User> list = (List<User>)application.getAttribute("users");
			if(list != null){
				for(User user : list){
		%>
			<tr align="center">
				<td><%=user.getName()%></td>
				<td><%=user.getSex()%></td>
				<td><%=user.getAdd()%></td>
			</tr>
		<%
				}
			}
		%>
		<tr>
			<td align="center" colspan="3">
				<a href="index.jsp">继续添加</a>
			</td>
		</tr>
	</table>
</body>
</html>
