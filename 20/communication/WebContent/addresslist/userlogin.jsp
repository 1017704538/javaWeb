<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/Untitled-4.css" rel="stylesheet" type="text/css" />
<title>用户注册</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/addresslist/js/request.js"></script>
<script type="text/javascript">
	var opinionRequest=false;
	
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
		if(pwd!=null&&pwd1!=""){
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
			var name=document.getElementById("username").value;
			var url= document.getElementById("path").value+"/UserServlet";	//服务器地址
			var param = "action=checkName&name="+name;						//请求参数
		    opinionRequest=httpRequest("post",url,true,callbackFunc,param);
		}
	}
	//响应的回调函数 
	
	function callbackFunc(){
		if( opinionRequest.readyState==4 ){ 
     	 	if( opinionRequest.status == 200 ){
         		var checkName=opinionRequest.responseXML.getElementsByTagName("checkName");
         		var iteranceName=opinionRequest.responseXML.getElementsByTagName("iteranceUserName");
         		if(typeof(checkName)!="undefined"&& checkName.length>0){   
         			
         			document.getElementById("loginform").action="<%=request.getContextPath()%>"+"/UserServlet?action=login";
	         		opinionRequest=false;
	         		document.getElementById("loginform").method="post";
	         		document.getElementById("loginform").submit();	
         		}
         		if(typeof(iteranceName)!="undefined"&& iteranceName.length>0){
	         		alert("用户名已存在，请重新输入！");
	         		document.getElementById("username").focus();
	         		opinionRequest=false;
	         		return false;
         		}
			}
		}
	}
</script>
</head>
<body bgcolor="#FFFFFF">
<form name="loginform" id="loginform" method="post">
	<input type="hidden" name="userid" id="userid" />
	<input type="hidden" name="path" id="path" value=<%=request.getContextPath() %> />
	<table width="1000" border="0" align="center" cellpadding="0" cellspacing="0" id="__01">
		<tr>
			<td height="124" colspan="4"><img src="images/zhuce_01.gif" width="1000" height="124" /></td>
		</tr>
		<tr>
		  <td height="61" colspan="4"><img src="images/yonghuzhucexiaoren.gif" width="1000" height="61" /></td>
	  </tr>
		<tr>
			<td width="12" height="372" align="left" valign="top" background="images/zuoshuxian.gif" bgcolor="#FFFFFF">&nbsp;</td>
		    <td width="596" height="372" align="center" valign="top" bgcolor="#FFFFFF"><table width="572" height="357" border="0" cellspacing="0">
	          <tr>
	            <td width="153" align="right" class="td">用 &nbsp;户 &nbsp;名：</td>
	            <td colspan="4" align="left"><input name="username" id="username"  type="text" class="01input" /></td>
	            <td width="205" align="left" class="hongxing">*</td>
	          </tr>
	          <tr>
	            <td align="right" class="td">密 &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;码：</td>
	            <td colspan="4" align="left"><input name="userpwd" id="userpwd" type="password" class="01input" /></td>
	            <td align="left"><span class="hongxing">*</span></td>
	          </tr>
	          <tr>
	            <td align="right" class="td">确认密码：</td>
	            <td colspan="4" align="left"><input name="userpwd1" id="userpwd1" type="password" class="01input" /></td>
	            <td align="left"><span class="hongxing">*</span></td>
	          </tr>
	          <tr>
	            <td align="right" class="td">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
	            <td width="25" align="left"><input  name="sex" id="man" value="m" type="radio" class="danxuananniu" /></td>
	            <td width="69" align="left">男</td>
	            <td width="25"><input name="sex"  id="woman" value="f" type="radio" class="danxuananniu" /></td>
	            <td width="83" align="left">女</td>
	            <td align="left">
	          </tr>
	          <tr>
	            <td align="right" class="td">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</td>
	            <td colspan="4" align="left"><input name="age" id="age" type="text" class="01input" /></td>
	            <td align="left">
	          </tr>
	          <tr>
	            <td>&nbsp;</td>
	            <td height="40" colspan="3" align="left"><img style="cursor:pointer" src="images/tijiao.gif" width="75" height="29" border="0" onclick="login()"/></td>
	            <td align="left"><img style="cursor:pointer" src="images/guanbi.gif" width="75" height="29" border="0" onclick="window.close()"/></td>
	            <td>&nbsp;</td>
	          </tr>
	        </table></td>
		    <td width="382" height="372" bgcolor="#FFFFFF">&nbsp;</td>
		    <td width="10" height="372" align="right" background="images/youshuxian.gif" bgcolor="#FFFFFF">&nbsp;</td>
		</tr>
		<tr>
			<td width="12" background="images/zhuce_04.gif">
				<img id="zhuce_03" src="images/zhuce_03.gif" width="12" height="99" alt="" /></td>
			<td colspan="2" align="right" valign="top" background="images/zhuce_04.gif"><img src="images/zhuce_08.gif" width="374" height="45" /></td>
			<td width="10" align="right" background="images/zhuce_04.gif">
				<img id="zhuce_06" src="images/zhuce_06.gif" width="10" height="99" alt="" /></td>
		</tr>
	</table>
</form>
</body>
</html>