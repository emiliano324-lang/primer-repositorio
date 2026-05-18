package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import views.GameMenuView;
import views.GameWindow;
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

        view.getBtnPlay().addActionListener(e -> window.showPanel("GAME"));
        view.getBtnCredits().addActionListener(e -> window.showPanel("CREDITS"));
        view.getBtnSkillTree().addActionListener(e -> window.showPanel("SKILLTREE"));
        view.getBtnExit().addActionListener(e -> {
        		new HomeController(new MainWindow());
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
