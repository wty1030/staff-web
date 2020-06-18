package com.utils;
import com.beans.PageInfo;

public class PageUtil {
	public static PageInfo getPageInfo(int pageSize,int rowCount, int pageIndex) {	
		PageInfo page=new PageInfo();
		page.setPageSize(pageSize==0?4:pageSize);
		page.setRowCount(rowCount);
		page.setPageIndex(pageIndex);
		
		page.setPageCount( (rowCount+pageSize-1)/pageSize   );  //������ҳ��
		page.setBeginRow( page.getPageSize()*(pageIndex-1)  );  //����ӵڼ��п�ʼ��
		
		page.setHasNext(pageIndex<page.getPageCount()); //�����ǲ�������һҳ
		page.setHasPre(!(pageIndex==1));  //�����ǲ�����ǰһҳ
		
		return page;
	}
}
