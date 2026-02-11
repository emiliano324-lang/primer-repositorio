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
		setLayout(null);
		
		//setBackground(new Color(10, 17, 40));
		setBackground(new Color(0, 31, 84));
		
		JLabel saludo = new JLabel("BIENVENIDO");
		saludo.setForeground(new Color(254, 252, 251));
		saludo.setBounds(55, 60, 500, 50);
		saludo.setFont(new Font("Verdana", Font.BOLD, 50));
		add(saludo);
		
		JLabel etiquetaUsuario = new JLabel("Nombre de Usuario");
		etiquetaUsuario.setForeground(new Color(254, 252, 251));
		etiquetaUsuario.setBounds(140, 280, 200, 20);
		etiquetaUsuario.setFont(new Font("Verdana", Font.BOLD, 15));
		add(etiquetaUsuario);
		
		JLabel etiquetaContrasena = new JLabel("Contraseña");
		etiquetaContrasena.setForeground(new Color(254, 252, 251));
		etiquetaContrasena.setBounds(140, 330, 200, 20);
		etiquetaContrasena.setFont(new Font("Verdana", Font.BOLD, 15));
		add(etiquetaContrasena);
		
		JTextField usuario = new JTextField();
		usuario.setBounds(140, 300, 200, 30);
		usuario.setFont(new Font("Verdana", Font.PLAIN, 15));
		add(usuario);
		
		JPasswordField contrasena = new JPasswordField();
		contrasena.setBounds(140, 350, 200, 30);
		contrasena.setFont(new Font("Verdana", Font.PLAIN, 15));
		add(contrasena);
		
		JButton boton = new JButton("Iniciar Sesión");
		boton.setBounds(330, 420, 150, 30);
		boton.setFont(new Font("Arial", Font.PLAIN, 15));
		add(boton);
	}
}
