package com.utils;
import com.beans.PageInfo;

public class PageUtil {
	public static PageInfo getPageInfo(int pageSize,int rowCount, int pageIndex) {	
		PageInfo page=new PageInfo();
		page.setPageSize(pageSize==0?4:pageSize);
		page.setRowCount(rowCount);
		page.setPageIndex(pageIndex);
		
		page.setPageCount( (rowCount+pageSize-1)/pageSize   );  //计算总页数
		page.setBeginRow( page.getPageSize()*(pageIndex-1)  );  //计算从第几行开始查
		
		page.setHasNext(pageIndex<page.getPageCount()); //计算是不是有下一页
		page.setHasPre(!(pageIndex==1));  //计算是不是有前一页
		
		return page;
	}
}
