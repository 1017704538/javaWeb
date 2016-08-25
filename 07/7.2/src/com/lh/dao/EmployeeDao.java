package com.lh.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.lh.model.Employee;
public class EmployeeDao {
	
	private static EmployeeDao instance = null;//创建一个空的该类对象
	public static EmployeeDao getInstance(){
		if(instance==null) instance = new EmployeeDao();//如果该类的对象为空，则创建该类对象
		return instance;//返回该类的对象
	}
/**
 * 保存员工信息到数据库	
 * @param emp 封装员工信息的对象
 * @return 操作成功返回true,否则返回false
 */
public boolean saveEmployee(Employee emp){
	boolean result = false;
	Connection con = null;
	try{
		con = DBCon.getConn();//建立数据库连接
		String sql = "insert into tb_employee(name,age,sex,duty) values(?,?,?,?)";//插入数据的SQL语句
		PreparedStatement stmt = con.prepareStatement(sql);//预编译SQL语句
		stmt.setString(1, emp.getEmpName());//设置SQL语句第1个“?”的参数值
		stmt.setInt(2, emp.getEmpAge());//设置SQL语句第2个“?”的参数值
		stmt.setString(3, emp.getEmpSex());//设置SQL语句第3个“?”的参数值
		stmt.setString(4, emp.getEmpDuty());//设置SQL语句第4个“?”的参数值
		int i = stmt.executeUpdate();//执行插入数据操作,返回影响的行数	
		if(i==1) result = true;//插入成功，返回true
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
/**
 * 查询员工信息
 * @return 员工信息的List<Employee>集合
 */

public List<Employee> selectEmployee(){
	List<Employee> empList = new ArrayList<Employee>();
	Connection conn = null;
	try{
		conn=DBCon.getConn();
		Statement stmt = conn.createStatement();
		//执行SQL语句并返回结果集
		ResultSet rs = stmt.executeQuery("select * from tb_employee");
		while(rs.next()){
			Employee emp = new Employee();
			emp.setEmpId(rs.getInt("id"));//从结果集中获得id值并封装到Employee对象中
			emp.setEmpName(rs.getString("name"));//从结果集中获得name值并封装到Employee对象中
			emp.setEmpSex(rs.getString("sex"));//从结果集中获得sex值并封装到Employee对象中
			emp.setEmpAge(rs.getInt("age"));//从结果集中获得age值并封装到Employee对象中
			emp.setEmpDuty(rs.getString("duty"));//从结果集中获得duty值并封装到Employee对象中
			empList.add(emp);//把Employee对象保存到List集合中
			
		}
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	finally{
		try {
			conn.close();//关闭数据库连接
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return empList;
}

}
