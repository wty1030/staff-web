package com.beans;

/**
 * 员工类
 * @author 文廷钰
 */
public class StaffInfo {

	private Integer id;//自增主键id
	private String staffName;//员工姓名
	private Integer bossId;//直属上司id
	private Integer departmentId;//所在部门id 外键
	private Integer positionId;//职位id
	private Double wages;//工资
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Integer getBossId() {
		return bossId;
	}
	public void setBossId(Integer bossId) {
		this.bossId = bossId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public Double getWages() {
		return wages;
	}
	public void setWages(Double wages) {
		this.wages = wages;
	}
	
	public String toString() {
		return "StaffInfo [id=" + id + ", staffName=" + staffName + ", bossId=" + bossId + ", departmentId="
				+ departmentId + ", positionId=" + positionId + ", wages=" + wages + "]";
	}
	
}
