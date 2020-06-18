package com.beans;

/**
 * 管理员类
 * @author 文廷钰
 */
public class AdminInfo {

	private Integer id;//自增主键id
	private String adminName;//账号
	private String password;//密码
	private String note;//备注
	private String state;//当前账号的状态 0锁定 1正常 2已删除
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String toString() {
		return "AdminInfo [id=" + id + ", adminName=" + adminName + ", password=" + password + ", note=" + note
				+ ", state=" + state + "]";
	}
	
}
