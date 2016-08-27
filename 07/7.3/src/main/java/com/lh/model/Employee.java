package com.lh.model;
/**
 * 员工信息类（POJO）
 * @author lh
 */
public class Employee {
	private int empId;       //员工编号
	private String empName;  //员工姓名
	private int empAge;      //员工年龄 
	private String empSex;   //员工性别
	private String empDuty;  //员工职务
	public Employee(){}      //默认构造方法
	//省略get和set方法
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEmpAge() {
		return empAge;
	}
	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}
	public String getEmpSex() {
		return empSex;
	}
	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}
	public String getEmpDuty() {
		return empDuty;
	}
	public void setEmpDuty(String empDuty) {
		this.empDuty = empDuty;
	}
}
