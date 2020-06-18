package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.beans.AdminInfo;
import com.dao.AdminDao;
import com.utils.Des;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao _adminDao=new AdminDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String adminName=request.getParameter("adminName");
		String password=request.getParameter("password");
		AdminInfo admin=new AdminInfo();
		admin.setAdminName(adminName);
		admin.setPassword(Des.encStr(password));
		admin=_adminDao.login(admin);
		PrintWriter out=response.getWriter();
		if(admin==null) {
			out.print("f");
		}else if(admin.getState().equals("1")) {
			//把实例放到session中
			request.getSession().setAttribute("adminInfo", admin);
			out.print("t");//正确
		}else if(admin.getState().equals("2")) {
			out.print("j");//禁用
		}else if(admin.getState().equals("0")) {
			out.print("s");//锁定
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
