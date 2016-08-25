package com.lh.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lh.dao.UserDao;
import com.lh.model.UserInfo;

/**
 * 处理账号登录的Servlet类
 * @author lh
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   
    public UserServlet() {
        super(); 
    }
    /**
     * 处理"Post"方式的请求
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doLogin(request, response);
	}
	/**
	 * 处理登陆请求的方法
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	
	private void doLogin(HttpServletRequest request,HttpServletResponse response) 
		throws IOException,ServletException{
		String name=request.getParameter("name");//获取请求中的用户名
		String pwd=request.getParameter("pwd");  //获取请求中的密码		
		Cookie cookie= new Cookie("LoginUserName", name); //把用户名保存到Cookie对象中	
		cookie.setMaxAge(60*1);                  //设置此Cookie的最大保存时间（以秒表示）
		response.addCookie(cookie);              //把保存用户名的Cookie对象添加到Response中			
		UserInfo user = new UserInfo();          //封装用户信息的实体类
		user.setName(name);                      //封装用户名
		user.setPwd(pwd);                        //封装密码
		boolean res=UserDao.getInstance().checkUserLog(user); //验证用户名密码
		if(res){//验证成功跳转到ok.jsp
			response.sendRedirect("/15.1/usermanager/ok.jsp");
		}else{
			response.sendRedirect("/15.1/usermanager/error.jsp");
		}
	}
}
