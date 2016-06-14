package com.ghllz.travel.bean;

import java.util.List;

public class DetailItem {
	List<String> Strings ;

	public List<String> getString() {
		return Strings;
	}

	public void setString(List<String> strings) {
		Strings = strings;
	}

	@Override
	public String toString() {
		return "DetailItem [Strings=" + Strings + "]";
	}
}
