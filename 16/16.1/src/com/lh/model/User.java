package com.lh.model;

import java.util.Calendar;

/**
 * 用户注册信息类
 * @author LH
 */

public class User {
	private int userId;        		//用户编号
	private String userName;  		//用户名
	private String userPwd; 		//密码
	private String userSex;  		//性别
	private int userAge; 			//年龄
	private String userLoginTime; 	//注册时间
									//省略了get和set方法
	public User(){}
	/**
	 * 完整的构造方法
	 * @param name
	 * @param pwd
	 * @param sex
	 * @param age
	 * @param loginTime
	 */
	public User(String name,String pwd,String sex,int age,String loginTime){
		this.userName=name;
		this.userPwd=pwd;
		this.userSex=sex;
		this.userAge=age;
		this.userLoginTime=loginTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserLoginTime() {
		return userLoginTime;
	}
	public void setUserLoginTime(String userLoginTime) {
		this.userLoginTime = userLoginTime;
	}

}
