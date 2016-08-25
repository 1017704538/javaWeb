<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登录</title>
    <style type="text/css">
        li {
            list-style: none;
        }
    </style>
</head>

<body>
<h1>JSP内置对象：</h1>
<li>request</li>
getParameter(name)用的比较多 以及一些获得头文件 服务器信息等 3.2有详细方法
<li>response</li>
<li>session</li>
<li>application</li>
<li>out</li>
out主要是向网页提交缓冲的包含了
<li>page</li>
<li>config</li>
<li>exception</li>
<li>pageContext</li>
<h1>form表单 action的实现：即：request的实现</h1>

<div>
    <form action="login.jsp" method="post">
        <ul>
            <li><h3>用户登录</h3></li>
        </ul>
        <ul>
            <li>用户名：<input type="text" name="username"></li>
        </ul>
        <ul>
            <li>密　码：<input type="password" name="password"></li>
        </ul>
        <ul>
            <li>
                <input type="submit" value="登　录">
            </li>
        </ul>
    </form>
</div>
</body>
</html>