import java.sql.*;
public class TestCon {

	public static Connection getMySQLCon(){
		Connection con = null;//创建一个空的连接
		try {
			Class.forName("com.mysql.jdbc.Driver");//加载并注册MySQL驱动
			String user = "root"; //用户名
			String pwd = "111";   //密码
			String url = "jdbc:mysql://localhost:3306/db_database07";//连接URL
  			con = DriverManager.getConnection(url, user, pwd);//创建连接
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;//返回连接
	}
	public Connection getOracleCon(){
		Connection con = null ;
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); //加载Oracle数据库驱动
			String user = "oracleUser"; //用户名
			String pwd = "oraclePwd"; //密码
			String oracleUrl="jdbc:oracle:thin:@localhost:1521:test"; //连接URL
			con = DriverManager.getConnection(oracleUrl, user, pwd);//创建Oracle数据库连接
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

		return con;
	}
	public static void main(String[] args) {
		Connection conn = TestCon.getMySQLCon();
		
		if(conn!=null){	
			System.out.println("MySQL数据库连接成功！Connection="+conn.toString());
		}
	}
}
