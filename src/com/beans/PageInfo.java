package com.beans;

public class PageInfo {

	private int pageSize;  //��ǰҳ��Ĵ�С
	private int rowCount;  //һ���ж���������
	private int pageIndex;  //��ǰ�ǵڼ�ҳ
	private int pageCount;   //һ��Ҫ�ֶ���ҳ
	private int beginRow;   //Ҫ���еڼ���ʼ��
	
	private boolean hasNext;  //�Ƿ�����һҳ
	private boolean hasPre;  //�ǲ�������һҳ
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getBeginRow() {
		return beginRow;
	}
	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public boolean isHasPre() {
		return hasPre;
	}
	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}
	
}
