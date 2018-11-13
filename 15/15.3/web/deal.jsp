<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String sessionCode = session.getAttribute("Code").toString();
	String requestCode = request.getParameter("code");
	if(requestCode.equals(sessionCode)){%>
		<script language="javascript">
                        alert("注册成功！");
                        window.location.href="register.jsp";
        </script>
	<%}else{%>
		<script language="javascript">
                        alert("您输入的验证码不正确！");
                        window.location.href="register.jsp";
        </script>
	<%}
%>

