package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.AdminInfo;
import com.beans.PageInfo;
import com.dao.AdminDao;
import com.utils.Des;
import com.utils.GetOracleId;
import com.utils.PageUtil;

@WebServlet("/AdminServlet.do")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao _adminDao=new AdminDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag=request.getParameter("flag");
		
		if("tuiChuXT".equals(flag)) {//退出登录
			tuiChuXT(request,response);
		}
		else if("nowdate".equals(flag)) {//当前时间
			nowDate(request,response);
		}
		else if("addAdmin".equals(flag)) {//添加管理员
			addAdmin(request,response);
		}
		else if("yzAdminName".equals(flag)) {//验证用户名格式和是否存在
			yzAdminName(request,response);
		}
		else if("tiao".equals(flag)) {//跳转页面
			adminWeiHu(request,response);
		}
		else if("yzothersPassword".equals(flag)) {//判断原来密码是否正确
			yzothersPassword(request,response);
		}
		else if("updatePassword".equals(flag)) {//修改密码
			updatePassword(request,response);
		}
		else if("manage".equals(flag)) {//跳转到用户维护界面
			adminWeiHu(request,response);
		}
		else if("suoDing".equals(flag)) {//锁定
			suoDing(request,response);
		}
		else if("shanChu".equals(flag)) {//删除
			shanChu(request,response);
		}
		else if("jieChu".equals(flag)) {//解除锁定
			jieChu(request,response);
		}
		else if("xiuGai".equals(flag)) {//进入修改界面
			xiuGai(request,response);
		}
		else if("xiuGaiAdmin".equals(flag)) {//修改用户信息
			xiuGaiAdmin(request,response);
		}
		else if("dels".equals(flag)) {
			dels(request,response);
		}
	}
	
	//删除所选
	private void dels(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[]args=request.getParameterValues("checks");
		for(String s:args) {
			_adminDao.shanChuAdmin(Integer.parseInt(s));
		}
		adminWeiHu(request,response);
	}

	//修改用户的方法
	private void xiuGaiAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String adminName=request.getParameter("adminName");
		String password=request.getParameter("password");
		String note=request.getParameter("note");
		String msg="";
		
		
		AdminInfo admin=new AdminInfo();
		admin.setAdminName(adminName);
		admin.setPassword(Des.encStr(password));
		admin.setNote(note);
		admin.setId(id);
		
		int r=_adminDao.updateAdmin(admin);
		if(r>0) {
			msg="修改成功";
		}else {
			msg="修改失败";
		}
		request.setAttribute("result_msg", msg);
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("admin/admin_xiuGai.jsp").forward(request, response);
	}
	
	//修改界面
	private void xiuGai(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt( request.getParameter("id") );
		AdminInfo admin=_adminDao.getAdminById(id);
		//System.out.println(admin);
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("admin/admin_xiuGai.jsp").forward(request, response);
	}
	
	//解除
	private void jieChu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		_adminDao.jieSuoAdmin(Integer.parseInt(id));
		//返回刚刚的界面
		adminWeiHu(request,response);
	}
	
	//删除
	private void shanChu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		_adminDao.shanChuAdmin(Integer.parseInt(id));
		//返回刚刚的界面
		adminWeiHu(request,response);
	}

	//锁定用户
	private void suoDing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		_adminDao.suoDingAdmin(Integer.parseInt(id));
		//返回刚刚的界面
		adminWeiHu(request,response);
	}

	//修改密码
	private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newPassword=request.getParameter("newPassword");
		AdminInfo admin=(AdminInfo)request.getSession().getAttribute("adminInfo");
		admin.setPassword(newPassword);
		_adminDao.updateAdmin(admin);
		request.getSession().invalidate();
		request.getRequestDispatcher("please_Login.jsp").forward(request, response);
	}
	
	//验证密码是否正确
	private void yzothersPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String pswd=request.getParameter("othersPassword");
		AdminInfo admin=(AdminInfo)request.getSession().getAttribute("adminInfo");
		if(pswd.equals(admin.getPassword())) {
			out.print("0");
		}else {
			out.print("1");
		}
	}
	
	//分页查询
	private void adminWeiHu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询数据库 表中一共多少行数据
		int rowCount=_adminDao.getAdminInfoCount();
		int pageIndex=1;
		int pageSize=4;
		
		String pageIndexStr=request.getParameter("pageIndex");
		if(pageIndexStr!=null) {
			pageIndex=Integer.parseInt(pageIndexStr);
		}
		//通过工具类获取page实例
		PageInfo page=PageUtil.getPageInfo(pageSize, rowCount, pageIndex);
		
		List<AdminInfo > adminList= _adminDao.getAdminList(page);
		
		request.setAttribute("adminList", adminList);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/admin_manage.jsp").forward(request, response);
	}
	
	//验证用户名格式和是否存在
	private void yzAdminName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminName=request.getParameter("adminName");
		String reg="[0-9a-zA-Z]{4,15}";
		PrintWriter out=response.getWriter();
		if(adminName==null||adminName=="") {
			out.print("2");
			return;
		}
		boolean b=adminName.matches(reg);
		AdminInfo a=_adminDao.getAdminByName(adminName);
		if(!b) {
			out.print("2");//格式错误
			return;
		}else if(a!=null) {//用户名已存在
			out.print("1");
		}else {
			out.print("0");//用户名可用
		}
	}
	
	//添加管理员
	private void addAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminName=request.getParameter("adminName");
		String password=request.getParameter("password");
		String note=request.getParameter("note");
		AdminInfo admin=new AdminInfo();
		if(note==null||note==""||note.trim()=="") {
			note="";
		}
		admin.setAdminName(adminName);
		admin.setPassword(Des.encStr(password));
		admin.setNote(note);
		admin.setId(GetOracleId.idBigOne());
		request.setAttribute("note", note);
		int r=_adminDao.addAdmin(admin);
		if(r>0) {
			request.setAttribute("result_msg", "添加成功");
		}else {
			request.setAttribute("result_msg", "添加失败");
		}
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("admin/admin_add.jsp").forward(request, response);
	}

	//获取当前时间
	private void nowDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date d=new Date();
		SimpleDateFormat sm=new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
		response.getWriter().print(sm.format(d));
	}

	//退出系统
	private void tuiChuXT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.setAttribute("msg", "当前账号已退出");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
