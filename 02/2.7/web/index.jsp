<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>输出九九乘法表</title>
</head>
<body>

<%
	// 声明保存九九乘法表的StringBuffer对象
	StringBuffer sb = new StringBuffer();
	// 连接生成九九乘法表的字符串
	for (int i = 1; i <= 9; i++) { // 外循环
		for (int j = 1; j <= i; j++) { // 内循环
			sb.append(j + "*" + i);
			sb.append("=");
			sb.append(j * i);
			sb.append("&nbsp;");// 加入空格符
		}
		sb.append("<br>"); // 加入换行符
	}
%>
<%-- 调用脚本输出变量sb中的字符串 --%>
<%=sb.toString()%>
</body>
</html>