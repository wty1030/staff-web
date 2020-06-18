package com.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.beans.DepartmentInfo;
import com.beans.PositionInfo;
import com.beans.StaffInfo;
import com.jdbc.DBUtil;

public class StaffDao {

	/**
	 * ��ȡ���еĲ�����Ϣ
	 * @return
	 */
	public List<DepartmentInfo> getDepartmentList(){
		String sql="select * from departmentInfo";
		return DBUtil.getList(sql, DepartmentInfo.class);
	}
	
	/**
	 * ��ȡ����ְλ��Ϣ
	 * @return
	 */
	public List<PositionInfo> getPositionList(){
		String sql="select * from positionInfo";
		return DBUtil.getList(sql, PositionInfo.class);
	}

	/**
	 * ���Ա��
	 * @param staff
	 */
	public void addStaff(StaffInfo staff) {
		String sql="insert into staffInfo (id,staffName,departmentId,positionId,wages,bossId) values (?,?,?,?,?,-1)";
		Object[]params= {staff.getId(),staff.getStaffName(),staff.getDepartmentId(),staff.getPositionId(),staff.getWages()};
		DBUtil.update(sql, params);
	}

//	/**
//	 * ��ȡ����Ա����Ϣ
//	 * @param page
//	 * @return
//	 */
//	public List<StaffInfo> getStaffList(PageInfo page) {
//		String sql="select * from (select t.*, rownum rn from (select * from staffInfo order by id asc ) t where rownum <= ?) where rn > ?";
//		int index=page.getPageIndex();
//		int size=page.getPageSize();
//		return DBUtil.getList(sql, StaffInfo.class, index*size , (index-1)*size );
//	}
	
	/**
	 * ��ȡ����Ա����Ϣ
	 * @param page
	 * @return
	 */
	public List<StaffInfo> getStaffList() {
		String sql="select * from staffInfo";
		return DBUtil.getList(sql, StaffInfo.class );
	}
	
	/**
	 * ��ȡ����Ա����Ϣ
	 * @param page
	 * @return
	 */
	public List<StaffInfo> getStaffList(String sql) {
		return DBUtil.getList(sql, StaffInfo.class );
	}

	/**
	 * ��ȡ������
	 * @return
	 */
	public int getStaffInfoCount() {
		int result=0;
		
		Connection conn=null;
		try {
			conn=DBUtil.getConn();
			result=Integer.parseInt( new QueryRunner().query(conn,"select count(*) from staffInfo",new ScalarHandler<Integer>(1))+"" );
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtil.close(conn);
		}
		return result;	
	}

	/**
	 * ɾ��Ա��
	 * @param parseInt
	 */
	public void delStaff(int id) {
		DBUtil.update("delete from staffInfo where id=?", id);
	}

	/**
	 * ����idɾ��Ա��
	 * @param id
	 * @return
	 */
	public StaffInfo getStaffById(int id) {
		return DBUtil.getSingleObject("select * from staffInfo where id=?", StaffInfo.class, id);
	}

	/**
	 * �޸��û�
	 * @param staff
	 */
	public void updateStaff(StaffInfo s) {
		DBUtil.update("update staffInfo set staffName=?,departmentId=?,positionId=?,wages=? where id=?", s.getStaffName(),s.getDepartmentId(),s.getPositionId(),s.getWages(),s.getId());
	}
	
}
