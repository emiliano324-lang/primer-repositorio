package views;

import java.awt.Color;
import java.awt.Font;
import java.net.PasswordAuthentication;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JPanel{

	public LoginView() {
	
		JLabel saludo = new JLabel("BIENVENIDO");
		saludo.setForeground(Color.white);
		saludo.setBounds(50, 10, 200, 40);
		
		JTextField usuario = new JTextField();
		usuario.setFont(new Font("Arial", Font.PLAIN, 30));
		
		JPasswordField contrasena = new JPasswordField();
		contrasena.setBounds(10, 240, 200, 50);
		contrasena.setFont(new Font("Arial", Font.PLAIN, 30));
		add(contrasena);
		
		JButton boton = new JButton("Iniciar");
		add(boton);
	}
}
