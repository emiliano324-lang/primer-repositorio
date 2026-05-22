package models;

public abstract class Character {

	private String name;
	
	private int maxHealth;
	private int health;
	private int attackPoints;
	private int blockPoints;
	private int healPoints;
	
	
	
	
	public Character(String name, int maxHealth, int health, int attackPoints, int blockPoints, int healPoints) {
		this.name = name;
		this.maxHealth = maxHealth;
		this.health = health;
		this.attackPoints = attackPoints;
		this.blockPoints = blockPoints;
		this.healPoints = 100;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getAttackPoints() {
		return attackPoints;
	}
	
	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}
	
	public int getBlockPoints() {
		return blockPoints;
	}
	
	public void setBlockPoints(int blockPoints) {
		this.blockPoints = blockPoints;
	}
	
	public int getHealPoints() {
		return healPoints;
	}
	
	public void setHealPoints(int healPoints) {
		this.healPoints = healPoints;
	}
	
	public abstract void heal();
	
	public abstract void getDamage(int damage);	
	
	
}

