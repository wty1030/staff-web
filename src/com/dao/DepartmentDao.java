package com.dao;

import com.beans.DepartmentInfo;
import com.jdbc.DBUtil;

public class DepartmentDao {

	/**
	 * ���ݲ������Ʋ�ѯ����
	 * @param name
	 * @return
	 */
	public DepartmentInfo getDepByName(String name) {
		return DBUtil.getSingleObject("select * from departmentInfo where name=? ", DepartmentInfo.class, name);
	}
	
	/**
	 * ��Ӳ�����Ϣ
	 * @param d
	 */
	public void addDepartment(DepartmentInfo d) {
		DBUtil.update("insert into departmentInfo (id,name) values (?,?)", d.getId(),d.getName());
	}

}
