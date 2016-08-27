package com.lh.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.lh.model.Employee;

public class Test {
	
public static void main(String[] args) {
	List<Employee> empList = EmployeeDao.getInstance().selectAllEmp();
	for(Employee emp:empList){
		System.out.println("-------------------------------------");
		System.out.println("姓名："+emp.getEmpName()+"，性别："+emp.getEmpSex()+
							"，年龄："+emp.getEmpAge()+"，职务："+emp.getEmpDuty());
	}
}

}
