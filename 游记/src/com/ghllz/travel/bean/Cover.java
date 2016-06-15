package com.ghllz.travel.bean;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Cover implements Serializable{
	@DatabaseField(canBeNull=false)//该项内容不能为空
	private String title;//标题
	@DatabaseField(canBeNull=false)//该项内容不能为空
	private String bookUrl;//详情页面Url
	@DatabaseField(canBeNull=false)//该项内容不能为空
	private String headImageUrl;//大图Url
	@DatabaseField(canBeNull=false)//该项内容不能为空
	private String startTime;//开始时间
	@DatabaseField(canBeNull=false)//该项内容不能为空
	private String days;//天数
	@DatabaseField(canBeNull=false)//该项内容不能为空
	private String likeCount;//点赞数量
	@DatabaseField(canBeNull=false)//该项内容不能为空
	private String userName;//作者名
	@DatabaseField
	private String userHeadImgUrl;//作者头像
	@DatabaseField
	private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	private String aboutTravel;//关于旅程
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
	public String getAboutTravel() {
		return aboutTravel;
	}
	public void setAboutTravel(String aboutTravel) {
		this.aboutTravel = aboutTravel;
	}
	
	@Override
	public String toString() {
		return "Cover [title=" + title + ", bookUrl=" + bookUrl
				+ ", headImageUrl=" + headImageUrl + ", startTime=" + startTime
				+ ", days=" + days + ", likeCount=" + likeCount + ", userName="
				+ userName + ", userHeadImgUrl=" + userHeadImgUrl + ", page="
				+ page + ", aboutTravel=" + aboutTravel + "]";
	}
	
}
