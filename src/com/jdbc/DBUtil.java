package com.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
	// ˽�л����캯��,��ֹ������new�����ʵ��
	private DBUtil() {}

	private static DataSource dataSource;

	static {
		dataSource = new ComboPooledDataSource("oracle");
	}

	// �õ�����,������Դ�õ�
	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return conn;
	}

	// ������Դ
	public static void close(Connection conn) {	
		if (conn != null) {
			try {
				conn.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// ������Դ
	public static void close(Statement stm, Connection conn) {
		
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// ������Դ
	public static void close(ResultSet rs, Statement stm, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	//���ܸ���(��,ɾ,��)
	public static int update(String sql,Object ... params) {
		int result=0;
		
		Connection conn=null;
		
		try {
			conn=DBUtil.getConn();
			QueryRunner qr=new QueryRunner();
			result=qr.update(conn, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(conn);
		}
		
		return result;
	}
	
	//�������,�������ɵ�����ID
	public static int addAndReturnId(String sql,Object ... params) {
		int autoId =0 ;
		
		Connection conn=null;
		PreparedStatement stm=null;
		
		try {
			conn=getConn();
			stm=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			for(int i=0;i<params.length;i++) {
				stm.setObject(i+1, params[i]);
			}
			
			//ִ����Ӳ���
			stm.execute();
			
			//ȡ�����ɵ���������
			ResultSet rskey=stm.getGeneratedKeys();
			rskey.next(); 
			autoId=rskey.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(stm,conn);
		}
		
		return autoId;
	}
	
	
	//��ѯ��������
	public static <T> T getSingleObject(String sql,Class<T> clazz,  Object ...  params) {
		T result=null;
		Connection conn=null;
		
		try {
			conn=getConn();
			QueryRunner qr=new QueryRunner();
			result=qr.query(conn,sql, new BeanHandler<T>(clazz),params);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(conn);
		}
			
		return result;		
	}
	
	//��ѯһ�����(����鵽������Ϊ��,���ص���һ�����б�,����null)
	public static <T>  List<T> getList(String sql,Class<T> clazz,  Object ...  params){
		List<T> list=new ArrayList<T>();
		Connection conn=null;
		try {
			conn=getConn();
			list=new QueryRunner().query(conn,sql, new BeanListHandler<T>(clazz),params);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(conn);
		}
				
		return list;
	}
	
	
	//����Map����(���鵽��һ������,��Map���ϵķ�ʽ����, key���ֶ���,value ���ֶ�ֵ)
	public static Map<String,Object> getMap(String sql,Object ...params){
		Map<String,Object> m=new HashMap<String,Object>();
		Connection conn=null;
		
		try {
			conn=getConn();
			m=new QueryRunner().query(conn,sql, new MapHandler(), params);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(conn);
		}
		
		return m;
	}

	//����һ��List����,�����ÿ�����ݶ���һ��Map����
	public static List<Map<String,Object>> getMapList(String sql,Object ... params){
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>> ();
		Connection conn=null;
		try {
			conn=getConn();
			listMap=new QueryRunner().query(conn,sql, new MapListHandler(), params);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(conn);
		}
		
		return listMap;
	}
	
	
	//���ص������� (ע�������select count(*) ���ص���long��)
	public static <T> T getScalar(String sql, Object ... params) {
		T result=null;
		
		Connection conn=null;
		try {
			conn=DBUtil.getConn();
			result=new QueryRunner().query(conn,sql,new ScalarHandler<T>(1), params);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(conn);
		}
		return result;	
	}
	
	//���ص������ݵ�List 
	public static List<Integer> getScalarList(String sql,Object ... params) {
		List<Integer> list=new ArrayList<Integer>();
		Connection conn=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		
		try {
			conn=getConn();
			stm=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++) {
				stm.setObject(i+1, params[i]);
			}
			rs=stm.executeQuery();
			while(rs.next()) {
				list.add((int)rs.getObject(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return list;
	}
	
  }









