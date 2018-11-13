
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lh.dao.*" %>
<%@ page import ="java.net.URLDecoder" %>
<%
	String databaseName = request.getParameter("dbName");							//数据库名
	String path = request.getParameter("filePath");									//备份路径
	String newPath = URLDecoder.decode(path,"UTF-8");								//字符串解码
	boolean bool = LoadDataBaseDao.getInstance().loadData(databaseName,newPath);	//调用方法执行恢复
	if(bool){%>
		<script type="text/javascript">
			alert("数据库<%=databaseName%>恢复成功！");
			window.location.href ="load.jsp";
		</script>
	<%}else{%>
		<script type="text/javascript">
			alert("数据库恢复失败！");
			window.location.href ="load.jsp";
		</script>
	<%}	
%>
