<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lh.dao.EmployeeDao" %>
<%@ page import="com.lh.model.Employee" %>
<%@ page import="java.util.*" %>
<%
	String currPage = request.getParameter("currentPage"); 	//获得当前页的值
	String pageFlag = request.getParameter("pageFlag");		//单击的是上一页还是下一页	
	int currentPage =1; 			//当前页
	int pageSize = 5 ;				//每页显示数据行数
	int rows = 0;					//数据总数
	int pageCount = 0 ;				//数据页数
	int firstResult = 0; 			//上一页数据的开始位置
	boolean hasBefore = false; 		//是否有上一页
	boolean hasNext = false ;  		//是否有下一页
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
%>
<%
	String conditionName="";
	String name = request.getParameter("name"); 				//获得姓名查询条件
	String selectResultFlag = request.getParameter("selectResultFlag");//获得是否在查询结果中搜索的标记
	if(selectResultFlag==null||selectResultFlag.equals("")){	//不在查询结果中搜索
		conditionName=name;
	}else{														//在查询结果中搜索
		if(name!=null&&!name.equals("")){
			conditionName = request.getParameter("conditionFlag")+","+name;//将之前记录的条件值与当前值组合
		}else{
			conditionName=request.getParameter("conditionFlag");//之前记录的条件值
		}	
	}
%>
<%	
	String flag = request.getParameter("flag");				//获得此标记判断是否是查询
	if(flag!=null&&flag.equals("select"))
		rows = EmployeeDao.getInstance().selectEmpCountByCondition(conditionName);	//根据条件查询数据总数	
	else
		rows = EmployeeDao.getInstance().selectEmpCount();	//查询所有数据总数
	
	pageCount = ((rows + pageSize) - 1) / pageSize;			//计算数据总页数
	firstResult = (currentPage-1) * pageSize;				//计算上一页数据的开始位置
	if(rows<=5){ 					//如果数据总数少于一行
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
	List<Employee> empList = null ;
	if(flag!=null&&flag.equals("select"))					//调用有条件的查询方法 
		 empList = EmployeeDao.getInstance().selectEmpByCondition(firstResult,pageSize,conditionName);
	else													//调用查询所有数据的方法
		 empList = EmployeeDao.getInstance().selectEmp(firstResult ,pageSize);
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
	
	color: #666666;
}
-->
</style>
<script type="text/javascript">
	function selectResult(){
		document.getElementById("selectResultFlag").value = "selectResult";
		document.forms.selectForm.action ="result.jsp?flag=select";
		document.forms.selectForm.method="post";
		document.forms.selectForm.submit();
	}
		
</script>
</head>
<body >
	<form id="selectForm" action="result.jsp?flag=select" method="post">
	<%if(conditionName!=null&&!conditionName.equals("")&&!conditionName.equals("null")){%>		
		<input type ="hidden" name="conditionFlag" id="conditionFlag" value="<%=conditionName%>" />
	<%}else{%>
		<input type ="hidden" name="conditionFlag" id="conditionFlag" value="" />
	<%}%>
		<input type="hidden" name="selectResultFlag" id="selectResultFlag" value="">
	<table align="center" width="800" height="617" border="0" background="bg.JPG">	
		<tr>
			<td colspan="8" height="170"></td>
		</tr>
		<tr class="fontStyle2" height="50" valign="bottom">
			<td width="180"></td>
			<td colspan="7">姓名：
			<%if(conditionName!=null&&!conditionName.equals("")&&!conditionName.equals("null")){%>		
				<input type ="text" class="fontStyle2" name="name" id="name" value="<%=conditionName%>" />
			<%}else{%>
				<input type ="text" class="fontStyle2" name="name" id="name" value="" />
			<%}%>
				<input type="submit" value="查 询" />
				<input type="button" value="在结果中搜索" onclick="selectResult()"/>
			</td>
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
		<tr class="fontStyle2" >
			<td width="180"></td>
			<td colspan="7" align="center" >
				共[<%=rows %>]条记录
				<%if(hasBefore){%>				
					<a href="result.jsp?currentPage=1&flag=<%=flag %>&selectResultFlag=<%=selectResultFlag %>&conditionFlag=<%=conditionName %>">首页</a>
					<a href="result.jsp?currentPage=<%=currentPage%>&pageFlag=before&flag=<%=flag %>&selectResultFlag=<%=selectResultFlag %>&conditionFlag=<%=conditionName %>">上一页</a>
				<%} else {
				%>
				<a>首页</a>&nbsp;&nbsp;<a>上一页</a>
				<%}if(hasNext){%>
				<a href="result.jsp?currentPage=<%=currentPage%>&pageFlag=next&flag=<%=flag %>&selectResultFlag=<%=selectResultFlag %>&conditionFlag=<%=conditionName %>">下一页</a>
				<a href="result.jsp?currentPage=<%=pageCount%>&flag=<%=flag %>&selectResultFlag=<%=selectResultFlag %>&conditionFlag=<%=conditionName %>">尾页</a>
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
	</form>
</body>
</html>