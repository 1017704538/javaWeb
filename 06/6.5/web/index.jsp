<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>时间格式化</title>
</head>
<body>
	<c:set var="date" value="<%=new Date()%>"/>
	<fmt:formatDate value="${date}" pattern="yyyy年MM月dd日  HH时mm分ss秒" type="date" dateStyle="full"/><br>
	<fmt:formatDate value="${date}" type="both" dateStyle="full" var="stringDate"/>
	<fmt:parseDate value="${stringDate}" type="both" dateStyle="full" var="parseDate"/>
	<c:out value="${parseDate}"/>
</body>
</html>