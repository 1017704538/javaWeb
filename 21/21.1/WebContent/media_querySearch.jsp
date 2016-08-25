<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>
<jsp:directive.page import="com.mr.dao.MediaDao" />
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="com.mr.model.MediaInfo" />
<jsp:useBean id="pagination" class="com.mr.tool.MyPagination"
	scope="session"></jsp:useBean>
<%
	String key = request.getParameter("key");
	MediaDao mediaDao = new MediaDao();
	String str = request.getParameter("Page");
	int Page = 1;
	List list = null;
	int num = 0;
	if (str == null) {
		list = mediaDao.media_querySearch(key); //执行搜索结果的方法
		num = list.size();
		int pagesize = 4; //指定每页显示的记录数
		list = pagination.getInitPage(list, Page, pagesize); //初始化分页信息
	} else {
		Page = pagination.getPage(str);
		list = pagination.getAppointPage(Page); //获取指定页的数据
	}
%>
<!-- 省略搜索结果的代码 -->
