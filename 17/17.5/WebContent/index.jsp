<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>fileUpload</title>
<style type="text/css">
  #div1 {
	background-image: url('image/1.bmp');
	width: 780px;
	height: 490px;
	text-align : center;
}
  #div2 {
    padding-top:216px;
    padding-left:318px;
  }
</style>
</head>
<body>
<div align="center">
<div id="div1">
<div id="div2">
<s:form action="zipAction_enCompress"  enctype="multipart/form-data" method="POST">
     <s:file name="file" label="压缩文件"></s:file>
     <s:submit value="压缩文件"></s:submit>
</s:form>
<s:div cssStyle="height:10px;"></s:div>
<s:form action="zipAction_deCompress" enctype="multipart/form-data" method="POST">
     <s:file name="file" label="解压文件"></s:file>
     <s:submit value="解压文件"></s:submit>
</s:form>
</div>
</div>
</div>
</body>
</html>