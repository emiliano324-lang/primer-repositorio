package models;

public abstract class Character {
	
	private String name;
	
	private int maxHealth;
	private int health;
	private int attackPoints;
	private int blockPoints;
	private int healPoints;
	private boolean dead;
	private int healCharges;
	private int blockCharges;
	private boolean turn;
	private boolean blocking;
	

	public Character() {
		
	}
	
	public Character(String name, int maxHealth, int health, int attackPoints, int blockPoints, int healPoints,boolean dead,
			int healCharges,int blockCharges,boolean turn,boolean blocking) {
		this.name = name;
		this.maxHealth = maxHealth;
		this.health = health;
		this.attackPoints = attackPoints;
		this.blockPoints = blockPoints;
		this.healPoints = healPoints;
		this.dead = false;
		
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


	
	public boolean isDead() {
		return dead;
	}
	
	public void setDead(boolean status) {
		this.dead = status;
	}

	public int getBlockCharges() {
		return blockCharges;
	}

	public void setBlockCharges(int blockCharges) {
		this.blockCharges = blockCharges;
	}
	
	public boolean getTurn() {
		return turn;
	}
	
	public void setTurn(boolean turn) {
		this.turn = turn;
	}	

	public int getHealCharges() {
		return healCharges;
	}

	public void setHealCharges(int healCharges) {
		this.healCharges = healCharges;
	}

	public boolean isBlocking() {
		return blocking;
	}

	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}

	
	
}

