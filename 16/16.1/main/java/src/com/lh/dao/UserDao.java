//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.dao;

import com.lh.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static UserDao instance = null;

    public UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }

        return instance;
    }

    public boolean saveUser(User user) {
        Connection con = null;

        try {
            con = DBCon.getConn();
            String sql = "insert into tb_user(name,pwd,sex,age,createTime) values(?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getUserPwd());
            pstmt.setString(3, user.getUserSex());
            pstmt.setInt(4, user.getUserAge());
            pstmt.setString(5, user.getUserLoginTime());
            int row = pstmt.executeUpdate();
            if (row == 1) {
                return true;
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException var14) {
                var14.printStackTrace();
            }

        }

        return false;
    }

    public List<User> selectUser() {
        Connection con = null;
        ArrayList list = new ArrayList();

        try {
            con = DBCon.getConn();
            String sql = "select * form tb_user";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("name"));
                user.setUserPwd(rs.getString("pwd"));
                user.setUserSex(rs.getString("sex"));
                user.setUserLoginTime(rs.getString("createTime"));
                user.setUserAge(rs.getInt("age"));
                list.add(user);
            }

            rs.close();
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException var14) {
                var14.printStackTrace();
            }

        }

        return list;
    }
}
