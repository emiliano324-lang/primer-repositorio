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
	GameMenuView menuView;
	GameCombatView combatView;	

	Player player = new Player();
	Enemy enemy = new Enemy("Segador del Vacío", 200, 200, 5, 5, 15,0,false,0,0,false,false);

	//int random;
	
	public GameCombatController(GameCombatView combatView) {
		this.combatView = combatView;
		
		try {
			player = Session.loadCharacter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		player.setTurn(true);
		
		registerListeners();
		
		combatView.initializePlayer(player);
	}
	
	public void registerListeners() {
		mouseListeners(combatView.getAttack());
		mouseListeners(combatView.getBlock());
		mouseListeners(combatView.getHeal());
		mouseListeners(combatView.getAnalyze());
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
				
				combatView.setSelfFrame(0);
				combatView.setFoeFrame(0);

			    combatView.getAnimation().stop();
			    
			    if(player.getTurn()) {
				    if(b == combatView.getAttack()) {

				    	actionFramesSelf = combatView.getAttackFramesSelf();
					    actionFramesFoe = combatView.getDamageFramesFoe();
					    
					    enemy.getDamage(enemy.getHealth() - player.getAttackPoints());
					    
					    combatView.topPanelMessage("El enemigo ha recibido " + player.getAttackPoints() + " pts de daño.");
					    
					   // player.setTurn(false);
				    }else if(b == combatView.getBlock()) {

				    	actionFramesSelf = combatView.getBlockFramesSelf();
				    	actionFramesFoe = combatView.getHealFramesFoe();

				    	player.block();
				    	
				    	combatView.topPanelMessage(player.getName() + " se cubrió. Le quedan " + player.getBlockCharges() + " bloqueos.");
					    
				    }else if(b == combatView.getHeal()) {

				    	actionFramesSelf = combatView.getHealFramesSelf();
				   		actionFramesFoe = combatView.getBlockFramesFoe();
				   		player.heal();
				   		
				   		combatView.topPanelMessage(player.getName() + " ha recibido "+ player.getHealPoints() + " pts de salud. Le quedan " + player.getHealCharges() + " curaciones.");
					    
				    }else if(b == combatView.getAnalyze()) {
				    	
				    	combatView.topPanelMessage("Vida actual del enemigo: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
				   
				    	actionFramesSelf = combatView.getIdleFramesSelf();
				    	actionFramesFoe = combatView.getIdleFramesFoe();
				    	
				    	player.block();
				    	
				    }else if(b == combatView.getHeal()) {
				   		
				    	actionFramesSelf = combatView.getHealFramesSelf();
				   		actionFramesFoe = combatView.getIdleFramesFoe();
				   		
				   		player.heal();

				    }else {
			    		actionFramesSelf = combatView.getDamageFramesSelf();
			    		actionFramesFoe = combatView.getAttackFramesFoe();
				    }
				    
				    player.setTurn(b == combatView.getAnalyze() ? true : false);
				    

				}else {
					
					if(enemy.getRandomAction() == 1) {
					
						player.setHealth(player.getHealth() - enemy.getAttackPoints());
						
						combatView.updateHealthBar(player.getHealth());
						
						actionFramesFoe = combatView.getAttackFramesFoe();
						actionFramesSelf = combatView.getDamageFramesSelf();
						
					}else if(enemy.getRandomAction() == 2) {
						
						enemy.heal();
						actionFramesFoe = combatView.getHealFramesFoe();
						actionFramesSelf = combatView.getIdleFramesSelf();
						
					}else{
						
						enemy.block();
						actionFramesFoe = combatView.getBlockFramesFoe();
						actionFramesSelf = combatView.getIdleFramesSelf();
					}
					
					player.setTurn(true);
				}
			    combatView.animateOnce(actionFramesSelf, actionFramesFoe);
			
			}
			public void mouseReleased(MouseEvent e) {
				b.setForeground(defaultForeground);
				
			}
		});
	}
	
	
	
}
