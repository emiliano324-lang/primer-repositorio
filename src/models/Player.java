package models;

public class Player extends Character{

	
	private boolean[] upgrades = {false,false,false,false,false,false};
	private int level;
	private int tokens;
	
	public Player(String name, int maxHealth, int health, int attackPoints, int blockPoints, int healPoints, int level,
			boolean[] hablilities,int tokens,boolean status,int healCharges,int blockCharges) {
		super(name, maxHealth, health, attackPoints, blockPoints, healPoints,status,healCharges,blockCharges);
		this.upgrades = upgrades;
		this.level = 0;
		this.tokens = 0;
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
