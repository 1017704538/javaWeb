package com.lh.dao;
import java.sql.*;
import java.util.Random;
public class StuDao {
	private static StuDao instance = null;
	public static StuDao getInstance(){
		if(instance==null) instance = new StuDao();
		return instance;
	}
	
	public int saveStuBatch(){
		int row = 0 ;	
		Connection conn = null;
		try {
			conn = DBCon.getConn();
			String sql = "insert into tb_student (id,name,sex,age) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);//创建PreparedStatement
			Random random = new Random();  //实例化Random
			for(int i = 0; i < 10; i++){   //循环添加数据
				ps.setInt(1, i+1);  //对SQL语句中的第1个参数赋值
				ps.setString(2, "student" + i);//对SQL语句中的第2个参数赋值
				ps.setBoolean(3, i % 2 == 0 ? true : false);//对SQL语句中的第3个参数赋值
				ps.setInt(4, random.nextInt(5) + 10);//对SQL语句中的第4个参数赋值
				ps.addBatch();//添加批处理命令
			}
			int[] rows = ps.executeBatch();//执行批处理操作并返回计数组成的数组
			row = rows.length;//对行数赋值
			ps.close();//关闭PreparedStatement
		} catch (Exception e) {
				e.printStackTrace();
		}finally{
			try {
				conn.close();//关闭Connection
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;//返回添加的行数
	}
}
