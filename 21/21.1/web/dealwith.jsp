<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.page import="com.mr.model.*" />
<jsp:directive.page import="com.mr.dao.*" />
<jsp:directive.page import="java.io.File" /> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">

<%
	Integer sign = Integer.valueOf(request.getParameter("sign"));
	if (sign == 0) {
		String result = "";
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		AdminDao adminDao = new AdminDao();
		AdminInfo info = adminDao.admin_queryPassword(account);
		if (null != info) {
			String password1 = info.getPassword();
			if (password.equals(password1)) {
				adminDao.admin_addVisit();
				info = adminDao.admin_queryPassword(account);
				session.setAttribute("info", info);
			} else {
				result = "您输入的密码有误！";
			}
		} else {
			result = "该账号不存在！";
		}

		if (result.equals("")) {
			response.sendRedirect("admin/mainPage.jsp");
		} else {
			out.print("<script language=javascript>alert('" + result
					+ "');history.go(-1);</script>");
		}

	}

	if (sign == 1) {
		session.invalidate();
		response.sendRedirect("index.jsp");
	}
	if (sign == 2) {
%>
<jsp:useBean id="user" class="com.mr.model.UserInfo" scope="request" /> 
<jsp:setProperty property="*" name="user" />
<%
	UserDao userDao = new UserDao();
		boolean flag = userDao.user_add(user);
		if (flag) {
			session.setAttribute("username", user.getUser_name());
			response.sendRedirect("index.jsp");
		} else {
			out.print("<script language=javascript>alert('您输入的用户名重复，请重新输入！');history.go(-1);</script>");
		}
	}
	if (sign == 3) {
		String code = request.getParameter("code");
		String rand = (String) session.getAttribute("rand");
		String result = "";
		if (code.equals(rand)) {
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			UserDao userDao = new UserDao();
			UserInfo userInfo = userDao.user_queryOne(account);
			if (null != userInfo) {
				if (password.equals(userInfo.getUser_pswd())) {
					userDao.user_addHit(account);
					session.setAttribute("username", userInfo
							.getUser_name());
				} else {
					result = "您输入的密码不正确，请重新输入！";
				}
			} else {
				result = "您输入的用户名不存在！，请重新输入！";
			}
		} else {
			result = "您输入的验证码有误！，请重新输入！";
		}

		if (result.equals(""))
			response.sendRedirect("index.jsp");
		else
			out.print("<script language=javascript>alert('" + result
					+ "');history.go(-1);</script>");

	}

	if (sign == 4) {
%>
<jsp:useBean id="mediaRInfo" class="com.mr.model.MediaRInfo"
	scope="request" />
<jsp:setProperty property="*" name="mediaRInfo" />
<%
	MediaRDao mediaRDao = new MediaRDao();
		mediaRDao.media_add(mediaRInfo);
		out
				.print("<script language=javascript>window.location.href='media_show.jsp?id="
						+ mediaRInfo.getMediaR_rootId() + "';</script>");//返回当前视频播放页面
	}

	if (sign == 5) {
		UserDao userDao = new UserDao();
		MediaRDao mediaRDao = new MediaRDao();
		Integer id = Integer.valueOf(request.getParameter("id"));
		String username = request.getParameter("username");
		username = new String(username.getBytes("ISO8859_1"), "GBK");
		mediaRDao.media_deleteAllUser(username);
		userDao.user_delete(id);
		out
				.print("<script language=javascript>alert('您输入的密码不正确，请重新输入！');window.location.href='admin/user_query.jsp';</script>");
	}

	if (sign == 6) {
		Integer id = Integer.valueOf(request.getParameter("id"));//获取id标识 
		MediaRDao mediaRDao = new MediaRDao();	//实例化操作视频留言MediaRDao类的对象
		mediaRDao.media_deleteAllId(id);//实例化操作视频留言MediaRDao类的对象
		MediaDao mediaDao = new MediaDao();//实例化视频信息MediaDao类的对象
		MediaInfo mediaInfo = mediaDao.media_query(id);	//删除视频信息的操作
		String media_pic = mediaInfo.getMediaPic();//获取要删除视频文件的路径
		media_pic = request.getRealPath("/" + media_pic);//获取视频截图路径 
		File file1 = new File(media_pic);
		if (file1.exists()) {
			file1.delete();	//删除视频截图服务器端的视频文件
		}
		String media_src = mediaInfo.getMediaSrc();
		media_src = request.getRealPath("/" + media_src);//获取视频路径 
		File file2 = new File(media_src);
		if (file2.exists()) {
			file2.delete();//删除视频服务器端的视频文件
		}
		mediaDao.media_delete(id);
		out.print("<script language=javascript>alert('操作成功！');window.location.href='admin/mainPage.jsp';</script>");
	}
	if (sign == 7) {
		Integer id = Integer.valueOf(request.getParameter("id"));
		MediaRDao mediaRDao = new MediaRDao();
		mediaRDao.media_delete(id);
		out.print("<script language=javascript>alert('操作成功！');window.location.href='admin/media_queryOne.jsp?id="
						+ request.getParameter("media_id")
						+ "';</script>");
	}
%>