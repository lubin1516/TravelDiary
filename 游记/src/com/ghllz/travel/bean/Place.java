package com.ghllz.travel.bean;

import java.io.Serializable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Place")
public class Place extends Model implements Serializable{
	@Column(name = "city")
	private String city;
	@Column(name = "city_url")
	private String city_url;
	@Column(name = "sortLetter")
	private String sortLetter;
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getCity_url() {
	return city_url;
}
public void setCity_url(String city_url) {
	this.city_url = city_url;
}
public String getSortLetter() {
	return sortLetter;
}
public void setSortLetter(String sortLetter) {
	this.sortLetter = sortLetter;
}
@Override
public String toString() {
	return "Place [city=" + city + ", city_url=" + city_url + ", sortLetter="
			+ sortLetter + "]";
}

}
