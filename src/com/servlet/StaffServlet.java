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
		
		if("addStaff".equals(flag)) {//���Ա����Ϣ
			addStaff(request,response);
		}
		else if("manage".equals(flag)) {//Ա���������
			manage(request,response);
		}
		else if("cha".equals(flag)) {//��ѯԱ����Ϣ
			chaXun(request,response);
		}
		else if("shan".equals(flag)) {//ɾ��Ա����Ϣ
			shanChu(request,response);
		}
		else if("shanChuOne".equals(flag)) {//ɾ��Ա����Ϣ
			shanChuOne(request,response);
		}
		else if("xiuGai".equals(flag)) {//Ա����Ϣ�޸Ľ���
			xiuGaiJieMian(request,response);
		}
		else if("xiuStaff".equals(flag)) {//�޸�Ա����Ϣ
			xiuStaff(request,response);
		}
		
	}

	//�޸�Ա����Ϣ
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
		request.setAttribute("result_msg", "�޸ĳɹ�");
		request.getRequestDispatcher("staff/staff_update.jsp").forward(request, response);
	}

	//�޸Ľ���
	private void xiuGaiJieMian(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		StaffInfo staff=_staffDao.getStaffById(id);
		request.setAttribute("staff", staff);
		request.setAttribute("dList", _staffDao.getDepartmentList());
		request.setAttribute("pList", _staffDao.getPositionList());
		request.getRequestDispatcher("staff/staff_update.jsp").forward(request, response);
	}

	//ɾ��Ա����Ϣ
	private void shanChu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[]arr=request.getParameterValues("checks");
		for(String s:arr) {
			_staffDao.delStaff(Integer.parseInt(s));
		}
		chaXun(request,response);
	}
	
	//ɾ��Ա����Ϣ
	private void shanChuOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		_staffDao.delStaff(Integer.parseInt(request.getParameter("id")));

		manage(request,response);
	}

	//��ѯԱ����Ϣ
	private void chaXun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql="select * from staffInfo where 1=1 ";
		String staffName=request.getParameter("staffName");//����
		String departmentId=request.getParameter("departmentId");//����ID
		String positionId=request.getParameter("positionId");//ְλID
		//String pageIndex=request.getParameter("pageIndex");//Ҫ��ѯ��ҳ��(����ѧ�ղ�����Oracle�����������ѯ��ҳ������ֻ�ò���ҳ��)
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

	//Ա���������
	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//��ѯ���ݿ� ����һ������������
				//int rowCount=_staffDao.getStaffInfoCount();
				//int pageIndex=1;
				//int pageSize=4;
				
				//String pageIndexStr=request.getParameter("pageIndex");
				//if(pageIndexStr!=null) {
				//	pageIndex=Integer.parseInt(pageIndexStr);
				//}
				//ͨ���������ȡpageʵ��
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

	//���Ա����Ϣ
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
		request.setAttribute("result_msg", "��ӳɹ�");
		request.getRequestDispatcher("staff/staff_add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
