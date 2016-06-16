package com.ghllz.travel.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "PlanBox")
public class PlanBox extends Model{
	@Column(name = "url")
	public String url;
	@Column(name = "days")
	public int days;
	@Column(name = "content")
	public String content;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "PlanBox [url=" + url + ", days=" + days + ", content=" + content
				+ ", getUrl()=" + getUrl() + ", getDays()=" + getDays()
				+ ", getContent()=" + getContent() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
