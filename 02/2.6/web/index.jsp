<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>

<body>
您现在位置为index.jsp！
<h1>jsp：forward</h1>
jsp：forward 标签使用，跳转后地址不会变
<h1>jsp：param</h1>
jsp：param 标签，类似javaBean 带有name以及value 与url+?xxx=xxx形式相同
<jsp:forward page="login.jsp" ></jsp:forward>
</body>
</html>