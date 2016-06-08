package com.ghllz.travel.bean;

public class Cover {
	private String title;//标题
	private String bookUrl;//详情页面Url
	private String headImageUrl;//大图Url
	private String startTime;//开始时间
	private String days;//天数
	private String likeCount;//点赞数量
	private String userName;//作者名
	private String userHeadImgUrl;//作者头像
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBookUrl() {
		return bookUrl;
	}
	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}
	public String getHeadImageUrl() {
		return headImageUrl;
	}
	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserHeadImgUrl() {
		return userHeadImgUrl;
	}
	public void setUserHeadImgUrl(String userHeadImgUrl) {
		this.userHeadImgUrl = userHeadImgUrl;
	}
	@Override
	public String toString() {
		return "covers [title=" + title + ", bookUrl=" + bookUrl
				+ ", headImageUrl=" + headImageUrl + ", startTime=" + startTime
				+ ", days=" + days + ", likeCount=" + likeCount + ", userName="
				+ userName + ", userHeadImgUrl=" + userHeadImgUrl + "]";
	}
	
}
