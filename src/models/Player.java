package models;

public class Player extends Character{

	
	private boolean[] upgrades = {false,false,false,false,false,false};
	private int level;
	private int tokens;
	private int id;
	
	
	public Player(String name, int maxHealth, int health, int attackPoints, int blockPoints, int healPoints, int level,
			int tokens,boolean status,int healCharges,int blockCharges, boolean[] upgrades) {
		super(name, maxHealth, health, attackPoints, blockPoints, healPoints,status,healCharges,blockCharges);
		this.upgrades = upgrades;
		this.setLevel(0);
		this.setTokens(0);
	}
	
	
	public int getTokens() {
		return tokens;
	}
	
	public void setTokens(int tokens) {
		this.tokens = tokens;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public void heal() {
		if(!isDead()) {
			if(getHealth()+getHealPoints() > getMaxHealth()) {
				setHealth(getMaxHealth());
			}else{
				setHealth(getHealPoints()+getHealth());
			}	
		}
	}
	
	@Override
	public void getDamage(int damage) {
		setHealth(getHealth()-damage);
		if(getHealth() == 0) setHealth(0) ;
	}
	
	public void upgrade(String nomUpgrade) {
		
		if(nomUpgrade.equalsIgnoreCase("Heal1") && upgrades[0] == false) {
			setHealPoints((int)(getHealPoints() + (getHealPoints() * 0.40)));
			upgrades[0] = true;
		}
		
		if(nomUpgrade.equalsIgnoreCase("Heal2") && upgrades[1] == false) {
			setHealPoints((int)(getHealPoints() + (getHealPoints())));
			upgrades[1] = true;
		}
		
		if(nomUpgrade.equalsIgnoreCase("Damage1") && upgrades[2] == false) {
			setAttackPoints((int)(getAttackPoints() + (getAttackPoints() * 0.40)));
			upgrades[2] = true;
		}
		
		if(nomUpgrade.equalsIgnoreCase("Damage2") && upgrades[3] == false) {
			setAttackPoints((int)(getAttackPoints() + (getAttackPoints())));
			upgrades[3] = true;
		}
		
		if(nomUpgrade.equalsIgnoreCase("Block1") && upgrades[2] == true) {
			setBlockPoints((int)(getBlockPoints() + (getBlockPoints() * 0.30)));
			upgrades[4] = true;
		}
		
		if(nomUpgrade.equalsIgnoreCase("Block1") && upgrades[2] == true) {
			setBlockPoints((int)(getBlockPoints() + (getBlockPoints() * 0.60)));
			upgrades[5] = true;
		}
		
	}
	
	public boolean isDead() {
		return getHealth() <= 0;
	}


	
	

}
