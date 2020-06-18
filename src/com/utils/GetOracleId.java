package com.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jdbc.DBUtil;
/**
 * ר��������ȡ��һ��id����
 * @author ��͢��
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

	//�õ���һ�����ݵ�id��ʲô
	public static int idBigOne() {
		//�õ�id
		int id=getBigOneId();
		//+1
		DBUtil.update("update idInfo set id=? where id=?", id+1,id);
		//����id
		return id;
	}
}
