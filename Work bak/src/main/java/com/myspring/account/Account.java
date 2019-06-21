package com.myspring.account;

public class Account {
	public String keyNumber;
	public String id;
	public String passwd;
	
	public Account(){
	}
	
	public Account(String keyNumber, String id, String passwd){
		this.keyNumber = keyNumber;
		this.id = id;
		this.passwd = passwd;
	}
	
}
