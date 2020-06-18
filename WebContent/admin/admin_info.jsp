<%@ page language="java" import="java.util.*,com.beans.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	<link rel="stylesheet" type="text/css" href="css/edittable.css"  ></link>  
	<link rel="stylesheet" type="text/css" href="css/validate.css"  ></link>  
	<script type="text/javascript"  src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript">
	$(function(){
		 $("input[type=text],textarea").focus(function(){
			  $(this).addClass("input_focus");
			}).blur(function(){
					$(this).removeClass("input_focus");
			});

		$(".form_btn").hover(function(){
				$(this).css("color","red").css("background","#6FB2DB");
			},function(){
				$(this).css("color","#295568").css("background","#BAD9E3");
			});	
		//-------------------------------------------
		
	});
	</script>
	<style type="text/css">
	input{color:red;}
	</style>
  </head>
   <%
  AdminInfo admin = (AdminInfo)session.getAttribute("adminInfo");
  request.setAttribute("admin", admin);
  %>
  <body>
     <div class ="div_title">
		 <div class="div_titlename"> <img src="images/san_jiao.gif" ><span>当前用户信息</span></div>
	 </div>
	 
		 <table class="edit_table" >
		 		<tr>
	 			 	<td class="td_info">用户账号:</td>	
	 			 	<td class="td_input_short"> 
	 			 		<input  class="txtbox" value="${admin.adminName }" /> 
	 			 	</td>
	 			 	<td></td>
		 		</tr>
		 		
		 		<tr>
	 			 	<td class="td_info">用户密码:</td>	
	 			 	<td class="td_input_short"> 
	 			 		<input  class="txtbox" value="******不该显示用户密码" /> 
	 			 	</td>
	 			 	<td></td>
		 		</tr>
		 		
		 		<tr>
	 			 	<td class="td_info">备注信息:</td>	
	 			 	<td class="td_input_short"> 
	 			 		<input  class="txtbox" value="${admin.note }" /> 
	 			 	</td>
	 			 	<td></td>
		 		</tr>
		 		
		 		
		 </table>
  </body>
</html>