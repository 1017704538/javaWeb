//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.model;

public class User {
    private int userId;
    private String userName;
    private String userPwd;
    private String userSex;
    private int userAge;
    private String userLoginTime;

    public User() {
    }

    public User(String name, String pwd, String sex, int age, String loginTime) {
        this.userName = name;
        this.userPwd = pwd;
        this.userSex = sex;
        this.userAge = age;
        this.userLoginTime = loginTime;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return this.userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserSex() {
        return this.userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserAge() {
        return this.userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserLoginTime() {
        return this.userLoginTime;
    }

    public void setUserLoginTime(String userLoginTime) {
        this.userLoginTime = userLoginTime;
    }
}
