<%@ page language="java" import="java.util.*,com.dao.*,com.beans.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="js/jquery.easing.js"></script>
	<script type="text/javascript" src="js/jquery.accordion.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$('#navigation').accordion({
				active:1,   /* 第2个被激活 */
				header: '.head',
				/*navigation1: false,  */
				event: 'click',
				fillSpace: true,
				animated: 'bounceslide'   /*slide,bounceslide ,bounceslide,easeslide'*/
			});
		});
	</script>
	
	<link rel="stylesheet" type="text/css" href="css/menu.css">
  </head>
  
  <body>
  <ul id="navigation">
  <li><a class="head">账号管理</a>
		<ul>
			<li>
				<a href="admin/admin_add.jsp" target="centerFrame">
				<img src="images/main_48.gif" /><label>账号添加</label></a>
			</li>
			
			<li>
				<a href="AdminServlet.do?flag=tiao" target="centerFrame">
				<img src="images/main_40.gif" /><label>账号维护</label></a>
			</li>
		</ul>
	</li>
	
	<li><a class="head">员工管理</a>
		<ul>
			<li>
				<a href="staff/staff_add.jsp" target="centerFrame">
				<img src="images/main_48.gif" /><label>员工添加</label></a>
			</li>
			
			<li>
				<a href="StaffServlet.do?flag=manage" target="centerFrame">
				<img src="images/main_50.gif" /><label>员工维护</label></a>
			</li>
		</ul>
	</li>
  
	<li><a class="head">部门管理</a>
		<ul>
			<li>
				<a href="department/department_add.jsp" target="centerFrame">
				<img src="images/main_48.gif" /><label>部门添加</label></a>
			</li>
			
			<li>
				<a href="" target="centerFrame">
				<img src="images/main_46.gif" /><label>部门维护</label></a>
			</li>
		</ul>
	</li>
	
	<li><a class="head">职位管理</a>
		<ul>
			<li>
				<a href="" target="centerFrame">
				<img src="images/main_48.gif" /><label>职位添加</label></a>
			</li>
			
			<li>
				<a href="" target="centerFrame">
				<img src="images/main_46.gif" /><label>职位维护</label></a>
			</li>
		</ul>
	</li>
	
	<li><a class="head">关于我们</a>
		<ul>
			<li>
				<a href="about/about.jsp" target="_blank">
				<img src="images/main_52.gif" /><label>公告</label></a>
			</li>
		</ul>
	</li>
  </ul>
	
  </body>
</html>