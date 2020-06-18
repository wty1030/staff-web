package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.DepartmentInfo;
import com.dao.DepartmentDao;
import com.utils.GetOracleId;

@WebServlet("/DepartmentServlet.do")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentDao _depDao=new DepartmentDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag=request.getParameter("flag");
		
		if("yzName".equals(flag)) {
			yzName(request,response);
		}
		else if("addDep".equals(flag)) {
			addDep(request,response);
		}
		
	}
	
	//添加部门信息
	private void addDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DepartmentInfo d=new DepartmentInfo();
		String name=request.getParameter("dname");
		d.setId(GetOracleId.getBigOneId());
		d.setName(name);
		
		_depDao.addDepartment(d);
		
		request.setAttribute("result_msg", "添加成功");
		request.getRequestDispatcher("department/department_add.jsp").forward(request, response);
	}

	//查询是否存在这个部门
	private void yzName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("dname").trim();
		DepartmentInfo dep=_depDao.getDepByName(name);
		PrintWriter pw=response.getWriter();
		if(name==null||name=="") {
			pw.print("2");
		}else if(dep!=null) {
			pw.print("1");
		}else {
			pw.print("0");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
