<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<style type="text/css">
#div1 {
	background-image: url('image/1.JPG');
	width: 780px;
	height: 490px;
	text-align: center;
}

#div2 {
	padding-top: 95px;
	padding-right: 0px;
}

#bigImg {
	padding-top: 100px;
	padding-right: 100px;
}

#link {
	padding-top: 250px;
	padding-right: 100px;
}

a {
	text-decoration: none;
	color: #286900
}
</style>
</head>
<body>
<div align="center">
<div id="div1">
<div id="div2">
<table>
	<tr>
		<td id="bigImg"><img alt="" src="image/Chrysanthemum.jpg"
			width="400" height="260"></td>
		<td id="link"><a href="generSmallImageAction">生成缩略图</a></td>
	</tr>
</table>
</div>
</div>
</div>
</body>
</html>