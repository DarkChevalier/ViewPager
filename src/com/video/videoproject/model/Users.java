package com.video.videoproject.model;

public class Users {
	private int userId;
	private String userName;
	private String userPassword;
	private String nickName;
	private String headPortraitAddr;
	private String sex;
	private String signature;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadPortraitAddr() {
		return headPortraitAddr;
	}
	public void setHeadPortraitAddr(String headPortraitAddr) {
		this.headPortraitAddr = headPortraitAddr;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
