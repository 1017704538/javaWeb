<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生详细信息</title>
<style type="text/css">
	table{ 
		border:2;
		border-collapse:collapse;
	}
	td{
		font:normal 12px/17px Arial;
		padding:2px;
	}
	th{
		font:bold 12px/17px Arial;
		text-align:left;
		padding:4px;
		border-bottom:2px solid #333;
	}
	body{
		font-size:14px;
	}
</style>
</head>
<body>
<c:if test="${studentVO!=null}">
<table border="2">
	<tr>
		<th colspan="4" align="center">学号<b>${studentVO.studentCode}</b>&nbsp;&nbsp;学生详细信息</th>
	</tr>
	<tr>
		<td width="70px">姓名</td>
		<td width="100px">${studentVO.name}</td>
		<td width="70px">学号</td>
		<td width="100px">${studentVO.studentCode}</td>
	</tr>
	<tr>
		<td width="70px">性别</td>
		<td>${studentVO.sex}</td>
		<td width="70px">班级</td>
		<td>${studentVO.classes}</td>
	</tr>
	<tr>
		<td>联系电话</td>
		<td colspan="3">${studentVO.phone}</td>
	</tr>
	<tr>
		<td>所在学院</td>
		<td colspan="3">${studentVO.institute}</td>
	</tr>
	<tr>
		<td>所在系别</td>
		<td colspan="3">${studentVO.department}</td>
	</tr>
	<tr>
		<td>家庭住址</td>
		<td colspan="3">${studentVO.address}</td>
	</tr>
</table>
</c:if>
<c:if test="${studentVO == null && id!=null}">
	<b>对不起，暂无学号为<font color="blue">“${id}”</font>的学生信息！</b>
</c:if>
</body>
</html>