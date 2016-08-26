<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>	     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数字格式化</title>
</head>
<body>
<fmt:setLocale value="zh_CN"/>
数字格式：<fmt:formatNumber value="12345.12345"/><br>
百分数：<fmt:formatNumber value="12345.12345" type="percent"/><br>
货币格式：<fmt:formatNumber value="12345.12345" type="currency"/>
</body>
</html>