<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.lh.dao.*" %>
<%@ page import="com.lh.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言板</title>
<style type="text/css">
<!--
.STYLE1 {
	font-size: 12px;
	line-height: 23px;
	color: #666666;
}
-->
</style>
<script type="text/javascript">
	function submitMessage(){
		document.forms.messageForm.action="savemessage.jsp";    
		document.forms.messageForm.method="post";
		document.forms.messageForm.submit();	
	}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="818" height="607" border="0" align="center" cellpadding="0" cellspacing="0" id="__01">
	<tr>
		<td>
			<img src="images/index_01.gif" width="818" height="87" alt=""></td>
	</tr>
	<tr>
		<td height="413" valign="top" background="images/index_02.gif"><table width="700" height="86" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="#106CCD">
          <tr>
            <td bgcolor="#FFFFFF">
	            <table width="650" border="0" align="center" cellpadding="0" cellspacing="0">
	              <tr>
	                <td ><span class="STYLE1">留言内容</span></td>
	              </tr>
	      <%int i=0;
			List<Message> list = MessageDao.getInstance().selectAllMessage();
			for(Message msg:list){	i++;%>
				  <tr>
					<td>
						<span class="STYLE1">
						<%=i %> .[<%=msg.getName() %>]  <%=msg.getMessageTime().substring(0,msg.getMessageTime().lastIndexOf(".")) %>：
						<%=msg.getNewMessage()%>
						</span>
					</td>
				  </tr>
			<%}
		 %>
	            </table>
            </td>
          </tr>
        </table>
	  <br>
	  <form id="messageForm">
	  <input type="hidden" id="msg"  />
	  <table width="650" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr><td align="right"><span class="STYLE1">用户名：</span></td>
				<td>
					<input type="text" id="name" name="name" value="james" readonly="readonly" />
				</td>
			</tr>
        <tr>
          <td valign="top" align="right"><span class="STYLE1">留言文本：</span></td>
          <td>
          	<textarea rows="5" cols="25" name="message" id="message"></textarea><br/>
          	<input type="button" value="提  交" onclick="submitMessage()" />
          </td>
          <td>
          	
          </td>
        </tr>
      </table>
      </form>
      </td>
	</tr>
	<tr>
		<td>
			<img src="images/index_03.gif" width="818" height="107" alt=""></td>
	</tr>
</table>

</body>
</html>