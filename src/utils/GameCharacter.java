package utils;

public class GameCharacter {

	String name;
	int level;
	int  curretnHealth;
	int maxHealth;
	int attackPoint;
	int experience;
	boolean effects[];
	 
	
	
	public GameCharacter() {
		
	}

	public GameCharacter(String name, int level, int curretnHealth, int maxHealth, int attackPoint, int experience) {
		super();
		this.name = name;
		this.level = level;
		this.curretnHealth = curretnHealth;
		this.maxHealth = maxHealth;
		this.attackPoint = attackPoint;
		this.experience = experience;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCurretnHealth() {
		return curretnHealth;
	}

	public void setCurretnHealth(int curretnHealth) {
		this.curretnHealth = curretnHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getAttackPoint() {
		return attackPoint;
	}

	public void setAttackPoint(int attackPoint) {
		this.attackPoint = attackPoint;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	
	
	
	
	
	
	


}
