package controllers;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;

import models.Enemy;
import models.Player;
import utils.Session;
import views.GameCombatView;
import views.GameMenuView;
import views.GameWindow;

public class GameCombatController {

	GameWindow window;
	GameMenuView gameMenuView;
	GameCombatView gameCombatView;	
	Random random;
	
	Player player = new Player("",0,0,0,0,0,0,0,false,0,0,null,false,false);
	Enemy enemy = new Enemy("Segador del Vacío", 200, 200, 40, 20, 15,0,false,0,0,false,false);
	
	
	public GameCombatController(GameCombatView combatView) {
		this.gameCombatView = combatView;
		
		random = new Random();
		
		player.setTurn(true);
		
		registerListeners();
		try {
			player = Session.loadCharacter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void registerListeners() {
		mouseListeners(gameCombatView.getAttack());
		mouseListeners(gameCombatView.getBlock());
		mouseListeners(gameCombatView.getHeal());
		mouseListeners(gameCombatView.getAnalyze());
	}
	
	public void mouseListeners(JButton b) {
		Color defaultForeground = b.getForeground();
		String defaultText = b.getText();
		
		b.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				b.setText("-> " + defaultText + " <-");
			}

			public void mouseExited(MouseEvent e) {
				b.setText(defaultText);
			}
			
			public void mousePressed(MouseEvent e) {
				
				b.setForeground(Color.LIGHT_GRAY);
				
				String[] actionFramesSelf = new String[4];
				String[] actionFramesFoe = new String[4];
				
				gameCombatView.setSelfFrame(0);
				gameCombatView.setFoeFrame(0);

			    gameCombatView.getAnimation().stop();
			    
			    if(player.getTurn()) {
				    if(b == gameCombatView.getAttack()) {

				    	actionFramesSelf = gameCombatView.getAttackFramesSelf();
					    actionFramesFoe = gameCombatView.getDamageFramesFoe();
					    
					    enemy.setHealth(enemy.getHealth()-player.getAttackPoints());
					    
					    gameCombatView.topPanelMessage("El enemigo ha recibido " + player.getAttackPoints() + " pts de daño.");
					    
				    }else if(b == gameCombatView.getBlock()) {

				    	actionFramesSelf = gameCombatView.getBlockFramesSelf();
				    	actionFramesFoe = gameCombatView.getHealFramesFoe();

				    	player.block();
				    	
				    	gameCombatView.topPanelMessage(player.getName() + " se cubrió. Le quedan " + player.getBlockCharges() + " bloqueos.");
					    
				    }else if(b == gameCombatView.getHeal()) {

				    	actionFramesSelf = gameCombatView.getHealFramesSelf();
				   		actionFramesFoe = gameCombatView.getBlockFramesFoe();
				   		player.heal();
				   		
				   		gameCombatView.topPanelMessage(player.getName() + " ha recibido "+ player.getHealPoints() + " pts de salud. Le quedan " + player.getHealCharges() + " curaciones.");
					    
				    }else if(b == gameCombatView.getAnalyze()) {
				    	
				    	gameCombatView.topPanelMessage("Vida actual del enemigo: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
				   
				    }else {
			    		actionFramesSelf = gameCombatView.getDamageFramesSelf();
			    		actionFramesFoe = gameCombatView.getAttackFramesFoe();
				    }
				    
				    player.setTurn(false);
				    
				}else {
					//TODO: Accion del enemigo
					//TODO: Hacer las animaciones de ataque del enemigo y recibir daño del personaje
					
					enemy.setRandomAction(random.nextInt(3));
					
					switch(enemy.getRandomAction()) {
					
					case 0:
						player.getDamage(enemy.getAttackPoints());
						break;
					case 1:
						enemy.heal();
						break;
					case 2:
						enemy.block();
						break;
					default:
						break;
					}
					
					player.setTurn(true);
				}
			    //gameCombatView.animateOnce(actionFramesSelf, actionFramesFoe);
			}
			
			public void mouseReleased(MouseEvent e) {
				b.setForeground(defaultForeground);
				
			}
		});
	}
	
	
	
}
