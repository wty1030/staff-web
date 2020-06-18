<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<script type="text/javascript"  src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript">
	$(function(){
		
		$("#ch_checkall,#top_ch_checkall").click(function(){
			if(this.checked){
				$("input[name=ck_id]").attr("checked","checked");
			}else{
				$("input[name=ck_id]").removeAttr("checked");
			}		
		});
				
		$("table tr").mouseover(function(){
			$(this).css("background","#D3EAEF");
			$(this).siblings().css("background","white");
		});
		//----------------------------------------------------------
		//全选按钮
		$("#top_ch_checkall").click(quanXuan);
		$("#ch_checkall").click(quanXuan);
		function quanXuan(){
			var v=this.checked;
			var arr=document.getElementsByName("checks");
			for(var i=0;i<arr.length;i++){
				arr[i].checked=v;
			}
			$("#top_ch_checkall")[0].checked=v;
			$("#ch_checkall")[0].checked=v;
		}
		
		//下拉菜单回显
		$("#department").val($("#departmentName").val());
		$("#position").val($("#positionName").val());
		
		//查询
		$("#cha").click(function(){
			$("#flag").val("cha");
		});
		//删除
		$("#shan").click(function(){
			$("#flag").val("shan");
			
			var r=false;
			var arr=document.getElementsByName("checks");
			for(var i=0;i<arr.length;i++){
				//---------------------
				if(arr[i].checked==true){
					r=true;
				}
			}
			
			if(r){
				r=confirm("确定删除吗");
			}else{
				alert("请选择需要删除的人员");
			}
			if(r){
				document.getElementById("myForm").submit();
			}
			return r;
		});
		
		
	});
	</script>
	<link rel="stylesheet" type="text/css" href="css/maintable.css" ></link>
	<style type="text/css">
		a{
			text-decoration: none;
		}
		form{margin:0px;display: inline}
		#tiao,.clBk{
			border:1px solid #8FB9D0;
			margin:1px 5px;
			padding:0.5px;
		}
		#tiao{
			background:#D3EFFB;
			padding:0px;
		}
		#tiao:link,#tiao:visited,.clBk:link,.clBk:visited{
			color:#295568;
			background:#D3EFFB;
		}
		#tiao:hover,.clBk:hover{
			color:red;
		}
		#delAll{
			position: absolute;
			top:0px;
			left:100px;
			visibility:  hidden;
		}
		.top{
			width:98%;
			margin:0 auto;
			margin:5px 0px 5px 3px;
			background:#D3EAEF;
			padding:3px 0px 12px 24px;
		}
	</style>
  </head>
  
  <body>
		<div class ="div_title">
		<div class="div_titlename"> <img src="images/san_jiao.gif" ><span>员工基本信息列表</span></div>
		<div class="div_titleoper">
			<input type="checkbox" id="top_ch_checkall"/> 全选 <a href="staff/staff_add.jsp"> <img src="images/add.gif"/>添加 </a> <a href="javascript:void(0)"  ><img src="images/del.gif"/><span id="shan">删除</span></a> </div>
		</div>
		
		<form id="myForm" action="StaffServlet.do" method="post">
			<input type="hidden" id="flag" name="flag" value="cha" />
			
			<div class="top">
					员工姓名： <input name="staffName" value="${staffName }" /> 
					所属部门：<input id="departmentName" value="${departmentId }" type="hidden" />
						<select id="department" name="departmentId">
							<option value="">==全部部门==</option>
							<c:forEach var="dId" items="${dList }">
								<option value="${dId.id }">${dId.name }</option>
							</c:forEach>
						</select>
					职位：<input id="positionName" value="${positionId }" type="hidden" />
					<select id="position" name="positionId">
						<option value="">==全部职位==</option>
						<c:forEach var="pId" items="${pList }">
							<option value="${pId.id }">${pId.name }</option>
						</c:forEach>
					</select>
					 <input type="submit" id="cha" value="查询" />
				 </div>
		
		
			<table class="main_table" >
				<tr>
		 			<th><input type="checkbox" id="ch_checkall" /></th>	<th>员工姓名</th> 	<th>所属部门</th>	<th>职位</th>  <th>工资</th> <th>操作</th>
		 		</tr>
		 		
		 		<c:forEach items="${staffList }" var="s">
							<tr>
								<td><input name="checks" type="checkbox" value="${s.id }" /></td>
								<td>${s.staffName }</td>
								<td>${s.departmentId }</td>
								<td>${s.positionId }</td>
								<td>${s.wages }</td>
								<td>
									<a href="StaffServlet.do?flag=xiuGai&id=${s.id }">修改</a> |
									<a onclick="return confirm('确定删除吗')" href="StaffServlet.do?flag=shanChuOne&id=${s.id }">删除</a>
								</td>
							</tr>
		 		</c:forEach>
		 		
		 	</table>
		 </form>
  </body>
  <script type="text/javascript">
  	$(function(){
  	//下拉菜单回显
		$("#department").val($("#departmentName").val());
		$("#position").val($("#positionName").val());
  	});
  </script>
</html>