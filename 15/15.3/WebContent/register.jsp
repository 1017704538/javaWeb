<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/css.css" rel="stylesheet" type="text/css" />
<title>用户注册</title>
<script type="text/javascript">
	function checkInput(){
		var name=document.getElementById("name").value;
		var pwd=document.getElementById("pwd").value;
		var pwd1=document.getElementById("pwd1").value;
		var age=document.getElementById("age").value;
		var man=document.getElementById("man");
		var woman=document.getElementById("woman");
		var validateCode=document.getElementById("code").value;
		//判断用户名
		if(name==null||name==""){
			alert("请输入用户名！");
			document.getElementById("name").focus();
			return false;
		}
		//判断密码
		if(pwd==null||pwd==""){
			alert("请输入密码！");
			document.getElementById("pwd").focus();
			return false;
		}
		else{
			if(pwd.length<6||pwd.length>16){
				alert("密码格式为6-16位！");
				return false;
			}
		}
		//判断确认密码
		if(pwd1==null||pwd1==""){
			alert("请输入确认密码！");
			document.getElementById("pwd1").focus();
			return false;
		}
		if(pwd!=""&&pwd1!=""){
			if(pwd!=pwd1){
				alert("确认密码输入有误！");
				document.getElementById("pwd1").focus();
				return false;
			}
		}
		//判断性别
		if(!man.checked&&!woman.checked){
			alert("请选择性别！");
			return false;
		}
		//判断年龄
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
		//判断验证码
		if(validateCode==""||validateCode==null){
			alert("请输入验证码！");
			document.getElementById("code").focus();
			return false;
		}
		return true;
	}
	//提交表单
	function register(){
		if(checkInput()){
			document.forms.registerform.action="deal.jsp";    
    		document.forms.registerform.method="post";
    		document.forms.registerform.submit();	
		}
	}
	/**
	*更换验证码
	*/
	
	function changeCode(){
		document.getElementById("imgcode").src="ValidateCodeServlet?nocache="+new Date().getTime();
	}
</script>
</head>
<body background="login.jpg">
	<table border="0" height="130"  align="center">
		<tr>
			<td></td>
		</tr>
	</table>
	<form  id="registerform" method="post" >	
		<table align="center" >
			<tr>
				<td colspan="2" align="center"><h4><font color="#666666">用户注册</font></h4></td>
			</tr>
			<tr class="ct12">
				<td  >用户名：</td>
				<td >
					<input type="text" name="name" id="name" />
					<font color="red">*</font>
				</td>
			</tr>
			<tr class="ct12">
				<td>密码：</td>
				<td>
					<input type="password" name="pwd" id="pwd" /><font color="red">*</font>
				</td>
			</tr >
			<tr class="ct12">
				<td>确认密码：</td>
				<td>
					<input type="password" name="pwd1" id="pwd1" /><font color="red">*</font>
				</td>
			</tr>
			<tr class="ct12">
				<td>性别：</td>
				<td>
					<input type="radio" name="sex" id="man" value="m" />男
					<input type="radio" name="sex"  id="woman" value="f" />女
				</td>
			</tr>
			<tr class="ct12">
				<td>年龄：</td>
				<td><input type="text" name="age" id="age"/></td>
			</tr>
			
			<tr class="ct12">
				<td>验证码：</td>
				<td >
					<img title="看不清，点击更换验证码" style="cursor:pointer" id="imgcode" alt="" src="ValidateCodeServlet" onclick="changeCode()" >
				</td>
			</tr>
			<tr class="ct12">
				<td >输入验证码：</td>
				<td><input type="text" name="code" id="code" /></td>
			</tr>
			<tr class="ct12">
				<td colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="注 册" onclick="register()" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" value="重 置" />
				</td>
				
			</tr>
		</table>
	</form>
</body>
</html>