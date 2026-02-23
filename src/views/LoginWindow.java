package views;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class LoginWindow extends JFrame { 
	
	public LoginWindow() {
		
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLocation(100,100);
		//setBounds(100,100,500,500);
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
		
		//LoginView panelito = new LoginView();
		//SpringPanel panelito = new SpringPanel();
		GridBagPanel panelito = new GridBagPanel();
		
		add(panelito);
		
		setVisible(true);
		//pack();
		
		//validate();
		//repaint();
	}
	
}