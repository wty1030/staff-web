package com.dao;

import com.beans.DepartmentInfo;
import com.jdbc.DBUtil;

public class DepartmentDao {

	/**
	 * 根据部门名称查询部门
	 * @param name
	 * @return
	 */
	public DepartmentInfo getDepByName(String name) {
		return DBUtil.getSingleObject("select * from departmentInfo where name=? ", DepartmentInfo.class, name);
	}
	
	/**
	 * 添加部门信息
	 * @param d
	 */
	public void addDepartment(DepartmentInfo d) {
		DBUtil.update("insert into departmentInfo (id,name) values (?,?)", d.getId(),d.getName());
	}

}
