<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计算10的阶乘</title>
</head>
<body>

<%
	int num = 1;// 结果
	// 通过for循环计算10的阶乘
	for(int i=1; i<=10; i++){
		num *= i;
	}
	out.print("10的阶乘是：" + num);// 输出10的阶乘
%>
</body>
</html>