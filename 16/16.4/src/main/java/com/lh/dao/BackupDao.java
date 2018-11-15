//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.dao;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BackupDao {
    private static BackupDao instance = null;

    public BackupDao() {
    }

    public static BackupDao getInstance() {
        if (instance == null) {
            instance = new BackupDao();
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

    public boolean backupDB(String database, String path) {
        try {
            String command = "cmd.exe /c mysqldump -uroot -p111 -d --add-drop-table " + database + " ";
            Process p = Runtime.getRuntime().exec(command);
            InputStreamReader inReader = new InputStreamReader(p.getInputStream(), "utf8");
            BufferedReader br = new BufferedReader(inReader);
            StringBuffer sb = new StringBuffer("");

            String line;
            while((line = br.readLine()) != null) {
                sb.append(line + "\r\n");
            }

            FileOutputStream fs = new FileOutputStream(path);
            OutputStreamWriter writer = new OutputStreamWriter(fs, "utf8");
            writer.write(sb.toString());
            writer.flush();
            inReader.close();
            br.close();
            writer.close();
            fs.close();
            return true;
        } catch (Exception var11) {
            var11.printStackTrace();
            return false;
        }
    }
}
