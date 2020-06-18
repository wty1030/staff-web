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
	
	<link rel="stylesheet" type="text/css" href="css/edittable.css"  ></link>  
	<link rel="stylesheet" type="text/css" href="css/validate.css"  ></link>  
	<script type="text/javascript"  src="js/jquery-1.8.0.js"></script>
		
	 <script>		
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
			
			//验证用户名
			$("#adminName").click(function(){
				$("#adminName_msg").html("4-15位只限数字和英文,不区分大小写");
			});
			$("#adminName").blur(adminNam);
			function adminNam(){
				var rr=false;
				$.ajax({
					url:"AdminServlet.do",
					method:"post",
					data:{flag:"yzAdminName",adminName:$("#adminName").val()},
					dataType:"text",
					async:false,
					success:function(data){
						if(data=="0"){
							$("#adminName_msg").html("<font color=green>√</font>");
							rr= true;
							return true;
						}else if(data=="1"){
							$("#adminName_msg").html("<font color=red>用户名已存在</font>");
							rr= false;
						}else if(data=="2"){
							$("#adminName_msg").html("<font color=red>格式错误</font>");
							rr= false;
						}
					}
				});
				return rr;
			}
			
			//验证密码1
			$("#password").click(function(){
				$("#password_msg").html("数字或英文,6-20位");
			});
			$("#password").blur(passwor);
			function passwor(){
				var reg=/^[0-9a-zA-Z]{6,12}$/;
				if(!reg.test($("#password").val())){
					$("#password_msg").html("<font color=red>格式错误</font>");
					return false;
				}else{
					$("#password_msg").html("<font color=green>√</font>");
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
				var p1=$("#password").val();
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
				if(!adminNam()){
					r=false;
				}
				if($("#adminName_msg").html()=="√"){
					r=true;
				}
				if(!passwor()){
					r=false;
				}
				if(!pwdconfir()){
					r=false;
				}
				if(r){
					return confirm("确认提交吗");
				}
				return r;
			});
			
		});		
	</script>
	<%--<style type="text/css">
		 tr:hover{
			background:#ADD2DA;
		}
	</style>--%>
  </head>
  
   <body>
     <div class ="div_title">
		 <div class="div_titlename"> <img src="images/san_jiao.gif" ><span>管理员添加</span></div>
	 </div>
				 
	 <form action="AdminServlet.do" >
	 	<input type="hidden" name="flag" value="addAdmin" />
		 <table class="edit_table" >
		 		<tr>
		 			 	<td class="td_info">用户账号:</td>	
		 			 	<td class="td_input_short"> 
		 			 		<input type="text" class="txtbox" id="adminName" name="adminName" value="${admin.adminName }" /> 
		 			 	</td>   
		 			 	<td>
		 			 		<label class="validate_info" id="adminName_msg">4-15位；只限数字(0-9)和英文(a-z),不区分大小写</label>
		 			 	</td> 
		 		</tr>
		 			<tr>
		 				<td class="td_info">用户密码:</td>	
		 				<td>
		 					<input type="text"  class="txtbox" name="password"  id="password" value="${admin.password }" />
		 				</td> 
		 				<td><label  class="validate_info" id="password_msg" >数字或英文,6-20位</label></td>	
		 		</tr>
		 			<tr>
		 				<td class="td_info">重复密码:</td>	
		 				<td><input type="text" value="${admin.password }"  class="txtbox" id="pwdconfirm" /> 
		 				</td>
		 				<td><label  class="validate_info" id="pwdconfirm_msg">请重复输入密码</label></td>	
		 		</tr>
		 		<tr>
		 			<td class="td_info">备注信息:</td>	
		 			<td><textarea rows="4" cols="27" id="note" name="note" class="txtarea">${admin.note }</textarea> </td>	
		 			<td><label></label></td>	
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
