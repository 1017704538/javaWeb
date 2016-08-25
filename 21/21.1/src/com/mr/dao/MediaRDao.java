package com.mr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mr.model.MediaRInfo;
import com.mr.tool.JDBConnection;

public class MediaRDao {
	private static JDBConnection connection = null;

	private String sql = null;

	public MediaRDao() {
		connection = new JDBConnection();
	}
	/**
	 * 获取当期系统时间
	 * @return
	 */
	public String getNowTime(){
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nowtime = sdf.format(date);
		return nowtime;	
	}
	/**
	 * 查询视频留言
	 * @param mediaR_rootId
	 * @return
	 */
	public List mediaR_query(Integer mediaR_rootId) {
		List<MediaRInfo> list = new ArrayList<MediaRInfo>();//定义List集合
		//根据视频id值查询视频的留言信息SQL语句
		sql = "select * from tb_mediaR where mediaR_rootId = " + mediaR_rootId
				+ " order by id desc";
		ResultSet rs = connection.executeQuery(sql);//执行查询操作
		try {
			while (rs.next()) {
				MediaRInfo mediaRInfo = new MediaRInfo();//实例化留言信息实体
				mediaRInfo.setId(rs.getInt(1));
				mediaRInfo.setMediaR_rootId(rs.getInt(2));
				mediaRInfo.setMediaR_author(rs.getString(3));
				mediaRInfo.setMediaR_content(rs.getString(4));
				mediaRInfo.setMediaR_time(rs.getString(5));
				list.add(mediaRInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;//返回list集合
	}
	/**
	 * 添加留言信息
	 * @param mediaRInfo 留言信息实体对象
	 */
	public void media_add(MediaRInfo mediaRInfo) {
		//添加留言信息的SQL语句
		sql = "insert into tb_mediaR (mediaR_rootId,mediaR_author,mediaR_conten,mediaR_time) values (" 
				+ mediaRInfo.getMediaR_rootId()
				+ ",'" + mediaRInfo.getMediaR_author() + "','"
				+ mediaRInfo.getMediaR_content() + "','"+this.getNowTime()+"')";
		connection.executeUpdata(sql);//执行添加操作
	}
	/**
	 * 删除用户信息
	 * @param mediaR_author 用户名称
	 */
	public void media_deleteAllUser(String mediaR_author) {
		sql = "delete from tb_mediaR where mediaR_author = '" + mediaR_author
				+ "'";
		connection.executeUpdata(sql);
	}
	/**
	 * 删除视频留言的方法
	 * @param mediaR_rootId 留言关联的视频ID值
	 */
	public void media_deleteAllId(Integer mediaR_rootId) {
		//执行删除视频留言信息的SQL语句
		sql = "delete from tb_mediaR where mediaR_rootId = " + mediaR_rootId+ "";
		connection.executeUpdata(sql);
	}
	/**
	 * 删除视频的方法
	 * @param id 视频ID
	 */
	public void media_delete(Integer id) {
		//执行删除视频信息的SQL语句
		sql = "delete from tb_mediaR where id = " + id + "";
		connection.executeUpdata(sql);
	}
}
