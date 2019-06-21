package com.kdj.signin;

public class Person {
	public int idx;
	public String id;
	public String password;
	public String name;
	public String address;
	public String birthday;
	public String favorite_color;

	Person() {
	}

	Person(String id, String password, String name, String address, String birthday, String favoriteColor) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.favorite_color = favoriteColor;
	}

	public String toTableTagString() {
		String tagString = "";
		tagString = tagString + "<tr>";
		tagString = tagString + "<td>";
		tagString = tagString + this.idx;
		tagString = tagString + "</td>";
		tagString = tagString + "<td>";
		tagString = tagString + this.id;
		tagString = tagString + "</td>";
		tagString = tagString + "<td>";
		tagString = tagString + this.name;
		tagString = tagString + "</td>";
		tagString = tagString + "<td>";
		tagString = tagString + this.address;
		tagString = tagString + "</td>";
		tagString = tagString + "<td>";
		tagString = tagString + this.birthday;
		tagString = tagString + "</td>";
		tagString = tagString + "<td>";
		tagString = tagString + this.favorite_color;
		tagString = tagString + "</td>";
		tagString = tagString + "<td>";
		tagString = tagString + "<a href='modify?idx=" + this.idx + "'>수정하기</a>";
		tagString = tagString + "</td>";
		tagString = tagString + "</tr>";
		return tagString;
	}
}
