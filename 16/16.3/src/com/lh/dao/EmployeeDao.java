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
			String sql = "select  * from tb_employee order by id limit ?,?" ;//查询分页SQL语句
			PreparedStatement pstmt = con.prepareStatement(sql);//预编译select语句
			pstmt.setInt(1, firstResult);						//设置第1个参数值
			pstmt.setInt(2, pageSize);							//设置第2个参数值
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
	
public List<Employee> selectEmpByCondition(int firstResult,int pageSize,String condition){
	Connection con = null;	
	List<Employee> list = new ArrayList<Employee>();
	try{
		con = DBCon.getConn();								//创建数据库连接
		StringBuffer sb = new StringBuffer();
		sb.append("select * from tb_employee where 1=1 ");
		if(condition!=null&&!condition.equals("")){
			if(condition.indexOf(",")!=-1){//如果字符串中包含","，则分割字符串
				String conditionStr[] = condition.trim().split(",");
				for(int i=0;i<conditionStr.length;i++){
					sb.append(" and name like "+"'%"+conditionStr[i]+"%'"+"");
				}
			}else{
				sb.append(" and name like "+"'%"+condition+"%'"+"");//姓名模糊查询条件
			}			
		}
		sb.append("order by id limit ?,?");	
		PreparedStatement pstmt = con.prepareStatement(sb.toString());//预编译select语句	
		pstmt.setInt(1, firstResult);
		pstmt.setInt(2, pageSize);
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
	
public int selectEmpCountByCondition(String condition){
	Connection con = null;	
	try{
		con = DBCon.getConn();											//创建数据库连接
		StringBuffer sb = new StringBuffer();
		sb.append("select count(id) from tb_employee where 1=1");
		if(condition!=null&&!condition.equals("")){
			if(condition.indexOf(",")!=-1){//如果字符串中包含","，则分割字符串
				String conditionStr[] = condition.split(",");
				for(int i=0;i<conditionStr.length;i++){
					sb.append(" and name like "+"'%"+conditionStr[i]+"%'"+"");
				}
			}else{
				sb.append(" and name like "+"'%"+condition+"%'"+"");//姓名模糊查询条件
			}			
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());	//预编译select语句	
		ResultSet rs = pstmt.executeQuery();							//执行查询并返回结果集
		if(rs.next())			//使用count函数查询时结果集只有1行，所有此处用if判断							
			return rs.getInt(1);//返回查询的数据总数
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
	return 0;
}
}
