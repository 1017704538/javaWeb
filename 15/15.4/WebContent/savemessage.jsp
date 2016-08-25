<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.lh.dao.*" %>
<%@ page import="com.lh.util.*" %>
<%@ page import="com.lh.model.*" %>
<%
	String name = request.getParameter("name");//留言人
	String message = request.getParameter("message");//留言信息
	Message msg = new Message();
	msg.setName(name);
	msg.setNewMessage(message);
	//获得当前系统时间的字符串
	String now = CalendarUtil.getParticularDateTime(Calendar.getInstance());
	msg.setMessageTime(now);
	boolean bool = MessageDao.getInstance().saveMessage(msg);//保存留言信息
	if(bool){%>
		<script type="text/javascript">
		
			window.location.href="messageboard.jsp";//保存成功则跳转到留言本页面
		</script>
	<%}
%>  
