<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>使用文件包含include指令</title>
</head>
<body style="margin:0px;">
<div align="center">
  <h1>jsp 的include动作指令</h1>
  这样的include是分开编译 3个class文件<br>
<jsp:include page="top.jsp"></jsp:include>
<table width="780" height="205" border="0" cellpadding="0" cellspacing="0" background="images/center.jpg">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<jsp:include page="copyright.jsp"></jsp:include>
</div>
</body>
</html>
