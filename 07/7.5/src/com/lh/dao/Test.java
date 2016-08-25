package com.lh.dao;

import java.util.List;

import com.lh.model.Employee;
public class Test {
	
	public static void main(String[] args) {
		boolean res = EmployeeDao.getInstance().deleteEmployeeById(12);
		if(res==true){
			System.out.println("删除成功！");
		}else{
			System.out.println("删除失败！");
		}
	}

}
