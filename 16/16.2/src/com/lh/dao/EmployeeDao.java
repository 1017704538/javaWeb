package com.lh.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.lh.model.Employee;

/**
 * 处理员工信息的DAO管理类
 * @author LH
 */
public class EmployeeDao {
	private static EmployeeDao instance = null;
	/**
	 * 返回一个EmployeeDao实例
	 * @return
	 */
	public static EmployeeDao getInstance(){
		if(instance==null) instance = new EmployeeDao();
		return instance;
	}

public int selectEmpCount(){
	Connection con = null;
	int rows = 0;
	try{
		con = DBCon.getConn();//创建数据库连接
		String sql = "select count(id) from tb_employee";
		PreparedStatement stmt = con.prepareStatement(sql); //创建PreparedStatement对象
		ResultSet rs = stmt.executeQuery(); //执行SQL并返回结果集
		if(rs.next())
			rows = rs.getInt(1);//返回所有数据的行数
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return rows;
}

	/**
	 * 分页查询数据
	 * @param firstResult：上一页数据查询的起始位置
	 * @param pageSize：每页显示的数据行数
	 * @return
	 */
	
public List<Employee> selectEmp(int firstResult,int pageSize){
	Connection con = null;	
	List<Employee> list = new ArrayList<Employee>();
	try{
		con = DBCon.getConn();								//创建数据库连接
		String sql = "select  * from tb_employee order by id limit "+firstResult+","+pageSize+" " ;					//查询分页SQL语句
		PreparedStatement pstmt = con.prepareStatement(sql);//预编译select语句
		ResultSet rs = pstmt.executeQuery();				//执行查询并返回结果集
		while(rs.next()){									//循环结果集
			Employee emp = new Employee();
			emp.setEmpId(rs.getInt("id"));					//设置编号
			emp.setEmpName(rs.getString("name"));			//设置姓名
			emp.setEmpSex(rs.getString("sex"));				//设置性别
			emp.setEmpAge(rs.getInt("age"));				//设置年龄
			emp.setDuty(rs.getString("duty"));				//设置职务
			emp.setDept(rs.getString("dept"));				//设置部门
			emp.setTelephoneNo(rs.getString("telephoneNo"));//设置电话
			emp.setAddress(rs.getString("address"));		//设置联系地址
			list.add(emp);									//将信息对象封装到集合中
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
