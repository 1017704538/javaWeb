<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set name="user" value="%{#request.userInfo}"/>
<title>相片详细查询</title>
</head>
<body>
&nbsp;
<table width="210" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="120"><img src="<%=request.getParameter("address")%>"></td>
  </tr>
</table>
</body>
</html>
