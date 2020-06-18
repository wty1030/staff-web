package com.beans;

/**
 * 部门类
 * @author 文廷钰
 */
public class DepartmentInfo {

	private Integer id;//自增主键id
	private String name;//部门名称
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "DepartmentInfo [id=" + id + ", name=" + name + "]";
	}
	
}
