package com.video.videoproject.model;

public class VideoMessage {
	private int videoId;
	private String videoName;
	private String videoAddress;
	private int userId;
	private int categoryId;
	private String ReleaseDate;
	private int collectNum;
	private int isLikeNum;
	private int isClickNum;
	private int commentId;
	
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoAddress() {
		return videoAddress;
	}
	public void setVideoAddress(String videoAddress) {
		this.videoAddress = videoAddress;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getReleaseDate() {
		return ReleaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		ReleaseDate = releaseDate;
	}
	public int getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}
	public int getIsLikeNum() {
		return isLikeNum;
	}
	public void setIsLikeNum(int isLikeNum) {
		this.isLikeNum = isLikeNum;
	}
	public int getIsClickNum() {
		return isClickNum;
	}
	public void setIsClickNum(int isClickNum) {
		this.isClickNum = isClickNum;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
}
