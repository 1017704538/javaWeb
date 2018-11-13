<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
<!--
.STYLE1 {
	font-size: 12px;
	line-height: 23px;
	color: #666666;
}
.STYLE2 {
	font-size: 12px;
	color: #666666;
}
-->
</style>
<script type="text/javascript">
	function checkInput(){
		var name=document.getElementById("username").value;
		var pwd=document.getElementById("userpwd").value;
		var pwd1=document.getElementById("userpwd1").value;
		var age=document.getElementById("age").value;
		var man=document.getElementById("man");
		var woman=document.getElementById("woman");
		if(name==null||name==""){
			alert("请输入用户名！");
			document.getElementById("username").focus();
			return false;
		}
		if(pwd==null||pwd==""){
			alert("请输入密码！");
			document.getElementById("userpwd").focus();
			return false;
		}
		else{
			if(pwd.length<6||pwd.length>16){
				alert("密码格式为6-16位！");
				return false;
			}
		}
		if(pwd1==null||pwd1==""){
			alert("请输入确认密码！");
			document.getElementById("userpwd1").focus();
			return false;
		}
		if(pwd!=""&&pwd1!=""){
			if(pwd!=pwd1){
				alert("确认密码输入有误！");
				document.getElementById("userpwd1").focus();
				return false;
			}
		}
		if(!man.checked&&!woman.checked){
			alert("请选择性别！");
			return false;
		}
		
		if(age!=""){
			if(age>150||age<=0){
				alert("年龄在1-150之间！");
				document.getElementById("age").focus();
				return false;
			}
			if(isNaN(age)){
				alert("年龄应为数字！");
				document.getElementById("age").focus();
				return false;
			}
		}
		return true;
	}
	//提交表单
	function login(){
		if(checkInput()){
			document.getElementById("flag").value="1";
			document.forms.loginform.action="saveuser.jsp";
    		document.forms.loginform.method="post";
    		document.forms.loginform.submit();;
		}
	}
</script>
</head>
<body >

<form name="loginform" id="loginform" method="post">
	<input type="hidden" id="flag" name="flag">
	<table align="center" background="bg.bmp" width="779" height="511" border="0">
		<tr height="210">
			<td ></td>
			<td ></td>
			<td ></td>
		</tr>
		<tr class="STYLE1" valign="top" >
			<td width="110"></td>
			<td align="right">用户名：</td>
			<td >
				<input type="text" name="username" id="username" class="STYLE2"/><font color="red">*</font>
			</td>
		</tr>
		<tr class="STYLE1" valign="top">
			<td width="110"></td>
			<td align="right">密码：</td>
			<td>
				<input type="password" name="userpwd" id="userpwd" class="STYLE2"/><font color="red">*</font>
			</td>
		</tr>
		<tr class="STYLE1" valign="top">
			<td width="110"></td>
			<td align="right">确认密码：</td>
			<td>
				<input type="password" name="userpwd1" id="userpwd1"  class="STYLE2"/><font color="red">*</font>
			</td>
		</tr>
		<tr class="STYLE1" valign="top">
			<td  width="110"></td>
			<td align="right">性别：</td>
			<td>
				<input type="radio" name="sex" id="man" value="m"  />男
				<input type="radio" name="sex"  id="woman" value="f" />女
			</td>
		</tr>
		<tr class="STYLE1" valign="top">
			<td  width="110"></td>
			<td align="right">年龄：</td>
			<td><input type="text" name="age" id="age" class="STYLE2"/></td>
		</tr>
		<tr class="STYLE1" valign="top">
			<td width="110"></td>
			<td  align="left" colspan="2">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="注 册" onclick="login()"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" value="重 置" />
			</td>
			
		</tr>
		<tr height="40">
			<td ></td>
			<td ></td>
			<td ></td>
		</tr>
	</table>
</form>
</body>
</html>