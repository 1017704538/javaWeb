<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// 获取用户名
	String username = request.getParameter("username");
	// 获取密码
	String password = request.getParameter("password");
	// 判断用户名与密码的值是否为"admin"
	if("admin".equals(username) && "admin".equals(password)){
		// 登录成功
		out.print("<h3>恭喜，登录成功！</h3>");
	}else{
		// 登录失败
		out.print("<h3>对不起，登录失败！</h3>");
	}
%>
</body>
</html>