package models;

import java.util.Random;

public class GameCombatSystem {
	
	private Player player;
	private Enemy enemy;
	
	private Random random;
	private int enemyAction;
	
	public GameCombatSystem(Player player, Enemy enemy) {
		this.player = player;
		this.enemy = enemy;
		
		random = new Random();
	}

	// Acciones del jugador
	public void playerAttack() {
		enemy.receiveDamage(player.getAttackPoints());
	}
	
	public void playerHeal() {
		player.heal(player.getHealPoints());
	}
	
	public void playerBlock() {
		player.setBlocking(true);
	}
	
	// Acciones del enemigo
	public void enemyTurn() {
		
		if(enemy.isDead()) {
			return;
		}
		
		switch(random.nextInt(2)) {
		case 0:
			player.receiveDamage(enemy.getAttackPoints());
			break;
		case 1:
			enemy.heal(enemy.getHealPoints());
			break;
		case 2:
			enemy.setBlocking(true);
		}
	
		player.setBlocking(false);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Enemy getEnemy() {
		return enemy;
	}
	
	public int getEnemyAction() {
		return enemyAction;
	}
}
