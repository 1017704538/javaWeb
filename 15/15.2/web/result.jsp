<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 	String q1 = new String(request.getParameter("question1").getBytes("ISO-8859-1"),"UTF-8");
	String q2 = new String(request.getParameter("question2").getBytes("ISO-8859-1"),"UTF-8");
	String q3 = new String(request.getParameter("question3").getBytes("ISO-8859-1"),"UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center">
		<tr>
			<td colspan="2"><h3>问卷调查结果</h3></td>
		</tr>
		<tr>
			<td>问题1答案：</td>
			<td>
				<%=q1 %>
			</td>
		</tr>
		<tr>
			<td>问题2答案：</td>
			<td>
				<%=q2 %>
			</td>
		</tr>
		<tr>
			<td>问题3答案：</td>
			<td>
				<%=q3 %>
			</td>
		</tr>
	</table>
</body>
</html>