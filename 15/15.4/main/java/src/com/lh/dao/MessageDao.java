//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.dao;

import com.lh.model.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    private static MessageDao instance = null;

    public MessageDao() {
    }

    public static MessageDao getInstance() {
        if (instance == null) {
            instance = new MessageDao();
        }

        return instance;
    }

    public boolean saveMessage(Message message) {
        boolean result = false;
        Connection con = null;

        try {
            con = DBCon.getConn();
            String sql = "insert into tb_message(name,message,createTime) values(?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, message.getName());
            stmt.setString(2, message.getNewMessage());
            stmt.setString(3, message.getMessageTime());
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

    public List<Message> selectAllMessage() {
        Connection con = null;
        ArrayList list = new ArrayList();

        try {
            con = DBCon.getConn();
            String sql = "select * from tb_message";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setName(rs.getString("name"));
                message.setNewMessage(rs.getString("message"));
                message.setMessageTime(rs.getString("createTime"));
                list.add(message);
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

        return list;
    }
}
