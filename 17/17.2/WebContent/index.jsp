<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>fileUpload</title>
<style type="text/css">
  #div1 {
	background-image: url('image/1.jpg');
	width: 780px;
	height: 490px;
	text-align : center;
}
  #div2 {
    padding-top:95px;
    padding-right:400px;
  }
</style>
</head>
<body>
<div id="div1">
<div id="div2">
<s:form action="imageUploadAction" method="POST" enctype="multipart/form-data">
   <s:file name="imageObj.image_" label="上传图片"></s:file>
   <s:submit value="上传图片"></s:submit>
</s:form>
</div>
</div>
</body>
</html>