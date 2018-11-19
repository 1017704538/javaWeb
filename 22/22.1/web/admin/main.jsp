<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">   
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>明日博客地带-后台首页</title>
</head>
<body>
<jsp:include page="top.jsp" flush="true"/>
<table width="900" height="303" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="F7FAFF">
  <tr align="center">
    <td>欢迎进入明日博客地址后台管理</td>
  </tr>
</table>
<jsp:include page="down.jsp" flush="true"/>
</body>
</html>
