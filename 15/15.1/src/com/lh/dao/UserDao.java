package com.lh.dao;
import java.sql.*;

import com.lh.model.*;
/**
 * 处理用户信息Dao类
 * @author lh
 */
public class UserDao {
	private static UserDao instance=null;
	public static UserDao getInstance(){
		if(instance==null) instance = new UserDao();
		return instance;
	}
	public UserDao(){}
	/**
	 * 验证用户的账户密码是否成功
	 * @param user 封装用户信息的对象
	 * @return 验证成功返回true,否则返回false
	 */
	public Boolean checkUserLog(UserInfo user){
		boolean res = false;  
		Connection con = null;      //创建空的数据库连接对象
		ResultSet rs = null;        //创建空的结果集对象
		try{
			con=DBCon.getInstance();//从DBCon类中获得数据库连接对象
			String sql = "select id from tb_userinfo where name=? and pwd=?";//select语句
			PreparedStatement pstmt = con.prepareStatement(sql);//创建PreparedStatement对象
			pstmt.setString(1, user.getName()); //对参数中的用户名赋值
			pstmt.setString(2, user.getPwd());  //对参数中的密码赋值
			rs=pstmt.executeQuery();            //执行并返回结果集
			if(rs.next()) res=true;             //如果结果集中有下一行数据，将返回true
		}
		catch(Exception ex){
			ex.printStackTrace();
		}	
		finally{
			try {
				rs.close();  //关闭结果集
				con.close(); //关闭数据库连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
}
