package com.kdj.address;

public class Student {
	int idx;
	String name;
	String address;
	String birthday;

	Student(String name, String address, String birthday) {
		this.name = name;
		this.address = address;
		this.birthday = birthday;
	}
	
	Student(int idx, String name, String address, String birthday) {
		this.idx = idx;
		this.name = name;
		this.address = address;
		this.birthday = birthday;
	}
}
