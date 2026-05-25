package models;

public class Player extends Character{

	private boolean[] upgrades = {false,false,false,false,false,false};
	private int level;
	private int tokens;
	
	public Player(String name, int maxHealth, int health, int attackPoints, int blockPoints, int healPoints, boolean blocking, int level,
			boolean[] upgrades, int tokens) {
		super(name, maxHealth, health, attackPoints, blockPoints, healPoints, blocking);
		this.upgrades = upgrades;
		this.level = level;
		this.tokens = tokens;
	}
	
}
