package controllers;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import views.GameCombatView;
import views.GameMenuView;
import views.GameWindow;

public class GameCombatController {

	GameWindow window;
	GameMenuView gameMenuView;
	GameCombatView gameCombatView;
	
	
	public GameCombatController(GameCombatView combatView) {
		this.gameCombatView = combatView;
		
		registerListeners();
		
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
			    	
			    if(b == gameCombatView.getAttack()) {
			    	actionFramesSelf = gameCombatView.getAttackFramesSelf();
			    	actionFramesFoe = gameCombatView.getDamageFramesFoe();
			    }else if(b == gameCombatView.getBlock()) {
			    	actionFramesSelf = gameCombatView.getBlockFramesSelf();
			    	actionFramesFoe = gameCombatView.getHealFramesFoe();
			    }else if(b == gameCombatView.getHeal()) {
			   		actionFramesSelf = gameCombatView.getHealFramesSelf();
			   		actionFramesFoe = gameCombatView.getBlockFramesFoe();
			    }else {
		    		actionFramesSelf = gameCombatView.getDamageFramesSelf();
		    		actionFramesFoe = gameCombatView.getAttackFramesFoe();
			    }
			    
			    gameCombatView.animateOnce(actionFramesSelf, actionFramesFoe);
			}
			public void mouseReleased(MouseEvent e) {
				b.setForeground(defaultForeground);
				
			}
		});
	}
	
	
	
}
