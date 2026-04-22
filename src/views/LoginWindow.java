package views;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controllers.LoginController;

public class LoginWindow extends JFrame { 
	
	private LoginView view;
	
	public LoginView getView() {
		return view;
	}
	public LoginWindow() {
		
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Mi Aplicación");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/logo_uabcs.png");
		setIconImage(icono);
		
		ImageIcon iconoCursor = new ImageIcon("src/img/cursor.png");
		Cursor miCursor = tk.createCustomCursor(iconoCursor.getImage(), 
		new Point(0,0), "Mi Cursor");
		setCursor(miCursor);
		
		view = new LoginView();
		add(view);
 
		new LoginController(view);
		
		setVisible(true);
	}
	
}