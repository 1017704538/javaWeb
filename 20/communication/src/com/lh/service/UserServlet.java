package com.lh.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lh.dao.UserDao;
import com.lh.model.Md5;
import com.lh.model.User;

/**
 * 控制用户登录和注册的Servlet类
 * @author LH
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    public UserServlet() {
        super();
    }	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");//获取设置的请求动作：注册、登陆或更新
		if(action!=null){
			if(action.equals("login")){
				this.login(request, response);
			}
			else if(action.equals("enter")){
				this.enter(request, response);
			}
			else if(action.equals("update")){
				this.update(request, response);
			}
			else if(action.equals("checkName")){
				this.checkName(request, response);
			}
			else if(action.equals("checkEnter")){
				this.checkEnter(request, response);
			}
		}
	}
	/**
	 * 处理用户注册
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void login(HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("username");	//用户名
		String userPwd = request.getParameter("userpwd");	//密码
		String userAge = request.getParameter("age");		//年龄
		Integer age=null;
		if(userAge!=null&&!userAge.equals("")){
			age=Integer.valueOf(userAge);
		}
		String userSex = request.getParameter("sex");		//性别
		Calendar calendar=Calendar.getInstance();			//获取系统当前日期时间
		User user = new User();
		user.setUserName(userName.trim());
		user.setUserPwd(Md5.getMD5(userPwd));				//使用MD5将密码加密
		user.setUserAge(age);
		user.setUserSex(userSex);
		user.setUserLoginTime(calendar);
		boolean res=UserDao.getInstance().saveUser(user);	//保存注册信息
		if(res){
			//User loginUser=UserDao.getInstance().getUserByNameAndPwd(userName, Md5.getMD5(userPwd));
			//HttpSession session=request.getSession();
			//session.setAttribute("loginUser", loginUser);
			out.println("<script>");
			out.println("alert('注册成功！稍后自动跳转到登录页进行登录！');");
			out.println("</script>");
			try{
				Thread.sleep(3000);
			}catch(Exception ex){
			}
			out.println("<script>");
			out.println("window.location.href='addresslist/userenter.jsp'");
			out.println("</script>");
			//response.sendRedirect("addresslist/userenter.jsp");
		}else{
			response.sendRedirect("addresslist/loginerror.jsp");
		}
	}
	/**
	 * 处理用户登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void enter(HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
		String userName = request.getParameter("username");//用户名
		String userPwd = request.getParameter("userpwd");	//密码
		User user=UserDao.getInstance().getUserByNameAndPwd(userName,Md5.getMD5(userPwd));
		if(user!=null){
			HttpSession session=request.getSession();
			session.setAttribute("enterUser", user);
			response.sendRedirect("addresslist/personlist.jsp");
		}else{
			response.sendRedirect("addresslist/loginerror.jsp");
		}
	}
	/**
	 * 处理用户修改
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		String userAge = request.getParameter("age");
		Integer age=null;
		if(userAge!=null&&!userAge.equals("")){
			age=Integer.valueOf(userAge);
		}
		String userSex = request.getParameter("sex");
		Calendar time=Calendar.getInstance();
		User user = new User();
		user.setUserId(userId);
		user.setUserPwd(Md5.getMD5(userPwd));
		user.setUserAge(age);
		user.setUserSex(userSex);
		user.setUserLoginTime(time);
		boolean res=UserDao.getInstance().updateUser(user);
		if(res==true){
			response.sendRedirect("addresslist/personlist.jsp");
		}else{
			response.sendRedirect("addresslist/loginerror.jsp");
		}
	}
	/**
	 * 判断用户名是否存在
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void checkName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/xml;charset=UTF-8" );//设置响应格式
		//设置响应头信息
		response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		response.addHeader( "Expires", "0" );
		response.addHeader( "Pragma", "no-cache" );
		PrintWriter out = response.getWriter();
		out.println( "<?xml version=\"1.0\" encoding=\"utf-8\"?>" );
		out.println( "<userName>" );
		String name = request.getParameter( "name" );//获取用户名
		boolean result=UserDao.getInstance().getUserByName(name.trim());//查找数据库是否存在该用户名
		if(result==false) 
			out.println("<checkName id=\"ok\"/>");
		else
			out.println("<iteranceUserName>"+name+"</iteranceUserName>");
		out.println( "</userName>" );
	}
	/**
	 * 根据用户名和密码
	 * 判断用户是否存在
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private  void checkEnter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/xml;charset=UTF-8" );			//设置响应格式为XML
		//设置响应头
		response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		response.addHeader( "Expires", "0" );
		response.addHeader( "Pragma", "no-cache" );
		PrintWriter out = response.getWriter();
		out.println( "<?xml version=\"1.0\" encoding=\"utf-8\"?>" );
		out.println( "<checkEnter>" );
		String name = request.getParameter( "name" );					//获取用户名
		name = java.net.URLDecoder.decode(name, "UTF-8");				//处理中文编码格式
		
		String pwd = request.getParameter("pwd");						//获取密码
		User user=UserDao.getInstance().getUserByNameAndPwd(name,Md5.getMD5(pwd));	//根据用户名和密码查询数据库
		if(user!=null) {	
			out.println("<hasUser id=\"ok\"/>");
		}else{
			out.println("<noUser>"+name+"</noUser>");
		}
		out.println( "</checkEnter>" );
	}
}
