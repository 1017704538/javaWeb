<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.lh.dao.*" %>
<%@ page import="com.lh.model.*" %>
<%
	/**
	*添加通讯信息的表单页
	*/
%>

<%
	
	String flag=request.getParameter("flag");
	String pid=request.getParameter("pid");
	PersonInfo person=null;
	if(pid!=null&&pid!=""){
		person=PersonListDao.getInstance().selectPersonInfoById(pid);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/Untitled-4.css" rel="stylesheet" type="text/css" />
<title>添加联系人</title>
<base target="_self">
<script type="text/javascript" src="<%=request.getContextPath()%>/addresslist/js/FunctionJs.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/addresslist/js/request.js"></script>
<script type="text/javascript">
	var request = false;
	
	function add(){
		var name=document.getElementById("name").value;
		var man=document.getElementById("man");
		var woman=document.getElementById("woman");
		var age=document.getElementById("age").value;
		var mobile=document.getElementById("mobile").value;
		var email=document.getElementById("email").value;
		var address=document.getElementById("address").value;
		var type=document.getElementById("type");
		var typeid=type.value;
		var sex;
		if(man.checked){
			sex=man.value;
		}
		if(woman.checked){
			sex=woman.value;
		}
		if(checkInput()){
			var serverPath = document.getElementById("path").value;
			var url = serverPath+"/PersonListServlet";//服务器地址
			//请求参数
			var parameter = "action=add&typeid="+typeid+"&name="+name
							+"&sex="+sex+"&age="+age+"&mobile="+mobile
							+"&email="+email+"&address="+address;
			request = httpRequest("post",url,true,callbackFunc,parameter);
		}	
	}
	function callbackFunc(){
		if( request.readyState==4 ){ 
	    	 	if( request.status == 200 ){
	    	 		var successFlag=request.responseXML.getElementsByTagName("success");
	        		var errorFlag=request.responseXML.getElementsByTagName("error");
	        		if(typeof(successFlag)!="undefined"&&successFlag.length>0){
						alert("添加成功！");
	            		window.close();
	            		request = false;
	            		window.opener.location.reload(true);
						//window.location.href ="addresslist/personlist.jsp";
	            	}
	        		if(typeof(errorFlag)!="undefined"&&errorFlag.length>0){
	            		alert("添加失败！");
	            		request = false;
	            		return false;
	            	}
			}
		}
	}
	function update(){
		var pid =document.getElementById("pid").value;
		var name=document.getElementById("name").value;
		var man=document.getElementById("man");
		var woman=document.getElementById("woman");
		var age=document.getElementById("age").value;
		var mobile=document.getElementById("mobile").value;
		var email=document.getElementById("email").value;
		var address=document.getElementById("address").value;
		var type=document.getElementById("type");
		var typeid=type.value;
		var sex;
		if(man.checked){
			sex=man.value;
		}
		if(woman.checked){
			sex=woman.value;
		}
		if(checkInput()){
			var serverPath = document.getElementById("path").value;
			var url = serverPath+"/PersonListServlet";//服务器地址
			//请求参数
			var parameter = "action=update&typeid="+typeid+"&pid="+pid+"&name="+name
							+"&sex="+sex+"&age="+age+"&mobile="+mobile
							+"&email="+email+"&address="+address;
			request = httpRequest("post",url,true,updateCallbackFunc,parameter);
			//document.forms.personform.action=document.getElementById("path").value+"/addresslist/saveperson.jsp?action=update&typeid="+typeid;
			//document.forms.personform.method="post";
			//document.forms.personform.submit();
			//window.close();
		
		}	
	}
	function updateCallbackFunc(){
		if( request.readyState==4 ){ 
     	 	if( request.status == 200 ){
     	 		var successFlag=request.responseXML.getElementsByTagName("success");
         		var errorFlag=request.responseXML.getElementsByTagName("error");
         		if(typeof(successFlag)!="undefined"&&successFlag.length>0){
					alert("修改成功！");
             		window.close();
             		request = false;
					window.location.href ="addresslist/personlist.jsp";
             	}
         		if(typeof(errorFlag)!="undefined"&&errorFlag.length>0){
             		alert("修改失败！");
             		request = false;
             		return false;
					//window.location.href ="addresslist/personlist.jsp";
             	}
			}
		}
	}
	function checkInput(){
		var name=document.getElementById("name").value;
		var man=document.getElementById("man");
		var woman=document.getElementById("woman");
		var age=document.getElementById("age").value;
		var mobile=document.getElementById("mobile").value;
		var email=document.getElementById("email").value;
		var address=document.getElementById("address").value;
		if(name==null||name==""){
			alert("请输入姓名！");
			document.getElementById("name").focus();
			return false;
		}
		if(!man.checked&&!woman.checked){
			alert("请选择性别！");
			return false;
		}
		if(age!=null&&age!=""){
			if(isNaN(age)){
				alert("年龄应为数字！");
				document.getElementById("age").focus();
				return false;
			}
			if(age>200||age<=0){
				alert("年龄格式不对！");
				return false;
			}
		}
		
		if(mobile!=null&&mobile!=""){
			if(IsMobTel(mobile)){
				return true;
			}else{
				alert("手机号码格式不正确！");
				document.getElementById("mobile").focus();
				return false;
			}
		}
		
		if(email!=null&&email!=""){
			if(CheckEmail(document.getElementById("email"))){
				return true;
			}
			else{
				return false;
			}
		}
		if(StrChnlength(address)>100){
			alert("住址最多可输入100个字符！");
			MaxInputLimit(document.getElementById("address"),50);
			return false;
		}
		
		return true;
	}
	function closewin(){
		
		window.close();
	}

</script>
</head>
<body>
<form name="personform" id="personform" method="post">
	<input type="hidden" name="path" id="path" value=<%=request.getContextPath() %> />
	<input type="hidden" name="pid" id="pid" value=<%=pid%> />
	<table width="556" height="390" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#333333">
	  <tr>
	    <td>
		    <table width="556" border="0" cellpadding="0" cellspacing="0">
		      <tr>
		        <td height="34" background="images/tianjiatabletop.gif"><img src="images/tianjialianxiren.gif" width="121" height="34" /></td>
		      </tr>
		      <tr>
		        <td height="337">
		        <table width="463" height="273" border="0" cellpadding="0" cellspacing="0">
		          <tr>
		            <td width="106" align="right" class="indextable"><p>姓 &nbsp;&nbsp;名：<br/>
		            </p>              </td>
		            <td height="30" colspan="4" class="indextable"><label>
		            
		            	<input type="text" name="name" id="name"  class="01input" value="<%if(flag!=null&&flag.equals("update")){out.println(person.getpName());} %>"/>
		            </label></td>
		          </tr>
		          <tr>
		            <td align="right" class="indextable"><p>性 &nbsp;&nbsp;   别：<br />
		            </p>              </td>
		            <td width="25" class="indextable">
		           		<input type="radio" name="sex"  id="man" value="m" checked="checked"
		           		<%if(flag!=null&&flag.equals("update")&&person.getpSex().equals("m")){
		            		out.println("checked=checked");}%>/>
		            </td>
		            <td width="38" align="left" class="indextable">男</td>
		            <td width="25" class="indextable">
		          	  <input type="radio" name="sex" id="woman" value="f" 
		          	   <%if(flag!=null&&flag.equals("update")&&person.getpSex().equals("f")){
		            		out.println("checked=checked");}%>/>
		            </td>
		            <td width="258" height="30" align="left" class="indextable">女</td>
		          </tr>
		          <tr>
		            <td align="right" class="indextable"><p>年 &nbsp;&nbsp;    龄：<br />
		            </p>              </td>
		            <td height="30" colspan="4" align="left" class="indextable">
		            	<% if(flag!=null&&flag.equals("update")){%>
		            	 <input type="text" name="age" id="age" class="01input"  value="<%=person.getpAge()%>"/> 
		            	<%}else{%>
		            	 <input type="text" name="age" id="age" class="01input"  /> 
		            	<%} %>
		            </td>
		          </tr>
		          <tr>
		            <td align="right" class="indextable"><p>手机号码：<br />
		            </p>              </td>
		            <td height="30" colspan="4" align="left" class="indextable">
		            <% if(flag!=null&&flag.equals("update")){%>
		            <input type="text" name="mobile" id="mobile" class="01input" value="<%=person.getpMobileNo()%>"/>  
		           <%}else{%>
		            <input type="text" name="mobile" id="mobile" class="01input"/>  
		           <%} %>
		            </td>
		          </tr>
		          <tr>
		            <td align="right" class="indextable"><p>保存类别：<br />
		            </p>              </td>
		            <td height="30" colspan="4" class="indextable"><label>
		            	<select name="type" id="type"  class="01input">
							<%List<PersonType> list=PersonTypeDao.getInstance().getType();
								if(list!=null&&list.size()>0){
									for(int i=0;i<list.size();i++){
										PersonType type=(PersonType)list.get(i);%>
											<option value="<%=type.getTypeId() %>">
												<%=type.getPersonType() %>
											</option>
									<%}
								}%>
						</select>
		            </label></td>
		          </tr>
		          <tr>
		            <td align="right" class="indextable"><p>电子邮件：</p>              </td>
		            <td height="30" colspan="4" align="left" class="indextable">
		             <% if(flag!=null&&flag.equals("update")){%>
		            	<input type="text" name="email" id="email" class="01input" value="<%=person.getEmail()%>"/>
		          	<%}else{ %>
		          		<input type="text" name="email" id="email" class="01input" />
		          	<%} %>
		            </td>
		          </tr>
		          <tr>
		            <td align="right" class="indextable">地  &nbsp;&nbsp;   址：</td>
		            <td colspan="4" class="indextable"><label>
		            <% if(flag!=null&&flag.equals("update")){%>
		            <textarea class="indextablelabelwenbenyu" name="address" id="address"><%=person.getAddress()%></textarea>
		          <%}else{ %>
		          	  <textarea class="indextablelabelwenbenyu" name="address" id="address"></textarea>
		          <%} %>
		            </label></td>
		          </tr>
		          <tr>
		            <td height="50" colspan="5" class="indextable"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
		          		
		          		<%if(flag!=null&&flag.equals("update")){%>
		          		 <img style="cursor:pointer" src="images/tianjialianxirenbaochun.gif" width="72" height="25" border="0" onclick="update()"/> 
		          		<% }else{ %>
		          		 <img style="cursor:pointer" src="images/tianjialianxirenbaochun.gif" width="72" height="25" border="0" onclick="add()"/> 
		          		<%} %>
		           		 <img style="cursor:pointer" src="images/tianjialianxirenguanbi.gif" width="72" height="25" border="0" onclick="window.close()"/>
		            </td>
		          </tr>
		        </table>
	        </td>
	      </tr>
	      <tr>
	        <td bgcolor="b2b2b2">&nbsp;</td>
	      </tr>
	    </table></td>
	  </tr>
	</table>
</form>
</body>
</html>