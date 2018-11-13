<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page  import="java.util.*" %>

<%
	 String uName=null; //用户名
	 Cookie cookies[]=request.getCookies();//获得所有Cookie信息
	 if(cookies!=null&&cookies.length>0){
		  for(int i=0;i<cookies.length;i++){//循环Cookie数组，查找用户名的Cookie信息
			  if(cookies[i].getName().equals("LoginUserName")){//找到Cookie数组中存储用户名的Cookie
				  uName=cookies[i].getValue();
			 }
		 }
	 } 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function checkinput(){
		var name= document.getElementById("name").value;
		var pwd=document.getElementById("pwd").value;
		if(name==""||name==null){
			alert("用户名不能为空！");
			return false;	
		}
		if(pwd==""||pwd==null){
			alert("密码不能为空！");
			return false;
		}
		//判断用户名是否包含中文
		
		if(escape(name).indexOf("%u")<0){
				return true;
		}else{
			alert("用户名不允许包含中文字符！");
			document.getElementById("name").value="";
			document.getElementById("name").focus();
			return false;
		}
	
		if(name.length>10){
			alert("账号长度最大为10个字母！");
			return false;
		}
		if(pwd.length>16){
			alert("密码长度最大为16位字符！");
			return false;
		}
		return true;
	}
	
	function login(){
		if(checkinput()==true){
			document.forms.userform.action="/15.1/UserServlet";
			document.forms.userform.method="post";
			document.forms.userform.submit();
		}
	}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form id="userform" method="post">
	<table width="746"  border="0" align="center" cellpadding="0" cellspacing="0" id="__01">
		<tr>
			<td width="190"   background="images/index_02.png" >
			<table width="190"  border="0" cellpadding="0" cellspacing="0">
	          <tr>
	            <td width="30" rowspan="3">&nbsp;</td>
	            <td width="141" height="40">&nbsp;</td>
	            <td width="19" rowspan="3">&nbsp;</td>
	          </tr>
	          <tr>
	            <td >
	            <table width="133"  border="0" cellpadding="0" cellspacing="0">
	              <tr>
	                <td width="80" >
	                <font size="2px">账&nbsp;&nbsp;号：</font>
	                </td>
	                <td width="76">
	               	 <input name="name" id="name" type="text"  size="10"
	               	 	value="<%if(uName!=null&&uName!="") out.println(uName); else out.println("");%>"/>
	                </td>
	              </tr>
	              <tr>
	                <td ><font size="2px">密&nbsp;&nbsp;码：</font></td>
	                <td><input name="pwd"  id="pwd" type="password" size="10"></td>
	              </tr>
	              <tr>
	                <td  colspan="2"><img style="cursor:pointer" src="images/tj.gif"  onClick="login()"></td>
	              </tr>
	            </table></td>
	          </tr>
	          <tr>
	            <td >&nbsp;</td>
	          </tr>
	        </table></td>
			<td rowspan="2">
				<img src="images/index_03.png" width="556"  alt=""></td>
		</tr>
		<tr>
			<td>
				<img src="images/index_04.png" width="190"  alt=""></td>
		</tr>
		<tr>
			<td colspan="2">
				<img src="images/index_05.png" width="746"  alt=""></td>
		</tr>
  </table>
</form>
</body>
</html>