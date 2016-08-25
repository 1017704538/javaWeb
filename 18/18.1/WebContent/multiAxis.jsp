<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.hrl.report.jfreechart.MultiAxis"%><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
    MultiAxis axis = new MultiAxis();
%>
<img alt="" src="chart.do?filename=<%=axis.getFileName()%>">
</body>

