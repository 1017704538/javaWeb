package com.lh.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lh.dao.PersonTypeDao;
import com.lh.model.PersonType;

/**
 * 控制类型的Servlet类
 * @author LH
 */
public class PersonTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PersonTypeServlet() {
        super(); 
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action.equals("add")){
			add(request,response);
		}
		if(action.equals("update")){
			update(request,response);
		}
		if(action.equals("del")){
			delete(request,response);
		}
	}

private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String typeName=request.getParameter("type");//获得讯友类别名称
	PersonType type=new PersonType();//创建类别对象，用于封装类别信息
	type.setPersonType(typeName);
	boolean res=PersonTypeDao.getInstance().saveOrUpdateType(type);//保存类别
	if(res){
		response.sendRedirect("/communication/addresslist/personlist.jsp");
	}
}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeName=request.getParameter("type");
		String typeId=request.getParameter("typeid");
		PersonType type = new PersonType();
		type.setTypeId(typeId);
		type.setPersonType(typeName);
		boolean res=PersonTypeDao.getInstance().saveOrUpdateType(type);
		if(res==true){
			response.sendRedirect("/communication/addresslist/personlist.jsp");
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeId = request.getParameter("typeid");
		PersonType type = new PersonType();
		type.setTypeId(typeId);
		boolean res=PersonTypeDao.getInstance().deleteType(type);
		if(res==true){
			response.sendRedirect("/communication/addresslist/personlist.jsp");
		}
	}
}
