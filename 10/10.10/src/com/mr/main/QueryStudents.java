package com.mr.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.mr.student.StudentVO;
import com.mr.util.HibernateUtil;

/**
 * 查找学生详细信息
 */
public class QueryStudents extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Integer id = new Integer(request.getParameter("id"));
		Session session = null;				      //声明Session对象
		try {
            session = HibernateUtil.getSession();//获取session
            //根据学号查询学生信息
            StudentVO studentVO = (StudentVO) session.get(StudentVO.class, id);
            request.setAttribute("studentVO", studentVO);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            HibernateUtil.closeSession(session);//关闭Sesssion
        }
        request.setAttribute("id", id);
		//实现页面跳转
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/student.jsp");
		rd.forward(request, response);
	}
}