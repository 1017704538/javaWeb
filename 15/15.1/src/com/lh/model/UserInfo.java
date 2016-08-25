package com.lh.model;

import java.util.Date;

/**
 * 用户信息实体类
 * @author lh
 */
public class UserInfo {
	private int id;       //用户编号
	private String name;  //用户名	
	private String pwd;   //密码
	public UserInfo(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
