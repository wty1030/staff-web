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
	
	<link rel="stylesheet" type="text/css" href="css/login.css" />
	<script src="js/jquery-1.8.0.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			
			$("#btn_img").click(function(){
				$.ajax({
					url:"LoginServlet",
					method:"post",
					data:{adminName:$("#adminName").val(),password:$("#password").val()},
					dataType:"text",
					success:function(data){
						if("t"==data){//正确
							window.location.href="main.html";
						}else if("f"==data){//错误
							alert("用户名或密码错误");
						}else if("s"==data){//账号锁定
							alert("账号已锁定");
						}else if("j"==data){//账号禁用
							alert("账号已禁用");
						}
					}
				});
			});
			
		});
	</script>
	<style type="text/css">
		#password{
			position: absolute;
			top:25px;
			left:0px;
		}
	</style>
  </head>
  
  <body>
    <div id="div_center">
			<div id="div_inputbox">
				<input type="text" id="adminName" placeholder="${msg }"  />
				<input type="password" id="password" />
			</div>
			<input id="btn_img" type="image" src="images/bg_login_btn.jpg" />
 	 </div>
  </body>
</html>