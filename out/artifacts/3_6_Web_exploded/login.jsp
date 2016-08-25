<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
	<h4>用户登录</h4>
	<form method="post" action="login_do.jsp">
		<p>用户名： <input name="name" type="text" id="name" style="width: 120px"></p>
		<p>密　码： <input name="pwd" type="password" id="pwd" style="width: 120px"></p>
		<input type="submit" value="提交">
	</form>
</body>
</html>