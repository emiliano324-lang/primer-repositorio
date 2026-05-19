package controllers;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import views.GameGameLoopView;
import views.GameMenuView;
import views.GameWindow;

public class GamePanelController {

	GameWindow window;
	GameMenuView gameMenuView;
	GameGameLoopView gameGameLoopView;
	
	public GamePanelController(GameGameLoopView gameGameLoopView) {
		this.gameGameLoopView = gameGameLoopView;
		
		registerListeners();
	}
	
	public void registerListeners() {
		mouseListeners(gameGameLoopView.getAttack());
		mouseListeners(gameGameLoopView.getBlock());
		mouseListeners(gameGameLoopView.getHeal());
		mouseListeners(gameGameLoopView.getAnalyze());
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
				
				gameGameLoopView.selfFrame = 0;
				gameGameLoopView.foeFrame = 0;

			    gameGameLoopView.animation.stop();

			    gameGameLoopView. animateOnce(gameGameLoopView.attackFramesSelf, gameGameLoopView.damageFramesFoe);
			}
			public void mouseReleased(MouseEvent e) {
				b.setForeground(defaultForeground);
				
			}
		});
	}
	
	
	
}
