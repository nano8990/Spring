package com.kdj.favorite;

public class Student {
	public int idx;
	public String name;
	public String address;
	public String birthday;
	public String favorite_color;
	
	public Student() {
		
	}
	public Student(String name, String address, String birthday, String favoriteColor) {
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
		tagString = tagString + "<td>";
		tagString = tagString + "<a href='delete?idx=" + this.idx + "'>삭제하기</a>";
		tagString = tagString + "</td>";
		tagString = tagString + "</tr>";
		return tagString;
	}	
}
