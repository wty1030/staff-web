package com.beans;

/**
 * Ա����
 * @author ��͢��
 */
public class StaffInfo {

	private Integer id;//��������id
	private String staffName;//Ա������
	private Integer bossId;//ֱ����˾id
	private Integer departmentId;//���ڲ���id ���
	private Integer positionId;//ְλid
	private Double wages;//����
	
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
