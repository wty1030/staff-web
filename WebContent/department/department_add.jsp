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
			$("#dname").click(function(){
				$("#name_msg").html("非空格");
			});
			$("#dname").blur(nam);
			function nam(){
				var rr=false;
				$.ajax({
					url:"DepartmentServlet.do",
					method:"post",
					data:{flag:"yzName",dname:$("#dname").val()},
					dataType:"text",
					async:false,
					success:function(data){
						if(data=="0"){
							$("#name_msg").html("<font color=green>√</font>");
							rr= true;
							return true;
						}else if(data=="1"){
							$("#name_msg").html("<font color=red>该部门已存在</font>");
							rr= false;
						}else if(data=="2"){
							$("#name_msg").html("<font color=red>格式错误</font>");
							rr= false;
						}
					}
				});
				return rr;
			}

			
			//总验证
			$("#subm").click(function(){
				var r=true;
				if(!nam()){
					r=false;
				}
				//if($("#name_msg").html()=="√"){
				//	r=true;
				//}
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
				 
	 <form action="DepartmentServlet.do" >
	 	<input type="hidden" name="flag" value="addDep" />
		 <table class="edit_table" >
		 		<tr>
		 			 	<td class="td_info">部门名称:</td>	
		 			 	<td class="td_input_short"> 
		 			 		<input type="text" class="txtbox" id="dname" name="dname" value="${param.dname }" /> 
		 			 	</td>   
		 			 	<td>
		 			 		<label class="validate_info" id="name_msg">非空格</label>
		 			 	</td> 
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
