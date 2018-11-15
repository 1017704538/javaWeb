package com.lh.model;
/**
 * 讯友录信息实体类
 * @author LH
 */

public class PersonInfo {
	private String pId;			//编号
	private String pName;		//姓名
	private String pSex;		//性别
	private Integer pAge;		//年龄
	private String pMobileNo;	//手机号码
	private String email;		//电子邮件
	private String address;		//联系地址
	private PersonType type;	//与讯友对象关联的类别对象
	private User user;			//与讯友对象关联的用户对象
	//省略了get和set方法
	/**
	 * 默认构造方法
	 */
	public PersonInfo(){}
	/**
	 * 完整的构造方法
	 * @param name 姓名
	 * @param sex 性别
	 * @param age 年龄
	 * @param mobile 手机号
	 * @param email 电子邮件
	 * @param address 住址
	 * @param type 类别对象
	 */
	public PersonInfo(String name,String sex,Integer age,String mobile,String email,String address,PersonType type){
		this.pName=name;
		this.pSex=sex;
		this.pAge=age;
		this.pMobileNo=mobile;
		this.email=email;
		this.address=address;
		this.type=type;
	}
	/**
	 * 返回编号
	 * @return
	 */
	public String getpId() {
		return pId;
	}
	/**
	 * 设置编号
	 * @param pId
	 */
	public void setpId(String pId) {
		this.pId = pId;
	}
	/**
	 * 返回姓名
	 * @return
	 */
	public String getpName() {
		return pName;
	}
	/**
	 * 设置姓名
	 * @param pName
	 */
	public void setpName(String pName) {
		this.pName = pName;
	}
	/**
	 * 返回性别
	 * @return
	 */
	public String getpSex() {
		return pSex;
	}
	/**
	 * 设置性别
	 * @param pSex
	 */
	public void setpSex(String pSex) {
		this.pSex = pSex;
	}
	/**
	 * 返回年龄
	 */
	public Integer getpAge() {
		return pAge;
	}
	/**
	 * 设置年龄
	 * @param pAge
	 */
	public void setpAge(Integer pAge) {
		this.pAge = pAge;
	}
	/**
	 * 返回手机号
	 * @return
	 */
	public String getpMobileNo() {
		return pMobileNo;
	}
	/**
	 * 设置手机号
	 * @param pMobileNo
	 */
	public void setpMobileNo(String pMobileNo) {
		this.pMobileNo = pMobileNo;
	}
	/**
	 * 返回电子邮件地址
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置电子邮件地址
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 返回住址
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置住址
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 返回类型实例
	 * @return
	 */
	public PersonType getType() {
		return type;
	}
	/**
	 * 设置类型实例
	 * @param type
	 */
	public void setType(PersonType type) {
		this.type = type;
	}
	/**
	 * 返回用户实例
	 * @return
	 */
	public User getUser() {
		return user;
	}
	/**
	 * 设置用户实例
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
