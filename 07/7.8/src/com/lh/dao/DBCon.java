package com.lh.dao;
import java.sql.*;
/**
 * 连接数据库的类，获得数据库连接
 * @author Administrator
 *
 */

public class DBCon {
	private static Connection conn = null;
	public static Connection getConn(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//加载驱动类
			String user = "sa";//用户名
			String pwd = "sa";//密码
			String url = "jdbc:sqlserver://localhost:1433;DataBaseName=test";//连接URL
			conn = DriverManager.getConnection(url, user, pwd);//获取连接
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
