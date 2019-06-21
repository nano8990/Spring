package com.myspring.character;

public class Character {
	public String id;
	public int hp;
	public int atk;
	public int def;
	public int atkRate;
	public int defRate;
	public int speed;

	private int atkGauge;
	private int currentHp;
	
	public Character() {

	}
	
	public Character(int atkGauge, int currentHp) {
		this.atkGauge = atkGauge;
		this.currentHp = currentHp;
	}

	public Character(String id, int hp, int atk, int def, int atkRate, int defRate, int speed) {
		this.id = id;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.atkRate = atkRate;
		this.defRate = defRate;
		this.speed = speed;
	}

	public String getKeyNumber() {
		return this.id;
	}

	public void setKeyNumber(String keyNumber) {
		this.id = keyNumber;
	}

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return this.atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return this.def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getAtkRate() {
		return this.atkRate;
	}

	public void setAtkRate(int atkRate) {
		this.atkRate = atkRate;
	}

	public int getDefRate() {
		return this.defRate;
	}

	public void setDefRate(int defRate) {
		this.defRate = defRate;
	}

	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getAtkGauge() {
		return this.atkGauge;
	}

	public void setAtkGauge(int atkGauge) {
		this.atkGauge = atkGauge;
	}
	
	public int getCurrentHp() {
		return this.currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}
	
}
