package com.hrl.image;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_database17";
    private static final String user = "root";
    private static final String password = "111";
    /**
     * 获得数据库连接
     * @return
     */
    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 将图片保存到数据库
     * @param image
     */
    public void saveImage(Image image) {
        Connection conn = this.getConnection();
        String sql = "insert into tb_image(image_,image_ContentType,image_FileName) values(?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setBytes(1, image.getImageByte());
            pst.setString(2, image.getImage_ContentType());
            pst.setString(3, image.getImage_FileName());
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 查询所有数据库中的图片信息
     * @return
     */
    public List<Image> queryAllImage() {
        Connection conn = this.getConnection();
        List<Image> images = new ArrayList<Image>();
        PreparedStatement pst = null;
        String sql = "select * from tb_image";
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Image image = new Image();
                image.setImage_id(rs.getInt(1));
                image.setImage_ContentType(rs.getString(2));
                image.setImageByte(rs.getBytes(3));
                image.setImage_FileName(rs.getString(4));
                images.add(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return images;
    }
}
