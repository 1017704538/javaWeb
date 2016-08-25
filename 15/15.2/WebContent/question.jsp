<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.lh.util.*" %>
<%
	UniqueUtil.saveUnique(request);
	//String id = String.valueOf(new Date().getTime());
	//session.setAttribute("UniqueId",new Date().getTime());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function submitQuestion(){
		document.forms.questionForm.action="doquestion.jsp";    
		document.forms.questionForm.method="post";
		document.forms.questionForm.submit();	
	}
</script>
</head>
<body >
	<form id="questionForm" action="">
	
		<%String unique = session.getAttribute("UniqueId").toString();
			if(unique!=null&&!unique.equals("")){%>	
			<input type="hidden" name="uniqueid" id="uniqueid" value="<%=unique %>" />
		<%}
		%>
		<table height="342" width="398" border="0"  align="center" background="question.gif">
			<tr>
				<td height="140"  align="center">  </td>
			</tr>
			<tr >
				<td height="20">
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
				 <font color="yellow" size="2px">问题1？</font><input type="text" name="question1"  />
				</td>
			</tr>
			<tr>
				<td height="20">
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
					<font color="yellow" size="2px">问题2？</font><input type="text" name="question2"  />
				</td>
			</tr>
			<tr>
				<td height="20">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="yellow" size="2px">问题3？</font><input type="text" name="question3"  />
				</td>
			</tr>
			<tr>
				<td height="20">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="提 交" onclick="submitQuestion()" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="重 置"  />
				</td>
			</tr>
				<tr>
				<td height="60"  align="center">  </td>
			</tr>
		</table>
	</form>
</body>
</html>