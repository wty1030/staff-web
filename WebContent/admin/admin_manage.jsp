<%@ page language="java" import="java.util.*,com.beans.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script> 
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
			//----------------------------------------------
			//锁定
			sD=function(id){
				var nowId=$("#nowid").val();
				if(id==nowId){
					alert("当前登录用户不能锁定");
					return false;
				}else{
					return confirm("确定锁定吗");
				}
			}

			//修改
			xG=function(id){
				var nowId=$("#nowid").val();
				if(id==nowId){
					alert("当前登录用户不能修改");
					return false;
				}else{
					return confirm("确定修改吗");
				}
			}
			
			//删除
			shCh=function(id){
				var nowId=$("#nowid").val();
				if(id==nowId){
					alert("当前登录用户不能删除");
					return false;
				}else{
					return confirm("确定删除吗");
				}
			}
			
			//------------------------------------------------
			$("#tiao").css("font-size","12px").css("width","25px");
			
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
			//删除按钮
		
			//$("#delAll").click(dellAll);
			//form1.submit(dellall);
			dellAll=function(){
				var nowId=$("#nowid").val();
				var r=false;
				var arr=document.getElementsByName("checks");
				for(var i=0;i<arr.length;i++){
					//---------------------
					if(arr[i].checked==true&&arr[i].value==nowId){
						alert("当前登录用户不能删除");
						return false;
					}
					//---------------------
					if(arr[i].checked==true){
						r=true;
					}
				}
				
				if(r){
					r=confirm("确定删除吗");
				}else{
					alert("请至少选择一个");
				}
				if(r){
					document.getElementById("myForm").submit();
				}
				return r;
			}
			//转到按钮
			$("#tiao").click(function(){
				var r=true;
				var index=$("#index").val();
				var pageIndex=$("#pageIndex").val();
				var reg=/^\d+$/;
				if(!reg.test(pageIndex)){
					alert("页数格式错误");
					r= false;
				}
				else if(pageIndex>index){
					alert("您输入的页数超过了最大页数");
					r= false;
				}
				else if(pageIndex<1){
					alert("您输入的页数小于了最小页数");
					r= false;
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
	</style>
  </head>
  
  <%
  AdminInfo admin=(AdminInfo)request.getSession().getAttribute("adminInfo");
  request.setAttribute("nowAdmin", admin);
  %>
  
  <body>
  <form action="AdminServlet.do" method="post" id="myForm" name="form1">
  	<input type="hidden" name="flag" value="dels" />
  	<input type="hidden" name="pageIndex" value="${page.pageIndex }" />
  	<input id="nowid" type="hidden" name="nowId" value="${nowAdmin.id }" />
		<div class ="div_title">
			<div class="div_titlename"> <img src="images/san_jiao.gif" ><span>管理人员基本信息列表</span></div>
			<div class="div_titleoper">
				<input type="checkbox" id="top_ch_checkall"/> 全选 <a href="admin/admin_add.jsp"> <img src="images/add.gif"/>添加 </a> <a href="javascript:void(0)" onclick="return dellAll()"><img src="images/del.gif"/>删除<input id="delAll" type="submit" value="删除" /></a> </div>
		 </div>
		 
		 
		<table class="main_table" >
			<tr>
	 			<th><input type="checkbox" id="ch_checkall" /></th>	<th>账号</th> 	<th>状态</th>	<th>备注</th>   	<th>操作</th>
	 		</tr>
	 		
	 		<c:forEach var="a" items="${adminList }" varStatus="st">
				<c:choose>
					<c:when test="${a.state!=2 }">
						<tr>
							<td><input name="checks" type="checkbox" value="${a.id }" /></td>
							<td>${a.adminName }</td>
							<td>
								<c:if test="${a.state ==0 }">已锁定</c:if>
							</td>
							<td>${a.note }</td>
							<td>
								<c:choose>
									<c:when test="${a.state ==1}">
										<a onclick="return sD('${a.id }')" href="AdminServlet.do?flag=suoDing&id=${a.id }&pageIndex=${page.pageIndex }">锁定</a> |
										<a onclick="return xG('${a.id }')" href="AdminServlet.do?flag=xiuGai&id=${a.id }&pageIndex=${page.pageIndex }">修改</a> |
										<a onclick="return shCh('${a.id }')" href="AdminServlet.do?flag=shanChu&id=${a.id }&pageIndex=${page.pageIndex }">删除</a>
									</c:when>
									<c:when test="${a.state ==0}">
										<a onclick="return confirm('确定解锁吗')" href="AdminServlet.do?flag=jieChu&id=${a.id }&pageIndex=${page.pageIndex }">解除锁定</a> |
										<a onclick="return shCh('${a.id }')" href="AdminServlet.do?flag=shanChu&id=${a.id }&pageIndex=${page.pageIndex }">删除</a>
									</c:when>
								</c:choose>
							</td>
						</tr>
					</c:when>
				</c:choose>
			</c:forEach>
	 		
		</table> 
		</form>
		
		<div class="div_page" >
			  <div class="div_page_left">    共有 <label>${page.rowCount }</label> 条记录，当前第 <label>${page.pageIndex }</label> 页，共 <label>${page.pageCount }</label> 页	</div>		
			  <div class="div_page_right" > 	 
					<c:choose>
						<c:when test="${page.hasPre }">
							<a class="clBk" href="AdminServlet.do?flag=manage&pageIndex=1">首页</a>
							<a class="clBk" href="AdminServlet.do?flag=manage&pageIndex=${page.pageIndex-1 }">上一页</a>  &nbsp;
						</c:when>
						<c:otherwise>
							<a class="clBk" href="javascript:void(0)">首页</a>
							<a class="clBk" href="javascript:void(0)">上一页</a>  &nbsp;
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${page.hasNext }">
							<a class="clBk" href="AdminServlet.do?flag=manage&pageIndex=${page.pageIndex+1 }">下一页</a>
							<a class="clBk" href="AdminServlet.do?flag=manage&pageIndex=${page.pageCount }">尾页</a>
						</c:when>
						<c:otherwise>
							<a class="clBk" href="javascript:void(0)">下一页</a>
							<a class="clBk" href="javascript:void(0)">尾页</a>
						</c:otherwise>
					</c:choose>
					<form action="AdminServlet.do" name="f2" method="post">
						<input type="hidden" name="flag" value="tiao" />
						<input type="hidden" id="index" value="${page.pageCount }" />
				  	  	<input id="tiao" type="submit" value="转到" />			
			  			 <input type="text" name="pageIndex" id="pageIndex" value="${page.pageIndex }" /> 页
					</form>
			  	
			   </div>
				
		</div>
		
  </body>
</html>