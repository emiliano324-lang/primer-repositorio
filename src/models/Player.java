package models;

public class Player extends Character{

	
	private boolean[] upgrades = {false,false,false,false,false,false};
	private int level;
	private int tokens;
	
	public Player(String name, int maxHealth, int health, int attackPoints, int blockPoints, int healPoints, int level,
			boolean[] hablilities,int tokens) {
		super(name, maxHealth, health, attackPoints, blockPoints, healPoints);
		this.upgrades = upgrades;
		this.level = 1;
		this.tokens = 2;
	}
	
	@Override
	public void heal() {
		setHealth(getHealPoints()+getHealth());
		
	}
	
	
	@Override
	public void getDamage(int damage) {
		setHealth(getHealth()-damage);
		if(getHealth() == 0) setHealth(0);
	}

	
	
	

}
