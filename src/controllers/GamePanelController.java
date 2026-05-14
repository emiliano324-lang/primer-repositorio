package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import views.GameGameLoopView;
import views.GameMenuView;

public class GamePanelController {

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
				
			}
			public void mouseReleased(MouseEvent e) {
				b.setForeground(defaultForeground);
				
			}
		});
	}
	
	
	
}
