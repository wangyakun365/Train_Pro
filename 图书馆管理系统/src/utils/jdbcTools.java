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
 * jdbc的工具类
 * 
 * 数据库连接的方法
 * 关闭的方法
 * 
 * 为了增删改查的简化
 * @author lenovo
 *
 */

public class jdbcTools {
	
	//1.静态的连接方法
	public  static Connection getConnection() throws IOException, Exception {
		
		Properties properties = new Properties();
		
		InputStream in = jdbcTools.class.getClassLoader().getResourceAsStream("jdbc.properties");

		properties.load(in);
		
		String driver=properties.getProperty("driver");
		String url=properties.getProperty("url");
		String user=properties.getProperty("user");
		String password=properties.getProperty("password");
		
		Class.forName(driver);
		
		//使用Drivermanger的getConnection的方法
		return DriverManager.getConnection(url,user,password);
	}
	
	///////////////////////////////
	// 关闭的方法
	// 关闭statement connection
	public static void release(Statement st, Connection connection) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void release(ResultSet rs,Statement st, Connection connection) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	
	

}
