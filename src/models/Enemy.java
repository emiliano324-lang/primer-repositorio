package models;

import java.util.Random;

public class Enemy extends Character{

	private Random randomAction = new Random();
	
	
	
	public Enemy(String name, int maxHealth, int health, int attackPoints, int blockPoints, int healPoints, Random randomAction) {
		super(name, maxHealth, health, attackPoints, blockPoints, healPoints);
		this.randomAction = randomAction;
		
	}
	
	@Override
	public void heal() {
		
	}
	public void getDamage(int damage) {
		
	}
	
	public void chooseAction() {
		
		switch(randomAction.nextInt(3)) {
		case 0:
			// Chooses Attack
		case 1:
			// Choose Heal
		case 2:
			// Chooses Block
		}
	}
}
