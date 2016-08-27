package com.lh.dao;

import java.util.List;

import com.lh.model.Employee;
public class Test {
	
	public static void main(String[] args) {
		List<Employee> empList = EmployeeDao.getInstance().selectEmployee();
		for(Employee emp:empList){
			System.out.println("----------------------------");
			System.out.print("员工姓名："+emp.getEmpName());
			System.out.print(",员工性别："+emp.getEmpSex());
			System.out.print(",员工年龄："+emp.getEmpAge());
			System.out.println(",员工职务："+emp.getEmpDuty());
		}
	}

}
