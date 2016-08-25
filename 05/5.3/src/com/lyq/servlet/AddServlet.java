package com.lyq.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lyq.bean.User;

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1473486348546537776L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置request的编码格式
		request.setCharacterEncoding("UTF-8");
		// 获取用户姓名
		String name = request.getParameter("name");
		// 获取性别
		String sex = request.getParameter("sex");
		// 获取家庭住址
		String add = request.getParameter("add");
		// 实例化User
		User user = new User();
		// 对姓名赋值
		user.setName(name);
		// 对性别赋值
		user.setSex(sex);
		// 对家庭住址赋值
		user.setAdd(add);
		// 获取ServletContext对象
		ServletContext application = getServletContext();
		// 从ServletContext中获取users
		List<User> list = (List<User>) application.getAttribute("users");
		// 判断List是否为null
		if(list == null){
			// 实例化list
			list = new ArrayList<User>();
		}
		// 将user添加到List集合中
		list.add(user);
		// 将List放置于Application范围中
		application.setAttribute("users", list);
		// 创建RequestDispatcher对象
		RequestDispatcher dispatcher = request.getRequestDispatcher("manager.jsp");
		// 请求转发到manager.jsp页面
		dispatcher.forward(request, response);
	}
}
