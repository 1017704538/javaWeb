//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoadDataBaseDao {
    private static LoadDataBaseDao instance = null;

    public LoadDataBaseDao() {
    }

    public static LoadDataBaseDao getInstance() {
        if (instance == null) {
            instance = new LoadDataBaseDao();
        }

        return instance;
    }

    public List<String> getDatabaseName() {
        List<String> list = new ArrayList();
        Connection conn = null;

        try {
            conn = DBCon.getConn();
            String sql = "select schema_name from schemata";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                list.add(rs.getString(1));
            }

            rs.close();
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException var13) {
                var13.printStackTrace();
            }

        }

        return list;
    }

    public boolean loadDB(String database, String path) {
        try {
            String command = "cmd.exe /c mysql -uroot -p111 " + database;
            Process p = Runtime.getRuntime().exec(command);
            OutputStream out = p.getOutputStream();
            FileInputStream fs = new FileInputStream(path);
            InputStreamReader ir = new InputStreamReader(fs, "utf8");
            BufferedReader br = new BufferedReader(ir);
            StringBuffer sb = new StringBuffer("");

            String outLine;
            while((outLine = br.readLine()) != null) {
                sb.append(outLine + "\r\n");
            }

            Writer writer = new BufferedWriter(new OutputStreamWriter(out, "utf8"));
            writer.write(sb.toString());
            writer.flush();
            ir.close();
            br.close();
            writer.close();
            fs.close();
            return true;
        } catch (Exception var13) {
            var13.printStackTrace();
            return false;
        }
    }

    public boolean loadData(String database, String path) {
        try {
            String command = "cmd.exe /c mysql -uroot -p111 " + database + " < " + path;
            Runtime.getRuntime().exec(command);
            return true;
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }
}
