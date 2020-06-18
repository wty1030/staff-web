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
		
		if("tuiChuXT".equals(flag)) {//�˳���¼
			tuiChuXT(request,response);
		}
		else if("nowdate".equals(flag)) {//��ǰʱ��
			nowDate(request,response);
		}
		else if("addAdmin".equals(flag)) {//��ӹ���Ա
			addAdmin(request,response);
		}
		else if("yzAdminName".equals(flag)) {//��֤�û�����ʽ���Ƿ����
			yzAdminName(request,response);
		}
		else if("tiao".equals(flag)) {//��תҳ��
			adminWeiHu(request,response);
		}
		else if("yzothersPassword".equals(flag)) {//�ж�ԭ�������Ƿ���ȷ
			yzothersPassword(request,response);
		}
		else if("updatePassword".equals(flag)) {//�޸�����
			updatePassword(request,response);
		}
		else if("manage".equals(flag)) {//��ת���û�ά������
			adminWeiHu(request,response);
		}
		else if("suoDing".equals(flag)) {//����
			suoDing(request,response);
		}
		else if("shanChu".equals(flag)) {//ɾ��
			shanChu(request,response);
		}
		else if("jieChu".equals(flag)) {//�������
			jieChu(request,response);
		}
		else if("xiuGai".equals(flag)) {//�����޸Ľ���
			xiuGai(request,response);
		}
		else if("xiuGaiAdmin".equals(flag)) {//�޸��û���Ϣ
			xiuGaiAdmin(request,response);
		}
		else if("dels".equals(flag)) {
			dels(request,response);
		}
	}
	
	//ɾ����ѡ
	private void dels(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[]args=request.getParameterValues("checks");
		for(String s:args) {
			_adminDao.shanChuAdmin(Integer.parseInt(s));
		}
		adminWeiHu(request,response);
	}

	//�޸��û��ķ���
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
			msg="�޸ĳɹ�";
		}else {
			msg="�޸�ʧ��";
		}
		request.setAttribute("result_msg", msg);
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("admin/admin_xiuGai.jsp").forward(request, response);
	}
	
	//�޸Ľ���
	private void xiuGai(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt( request.getParameter("id") );
		AdminInfo admin=_adminDao.getAdminById(id);
		//System.out.println(admin);
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("admin/admin_xiuGai.jsp").forward(request, response);
	}
	
	//���
	private void jieChu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		_adminDao.jieSuoAdmin(Integer.parseInt(id));
		//���ظոյĽ���
		adminWeiHu(request,response);
	}
	
	//ɾ��
	private void shanChu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		_adminDao.shanChuAdmin(Integer.parseInt(id));
		//���ظոյĽ���
		adminWeiHu(request,response);
	}

	//�����û�
	private void suoDing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		_adminDao.suoDingAdmin(Integer.parseInt(id));
		//���ظոյĽ���
		adminWeiHu(request,response);
	}

	//�޸�����
	private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newPassword=request.getParameter("newPassword");
		AdminInfo admin=(AdminInfo)request.getSession().getAttribute("adminInfo");
		admin.setPassword(newPassword);
		_adminDao.updateAdmin(admin);
		request.getSession().invalidate();
		request.getRequestDispatcher("please_Login.jsp").forward(request, response);
	}
	
	//��֤�����Ƿ���ȷ
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
	
	//��ҳ��ѯ
	private void adminWeiHu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ѯ���ݿ� ����һ������������
		int rowCount=_adminDao.getAdminInfoCount();
		int pageIndex=1;
		int pageSize=4;
		
		String pageIndexStr=request.getParameter("pageIndex");
		if(pageIndexStr!=null) {
			pageIndex=Integer.parseInt(pageIndexStr);
		}
		//ͨ���������ȡpageʵ��
		PageInfo page=PageUtil.getPageInfo(pageSize, rowCount, pageIndex);
		
		List<AdminInfo > adminList= _adminDao.getAdminList(page);
		
		request.setAttribute("adminList", adminList);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/admin_manage.jsp").forward(request, response);
	}
	
	//��֤�û�����ʽ���Ƿ����
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
			out.print("2");//��ʽ����
			return;
		}else if(a!=null) {//�û����Ѵ���
			out.print("1");
		}else {
			out.print("0");//�û�������
		}
	}
	
	//��ӹ���Ա
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
			request.setAttribute("result_msg", "��ӳɹ�");
		}else {
			request.setAttribute("result_msg", "���ʧ��");
		}
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("admin/admin_add.jsp").forward(request, response);
	}

	//��ȡ��ǰʱ��
	private void nowDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date d=new Date();
		SimpleDateFormat sm=new SimpleDateFormat("yyyy��MM��dd�� hhʱmm��ss��");
		response.getWriter().print(sm.format(d));
	}

	//�˳�ϵͳ
	private void tuiChuXT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.setAttribute("msg", "��ǰ�˺����˳�");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
