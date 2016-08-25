<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
if(request.getParameter("Submit")!=null){
	response.setContentType("application/msword;charset=UTF-8");	//设置响应的内容类型
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>将网页保存为Word文档</title>
</head>
<body>
<form name="form1" method="post" action="">
<p>用户名： <input	name="name" type="text" id="name" style="width: 120px"></p>
<p>密　码： <input name="pwd" type="password" id="pwd" style="width: 120px"></p>
<input type="submit" name="Submit" value="保存为word">
</form>
</body>
</html>