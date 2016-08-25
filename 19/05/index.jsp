<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实时弹出气泡提示窗口</title>
<link href="CSS/style.css" rel="stylesheet">
<script src="JS/AjaxRequest.js"></script>
<script language="javascript" src="JS/remind.js"></script>
<script language="javascript">
var bbsid=0;	//当前公告ID
function loadBubbleTip(){
	var loader = new net.AjaxRequest(
			"BbsServlet?action=getBbs&nocache=" + new Date().getTime(),
			deal, onerror, "GET");
}

function onerror() {}//错误处理函数
function deal() {
	var doc = this.req.responseXML; //获取XML格式的新闻信息
	//alert(this.req.responseText);
	if(doc.getElementsByTagName("title").length>0){
		var id = doc.getElementsByTagName("id")[0].firstChild.nodeValue;//获取XML文档中的id节点的第一个子节点的值
		bbsid=id;
		var content = doc.getElementsByTagName("title")[0].firstChild.nodeValue;//获取XML文档中的title节点的第一个子节点的值
		var foot1= doc.getElementsByTagName("time")[0].firstChild.nodeValue;	//获取XML文件中的time节点的第一个子节点的值
		var remindMessage = new PopBubble(307,170," ",content,foot1);  
		remindMessage.box(null,null,null,screen.height-30);	//设置弹出窗口的左边、右边、顶边和底边框的位置 
		remindMessage.speed    = 10; 		//设置窗口的弹出速度
		remindMessage.step    = 2; 			//设置窗口的弹出步幅
		remindMessage.show(); 				//弹出窗口
		PopBubble.prototype.oncommand = function(){  
			window.open("BbsServlet?action=getDetail&id="+id,"","width=513,height=567,scrollbars=1");
			this.close = true;
			this.hide(); 			//收缩窗口
		}
	}
}
window.onload=function(){
		loadBubbleTip();
		window.setInterval(loadBubbleTip,1000*20);
}
	</script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body><center>
    <img src="images/main_bg.jpg">
  </center>
  </body>
</html>
