package utils;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class c3p0Utils {
	public static DataSource ds=null; 
	static {
		//创建数据库的连接对象
		/**
		 * 自动读取以c3p0-config.xml命名的文件
		 */
		ComboPooledDataSource cpds = new  ComboPooledDataSource();//默认配置
		ds=cpds; 
	}
	public static DataSource getDataSource() {
		return ds;
	}
	

}
