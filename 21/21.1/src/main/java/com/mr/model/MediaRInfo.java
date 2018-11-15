package com.mr.model;
/**
 * 留言信息实体类
 * 
 * @author CHUNBIN
 * 
 */
public class MediaRInfo {
	private Integer id; // 自动编号属性设置
	private Integer mediaR_rootId; // 回复视频的id号
	private String mediaR_author; // 回复视频人名称
	private String mediaR_content; // 回复视频内容
	private String mediaR_time; // 回复视频时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMediaR_rootId() {
		return mediaR_rootId;
	}
	public void setMediaR_rootId(Integer mediaRRootId) {
		mediaR_rootId = mediaRRootId;
	}
	public String getMediaR_author() {
		return mediaR_author;
	}
	public void setMediaR_author(String mediaRAuthor) {
		mediaR_author = mediaRAuthor;
	}
	public String getMediaR_content() {
		return mediaR_content;
	}
	public void setMediaR_content(String mediaRContent) {
		mediaR_content = mediaRContent;
	}
	public String getMediaR_time() {
		return mediaR_time;
	}
	public void setMediaR_time(String mediaRTime) {
		mediaR_time = mediaRTime;
	}
}
