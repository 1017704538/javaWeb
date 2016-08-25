<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仿Google Suggest自动完成</title>
<link href="CSS/style.css" rel="stylesheet">
<script src="JS/AjaxRequest.js"></script>
<script language="javascript">
var size=0;//总条目
var curSize=-1;//当前条目
	function getSuggest() {
		if(event.keyCode!=13 && event.keyCode!=38 && event.keyCode!=40){
			curSize=-1;//当前条目
			var loader = new net.AjaxRequest(
				"KeyServlet?action=getKey&inputKey="+document.getElementById("suggest").value+"&nocache=" + new Date().getTime(),
				deal, onerror, "GET");
		}
	}
	function onerror() {}
	function deal() {
		var doc = this.req.responseXML; //获取XML格式的关键字

		var contentObj = document.getElementById("content");//获取content对象
		size=contentObj.childNodes.length;	//设置条目数
		//清空提示内容对象
		while(true){
			if(contentObj.hasChildNodes()){
				contentObj.removeChild(contentObj.firstChild);//移除指定的节点
			}else{
				break;//跳出while循环
			}
		}
		
		var content = doc.getElementsByTagName("keyWord");//获取XML文档中的keyWord节点
		if(content.length>0){
			for(var i=0;i<content.length;i++){
				addContent(content(i).firstChild.nodeValue);
			}
			document.getElementById("toolBox").style.visibility="visible";//显示提示框
		}else{
			document.getElementById("toolBox").style.visibility="hidden";//隐藏提示框
		}
	}
window.onload=function(){
	document.getElementsByTagName('body')[0].onkeydown=ctrlMove;//监听onkeydown事件
}
	//通过↑键和↓键选择所需列表项
	function ctrlMove(){
		if(size>0 ){
			var contentObj = document.getElementById("content");//获取content对象
			size=contentObj.childNodes.length;	//设置条目数
			switch(event.keyCode){
			case 13:
				document.getElementById("toolBox").style.visibility="hidden";//隐藏提示框
				size=0;
				return false;
				break;
			case 38://向上方向键
					if(curSize==-1){
						curSize=size-1;
					}else if(curSize>0){
						curSize--;
					}
				break;
			case 40://向下方向键
				if(curSize==-1){
					curSize=0;
				}else if(curSize<size-1){
					curSize++;
				}
				break;
			}
			if(event.keyCode==38 || event.keyCode==40){
				contentObj.childNodes[curSize].style.background="#CE83FF";//当鼠标在关键词上时改变背景色 
				contentObj.childNodes[curSize].style.color="#FFFFFF";//当鼠标在关键词上时改变文字颜色
				document.getElementById("suggest").value=contentObj.childNodes[curSize].innerHTML;
				for(var i=0;i<size;i++){
					if(i!=curSize){
						contentObj.childNodes[i].style.background="#FFFFFF";//当鼠标在关键词上时改变背景色 
						contentObj.childNodes[i].style.color="#000000";//当鼠标在关键词上时改变文字颜色
					}
				}
			}
		}
	}
	//在content中添加一条信息 
	function addContent(content){
		var contentObj = document.getElementById("content");//获取content对象
		var divContent = document.createElement("div");//创建一个DIV对象
		divContent.innerHTML=content;//设置div标记的内容
		divContent.style.paddingLeft="2px";//设置文字与左边框的距离
		divContent.onmouseover = function(){//添加鼠标移入事件
			this.style.background="#CE83FF";//当鼠标在关键词上时改变背景色 
			this.style.color="#FFFFFF";//当鼠标在关键词上时改变文字颜色
		};
		divContent.onmouseout = function(){//添加鼠标移出事件
			this.style.background="#FFFFFF";//鼠标移开时变为原来的背景色 
			this.style.color="#000000";//当鼠标在关键词上时改变文字颜色
		};
		divContent.onclick=function(){//添加单击事件
			document.getElementById("suggest").value=this.innerHTML;
			document.getElementById("toolBox").style.visibility="hidden";
		};
		
		contentObj.appendChild(divContent);//将对象添加在contentObj对象中 
	}
</script>
</head>
<body>
<div id="box">
	<div id="top"></div>
	<div id="center" style="float: left; width:753px; height: 300px; background-image:url(images/bottom.jpg); background-repeat:no-repeat; background-position:top center;">
	<div style="position: absolute;padding-left: 100px;">
	<ul>
	<form action="KeyServlet?action=saveKey" name="form1" method="post" style="margin:0px;">
	  <li style="padding-right:5px;">	<input type="text" id="suggest" name="suggest" class="inputText"
			onkeydown="getSuggest();" autocomplete="off">
	  </li>
	  <li><input type="submit" name="button" value="" class="inputButton"></li>
	  <li style="color: #FFFFFF; clear:left;"><b><font color="#FFFF00">明日知道热门搜索：</font></b>Java编程词典&nbsp;&nbsp;&nbsp;&nbsp;Java Web&nbsp;&nbsp;&nbsp;&nbsp;Java&nbsp;&nbsp;&nbsp;&nbsp;Java从入门到精通...<br>
	  	<div id="toolBox" align="left"><span id="content"></span></div>
	  </li>
	</form>
	</ul>	
	</div>
	</div>
</div>
</body>
</html>