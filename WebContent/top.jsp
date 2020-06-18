<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
	
	<link rel="stylesheet" type="text/css" href="css/top.css" ></link>
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript">
		$(function(){
			
			//在服务端获取时间
			function getServerDate(){
				var d=0;
				$.ajax({
					url:"AdminServlet.do",
					type:"post",
					data:{flag:"nowdate"},
					async:false,
					dataType:"text",
					success:function(data){
						d=data;
					}
				});
			    return d;
			}
			//定时器5s一次
			setInterval(function(){
				var v=getServerDate();
				$("#div_date").html(v);
			},1000);
		});
	</script>
	
  </head>
  
  <body>
    <table id="t_head">
		   		<tr>
		   			<td id="td1" ></td>
		   			<td id="td2">&nbsp;</td>
		   			<td id="td3">
		   				<a id="td3_a1"  target="centerFrame" href="admin/password_edit.jsp"><img src="images/btn_head_bg1.jpg"/>修改密码</a>
		   				<a  target="centerFrame" href="admin/admin_info.jsp"><img src="images/btn_head_bg1.jpg"/>用户信息</a>
		   				<a href="AdminServlet.do?flag=tuiChuXT" target="_top" onclick="return confirm('确定退出吗')"><img src="images/btn_head_bg1.jpg"/>退出系统</a>
		   			</td>
		
		   		</tr>
		   </table>
			<table id="t_bar" >
					<tr>
							<td id="bar_td1"></td>
							<td id="bar_td2">
								<div id="div_date"></div>
							</td>
							
						<td id="bar_td3">
							 员工信息管理系统</td>
					
					</tr>
			</table>
			<table id="t_title">
					<tr>
							<td id="title_td1">
									<img src="images/main_28.gif"/>
							</td>	
							<td id="title_td2"><img src="images/main_29.gif" /></td>	
							<td id="title_td3"><img src="images/main_30.gif" /></td>	
							<td id="title_td4">&nbsp;
									<label class="admininfo">当前登录用户: ${adminInfo.adminName } 用户状态  : 正常
								</label>
							</td>	
							<td id="title_td5"><img src="images/main_32.gif" /></td>
					</tr>
			</table>
  </body>
</html>