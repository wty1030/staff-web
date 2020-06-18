package com.test;

import com.jdbc.DBUtil;

public class Test {

	public static void main(String[] args) throws Exception {
		String sql="select count(*) from admininfo where state=1 or state=0";
		System.out.println( DBUtil.getScalar(sql)+"" );
		long l=DBUtil.getScalar(sql);
		System.out.println(l+"");
	}
}
