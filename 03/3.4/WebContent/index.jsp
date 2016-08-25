<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.Enumeration"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>application实现网页计数器</title>
</head>
<body>
<%
	// 获取application中存放 的visitorCount值
	Integer visitorCount = (Integer) application.getAttribute("visitorCount");
	// 判断visitorCount是不为空
	if (visitorCount == null) {
		visitorCount = 1;
	}else{
		visitorCount++; // 来访数量自增
	}
	// 将来访数量保存到application
	application.setAttribute("visitorCount", visitorCount);
%>
<h3>您是本站的第 <%=visitorCount %> 位访客！</h3>
</body>
</html>