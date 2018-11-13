<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lh.model.*" %>
<%@ page import="com.lh.util.*" %>
<%@ page import="com.lh.dao.*" %>
<%
	String flag = request.getParameter("flag");
	try{
		Thread.sleep(3000);
	}catch(Exception ex){

	}
	if(flag!=null&&!flag.equals("")){
		String userName = request.getParameter("username");	//用户名
		String userPwd = request.getParameter("userpwd");	//用户密码
		String userAge = request.getParameter("age");		//用户年龄
		int age=0;
		if(userAge!=null&&!userAge.equals("")){
			age=Integer.parseInt(userAge); 					//转换为int值
		}
		String userSex = request.getParameter("sex");		//用户性别
		User user = new User();								//创建用户信息对象，将注册信息封装
		user.setUserName(userName.trim());
		user.setUserPwd(Md5.getMD5(userPwd));				//密码加密后封装到User对象中
		user.setUserAge(age);
		user.setUserSex(userSex);
		//获取当前系统时间的字符串格式
		String time = CalendarUtil.getParticularDateTime(Calendar.getInstance());
		user.setUserLoginTime(time);
		boolean res=UserDao.getInstance().saveUser(user);	//保存注册信息
		if(res){%>
			<script type="text/javascript">
				alert("注册成功！");
				window.location.href = "userlogin.jsp" ;
			</script>
		<% }else{%>
			<script type="text/javascript">
				alert("注册失败！");
				window.location.href = "userlogin.jsp" ;
			</script>
		<%}
	}else{%>
		<script type="text/javascript">
			window.location.href = "userlogin.jsp" ;
		</script>
	<%}
%>