package com.lh.dao;

import java.util.List;

import com.lh.model.Employee;
public class Test {
	
	public static void main(String[] args) {
		List<Employee> empList = EmployeeDao.getInstance().selectEmployee();
		for(Employee emp:empList){
			System.out.println("----------------------------");
			System.out.println("员工编号："+emp.getEmpId());
		}
		Employee emp = new Employee();
		emp.setEmpId(13);//已知数据库中的某员工编号
		emp.setEmpAge(28);
		boolean res = EmployeeDao.getInstance().updateEmployee(emp);
		if(res==true){
			System.out.println("编号为13的员工信息修改成功！");
		}else{
			System.out.println("编号为13的员工信息修改失败！");
		}
	}

}
