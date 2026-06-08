package models;

import java.util.Arrays;

public class Player extends Character{

	private boolean[] upgrades = {false,false,false,false,false,false};
	private int level;
	private int tokens;
	private int id;
	
	public Player() {
	
	}
	
	public Player(String name, int maxHealth, int health, int attackPoints, int blockPoints, int healPoints, int level,
			int tokens,boolean dead,int healCharges,int blockCharges, boolean[] upgrades,boolean turn,boolean blocking) {
		super(name, maxHealth, health, attackPoints, blockPoints, healPoints,dead,healCharges,blockCharges,turn,blocking);
		
		this.upgrades = upgrades;
		
		this.level = level;
		this.tokens = tokens;
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
	
	 public boolean[] getUpgrades() {
	        return upgrades;
	    }

	    public void setUpgrades(
	            boolean[] upgrades
	    ) {

	        this.upgrades = upgrades;
	    }
	
	@Override
	public void heal() {
		if(!isDead()) {
			if(getHealth()+getHealPoints() > getMaxHealth()) {
				setHealth(getMaxHealth());
			}else{
				setHealth(getHealPoints()+getHealth());
			}	
			setHealCharges(getHealCharges()-1); 
		}
	}
	
	@Override
	public void getDamage(int damage) {
		//Daño bloqueado
		if(isBlocking()) {
			
			damage -= getBlockPoints();
			
			setHealth(getHealth()-damage);
			setBlocking(false);
			
			if(getHealth() <= 0) {
				setHealth(0) ;
				setDead(true);
				return;
			}
			return;
		}
		
		//Daño normal
		setHealth(getHealth()-damage);
		if(getHealth() <= 0) {
			
			setHealth(0) ;
			setDead(true);
			return;
		}
	}
	
	public void block(){
		if(getBlockCharges() > 0) setBlocking(true);
	}
	
	public void upgrade(String nomUpgrade) {

	    switch (nomUpgrade.toUpperCase()) {

	        case "CURACIÓN I":
	            setHealPoints((int)(getHealPoints() * 1.4));
	            setMaxHealth((int)(getMaxHealth() * 1.4));
	            setHealth(getMaxHealth());
	            setHealCharges(getHealCharges() + 1);
	            upgrades[0] = true;
	            break;

	        case "CURACIÓN II":
	            setHealPoints(getHealPoints() * 2);
	            setMaxHealth(getMaxHealth() * 2);
	            setHealth(getMaxHealth());
	            setHealCharges(getHealCharges() + 2);
	            upgrades[1] = true;
	            break;

	        case "DAÑO I":
	            setAttackPoints((int)(getAttackPoints() * 1.4));
	            upgrades[2] = true;
	            break;

	        case "DAÑO II":
	            setAttackPoints(getAttackPoints() * 2);
	            upgrades[3] = true;
	            break;

	        case "BLOQUEO I":
	            setBlockPoints((int)(getBlockPoints() * 1.3));
	            upgrades[4] = true;
	            break;

	        case "BLOQUEO II":
	            setBlockPoints((int)(getBlockPoints() * 1.6));
	            upgrades[5] = true;
	            break;
	    }
	}
	
	/*public void upgrade(String nomUpgrade) {
		// TODO: Falta aumentar las cargas de heal y Block y aumento de vida maxima.
		if(nomUpgrade.equalsIgnoreCase("CURACIÓN I") && upgrades[0] == false) {
			setHealPoints((int)(getHealPoints() + (getHealPoints() * 0.40)));
			setMaxHealth((int)(getMaxHealth() + (getMaxHealth()* 0.40)));
			setHealth(getMaxHealth());
			setHealCharges(getHealCharges() + 1);
			
			upgrades[0] = true;
		}
		
		if(nomUpgrade.equalsIgnoreCase("CURACIÓN II") && upgrades[1] == false) {
			setHealPoints((int)(getHealPoints() + (getHealPoints())));
			setMaxHealth((int)(getMaxHealth()) + (getMaxHealth()));
			setHealth(getMaxHealth());
			setHealCharges(getHealCharges() + 2);
			upgrades[1] = true;
		}
		
		if(nomUpgrade.equalsIgnoreCase("DAÑO I") && upgrades[2] == false) {
			setAttackPoints((int)(getAttackPoints() + (getAttackPoints() * 0.40)));
			upgrades[2] = true;
		}
		
		if(nomUpgrade.equalsIgnoreCase("DAÑO II") && upgrades[3] == false) {
			setAttackPoints((int)(getAttackPoints() + (getAttackPoints())));
			upgrades[3] = true;
		}
		
		if(nomUpgrade.equalsIgnoreCase("BLOQUEO I") && upgrades[4] == false) {
			setBlockPoints((int)(getBlockPoints() + (getBlockPoints() * 0.30)));
			upgrades[4] = true;
		}
		
		if(nomUpgrade.equalsIgnoreCase("BLOQUEO II") && upgrades[5] == false) {
			setBlockPoints((int)(getBlockPoints() + (getBlockPoints() * 0.60)));
			upgrades[5] = true;
		}
		
	}*/

	@Override
	public String toString() {
		return "Player:  level=" + level + ", tokens=" + tokens + ", id="
				+ id + ", getName()=" + getName() + ", getMaxHealth()=" + getMaxHealth() + ", getHealth()="
				+ getHealth() + ", getAttackPoints()=" + getAttackPoints() + ", getBlockPoints()=" + getBlockPoints()
				+ ", getHealPoints()=" + getHealPoints() + ", isDead()=" + isDead() + ", getBlockCharges()="
				+ getBlockCharges() + ", getTurn()=" + getTurn() + ", getHealCharges()=" + getHealCharges()
				+ ", isBlocking()=" + isBlocking() ;
	}
	
}
