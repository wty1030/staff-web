package com.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jdbc.DBUtil;
/**
 * 专门用来获取下一个id的类
 * @author 文廷钰
 */
public class GetOracleId {
	
	public static int getBigOneId() {
		int result=0;
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement stm = null;
		try {
			conn=DBUtil.getConn();
			stm = conn.prepareStatement("select * from idInfo");
			rs=stm.executeQuery();
			rs.next();
			result=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs, stm, conn);
		}
		return result;
	}

	//得到下一个数据的id是什么
	public static int idBigOne() {
		//得到id
		int id=getBigOneId();
		//+1
		DBUtil.update("update idInfo set id=? where id=?", id+1,id);
		//返回id
		return id;
	}
}
