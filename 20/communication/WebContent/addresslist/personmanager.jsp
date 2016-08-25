<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.lh.model.*,com.lh.dao.*" %>
<%
	/**
	*通讯信息管理页
	*/
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/Untitled-4.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>

<script type="text/javascript">
	function add(){
		var url="<%=request.getContextPath()%>/addresslist/addpersoninfo.jsp?flag=add&pid=";
		var ret=window.showModalDialog(url,'window','status=no;dialogWidth=560px;dialogHeight=395px;center=yes;help=no;location=no;');
		if(typeof(ret)==="string"){
			window.frames.personlist.src="addresslist/personlist.jsp";
		}
	}
	function addtype(){
		var url="addpersontype.jsp";
		var ret=window.showModalDialog(url,'window','status=no;dialogWidth=560px;dialogHeight=190px;center=yes;help=no;location=no;');
		if(typeof(ret)==="string"){
			window.frames.personlist.src="addresslist/personlist.jsp";
		}
	}
	function selectperson(){
		
		var url="selectperson.jsp";
		var ret=window.open(url,'window','status=no,width=500px,height=100px,center=yes,help=no,location=no,menubar=no,toolbar=no');
		//var ret=window.showModalDialog(url,'window','status=no;dialogWidth=560px;dialogHeight=200px;center=yes;help=no;');
		if(typeof(ret)==="string"){
			window.frames.personlist.src="addresslist/personlist.jsp";
		}
	}
	function del(){
		window.frames.personlist.deleteinfo();
	}
</script>

</head>
<body bgcolor="#FFFFFF">
		<table width="1000" border="0" align="center" cellpadding="0" cellspacing="0" id="__01">
			<tr>
				<td height="124" colspan="4"><img src="images/zhuce_01.gif" width="1000" height="124" /></td>
			</tr>
			<tr>
				<td width="12" height="431" align="left" valign="top" background="images/zuoshuxian.gif" bgcolor="#FFFFFF">&nbsp;</td>
			    <td width="191" height="431" align="center" valign="top" bgcolor="#FFFFFF">
				    <table width="188" height="380" border="1" cellpadding="0" cellspacing="0" bordercolor="b2b2b2">
			          <tr>
			            <td width="184" height="37" align="left" bgcolor="f2f1f1"><img src="images/index01.gif" width="184" height="37" /></td>
			          </tr>
			          <tr>
			            <td width="184" height="311" align="center" valign="top" bgcolor="f2f1f1"><table width="180" height="388" border="0" cellpadding="0" cellspacing="0">
			              <tr>
			                <td height="74"><a href="#"><img src="images/family.gif" width="176" height="72" border="0" /></a></td>
			              </tr>
			              <tr>
			                <td height="74"><a href="#"><img src="images/friend.gif" width="176" height="71" border="0" /></a></td>
			              </tr>
			              <tr>
			                <td height="74"><a href="#"><img src="images/classmate.gif" width="176" height="72" border="0" /></a></td>
			              </tr>
			              <tr>
			                <td height="74"><a href="#"><img src="images/colleague.gif" width="176" height="71" border="0" /></a></td>
			              </tr>
			              <tr>
			                <td height="88"><p>&nbsp;</p>
			                    <p>&nbsp;</p></td>
			              </tr>
			            </table>
		            </td>
		          </tr>
		        </table>
		        </td>
			    <td width="787" height="431" align="center" valign="top" bgcolor="#FFFFFF">
			    	<iframe frameborder="0" height="500" width="100%" src="<%=request.getContextPath()%>/addresslist/personlist.jsp" name="personlist1" id="personlist"></iframe>
		        </td>
			    <td width="10" height="431" align="right" background="images/youshuxian.gif" bgcolor="#FFFFFF">&nbsp;</td>
			</tr>
			<tr>
				<td width="12" background="images/zhuce_04.gif">
					<img id="zhuce_03" src="images/zhuce_03.gif" width="12" height="99" alt="" /></td>
				<td colspan="2" align="right" valign="top" background="images/zhuce_04.gif"><img src="images/zhuce_08.gif" width="374" height="45" /></td>
				<td width="10" align="right" background="images/zhuce_04.gif">
					<img id="zhuce_06" src="images/zhuce_06.gif" width="10" height="99" alt="" /></td>
			</tr>
		</table>
	
</body>
</html>