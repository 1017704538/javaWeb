package com.lyq.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 处理POST请求
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// 获取name参数值
		String name = request.getParameter("name");
		// 从response中获取PrintWriter对象
		PrintWriter writer = response.getWriter();
		// 判断name是否为空
		if (name != null) {
			// 如果name不为空,将通过writer输出name
			writer.write("Hello " + name + " !");
		}
		writer.flush();// 刷新writer
		writer.close();// 关闭writer
	}
}
