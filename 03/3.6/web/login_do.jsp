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
	// session中是否保存了用户信息
	if(session.getAttribute("user") != null){
		// 清空session中保存的数据
		session.invalidate();
	}
	// 获取用户名
	String name = request.getParameter("name");
	// 获取密码
	String pwd = request.getParameter("pwd");
	// 判断用户名与密码是否正确
	if("admin".equals(name) && "admin".equals(pwd)){
		// 将用户保存到session中
		session.setAttribute("user",name);
	}
	// 请求重定向到index.jsp页面
	response.sendRedirect("index.jsp");
%>
</body>
</html>