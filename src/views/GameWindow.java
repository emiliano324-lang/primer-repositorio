package views;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.GameManuController;
import controllers.LoginController;

public class GameWindow extends JFrame{

	
	private CardLayout layout;
    private JPanel container;

	public GameWindow() {
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		  setUndecorated(true); // Quita bordes y barra
	        setDefaultCloseOperation(EXIT_ON_CLOSE);

	        GraphicsDevice gd = GraphicsEnvironment
	                .getLocalGraphicsEnvironment()
	                .getDefaultScreenDevice();

	        gd.setFullScreenWindow(this);
		
		Image icon = tk.getImage("src/img/logo_uabcs.png");
		setIconImage(icon);
		
		ImageIcon iconoCursor = new ImageIcon("src/img/cursor.png");
		Cursor miCursor = tk.createCustomCursor(iconoCursor.getImage(), new Point(0, 0), "Mi Cursor");
		setCursor(miCursor);
		
		layout = new CardLayout();

		container = new JPanel(layout);

        add(container);
        GameMenuView menuView = new GameMenuView();
        GameGameLoopView gameLoopView = new  GameGameLoopView();
		
        container.add(menuView, "MENU");
        container.add(gameLoopView, "GAME");
        
        
        new GameManuController(menuView, this); 
        showPanel("MENU");
        
        
        
		setVisible(true);
	}
	public void showPanel(String name) {
        layout.show(container, name);
    }
	
}
