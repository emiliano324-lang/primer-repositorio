package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import utils.ScreenManager;
import views.GameSkillTreeView;

public class GameSkillTreeController {

	GameSkillTreeView view;
	
	public GameSkillTreeController(GameSkillTreeView view) {
		this.view = view;
		
		registerListeners();
	}
	
	public void registerListeners() {
		view.getBack().addActionListener(e -> ScreenManager.showPanel("MENU"));
		
		mouseListeners(view.getBack());
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
