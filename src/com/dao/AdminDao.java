package com.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.beans.AdminInfo;
import com.beans.PageInfo;
import com.jdbc.DBUtil;

public class AdminDao {

	/**
	 * �õ���¼�û�����Ϣ
	 * @param admin
	 * @return ���û�и��û��ͷ��ؿ�
	 */
	public AdminInfo login(AdminInfo admin) {
		String sql="select * from adminInfo where adminName=? and password=?";
		return DBUtil.getSingleObject(sql,AdminInfo.class, admin.getAdminName(),admin.getPassword());
	}

	/**
	 * ��ӹ���Ա
	 * @param a
	 */
	public int addAdmin(AdminInfo a) {
		return DBUtil.update("insert into admininfo(id,adminName,password,note,state) values(?,?,?,?,1)", a.getId(),a.getAdminName(),a.getPassword(),a.getNote());
	}

	/**
	 * �鿴���û����Ƿ����
	 * @param adminName
	 * @return �����ھͷ���null
	 */
	public AdminInfo getAdminByName(String adminName) {
		return DBUtil.getSingleObject("select * from admininfo where adminName=?", AdminInfo.class, adminName);
	}

	/**
	 * ��ȡ���е���������
	 * @return
	 */
	public int getAdminInfoCount() {
        int result=0;
		
		Connection conn=null;
		try {
			conn=DBUtil.getConn();
			result=Integer.parseInt( new QueryRunner().query(conn,"select count(*) from adminInfo where state=1 or state=0",new ScalarHandler<Integer>(1))+"" );
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtil.close(conn);
		}
		return result;	
	}

	/**
	 * �õ������û��ļ���
	 * @param page
	 * @return
	 */
	public List<AdminInfo> getAdminList(PageInfo page) {
		String sql="select * from (select t.*, rownum rn from (select * from adminInfo order by id asc ) t where rownum <= ?) where rn > ?";
		int index=page.getPageIndex();
		int size=page.getPageSize();
		return DBUtil.getList(sql, AdminInfo.class, index*size , (index-1)*size );
	}

	/**
	 * �޸��û���Ϣ
	 * @param admin
	 */
	public int updateAdmin(AdminInfo admin) {
		return DBUtil.update("update admininfo set adminName=?,password=?,note=? where id=?", admin.getAdminName(),admin.getPassword(),admin.getNote(),admin.getId());
	}

	/**
	 * �����û�
	 * @param id
	 * @return
	 */
	public int suoDingAdmin(int id) {
		return DBUtil.update("update admininfo set state=0 where id=?", id);
	}

	/**
	 * ɾ���û�
	 * @param id
	 * @return
	 */
	public int shanChuAdmin(int id) {
		return DBUtil.update("delete from admininfo where id=?", id);
	}
	
	/**
	 * �����û�
	 * @param id
	 * @return
	 */
	public int jieSuoAdmin(int id) {
		return DBUtil.update("update admininfo set state=1 where id=?", id);
	}

	/**
	 * ����id��ȡ�û���Ϣ
	 * @param id
	 * @return
	 */
	public AdminInfo getAdminById(int id) {
		return DBUtil.getSingleObject("select * from admininfo where id=?", AdminInfo.class, id);
	}

}
