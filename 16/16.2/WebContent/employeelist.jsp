<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lh.dao.EmployeeDao" %>
<%@ page import="com.lh.model.Employee" %>
<%@ page import="java.util.*" %>

<%
	String currPage = request.getParameter("currentPage"); //获得当前页的值
	String pageFlag = request.getParameter("pageFlag");//单击的是上一页还是下一页	
	int currentPage =1; 			//当前页
	int pageSize = 5 ;				//每页显示数据行数
	int rows = 0;					//数据总数
	int pageCount = 0 ;				//数据页数
	int firstResult = 0; 			//上一页数据的开始位置
	if(currPage!=null){
		currentPage = Integer.parseInt(currPage);
	}
	if(pageFlag !=null){
		if(pageFlag.equals("before")){//单击上一页时，当前值-1
			currentPage--;
		}
		if(pageFlag.equals("next")){//单击下一页时，当前值+1
			currentPage++;
		}
	}
	rows = EmployeeDao.getInstance().selectEmpCount();//从数据库中查询数据总数	
	pageCount = ((rows + pageSize) - 1) / pageSize;	//计算数据总页数
	firstResult = (currentPage-1) * pageSize;		//计算上一页数据的开始位置
	//判断上一页和下一页边界
	boolean hasBefore = false; 		//是否有上一页
	boolean hasNext =false ;  		//是否有下一页
	if(rows<=1){ 					//如果数据总数少于一行
		hasBefore = false ;
		hasNext = false;
	}else if(currentPage==1){		//如果当前页是第一页
		hasBefore =false;
		hasNext =true;
	}else if(currentPage == pageCount){	 //如果当前页是最后一页
		hasBefore = true;
		hasNext =false ;
	}else{						
		hasBefore =true ;
		hasNext =true ;
	}
	List<Employee> empList = EmployeeDao.getInstance().selectEmp(firstResult ,pageSize);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工信息页</title>
<style type="text/css">
<!--
.fontStyle1 {
	font-size: 15px;
	line-height: 23px;
	color: #000000;
}
.fontStyle2 {
	font-size: 12px;
	line-height: 23px;
	color: #666666;
}
-->
</style>
</head>
<body >
	<table align="center" width="800" height="617" border="0" background="bg.JPG">	
		<tr>
			<td colspan="8" height="195"></td>
		</tr>
		<tr class="fontStyle1" height="30">
			<td width="180"></td>
			<td colspan="7" align="center" bgcolor="#C5EAFA">员工信息列表</td>	
		</tr>
		<tr class="fontStyle2" >
			<td width="180"></td>
			<td>编号</td>
			<td>姓名</td>
			<td>性别</td>
			<td>年龄</td>
			<td>职务</td>
			<td>部门</td>
			<td>住址</td>
		</tr>	
	<%
		if(empList!=null&&empList.size()>0){
			for(Employee emp :empList){%>
		<tr class="fontStyle2">	
			<td width="180"></td>
			<td><%=emp.getEmpId() %></td>
			<td><%=emp.getEmpName() %></td>
			<td><%=emp.getEmpSex() %></td>
			<td><%=emp.getEmpAge() %></td>
			<td><%=emp.getDuty() %></td>
			<td><%=emp.getDept() %></td>
			<td><%=emp.getAddress() %></td>	
		</tr>
		<%}
		}
	%>		
		<tr class="fontStyle2">
			<td width="180"></td>
			<td colspan="7" align="center">
				共[<%=rows %>]条记录
				<%if(hasBefore){%>				
					<a href="employeelist.jsp?currentPage=1">首页</a>
					<a href="employeelist.jsp?currentPage=<%=currentPage%>&pageFlag=before">上一页</a>
				<%} else {
				%>
				<a>首页</a>&nbsp;&nbsp;<a>上一页</a>
				<%}if(hasNext){%>
				<a href="employeelist.jsp?currentPage=<%=currentPage%>&pageFlag=next">下一页</a>
				<a href="employeelist.jsp?currentPage=<%=pageCount%>">尾页</a>
				<%} else {%>
				<a>下一页</a>&nbsp;&nbsp;<a>尾页</a>
				<%}%>
				当前为[<%=currentPage%>/<%=pageCount %>]页
			</td>
		</tr>
		<tr>
			<td colspan="8" height="45"></td>
		</tr>
	</table>
</body>
</html>