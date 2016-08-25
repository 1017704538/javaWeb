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
	background-image: url('image/1.jpg');
	width: 620px;
	height: 350px;
	text-align : center;
}
  #div2 {
    padding-top:50px;
  }
</style>
</head>
<body>
<div id="div1">
<div id="div2">
<s:form action="fileUploadAction" method="POST" enctype="multipart/form-data">
   <s:file name="myFile" label="上传文件"></s:file>
   <s:file name="myFile" label="上传文件"></s:file>
   <s:file name="myFile" label="上传文件"></s:file>
   <s:file name="myFile" label="上传文件"></s:file>
   <s:file name="myFile" label="上传文件"></s:file>
   <s:submit value="上传文件"></s:submit>
</s:form>
</div>
</div>
</body>
</html>