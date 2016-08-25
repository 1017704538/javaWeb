package com.lh.dao;

import java.io.UnsupportedEncodingException;

import com.lh.model.Employee;

public class Test {
	
	public static void main(String[] args) {
		Employee emp = new Employee();
		emp.setEmpName("james");
		emp.setEmpAge(25);
		emp.setEmpSex("man");
		emp.setEmpDuty("ASP.Net Programmer");
		boolean res = EmployeeDao.getInstance().saveEmployee(emp);
		if(res==true){
			System.out.println("插入数据成功！");
		}
		else{
			System.out.println("插入数据失败！");
		}
	}

}
