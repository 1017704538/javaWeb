package com.lh.model;
/**
 * 留言信息类
 * @author lh
 *
 */

public class Message {
	private int id;            //编号
	private String name ;      //消息留言者
	private String newMessage; //留言内容
	private String messageTime;//留言时间
	
	public String getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNewMessage() {
		return newMessage;
	}
	public void setNewMessage(String newMessage) {
		this.newMessage = newMessage;
	}
}
