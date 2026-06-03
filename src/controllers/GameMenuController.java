package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import utils.ScreenManager;
import views.GameCombatView;
import views.GameMenuView;
import views.GameWindow;
import views.LoginWindow;
import views.MainWindow;

public class GameMenuController {

    private GameMenuView view;
    private GameWindow window;
    
    public GameMenuController(GameMenuView view, GameWindow window) {

        this.view = view;
        this.window = window;

        gameMenuListener();
    }

    private void gameMenuListener() {

        view.getBtnPlay().addActionListener(e -> {
        
        	window.getCombatController().refreshPlayer();
        	
        	ScreenManager.showPanel("GAME");
        
        });
        view.getBtnCredits().addActionListener(e ->  ScreenManager.showPanel("CREDITS"));
        view.getBtnSkillTree().addActionListener(e ->  ScreenManager.showPanel("SKILLTREE"));
        
        view.getBtnExit().addActionListener(e -> {
        		new LoginWindow();
        		window.dispose();
        });
        
        mouseListeners(view.getBtnPlay());
        mouseListeners(view.getBtnSkillTree());
        mouseListeners(view.getBtnCredits());
        mouseListeners(view.getBtnExit());
    }
    
    private void mouseListeners(JButton b) {
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
