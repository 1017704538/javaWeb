<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/**
	*添加通讯信息的人的类别表单页，例如添加类别朋友、同学等
	*/
%>
<%@ page import="java.util.*,com.lh.model.*,com.lh.dao.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/Untitled-4.css" rel="stylesheet" type="text/css" />
<title>添加迅友类别</title>
<script type="text/javascript">
	function save(){
		if(checkinput()){
			alert("保存成功！");
			document.getElementById("form1").action="<%=request.getContextPath() %>"+"/PersonTypeServlet?action=add";
			document.getElementById("form1").method="post";
			document.getElementById("form1").submit();
			window.close();
		}
		
	}
	function checkinput(){
		var type=document.getElementById("type").value;
		if(type==null||type==""){
			alert("请输入类别名称！");
			document.getElementById("type").focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<form action="" id="form1" name="form1" >
	<table width="556" height="180" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#000000">
	  <tr>
	    <td><table width="556" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td height="34" background="images/tianjiatabletop.gif"><img src="images/addtype.gif" width="110" height="34" /></td>
	      </tr>
	      <tr>
	        <td height="130" align="center" valign="middle"><table width="417" height="50" border="0" cellpadding="0" cellspacing="0">
	          <tr>
	            <td width="91" align="right" class="indextable">输入类别名称：</td>
	            <td width="209">
	            <label>
	              <input name="type" id="type" type="text" class="01input" />
	            </label></td>
	            <td width="117" align="left">
	            	<img style="cursor:pointer" src="images/tianjialianxirenbaochun.gif" width="72" height="25" border="0" onclick="save()"/>
	           
	            </td>
	          </tr>
	        </table></td>
	      </tr>
	      <tr>
	        <td height="13" bgcolor="b2b2b2">&nbsp;</td>
	      </tr>
	    </table></td>
	  </tr>
	</table>
</form>
</body>
</html>