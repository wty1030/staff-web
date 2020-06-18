<%@ page language="java" import="java.util.*,com.beans.*,com.dao.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		//----------------------------------------------------------------
		//回显下拉框数据
		$("#departmentId").val($("#dId").val());
		$("#positionId").val($("#pId").val());
		
		//员工姓名验证
		$("#staffName").click(function(){
			$("#staffName_msg").html("1-15位除空格外任意字符");
		});
		$("#staffName").blur(staffNam);
		function staffNam(){
			var r=false;
			var v=$("#staffName").val();
			var rsg=/^\S{1,15}$/;
			if(rsg.test(v)){
				r=true;
				$("#staffName_msg").html("<font color=green>√</font>");
			}else{
				$("#staffName_msg").html("<font color=red>格式错误</font>");
			}
			return r;
		}
		
		
		//工资验证
		$("#wages").click(function(){
			$("#wages_msg").html("4-9位正数");
		});
		$("#wages").blur(wage);
		function wage(){
			var r=false;
			var v=$("#wages").val();
			var rsg1=/^[1-9][0-9]{3,8}[.][0-9]+$/;
			var rsg2=/^[1-9][0-9]{3,8}$/;
			if(rsg1.test(v)||rsg2.test(v)){
				r=true;
				$("#wages_msg").html("<font color=green>√</font>");
			}else{
				$("#wages_msg").html("<font color=red>格式错误</font>");
			}
			return r;
		}
		
		//提交总验证
		$("#subm").click(function(){
			var r=true;
			
			if(!staffNam()){
				r=false;
			}
			
			if(!wage()){
				r=false;
			}
			
			if(r){
				r=confirm("确定添加吗");
			}
			return r;
		});
		
	});
	</script>
  </head>
  <%
  StaffDao dao=new StaffDao();
  request.setAttribute("dList", dao.getDepartmentList());
  request.setAttribute("pList", dao.getPositionList());
  %>
  <body>
     <div class ="div_title">
		 <div class="div_titlename"> <img src="images/san_jiao.gif" ><span>员工添加</span></div>
	 </div>
	 
	 <form action="StaffServlet.do" method="post">
	 	<input type="hidden" name="flag" value="addStaff" />
	 	<table class="edit_table" >
	 		<tr>
	 			 	<td class="td_info">员工姓名:</td>	
	 			 	<td class="td_input_short"> 
	 			 		<input type="text" class="txtbox" id="staffName" name="staffName" value="${staff.staffName }" /> 
	 			 	</td>   
	 			 	<td>
	 			 		<label class="validate_info" id="staffName_msg">1-15位除空格外任意字符</label>
	 			 	</td> 
	 		</tr>
	 		
	 		
	 		<tr>
	 			 	<td class="td_info">所属部门:</td>	
	 			 	<td class="td_input_short"> 
	 			 		<input type="hidden" id="dId" value="${staff.departmentId }" />
	 			 		<select name="departmentId" id="departmentId">
		 			 		<c:forEach var="d" items="${dList }">
		 			 			<option value="${d.id }">${d.name }</option>
		 			 		</c:forEach>
	 			 		</select>
	 			 	</td>   
	 			 	<td>
	 			 		<label class="validate_info" id="departmentId_msg"></label>
	 			 	</td> 
	 		</tr>
	 		
	 		<tr>
	 			 	<td class="td_info">职位:</td>	
	 			 	<td class="td_input_short"> 
	 			 		<input type="hidden" id="pId" value="${staff.positionId }"  />
	 			 		<select name="positionId" id="positionId">
		 			 		<c:forEach var="p" items="${pList }">
		 			 			<option value="${p.id }">${p.name }</option>
		 			 		</c:forEach>
	 			 		</select>
	 			 	</td>   
	 			 	<td>
	 			 		<label class="validate_info" id="positionId_msg"></label>
	 			 	</td> 
	 		</tr>
	 		
	 		<tr>
	 			 	<td class="td_info">工资:</td>	
	 			 	<td class="td_input_short"> 
	 			 		<input type="text" class="txtbox" id="wages" name="wages" value="${staff.wages }" /> 
	 			 	</td>   
	 			 	<td>
	 			 		<label class="validate_info" id="wages_msg">4-9位正数</label>
	 			 	</td> 
	 		</tr>
	 		
	 		<tr>
	 			<td class="td_info"> </td>	
	 			<td> 
	 			<input id="subm" class="form_btn" type="submit" value="添加" /> 
	 			<input type="reset"  class="form_btn" value="重置" /> </td>	
	 			<td>
	 				<label id="result_msg" class="result_msg">${result_msg }</label>
	 			</td>	
	 		</tr>
	 	</table>
	 </form>
  </body>
</html>