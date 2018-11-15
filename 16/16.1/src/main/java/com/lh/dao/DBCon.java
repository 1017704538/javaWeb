//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {
    private static Connection conn = null;

    public DBCon() {
    }

    public static Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String user = "root";
            String pwd = "111";
            String url = "jdbc:mysql://localhost:3306/db_database16";
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return conn;
    }
}
