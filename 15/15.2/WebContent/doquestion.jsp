<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//从session中获得标识符
	String uniqueId = session.getAttribute("UniqueId").toString();
	//从请求表单中获得标识符
	String requestId = request.getParameter("uniqueid");
	if(uniqueId.equals(requestId)){//判断标识符是否相同
		
		session.setAttribute("UniqueId","");//将session中的标识符清除
		request.getRequestDispatcher("result.jsp").forward(request,response);
	}else{%>
		<script type="text/javascript">
			alert("您已经提交过了，请不要重复提交！");
			window.location.href="question.jsp";
		</script>
	<%}
%>