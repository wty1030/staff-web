<%@ page language="java" import="java.util.*,com.beans.*,com.dao.*" pageEncoding="UTF-8"%>
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
		//验证原来密码是否正确
		$("#othersPassword").click(function(){
			$("#othersPassword_msg").html("请输入原来的密码");
		});
		$("#othersPassword").blur(othersPasswor);
		function othersPasswor(){
			var rr=false;
			$.ajax({
				url:"AdminServlet.do",
				method:"post",
				data:{flag:"yzothersPassword",othersPassword:$("#othersPassword").val()},
				dataType:"text",
				async:false,
				success:function(data){
					if(data=="0"){
						$("#othersPassword_msg").html("<font color=green>√</font>");
						rr= true;
						return true;
					}else if(data=="1"){
						$("#othersPassword_msg").html("<font color=red>格式错误</font>");
						rr= false;
					}
				}
			});
			return rr;
		}
		
		//验证密码1
		$("#newPassword").click(function(){
			$("#newPassword_msg").html("数字或英文,6-20位");
		});
		$("#newPassword").blur(passwor);
		function passwor(){
			var reg=/^[0-9a-zA-Z]{6,12}$/;
			if(!reg.test($("#newPassword").val())){
				$("#newPassword_msg").html("<font color=red>格式错误</font>");
				return false;
			}else{
				$("#newPassword_msg").html("<font color=green>√</font>");
				return true;
			}
		}
		
		//验证密码2
		$("#pwdconfirm").click(function(){
			$("#pwdconfirm_msg").html("数字或英文,6-20位");
		});
		$("#pwdconfirm").blur(pwdconfir);
		function pwdconfir(){
			passwor();
			var reg=/^[0-9a-zA-Z]{6,12}$/;
			var p1=$("#newPassword").val();
			var p2=$("#pwdconfirm").val();
			if(p1==""){
				$("#pwdconfirm_msg").html("<font color=red>第一次密码未填写</font>");
				return false;
			}else if(p1!=p2){
				$("#pwdconfirm_msg").html("<font color=red>两次密码填写不一致</font>");
				return false;
			}else if(reg.test(p2)){
				$("#pwdconfirm_msg").html("<font color=green>√</font>");
				return true;
			}else{
				$("#pwdconfirm_msg").html("<font color=red>格式错误</font>");
				return false;
			}
		}
		//总验证
		$("#subm").click(function(){
			var r=true;
			
			if(!othersPasswor()){
				r=false;
			}
			if(!passwor()){
				r=false;
			}
			if(!pwdconfir()){
				r=false;
			}
			
			if(r){
				r=confirm("确定修改密码吗,修改后将需要重新登录");
			}
			return r;
		});
		
	});
	</script>
  </head>
  <%
  AdminInfo admin = (AdminInfo)session.getAttribute("adminInfo");
  request.setAttribute("admin", admin);
  %>
  <body>
     <div class ="div_title">
		 <div class="div_titlename"> <img src="images/san_jiao.gif" ><span>用户密码修改</span></div>
	 </div>
				 
	 <form action="AdminServlet.do" >
	 	<input type="hidden" name="flag" value="updatePassword" />
	 	<input type="hidden" id="id" value="${admin.id }" />
	 	
		 <table class="edit_table" >
		 		<tr>
		 			 	<td class="td_info">用户账号:</td>	
		 			 	<td class="td_input_short"> 
		 			 		<input type="text" class="txtbox" id="adminName" name="adminName" readonly="readonly" value="${admin.adminName }" /> 
		 			 	</td>   
		 			 	<td> </td> 
		 		</tr>
		 		
		 		<tr>
		 				<td class="td_info">原来的密码:</td>	
		 				<td>
		 					<input type="text"  class="txtbox"  id="othersPassword" />
		 				</td> 
		 				<td><label  class="validate_info" id="othersPassword_msg" >请输入原来的密码</label></td>	
		 		</tr>
		 		
		 		<tr>
		 				<td class="td_info">新密码:</td>	
		 				<td><input type="text" class="txtbox" name="newPassword" id="newPassword" /> 
		 				</td>
		 				<td><label  class="validate_info" id="newPassword_msg">数字或英文,6-20位</label></td>	
		 		</tr>
		 		
		 		<tr>
		 				<td class="td_info">重复密码:</td>	
		 				<td><input type="text" class="txtbox" id="pwdconfirm" /> 
		 				</td>
		 				<td><label  class="validate_info" id="pwdconfirm_msg">请重复输入密码</label></td>	
		 		</tr>
		 		
		 		<tr>
		 			<td class="td_info"> </td>	
		 			<td> 
		 			<input id="subm" class="form_btn" type="submit" value="提交" /> 
		 			<input type="reset"  class="form_btn" value="重置" /> </td>	
		 			<td>
		 				<label id="result_msg" class="result_msg">${result_msg }</label>
		 			</td>	
		 		</tr>
		</table>
     </form>
  </body>
</html>