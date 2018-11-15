//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.servlet;

import com.lh.dao.UserDao;
import com.lh.model.UserInfo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doLogin(request, response);
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        Cookie cookie = new Cookie("LoginUserName", name);
        cookie.setMaxAge(60);
        response.addCookie(cookie);
        UserInfo user = new UserInfo();
        user.setName(name);
        user.setPwd(pwd);
        boolean res = UserDao.getInstance().checkUserLog(user);
        if (res) {
            response.sendRedirect("/15.1/usermanager/ok.jsp");
        } else {
            response.sendRedirect("/15.1/usermanager/error.jsp");
        }

    }
}
