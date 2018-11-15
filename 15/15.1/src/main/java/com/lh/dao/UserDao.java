//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.dao;

import com.lh.model.UserInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static UserDao instance = null;

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }

        return instance;
    }

    public UserDao() {
    }

    public Boolean checkUserLog(UserInfo user) {
        boolean res = false;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = DBCon.getInstance();
            String sql = "select id from tb_userinfo where name=? and pwd=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPwd());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                res = true;
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException var14) {
                var14.printStackTrace();
            }

        }

        return res;
    }
}
