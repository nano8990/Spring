package com.kdj.game;

public class Player {
	public int idx;
	public String id;
	public String password;
	public String name;
	public String attackPower;
	public String attackRate;
	public String defensePower;
	public String defenseRate;
	
	Player() {
		
	}
	
	Player(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}
	
}
