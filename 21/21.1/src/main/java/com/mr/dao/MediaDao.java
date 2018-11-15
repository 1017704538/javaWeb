package com.mr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mr.model.MediaInfo;
import com.mr.tool.JDBConnection;

public class MediaDao {

	private static JDBConnection connection = null;

	private String sql = null;	
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
	
	public MediaDao() {
		connection = new JDBConnection();
	}

	public void media_addNumber(Integer id) {
		sql = "update tb_media set media_lookCount=media_lookCount+1 where id = "
				+ id + " ";
		connection.executeUpdata(sql);

	}

	public void media_delete(Integer id) {
		sql = "delete from tb_media where id = " + id + " ";
		connection.executeUpdata(sql);

	}

	public boolean media_add(MediaInfo info) {
		sql = "insert into tb_media (media_title,media_type,media_src,media_pic,media_info,media_uptime,media_lookCount) values ('" 
				+ info.getMediaTitle() + "','"
				+ info.getMedia_type() + "','" + info.getMediaSrc() + "','"
				+ info.getMediaPic() + "','" + info.getMediaInfo()
				+ "','"+this.getNowTime()+"',0)";
		return connection.executeUpdata(sql);
	}

	/**
	 * 按类别查找视频信息
	 * 
	 * @param type
	 *            视频类别参数
	 * @return
	 */
	public List media_query(String type) {
		List list = new ArrayList();
		MediaInfo mediaInfo = null;
		if (null == type) {// 当type参数为null时，设置对视频文件的全部查询的SQL语句
			sql = "select * from tb_media order by media_uptime desc";
		} else { // 当type参数不为null时，根据type内容设置对视频文件类别查询的SQL语句
			sql = "select * from tb_media where media_type = '" + type
					+ "' order by media_uptime desc";
		}
		ResultSet rs = connection.executeQuery(sql);// 执行对视频查询的SQL语句
		try {
			while (rs.next()) {
				mediaInfo = new MediaInfo();// 实例化视频MediaInfo类的对象
				mediaInfo.setId(rs.getInt(1));// 自动编号赋值
				mediaInfo.setMediaTitle(rs.getString(2));// 视频标题赋值
				mediaInfo.setMedia_type(rs.getString(3));// 视频类别赋值
				mediaInfo.setMediaSrc(rs.getString(4));// 视频存放位置赋值
				mediaInfo.setMediaPic(rs.getString(5));// 视频截图存放位置赋值
				mediaInfo.setMediaInfo(rs.getString(6));// 视频信息赋值
				mediaInfo.setMediaUptime(rs.getString(7));// 视频上传时间赋值
				mediaInfo.setLookCount(rs.getString(8));// 视频浏览次数赋值
				list.add(mediaInfo);// 将视频MediaInfo类的对象存放在List集合对象中
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;// 返回查询的结果list对象
	}
	/**
	 * 视频信息的模糊查询
	 * 
	 * @param key
	 *            模糊查询的参数
	 * @return
	 */
	public List media_querySearch(String key) {
		List list = new ArrayList();// 定义List集合
		MediaInfo mediaInfo = null;
		// 设置media_title字段和media_info字段的模糊查询SQL语句
		sql = "select * from tb_media  where (media_title like '%" + key
				+ "%' or media_info like '%" + key + "%')";
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				mediaInfo = new MediaInfo();// 实例化视频对象实体
				mediaInfo.setId(rs.getInt(1));
				mediaInfo.setMediaTitle(rs.getString(2));
				mediaInfo.setMedia_type(rs.getString(3));
				mediaInfo.setMediaSrc(rs.getString(4));
				mediaInfo.setMediaPic(rs.getString(5));
				mediaInfo.setMediaInfo(rs.getString(6));
				mediaInfo.setMediaUptime(rs.getString(7));
				mediaInfo.setLookCount(rs.getString(8));
				list.add(mediaInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;// 返回List结合
	}

	public List media_queryAuto(String sql) {
		List list = new ArrayList();
		MediaInfo mediaInfo = null;
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				mediaInfo = new MediaInfo();
				mediaInfo.setId(rs.getInt(1));
				mediaInfo.setMediaTitle(rs.getString(2));
				mediaInfo.setMedia_type(rs.getString(3));
				mediaInfo.setMediaSrc(rs.getString(4));
				mediaInfo.setMediaPic(rs.getString(5));
				mediaInfo.setMediaInfo(rs.getString(6));
				mediaInfo.setMediaUptime(rs.getString(7));
				mediaInfo.setLookCount(rs.getString(8));
				list.add(mediaInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	/**
	 * 根据视频id查询视频信息
	 * @param id 视频id
	 * @return
	 */
	public MediaInfo media_query(Integer id) {
		MediaInfo mediaInfo = new MediaInfo();//实例化视频对象
		//根据id查询视频信息的SQL语句
		sql = "select * from tb_media where id = " + id + "";
		ResultSet rs = connection.executeQuery(sql);//执行查询方法
		try {
			while (rs.next()) {
				mediaInfo.setId(rs.getInt(1));
				mediaInfo.setMediaTitle(rs.getString(2));
				mediaInfo.setMedia_type(rs.getString(3));
				mediaInfo.setMediaSrc(rs.getString(4));
				mediaInfo.setMediaPic(rs.getString(5));
				mediaInfo.setMediaInfo(rs.getString(6));
				mediaInfo.setMediaUptime(rs.getString(7));
				mediaInfo.setLookCount(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mediaInfo;//返回实体对象
	}

	public Integer media_queryNumber(String type) {
		Integer number = 0;
		if (null == type)
			sql = "select count(*) as number from tb_media";
		else
			sql = "select count(*) as number from tb_media where media_type='"
					+ type + "'";
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				number = rs.getInt("number");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return number;
	} // 返回查询的结果list对象
}
