package com.beans;

/**
 * ������
 * @author ��͢��
 */
public class DepartmentInfo {

	private Integer id;//��������id
	private String name;//��������
	
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
