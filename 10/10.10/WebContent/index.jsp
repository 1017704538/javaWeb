<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;UTF-8">
<title>根据学号查询学生详细信息</title>
</head>
<body>
<form action="QueryStudents" method="post" target="student">
&nbsp;&nbsp;&nbsp;&nbsp; 请输入学号：<input type="text" name="id" value="" />
<input type="submit" value="查询" /></form>
<iframe frameborder="0" name="student" width="800px" height="500px"></iframe>
</body>
</html>