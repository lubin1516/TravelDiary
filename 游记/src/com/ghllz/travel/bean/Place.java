package com.ghllz.travel.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Place {
	@DatabaseField
	private String city;
	@DatabaseField
	private String city_url;
	@DatabaseField
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
