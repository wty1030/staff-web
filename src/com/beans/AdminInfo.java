package com.beans;

/**
 * ����Ա��
 * @author ��͢��
 */
public class AdminInfo {

	private Integer id;//��������id
	private String adminName;//�˺�
	private String password;//����
	private String note;//��ע
	private String state;//��ǰ�˺ŵ�״̬ 0���� 1���� 2��ɾ��
	
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
