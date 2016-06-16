package com.ghllz.travel.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "DayInfo")
public class DayInfo extends Model {
	
	@Column(name = "url")
	public String url;
	@Column(name = "indexOfDay")
	public String indexOfDay;
	@Column(name = "date")
	public String date;
	@Column(name = "titles")
	public String titles;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIndexOfDay() {
		return indexOfDay;
	}
	public void setIndexOfDay(String indexOfDay) {
		this.indexOfDay = indexOfDay;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitles() {
		return titles;
	}
	public void setTitles(String titles) {
		this.titles = titles;
	}
	@Override
	public String toString() {
		return "DayInfo [url=" + url + ", indexOfDay=" + indexOfDay + ", date="
				+ date + ", titles=" + titles + "]";
	}

}
