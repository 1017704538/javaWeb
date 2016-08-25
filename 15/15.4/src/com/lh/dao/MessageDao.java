package com.lh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.lh.model.Message;



public class MessageDao {
	private static MessageDao instance = null;
	/**
	 * 静态方法，返回本类的实例
	 * @return
	 */
	public static MessageDao getInstance(){
		if(instance==null) instance = new MessageDao();
		return instance;
	}
	
	/**
	 * 保存用户注册信息
	 * @param user
	 * @return 保存成功返回true，否则返回false
	 */
	
	public boolean saveMessage(Message message){
		boolean result = false;
		Connection con = null;
		try{
			con = DBCon.getConn();//获得数据库连接
			String sql="insert into tb_message(name,message,createTime) values(?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql); //创建PreparedStatement对象
			stmt.setString(1, message.getName());      //对第1个参数赋值
			stmt.setString(2, message.getNewMessage());//对第2个参数赋值
			stmt.setString(3, message.getMessageTime());//对第3个参数赋值
			int count = stmt.executeUpdate();//执行SQL命令后，返回所影响的行数
			if(count==1){
				result = true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<Message> selectAllMessage(){
		Connection con = null;
		List<Message> list = new ArrayList<Message>();
		try{
			con = DBCon.getConn();//获得数据库连接
			String sql="select * from tb_message";
			PreparedStatement stmt = con.prepareStatement(sql);//创建PreparedStatement对象
			ResultSet rs = stmt.executeQuery(); //执行查询并返回结果集
			while(rs.next()){//循环结果集，
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setName(rs.getString("name"));
				message.setNewMessage(rs.getString("message"));
				message.setMessageTime(rs.getString("createTime"));
				list.add(message);//将留言信息对象封装到List集合中
			}
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
