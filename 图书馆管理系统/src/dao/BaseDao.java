package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbutils.ResultSetHandler;

import utils.jdbcTools;

/**
 * 编写一个通用的query方法
 * @author lenovo
 *
 */
public class BaseDao {
	public static Object query(String sql,ResultSetHandler<?> rsh,Object...params ) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = jdbcTools.getConnection();
			pst=conn.prepareStatement(sql);
			
			//遍历object里的各个参数
			for (int i = 0; i <params.length; i++) {
				pst.setObject(i+1, params[i]);
			}
			
			rs=pst.executeQuery();
			
			Object obj = rsh.handle(rs);
			return obj;
		} catch (Exception e) {
			//e.printStackTrace();
		}finally {
			jdbcTools.release(rs, pst,conn);
		}
		return rs;
	}
	
}
