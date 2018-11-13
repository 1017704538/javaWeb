<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lh.dao.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据库恢复</title>
<style type="text/css">
<!--
.fontStyle1 {
	font-size: 15px;
	line-height: 23px;
	color: #000000;
}
.fontStyle2 {
	font-size: 12px;
	
	color: #666666;
}
-->
</style>
<script type="text/javascript">

	function load(){
		var path = getFilePath();
		if(path.lastIndexOf(".sql")==-1){	//判断是不是.sql文件 
			alert("备份文件错误，请重新选择！");
			return false;
		}
		var obj = document.getElementById("dataBaseName");
		var index = obj.selectedIndex;
		var dbName = obj.options[index].value;
		document.forms.form1.action = "doload.jsp?dbName="+dbName+"&filePath="+path;
		document.forms.form1.method="post";
		document.forms.form1.submit();
	}
	/**
	*此方法用户获得文件本地路径
	*由于备份时路径必须是D:\\data\\test.sql格式
	*所以需要在此转换
	*/
	
	function getFilePath(){
		var file = document.getElementById("path");
		var filePath;
		file.select();
		try{
			filePath = document.selection.createRange().text;//获得文件的本地路径
		} 
		finally{
			document.selection.empty();
		}
		//将路径格式转换为“\\”格式，例如D:\data\test.sql需要转换为D:\\data\\test.sql
		var pathStr = filePath.split('\\');
		var newPath = "";
		for(var i=0;i<pathStr.length;i++){
			newPath += pathStr[i]+"\\\\";
		}
		var path = newPath.substring(0,newPath.length-2);
		path = encodeURI(path);				//转换成Unicode编码
		path = encodeURI(path);				//需要调用两次才有效 
		return path;
	}
</script>
</head>
<body>

<form id="form1">
	<table align="center" background="bg.JPG" width="784" height="623" border="0">
		<tr>
			<td colspan="4" height="200"></td>
		</tr>
		<tr>
			<td colspan="4" height="50"></td>
		</tr>
		<tr class="fontStyle2">
			<td width="60"></td>
			<td width="150" align="right">选择需要恢复的数据库：</td>
			<td><select name="dbName" id="dataBaseName" class="fontStyle2" >
				<%
					List<String> list = LoadDataBaseDao.getInstance().getDatabaseName();
						if(list!=null&&list.size()>0){
							for(String databaseName:list){
				%>								
					<option value="<%=databaseName %>"><%=databaseName %></option>
						<%}
					}
				%>
				</select>
			</td>
			<td width="60"></td>
		</tr>
		<tr class="fontStyle2" >
			<td width="60"></td>
			<td width="150"  align="right">选择数据库备份文件：</td>
			<td><input type="file" name="path" id="path" size="30" class="fontStyle2" /></td>
			<td width="60"></td>
		</tr>
		<tr>
			<td width="60"></td>
			<td colspan="2" valign="top">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="恢 复" onclick="load()" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" value="重 置"  />
			</td>	
			<td width="60"></td>
		</tr>
		<tr>
			<td colspan="4" height="50"></td>
		</tr>
		<tr>
			<td colspan="4" height="50"></td>
		</tr>
		<tr>
			<td colspan="4" height="50"></td>
		</tr>
		<tr>
			<td colspan="4" height="50"></td>
		</tr>
	</table>
</form>
</body>
</html>