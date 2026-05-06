package views;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controllers.GameController;
import controllers.LoginController;

public class GameWindow extends JFrame{

	GameView view;
	
	public GameWindow() {
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		setSize(tk.getScreenSize().width, tk.getScreenSize().height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tekhnaryan");
		setLocationRelativeTo(null);
		
		Image icon = tk.getImage("src/img/logo_uabcs.png");
		setIconImage(icon);
		
		ImageIcon iconoCursor = new ImageIcon("src/img/cursor.png");
		Cursor miCursor = tk.createCustomCursor(iconoCursor.getImage(), new Point(0, 0), "Mi Cursor");
		setCursor(miCursor);
		
		view = new GameView();
		add(view);
		
		new GameController(view);
		
		setVisible(true);
	}
}
