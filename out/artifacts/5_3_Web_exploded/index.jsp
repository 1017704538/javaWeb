<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加人员信息</title>
<script type="text/javascript">
	function check(form){
		with(form){
			if(name.value == ""){
				alert("姓名不能为空！");
				return false;
			}
			if(add.value == ""){
				alert("家庭住址不能为空！");
				return false;
			}
		}
	}
</script>
<style type="text/css">
td{
	font-size: 14px;
}
</style>
</head>
<body>
	<form action="AddServlet" method="post" onsubmit="return check(this);">
		<table align="center" width="400">
			<tr>
				<td align="center" colspan="2">
					<h2>添加人员信息</h2>
					<hr>
				</td>
			</tr>
			<tr>
				<td align="right">姓　　名：</td>
				<td>
					<input type="text" name="name">
				</td>
			</tr>
			<tr>
				<td align="right">性　　别：</td>
				<td>
					<input type="radio" name="sex" value="男" checked="checked">男
					<input type="radio" name="sex" value="女">女
				</td>
			</tr>
			<tr>
				<td align="right">家庭住址：</td>
				<td>
					<textarea rows="5" cols="30" name="add"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="添　加">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>