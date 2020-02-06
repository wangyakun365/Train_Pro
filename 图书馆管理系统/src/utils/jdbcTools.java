package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * jdbc�Ĺ�����
 * 
 * ���ݿ����ӵķ���
 * �رյķ���
 * 
 * Ϊ����ɾ�Ĳ�ļ�
 * @author lenovo
 *
 */

public class jdbcTools {
	
	//1.��̬�����ӷ���
	public  static Connection getConnection() throws IOException, Exception {
		
		Properties properties = new Properties();
		
		InputStream in = jdbcTools.class.getClassLoader().getResourceAsStream("jdbc.properties");

		properties.load(in);
		
		String driver=properties.getProperty("driver");
		String url=properties.getProperty("url");
		String user=properties.getProperty("user");
		String password=properties.getProperty("password");
		
		Class.forName(driver);
		
		//ʹ��Drivermanger��getConnection�ķ���
		return DriverManager.getConnection(url,user,password);
	}
	
	///////////////////////////////
	// �رյķ���
	// �ر�statement connection
	public static void release(Statement st, Connection connection) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void release(ResultSet rs,Statement st, Connection connection) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}

	
	

}
