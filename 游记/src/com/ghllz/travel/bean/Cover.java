package com.ghllz.travel.bean;

import java.io.Serializable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
@Table(name = "Cover")
public class Cover extends Model implements Serializable{
	@Column(name = "title")
	public String title;//����
	@Column(name = "bookUrl")
	public String bookUrl;//����ҳ��Url
	@Column(name = "headImageUrl")
	public String headImageUrl;//��ͼUrl
	@Column(name = "startTime")
	public String startTime;//��ʼʱ��
	@Column(name = "days")
	public String days;//����
	@Column(name = "likeCount")
	public String likeCount;//��������
	@Column(name = "userName")
	public String userName;//������
	@Column(name = "userHeadImgUrl")
	public String userHeadImgUrl;//����ͷ��
	@Column(name = "page")
	public int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String aboutTravel;//�����ó�
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
