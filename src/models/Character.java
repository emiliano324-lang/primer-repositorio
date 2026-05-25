package models;

public abstract class Character {

	private String name;
	
	private int maxHealth;
	private int health;
	private int attackPoints;
	private int blockPoints;
	private int healPoints;
	
	private boolean blocking;
	
	public Character(String name, int maxHealth, int health, int attackPoints, int blockPoints, int healPoints, boolean blocking) {
		this.name = name;
		this.maxHealth = maxHealth;
		this.health = health;
		this.attackPoints = attackPoints;
		this.blockPoints = blockPoints;
		this.healPoints = 100;
		this.blocking = blocking;
	}
	
	public void receiveDamage(int amount) {

		if(blocking) {

			amount -= blockPoints;

			if(amount < 0) {
				amount = 0;
			}
		}

		health -= amount;

		if(health < 0) {
			health = 0;
		}
	}

	public void heal(int amount) {

		health += amount;

		if(health > maxHealth) {
			health = maxHealth;
		}
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
		
		if(health > maxHealth) {
			this.health = maxHealth;
		}
		
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
	
	public boolean isBlocking() {
		return blocking;
	}
	
	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}
	
	public boolean isDead() {
		return health <= 0;
	}
}

