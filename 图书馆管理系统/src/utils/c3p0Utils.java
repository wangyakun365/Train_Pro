package utils;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class c3p0Utils {
	public static DataSource ds=null; 
	static {
		//�������ݿ�����Ӷ���
		/**
		 * �Զ���ȡ��c3p0-config.xml�������ļ�
		 */
		ComboPooledDataSource cpds = new  ComboPooledDataSource();//Ĭ������
		ds=cpds; 
	}
	public static DataSource getDataSource() {
		return ds;
	}
	

}
