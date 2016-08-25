package com.lh.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.lh.model.User;

/**
 * 处理用户注册的DAO管理类
 * @author LH
 */
public class UserDao {
	private static UserDao instance = null;
	/**
	 * 返回一个UserDao实例
	 * @return
	 */
	public static UserDao getInstance(){
		if(instance==null) instance = new UserDao();
		return instance;
	}
	/**
	 * 保存用户注册信息
	 * @param user
	 * @return
	 */
	
public boolean saveUser(User user){
	Connection con = null;	
	try{
		con = DBCon.getConn();								//创建数据库连接
		String sql = "insert into tb_user(name,pwd,sex,age,createTime) values(?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);//预编译insert语句
		pstmt.setString(1, user.getUserName());				//对SQL语句第1个参数赋值
		pstmt.setString(2, user.getUserPwd());				//对SQL语句第2个参数赋值
		pstmt.setString(3, user.getUserSex());				//对SQL语句第3个参数赋值
		pstmt.setInt(4, user.getUserAge());   				//对SQL语句第4个参数赋值
		pstmt.setString(5, user.getUserLoginTime());		//对SQL语句第5个参数赋值
		int row = pstmt.executeUpdate();					//执行插入操作并返回所影响的行数
		if(row==1) 
			return true;									//数据插入成功返回true
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return false;
}
	
	public List<User> selectUser(){
		Connection con = null;	
		List<User> list = new ArrayList<User>();
		try{
			con = DBCon.getConn();//创建数据库连接
			String sql = "select * form tb_user";
			PreparedStatement pstmt = con.prepareStatement(sql);//预编译select语句
			ResultSet rs = pstmt.executeQuery();//执行查询并返回结果集
			while(rs.next()){//循环结果集，将信息封装到ArrayList集合中
				User user = new User();
				user.setUserId(rs.getInt("id"));
				user.setUserName(rs.getString("name"));
				user.setUserPwd(rs.getString("pwd"));
				user.setUserSex(rs.getString("sex"));
				user.setUserLoginTime(rs.getString("createTime"));
				user.setUserAge(rs.getInt("age"));
				list.add(user);
			}
			rs.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
