package com.lh.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lh.dao.PersonListDao;
import com.lh.dao.PersonTypeDao;
import com.lh.model.PersonInfo;
import com.lh.model.PersonType;
import com.lh.model.User;

/**
 * 控制通讯录信息的Servlet类
 * @author LH
 *
 */
public class PersonListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PersonListServlet() {
        super();
    }
	/**
	 * 处理表单为"get"请求
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	/**
	 * 处理表单的"post"请求
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action.equals("add")){
			this.add(request, response);
		}
		if(action.equals("update")){
			this.update(request, response);
		}
//		if(action.equals("del")){
//			this.delete(request, response);
//		}
//		if(action.equals("select")){
//			this.select(request, response);
//		}
	}
	/**
	 * 添加通讯录信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
private void add(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	response.setContentType( "text/xml;charset=UTF-8" );//设置响应格式
	//设置响应头信息
	response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
	response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
	response.addHeader( "Expires", "0" );
	response.addHeader( "Pragma", "no-cache" );
	String name=request.getParameter("name");//获取姓名
	String sex=request.getParameter("sex");//获取性别
	Integer age=0;
	if(request.getParameter("age")!=null&&!request.getParameter("age").equals("")){
		age=Integer.valueOf(request.getParameter("age"));//获取年龄
	}	
	String mobile=request.getParameter("mobile");//获取手机号
	String email=request.getParameter("email");//获取电子邮件地址
	String address=request.getParameter("address");//获取家庭地址
	String typeid=request.getParameter("typeid");//获取类别编号
	User sessionUser = null;//从Session取出登录用户或注册用户的对象
	HttpSession session = request.getSession();
	if((User)session.getAttribute("enterUser")!=null){
		sessionUser=(User)session.getAttribute("enterUser");
	}
	else if((User)session.getAttribute("loginUser")!=null){
		sessionUser=(User)session.getAttribute("loginUser");
	}
	PersonInfo person = new PersonInfo();//创建通讯录对象，把请求的表单信息进行封装
	PersonType type = PersonTypeDao.getInstance().selectPersonTypeById(typeid);//根据类别编号查找类别对象
	person.setpName(name);
	person.setpSex(sex);
	person.setpAge(age);
	person.setpMobileNo(mobile);
	person.setEmail(email);
	person.setAddress(address);
	person.setType(type);
	person.setUser(sessionUser);
	//保存通讯录信息，并返回一个布尔值
	boolean res=PersonListDao.getInstance().savePersonInfo(person);
	PrintWriter out = response.getWriter();
	out.println( "<?xml version=\"1.0\" encoding=\"utf-8\"?>" );
	out.println( "<savePerson>" );
	if(res) 
		out.println("<success id=\"ok\"/>");
	else
		out.println("<error id=\"error\">");
	out.println( "</savePerson>" );
}
	/**
	 * 更新信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		response.setContentType( "text/xml;charset=UTF-8" );//设置响应格式
		//设置响应头信息
		response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		response.addHeader( "Expires", "0" );
		response.addHeader( "Pragma", "no-cache" );
		String pid=request.getParameter("pid");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		Integer age=0;
		if(request.getParameter("age")!=null&&!request.getParameter("age").equals("")){
			age = Integer.valueOf(request.getParameter("age"));
		}
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String typeid=request.getParameter("typeid");
		User sessionUser = null;//从Session取出登录用户或注册用户的对象
		HttpSession session=request.getSession();
		if((User)session.getAttribute("enterUser")!=null){
			sessionUser=(User)session.getAttribute("enterUser");
		}
		else if((User)session.getAttribute("loginUser")!=null){
			sessionUser=(User)session.getAttribute("loginUser");
		}
		PersonInfo person = new PersonInfo();
		PersonType type = PersonTypeDao.getInstance().selectPersonTypeById(typeid);
		person.setpId(pid);
		person.setpName(name);
		person.setpSex(sex);
		person.setpAge(age);
		person.setpMobileNo(mobile);
		person.setEmail(email);
		person.setAddress(address);
		person.setType(type);
		person.setUser(sessionUser);
		/**更新人员信息*/
		boolean res=PersonListDao.getInstance().updatePersonInfoById(person);
		PrintWriter out = response.getWriter();
		out.println( "<?xml version=\"1.0\" encoding=\"utf-8\"?>" );
		out.println( "<updatePerson>" );
		if(res) 
			out.println("<success id=\"ok\"/>");
		else
			out.println("<error id=\"error\">");
		out.println( "</updatePerson>" );
	}
	/**
	 * 删除信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String allpid=request.getParameter("allpid");
		String pid[]=allpid.split(",");
		boolean res=false;
		for(int i=0;i<pid.length;i++){
			res=PersonListDao.getInstance().deletePersonInfoById(pid[i]);
		}
		if(res==true){
			response.sendRedirect("/communication/addresslist/personmanager.jsp");
		}
	}
	/**
	 * 查询信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
//	protected void select(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
//		String namestr = FormatString.formatStr(request.getParameter("name"));
//		String typeid = request.getParameter("typeid");
//		PersonType type = PersonTypeDao.getInstance().selectPersonTypeById(typeid);
//		PersonListDao.getInstance().selectPersonInfo(namestr, type);
//		
//	}
}
