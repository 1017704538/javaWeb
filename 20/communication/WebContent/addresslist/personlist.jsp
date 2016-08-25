<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.lh.model.*,com.lh.dao.*" %>
<%
	/**
	*通讯信息列表页
	*@auther LH 
	*
	*/
%>
<%
	User sessionUser = null;//从登陆用户或注册用户Session取出已有用户对象
	if((User)session.getAttribute("enterUser")!=null){//判断Session中是否存在登录用户
		sessionUser=(User)session.getAttribute("enterUser");
	}
	String ordName = request.getParameter("ordName");//获取排序的值
	String flagOrder = "";
	if(ordName == null){
		ordName = "0";
	}
	if(ordName!=null&&!ordName.equals("")){
		if(ordName.equals("0")){//按降序
			flagOrder="↓";
		}
		else if(ordName.equals("1")){//按升序
			flagOrder="↑";
		}
	}
	String currentP = request.getParameter("currentp");//获取当前页的值
	// 点击是上一页还是下一页
	String preOrnext = request.getParameter("pageAction");
	int currentPage = 1;//当前页
	int totalRows = 0;//数据总条数
	int totalPages = 0;//总页数
	int pageSize = 10;//每页显示的最大条数
	int firstResult = 0;//读取数据的开始位置
	if (currentP != null) 
	{
		currentPage = Integer.parseInt(currentP);
	}
	
	Page page1 = new Page();//分页封装类
	String flag=request.getParameter("flag");//查询条件用到的的标记
	String namestr = request.getParameter("name");//从查询输入框中获取的用户名
	String  typeid = request.getParameter("typeid");//从查询中获取的类别编号
	if(flag!=null&&flag.equals("select")){//根据查询条件，获取的数据总条数
		PersonType selecttype = PersonTypeDao.getInstance().selectPersonTypeById(typeid);
		totalRows=PersonListDao.getInstance().selectPersonInfoCount(sessionUser,namestr,selecttype);
	}else{//第一次加载该页时所有数据总条数
		totalRows=PersonListDao.getInstance().getInfoCount(sessionUser);
	}
	//把分页的各项值封装到Page类中
	page1.init(totalRows, pageSize);
	page1.setCurrentPage(currentPage);
	//计算出点击下一页时，读取数据的开始位置，根据当前页currentPage的值改变
	firstResult = (currentPage - 1) * pageSize;
	//封装到Page类中
	page1.setFirstResult(firstResult);
	//通讯录信息列表集合。判断如果页面提交状态是查询，就把查询出的集合赋给personlist,否则就是第一次加载页时的 集合
	List<PersonInfo> personList=null;
	if(flag!=null&&flag.equals("select")){
		PersonType selecttype = PersonTypeDao.getInstance().selectPersonTypeById(typeid);
		personList=PersonListDao.getInstance().selectPersonInfo(sessionUser,namestr, selecttype,firstResult,pageSize,ordName);
	}else{
		personList=PersonListDao.getInstance().getPersonListInfo(sessionUser,firstResult,pageSize,ordName);
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/Untitled-4.css" rel="stylesheet" type="text/css" />
<title>迅友联系人管理</title>
<script type="text/javascript">
	/**
	*删除记录
	*/
	
function deleteInfo(){
	var pidstr=choosePersonid();
	if(pidstr==""){
		alert("请选择一条信息！");
	}else{
		if(window.confirm("您确定要删除选中信息吗？")==true){
			
			document.getElementById("listform").action="<%=request.getContextPath()%>"+"/addresslist/delperson.jsp?&allpid="+pidstr;
			document.getElementById("listform").method="post";
			document.getElementById("listform").submit();
		}
		else
			return;
	}
}
	/**
	*返回选中所有信息编号字符串
	*/
	
function choosePersonid(){
	var pidStr="";
	var pidobj=document.getElementsByName("checkonedel");//获得复选框对象数组  
	for(var i=0;i<pidobj.length;i++){
		if(pidobj[i].checked==true){//根据checked属性判断是否选中 
			
			pidStr=pidStr+pidobj[i].value+",";//将选中项的值连接成一个以","分隔的字符串 
		}
		
	}
	return pidStr;
}
	/**
	*复选框的全选或全不选
	*/
	function selectAllCheckBox(){
		var allcheckObj=document.getElementById("checkalldel");
		var pidobj=document.getElementsByName("checkonedel");
		
		if(allcheckObj.checked==true){
			for(var i=0;i<pidobj.length;i++){	
				pidobj[i].checked=true;	
			}
		}
		if(allcheckObj.checked==false){
			for(var i=0;i<pidobj.length;i++){	
				pidobj[i].checked=false;	
			}
		}
	}
	/**
	*打开一个窗口页
	*@param url:打开页的路径
	*/
	
	function showWindow(url){
		window.showModalDialog(url,'window','status=no;dialogWidth=590px;dialogHeight=450px;center=yes;help=no;location=no;');	
	}
	/**
	*查询通讯录信息
	*查询条件：姓名和类别
	*/
	function selectPerson(){
	
		var type=document.getElementById("type");
		document.getElementById("typeid").value=type.value;
		document.getElementById("flag").value="select";
		document.getElementById("listform").action="personlist.jsp";
		document.getElementById("listform").method="post";
		document.getElementById("listform").submit();
	}
	/**
	*测试方法
	*/
	function setTypeid(){
		if("<%=typeid%>"!=null&&"<%=typeid%>"!=""){
			var selecttype=document.getElementById("type");
			selecttype.value="<%=typeid%>";
		}
	}
	/**
	*根据姓名列表排序
	*/
	function ordName()
	{
		var order=document.getElementById("ordName").value;
		if(order=="0")
		{
			document.getElementById("ordName").value="1";
		}
		if(order=="1")
		{
			document.getElementById("ordName").value="0";
		}
		var flag=document.getElementById("flag").value;
		if(flag=="select"){
			selectPerson();
		}else{
			document.getElementById("listform").action="personlist.jsp";
			document.getElementById("listform").submit();
		}
	
	}
	function add(){
		var url="<%=request.getContextPath()%>/addresslist/addpersoninfo.jsp?flag=add&pid=";
		var ret=window.showModalDialog(url,'window','status=no;dialogWidth=590px;dialogHeight=450px;center=yes;help=no;location=no;');
		if(typeof(ret)==="string"){
			window.location.href="addresslist/personlist.jsp";
		}
	}
	function addType(){
		var url="addpersontype.jsp";
		var ret=window.showModalDialog(url,'window','status=no;dialogWidth=560px;dialogHeight=190px;center=yes;help=no;location=no;');
		if(typeof(ret)==="string"){
			
			window.location.href="addresslist/personlist.jsp";
		}
	}
</script>
</head>
<body bgcolor="#FFFFFF">
	<form action="" id="listform">
		<input type="hidden" name="typeid" id="typeid" <%=typeid %>/>
		<input type="hidden" name="ordName" id="ordName" value="<%=ordName%>" />
		<input type="hidden" name="flag" id="flag" value="<%=flag %>" />
		<table width="1000" border="0" align="center" cellpadding="0" cellspacing="0" id="__01">
			<tr>
				<td height="124" colspan="4"><img src="images/zhuce_01.gif" width="1000" height="124" /></td>
			</tr>
			<tr>
				<td width="12" height="431" align="left" valign="top" background="images/zuoshuxian.gif" bgcolor="#FFFFFF">&nbsp;</td>
			    <td width="191" height="431" align="center" valign="top" bgcolor="#FFFFFF">
				    <table   width="188" height="380" border="1" cellpadding="0" cellspacing="0" bordercolor="b2b2b2">
			          <tr >
			            <td width="184" height="37" align="left" bgcolor="f2f1f1"><img src="images/index01.gif" width="184" height="37" /></td>
			          </tr>
			          <tr>
			            <td width="184" height="311" align="center" valign="top" bgcolor="f2f1f1"><table width="180" height="388" border="0" cellpadding="0" cellspacing="0">
			              <tr>
			                <td height="74"><a href="#"><img src="images/family.gif" width="176" height="72" border="0" /></a></td>
			              </tr>
			              <tr>
			                <td  height="74"><a href="#"><img src="images/friend.gif" width="176" height="71" border="0" /></a></td>
			              </tr>
			              <tr>
			                <td height="74"><a href="#"><img src="images/classmate.gif" width="176" height="72" border="0" /></a></td>
			              </tr>
			              <tr>
			                <td height="74"><a href="#"><img src="images/colleague.gif" width="176" height="71" border="0" /></a></td>
			              </tr>
			              <tr>
			                <td height="88"><p>&nbsp;</p>
			                    <p>&nbsp;</p></td>
			              </tr>
			            </table>
		            </td>
		          </tr>
		        </table>
		        </td>
			    <td width="787" height="431" align="center" valign="top" bgcolor="#FFFFFF">
			    <table width="787"  border="1" cellpadding="0" cellspacing="0" bordercolor="b2b2b2">
		          <tr height="30">
		            <td colspan="8" align="left" valign="middle" background="images/indextable.gif"> &nbsp;&nbsp;
		            	 <input  style="cursor:pointer" type ="button" value="添加讯友" onclick="add()" />
			          
			           &nbsp;&nbsp;&nbsp;
			            <input  style="cursor:pointer" type ="button" value="删除讯友" onclick="deleteInfo()" />
			           &nbsp;&nbsp;&nbsp;
			            <input  style="cursor:pointer" type ="button" value="添加类别" onclick="addType()" />
		            </td>
		          </tr>
		          <tr height="30">
		            <td colspan="8"  align="left" valign="middle" background="images/indextable.gif" class="indextable"> &nbsp;&nbsp;
						姓名:<input type="text" id="name" name="name" value="<%if(namestr!=null){out.println(namestr);}%>" />
						类别：
						<select name="type" id="type" style="width: 100">
							<option value="">请选择</option>
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
							<input type="button" value="查   询" onclick="selectPerson()"/>
		            </td>
		          </tr>
          		 <tr height="30">
	              	<td width="30"  bgcolor="f2f1f1" >
						<input type="checkbox" name="checkalldel" id="checkalldel" onclick="selectAllCheckBox()" />
					</td>
					<td width="60" bgcolor="f2f1f1" class="indextable">
						<a href="javascript: ordName()">姓名 <%=flagOrder %></a>
					</td>
					<td width="60" bgcolor="f2f1f1" class="indextable">类别</td>
					<td width="50" bgcolor="f2f1f1" class="indextable" >性别</td>
					<td width="40" bgcolor="f2f1f1" class="indextable">年龄</td>
					<td width="100" bgcolor="f2f1f1" class="indextable">手机</td>
					<td width="100" bgcolor="f2f1f1" class="indextable">email</td>
					<td width="147"bgcolor="f2f1f1" class="indextable">住址</td> 
	              </tr>     
			  <%
				if(personList!=null&&personList.size()>0){
					for(int i=0;i<personList.size();i++){
						PersonInfo person=(PersonInfo)personList.get(i);
						PersonType type=PersonTypeDao.getInstance().selectPersonTypeById(person.getType().getTypeId());
			  %>
	              <tr >
	              	<td  bgcolor="f2f1f1" >
						<input type="checkbox" name="checkonedel"   value="<%=person.getpId() %>"/>
					</td>
					<td  bgcolor="f2f1f1" class="indextable">			
						<a href="javascript:showWindow('addpersoninfo.jsp?flag=update&pid=<%=person.getpId() %>')"><%=person.getpName() %></a>
					</td>
					<td  bgcolor="f2f1f1" class="indextable"><%=type.getPersonType() %></td>
					<td  bgcolor="f2f1f1" class="indextable">
					<%if(person.getpSex().equals("m")){out.println("男");}else{out.println("女");} %>
					</td>
					<td  bgcolor="f2f1f1" class="indextable">
					<%if(person.getpAge()==0||person.getpAge()==null){out.println(" ");}
					else{out.println(person.getpAge());} %>
					</td>
					<td  bgcolor="f2f1f1" class="indextable">
					<%if(person.getpMobileNo()==null) out.println(" ");
						else out.println(person.getpMobileNo());
					%>
					</td>
					<td  bgcolor="f2f1f1" class="indextable">
					<%if(person.getEmail()==null) out.println(" ");
						else out.println(person.getEmail());
					%>
					</td>
					<td  bgcolor="f2f1f1" class="indextable">
					<%if(person.getAddress()==null) out.println(" ");
						else out.println(person.getAddress());
					%>
					</td>
	              </tr>
			        <%}
				}%>      	 
	             <tr >
					<td colspan="8"  align="center" bgcolor="f2f1f1" class="indextable">
						共[<%=page1.getTotalRows()%>]条记录
						<%if(page1.isHasPrevious()){%>
							<a href="personlist.jsp?currentp=1">首页</a>
							<a href="personlist.jsp?currentp=<%=page1.getCurrentPage()-1%>&pageAction=before">上一页</a>
						<%} else {%>
							<a>首页</a>&nbsp;&nbsp;<a>上一页</a>
						<%}if (page1.isHasNext()) {%>
							<a href="personlist.jsp?currentp=<%=page1.getCurrentPage()+1%>&pageAction=next">下一页</a>
							<a href="personlist.jsp?currentp=<%=page1.getTotalPages()%>">尾页</a>
						<%} else {%>
							<a>下一页</a>&nbsp;&nbsp;<a>尾页</a>
						<%}%>
						当前为[<%=page1.getCurrentPage()%>/<%=page1.getTotalPages()%>]页
					</td>
				</tr>
		        </table>
		        </td>
			    <td width="10" height="431" align="right" background="images/youshuxian.gif" bgcolor="#FFFFFF">&nbsp;</td>
			</tr>
			<tr>
				<td width="12" background="images/zhuce_04.gif">
					<img id="zhuce_03" src="images/zhuce_03.gif" width="12" height="99" alt="" /></td>
				<td colspan="2" align="right" valign="top" background="images/zhuce_04.gif"><img src="images/zhuce_08.gif" width="374" height="45" /></td>
				<td width="10" align="right" background="images/zhuce_04.gif">
					<img id="zhuce_06" src="images/zhuce_06.gif" width="10" height="99" alt="" /></td>
			</tr>
		</table>
	</form>
</body>
</html>