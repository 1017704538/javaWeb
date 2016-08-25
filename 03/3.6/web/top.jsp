<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
span {
	background-color: blue;
	color: white;
	text-align: center;
	font-size: 12px;
	padding: 3px;
	font-weight: bold;
}
</style>
<%
	// 从session中获取user
	String user = (String)session.getAttribute("user");
	// 判断用户会话是否有效
	if(user == null || "".equals(user)){
		response.sendRedirect("login.jsp");	
	}
%>
<div>
	<span>学生管理</span>
	<span>班级管理</span>
	<span>课程管理</span>
	<span>学籍管理</span>
	<span>教师管理</span>
</div>
<br>
<b><%=user %></b> ，您好，欢迎使用学生管理系统！<br>
<a href="login_do.jsp">退出</a>

