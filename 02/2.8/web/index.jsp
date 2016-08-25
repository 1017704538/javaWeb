<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Random"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>幸运大抽奖</title>
</head>
<body>
<div align="center">
	<h1>代码中可以插入html文档</h1>
<%
	// 实例化Random对象
	Random random = new Random();
	// 通过随机数生成器获取int型随机数值
	int rundomNum = random.nextInt();
	// 判断随机数是否是8的倍数
	if (rundomNum % 8 == 0) {
%> <!-- 是8的倍数则中奖 --> <img src="images/yes.jpg"> <%
 	} else {
 %> <!-- 不是8的倍数没有中奖 --> <img src="images/no.jpg"> <%
 	}
 %>
</div>
</body>
</html>