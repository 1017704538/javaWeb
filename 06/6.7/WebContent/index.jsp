<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String[] arr={"足球","篮球","排球","乒乓球"}; 
	request.setAttribute("arrBall",arr);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示数组中的数据</title>
</head>
<body>
<c:forEach items="${requestScope.arrBall}" var="ball" varStatus="id">
	${id.index }&nbsp;${ball}&nbsp;&nbsp;
</c:forEach>
</body>
</html>