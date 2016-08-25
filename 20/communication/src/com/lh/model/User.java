package com.lh.model;

import java.util.Calendar;

/**
 * 用户实体类
 * @author LH
 */

public class User {
	private String userId;			//编号	
	private String userName;		//用户名	
	private String userPwd;			//密码	
	private String userSex;			//性别	
	private Integer userAge;		//年龄
	private Calendar userLoginTime;	//注册时间
	
	public User(){}
	/**
	 * 完整的构造方法
	 * @param name
	 * @param pwd
	 * @param sex
	 * @param age
	 * @param loginTime
	 */
	public User(String name,String pwd,String sex,Integer age,Calendar loginTime){
		this.userName=name;
		this.userPwd=pwd;
		this.userSex=sex;
		this.userAge=age;
		this.userLoginTime=loginTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
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
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public Calendar getUserLoginTime() {
		return userLoginTime;
	}
	public void setUserLoginTime(Calendar userLoginTime) {
		this.userLoginTime = userLoginTime;
	}

}
