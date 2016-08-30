<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>用户注册</title>
</head>
<body>

	<form  id="registerform" method="post" action="registerdo.jsp">	
		<table align="center" >
			<tr>
				<td>用户名：</td>
				<td>
					<input type="text" name="name" id="name" /><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>密码：</td>
				<td>
					<input type="password" name="pwd" id="pwd" /><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					<input type="radio" name="sex" id="man" value="m" />男
					<input type="radio" name="sex"  id="woman" value="f" />女
				</td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td>
					<input type="text" name="age" id="age"/>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="注册" />
				</td>
				<td>
					<input type="button" value="关闭" onclick="window.close()"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>