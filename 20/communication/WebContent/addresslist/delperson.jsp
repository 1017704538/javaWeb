<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lh.dao.*" %>
<%@ page import="com.lh.model.*" %>
<%@ page import="com.lh.service.*" %>
<%@ page import="java.util.*" %>

<%
	String allpid=request.getParameter("allpid");
	String pid[]=allpid.split(",");//分割编号的字符串 
	boolean res=false;
	for(int i=0;i<pid.length;i++){
		res=PersonListDao.getInstance().deletePersonInfoById(pid[i]);//调用Dao类的方法执行删除
	}
	if(res){%>
		<script type="text/javascript">
			window.location.href="personlist.jsp";//如果删除成功，刷新页面
		</script>
	<%}
%>
