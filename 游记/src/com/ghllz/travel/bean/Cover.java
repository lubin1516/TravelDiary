package com.ghllz.travel.bean;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Cover implements Serializable{
	@DatabaseField(canBeNull=false)//�������ݲ���Ϊ��
	private String title;//����
	@DatabaseField(canBeNull=false)//�������ݲ���Ϊ��
	private String bookUrl;//����ҳ��Url
	@DatabaseField(canBeNull=false)//�������ݲ���Ϊ��
	private String headImageUrl;//��ͼUrl
	@DatabaseField(canBeNull=false)//�������ݲ���Ϊ��
	private String startTime;//��ʼʱ��
	@DatabaseField(canBeNull=false)//�������ݲ���Ϊ��
	private String days;//����
	@DatabaseField(canBeNull=false)//�������ݲ���Ϊ��
	private String likeCount;//��������
	@DatabaseField(canBeNull=false)//�������ݲ���Ϊ��
	private String userName;//������
	@DatabaseField
	private String userHeadImgUrl;//����ͷ��
	@DatabaseField
	private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	private String aboutTravel;//�����ó�
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
