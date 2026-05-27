package models;

import java.util.Random;

public class Enemy extends Character{

	private int randomAction;
	
	public Enemy(String name, int maxHealth, int health, int attackPoints, int blockPoints, int healPoints, int randomAction,boolean status,
			int healCharges,int blockCharges,boolean turn,boolean blocking) {
		super(name, maxHealth, health, attackPoints, blockPoints, healPoints,status,healCharges,blockCharges,turn,blocking);
		this.randomAction = randomAction;
		
	}
	
	public int getRandomAction() {
		return randomAction;
	}
	public void setRandomAction(int randomAction) {
		this.randomAction = randomAction;
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

	public void getDamage(int damage) {
		//dano bloqueado
		if(isBlocking()) {
			damage -= getBlockPoints();
			setHealth(getHealth()-damage);
			setBlocking(false);
			if(getHealth() <= 0) {
				setHealth(0) ;
				setStatus(true);
				return;
			}
		}
		//dano normal
		setHealth(getHealth()-damage);
		if(getHealth() <= 0) {
			setHealth(0) ;
			setStatus(true);
			return;
		}
	}
	public void block(){
		if(getBlockCharges() > 0) setBlocking(true);
	}
	
	
	
	/*
	public void chooseAction(int damage,int heal, int block) {
		
		switch(randomAction) {
		case 0:
			// Chooses Attack
			getDamage(damage);
		case 1:
			// Choose Heal
			heal();
		case 2:
			
			block();
		}
	}
	*/
	private boolean isDead() {
		return getStatus();
	}
}
