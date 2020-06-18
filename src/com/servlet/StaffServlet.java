package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.DepartmentInfo;
import com.beans.PositionInfo;
import com.beans.StaffInfo;
import com.dao.StaffDao;
import com.utils.GetOracleId;

@WebServlet("/StaffServlet.do")
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StaffDao _staffDao=new StaffDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag=request.getParameter("flag");
		
		if("addStaff".equals(flag)) {//添加员工信息
			addStaff(request,response);
		}
		else if("manage".equals(flag)) {//员工管理界面
			manage(request,response);
		}
		else if("cha".equals(flag)) {//查询员工信息
			chaXun(request,response);
		}
		else if("shan".equals(flag)) {//删除员工信息
			shanChu(request,response);
		}
		else if("shanChuOne".equals(flag)) {//删除员工信息
			shanChuOne(request,response);
		}
		else if("xiuGai".equals(flag)) {//员工信息修改界面
			xiuGaiJieMian(request,response);
		}
		else if("xiuStaff".equals(flag)) {//修改员工信息
			xiuStaff(request,response);
		}
		
	}

	//修改员工信息
	private void xiuStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String staffName=request.getParameter("staffName");
		int departmentId=Integer.parseInt( request.getParameter("departmentId") );
		int positionId=Integer.parseInt( request.getParameter("positionId") );
		double wages=Double.parseDouble( request.getParameter("wages") );
		//System.out.println(id);
		StaffInfo staff=new StaffInfo();
		staff.setId(Integer.parseInt(id));
		staff.setStaffName(staffName);
		staff.setDepartmentId(departmentId);
		staff.setPositionId(positionId);
		staff.setWages(wages);
		
		_staffDao.updateStaff(staff);
		
		request.setAttribute("staff", staff);
		request.setAttribute("result_msg", "修改成功");
		request.getRequestDispatcher("staff/staff_update.jsp").forward(request, response);
	}

	//修改界面
	private void xiuGaiJieMian(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		StaffInfo staff=_staffDao.getStaffById(id);
		request.setAttribute("staff", staff);
		request.setAttribute("dList", _staffDao.getDepartmentList());
		request.setAttribute("pList", _staffDao.getPositionList());
		request.getRequestDispatcher("staff/staff_update.jsp").forward(request, response);
	}

	//删除员工信息
	private void shanChu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[]arr=request.getParameterValues("checks");
		for(String s:arr) {
			_staffDao.delStaff(Integer.parseInt(s));
		}
		chaXun(request,response);
	}
	
	//删除员工信息
	private void shanChuOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		_staffDao.delStaff(Integer.parseInt(request.getParameter("id")));

		manage(request,response);
	}

	//查询员工信息
	private void chaXun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql="select * from staffInfo where 1=1 ";
		String staffName=request.getParameter("staffName");//姓名
		String departmentId=request.getParameter("departmentId");//部门ID
		String positionId=request.getParameter("positionId");//职位ID
		//String pageIndex=request.getParameter("pageIndex");//要查询的页数(由于学艺不精，Oracle不会多条件查询分页，所以只好不分页了)
		if( !"".equals(staffName) ) {
			sql+=" and staffName like '%"+staffName+"%' ";
		}
		if( !"".equals(departmentId) ) {
			sql+=" and departmentId = "+departmentId+" ";
		}
		if( !"".equals(positionId) ) {
			sql+=" and positionId = "+positionId+" ";
		}
		//System.out.println(sql);
		List<StaffInfo>staffList=_staffDao.getStaffList(sql);
		List<DepartmentInfo>dList=_staffDao.getDepartmentList();
		List<PositionInfo>pList=_staffDao.getPositionList();
		
		request.setAttribute("staffList", staffList);
		request.setAttribute("dList", dList);
		request.setAttribute("pList", pList);
		request.setAttribute("departmentId", departmentId);
		request.setAttribute("positionId", positionId);
		request.setAttribute("staffName", staffName);
		request.getRequestDispatcher("/staff/staff_manage.jsp").forward(request, response);
		
	}

	//员工管理界面
	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//查询数据库 表中一共多少行数据
				//int rowCount=_staffDao.getStaffInfoCount();
				//int pageIndex=1;
				//int pageSize=4;
				
				//String pageIndexStr=request.getParameter("pageIndex");
				//if(pageIndexStr!=null) {
				//	pageIndex=Integer.parseInt(pageIndexStr);
				//}
				//通过工具类获取page实例
				//PageInfo page=PageUtil.getPageInfo(pageSize, rowCount, pageIndex);
				
				List<StaffInfo > staffList= _staffDao.getStaffList();;
				List<DepartmentInfo>dList=_staffDao.getDepartmentList();
				List<PositionInfo>pList=_staffDao.getPositionList();
				
				request.setAttribute("staffList", staffList);
				request.setAttribute("dList", dList);
				request.setAttribute("pList", pList);
				//request.setAttribute("page", page);
				request.getRequestDispatcher("/staff/staff_manage.jsp").forward(request, response);
	}

	//添加员工信息
	private void addStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String staffName=request.getParameter("staffName");
		int departmentId=Integer.parseInt( request.getParameter("departmentId") );
		int positionId=Integer.parseInt( request.getParameter("positionId") );
		double wages=Double.parseDouble( request.getParameter("wages") );
		
		StaffInfo staff=new StaffInfo();
		staff.setStaffName(staffName);
		staff.setDepartmentId(departmentId);
		staff.setPositionId(positionId);
		staff.setWages(wages);
		staff.setId(GetOracleId.idBigOne());
		
		_staffDao.addStaff(staff);
		
		request.setAttribute("staff", staff);
		request.setAttribute("result_msg", "添加成功");
		request.getRequestDispatcher("staff/staff_add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
