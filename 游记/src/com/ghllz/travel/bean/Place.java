package com.ghllz.travel.bean;

import java.io.Serializable;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Place")
public class Place extends Model implements Serializable {
	@Column(name = "city")
	private String city;
	@Column(name = "city_url")
	private String city_url;
	@Column(name = "sortLetter")
	private String sortLetter;
	@Column(name = "upperCase")
	private String upperCase;
	@Column(name = "lowerCase")
	private String lowerCase;

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

	public String getUpperCase() {
		return upperCase;
	}

	public void setUpperCase(String upperCase) {
		this.upperCase = upperCase;
	}

	public String getLowerCase() {
		return lowerCase;
	}

	public void setLowerCase(String lowerCase) {
		this.lowerCase = lowerCase;
	}

	@Override
	public String toString() {
		return "Place [city=" + city + ", city_url=" + city_url + ", sortLetter=" + sortLetter + ", upperCase="
				+ upperCase + ", lowerCase=" + lowerCase + "]";
	}

}
