<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import=" java.util.*,com.lh.dao.EmployeeDao" %>
<%@ page language="java" import="com.lh.model.Employee" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table >
		<tr>
			<td colspan="5" align="center"><h3>员工信息表</h3></td>
		</tr>
		<tr align="center">
			<td>员工姓名</td>
			<td>员工性别</td>
			<td>员工年龄</td>
			<td>员工职务</td>
			<td></td>
		</tr>
		<%List<Employee> empList = EmployeeDao.getInstance().selectEmployee();
			if(empList!=null&&empList.size()>0){
				for(Employee  emp:empList){%>	
		<tr align="center">
			<td><%=emp.getEmpName() %></td>
			<td><%=emp.getEmpSex() %></td>
			<td><%=emp.getEmpAge() %></td>
			<td><%=emp.getEmpDuty() %></td>
			<td><a href="update.jsp?id=<%=emp.getEmpId() %>">修改</a></td>
		</tr>
		<%}}%>
	</table>
</body>
</html>