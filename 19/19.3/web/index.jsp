<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>多级级联下拉列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
	<script language="javascript" src="../JS/AjaxRequest.js"></script>
	<link rel="stylesheet" href="../CSS/style.css">
<script language="javascript">
//获取省份和直辖市
function getProvince(){
	var loader=new net.AjaxRequest("ZoneServlet?action=getProvince&nocache="+new Date().getTime(),deal_getProvince,onerror,"GET");
}
function deal_getProvince(){
	provinceArr=this.req.responseText.split(",");	//将获取的省份名称字符串分隔为数组
	
	for(i=0;i<provinceArr.length;i++){		//通过循环将数组中的省份名称添加到下拉列表中
		document.getElementById("province").options[i]=new Option(provinceArr[i],provinceArr[i]);
	}
	if(provinceArr[0]!=""){
		getCity(provinceArr[0]);	//获取地级市
	}
}
window.onload=function(){
	 getProvince(); 		//11获取省份和直辖市
}
/*************************************************************************************************************/
//获取地级市
function getCity(selProvince){
	var loader=new net.AjaxRequest("ZoneServlet?action=getCity&parProvince="+selProvince+"&nocache="+new Date().getTime(),deal_getCity,onerror,"GET");
}
function deal_getCity(){
	cityArr=this.req.responseText.split(",");	//将获取的市县名称字符串分隔为数组
	document.getElementById("city").length=0;	//清空下拉列表
	for(i=0;i<cityArr.length;i++){		//通过循环将数组中的地级市名称添加到下拉列表中
		document.getElementById("city").options[i]=new Option(cityArr[i],cityArr[i]);
	}
	if(cityArr[0]!=""){
		getArea(document.getElementById("province").value,cityArr[0]);	//获取县/县级市/区
	}
}
/****************************************************************************/
//获取县、县级市或区
function getArea(selProvince,selCity){
	var loader=new net.AjaxRequest("ZoneServlet?action=getArea&parProvince="+selProvince+"&parCity="+selCity+"&nocache="+new Date().getTime(),deal_getArea,onerror,"GET");
}
function deal_getArea(){
	areaArr=this.req.responseText.split(",");	//将获取的市县名称字符串分隔为数组
	document.getElementById("area").length=0;	//清空下拉列表
	for(i=0;i<areaArr.length;i++){		//通过循环将数组中的县、县级市和区名称添加到下拉列表中
		document.getElementById("area").options[i]=new Option(areaArr[i],areaArr[i]);
	}
}
function onerror(){}		//错误处理函数
</script>
  </head>
  
<body style="background-color:#eef2eb;">
<div id="box">
	<div id="top"></div>
	<div id="center" style="border:1px solid #91D564;width:730px; height:350px; margin-left:50px; margin-bottom:15px;">
		<div id="title" style="height:37px;background-image:url(../images/zhuce_05.jpg); background-repeat:repeat-x"><img src="../images/zhuce_04.jpg" width="124" height="37"></div>
		<div id="content" style="margin-left:130px;">
		  <form method="post" name="form1" id="form1">
		  <ul>
			<li>用&nbsp;&nbsp;户&nbsp;&nbsp;名：
			  <input name="username" type="text" id="username">
			*</li>
			<li>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：
			  <input name="pwd" type="password" id="pwd">
			*</li>
			<li>确认密码：
			  <input name="repwd" type="password" id="repwd">
			*</li>
			<li>E-mail地址：
			  <input name="email" type="text" id="email" size="35">
			*</li>
			<li>
		  居&nbsp;住&nbsp;地：
			  <select name="province" id="province" onChange="getCity(this.value)">
			  </select>
			  -
			  <select name="city" id="city"  onChange="getArea(document.getElementById('province').value,this.value)">
			  </select>
			  -
			  <select name="area" id="area">
			  </select>
		    </li>
			  <li style="margin-top:20px;">以下两个选项，只要有任何一个没有输入，将不可以通过答案问题重新设置密码。</li>
			  <li>密码提示问题：
				<input name="question" type="text" id="question" size="35">
			  </li>	
			  <li>提示问题答案：
				<input name="answer" type="text" id="answer" size="35">
			  </li>	
			  <li style="padding-left:30px;">
			    <input type="image" style="border:none;" name="imageField" src="../images/zhuce_09.jpg">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="image" style="border:none;" name="imageField2" src="../images/zhuce_11.jpg" onClick="this.form.reset();return false;">
&nbsp;			  </li>  
			</ul>	  
			  
	      </form>
	  </div>
  	</div>
	<div id="bottom"></div>
</div>
</body>
</html>
