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

    public boolean checkLogin(String name, String pwd) {
        boolean result = false;
        Connection con = null;

        try {
            con = DBCon.getConn();
            String sql = "select * from tb_user where name=? and pwd=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, pwd);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException var15) {
                var15.printStackTrace();
            }

        }

        return result;
    }

    public boolean checkUserName(String name) {
        boolean result = false;
        Connection con = null;

        try {
            con = DBCon.getConn();
            String sql = "select id from tb_user where name=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = true;
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

        return result;
    }

    public boolean saveUser(User user) {
        boolean result = false;
        Connection con = null;

        try {
            con = DBCon.getConn();
            String sql = "insert into tb_user(name,pwd,age,sex) values(?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPwd());
            stmt.setInt(3, user.getAge());
            stmt.setString(4, user.getSex());
            int count = stmt.executeUpdate();
            if (count == 1) {
                result = true;
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

        return result;
    }
}
