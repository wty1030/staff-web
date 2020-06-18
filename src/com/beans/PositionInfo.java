package com.beans;

/**
 * 职位类
 * @author 文廷钰
 */
public class PositionInfo {

	private Integer id;//自增主键id
	private String name;//职位名称
	
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
		return "PositionInfo [id=" + id + ", name=" + name + "]";
	}
	
}
