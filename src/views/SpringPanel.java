package views;

import javax.swing.*;
import java.awt.*;

public class SpringPanel extends JPanel {
	public SpringPanel() {
		setBackground(new Color(0, 31, 84));
		
		JPanel panelCentro = new JPanel();
		SpringLayout spring = new SpringLayout();
		
		panelCentro.setLayout(spring);
		
		String etiquetas[] = {"Usuario", "Contraseña"};
		int cantidadEtiquetas = etiquetas.length;
		
		JPanel panel = new JPanel(new SpringLayout());
		
		for (int i = 0; i < cantidadEtiquetas; i++) {
			JLabel etiqueta = new JLabel(etiquetas[i]);
			etiqueta.setForeground(new Color(254, 252, 251));
			etiqueta.setFont(new Font("Verdana", Font.BOLD, 50));
			panel.add(etiqueta);
			
		}
		
		JTextField campoUsuario = new JTextField(15);
		campoUsuario.setFont(new Font("Verdana", Font.PLAIN, 15));
		panel.add(campoUsuario);
		
		JPasswordField campoContrasena = new JPasswordField(15);
		campoContrasena.setFont(new Font("Verdana", Font.PLAIN, 15));
		panel.add(campoContrasena);
		
		// Etiqueta de Bienvenida
		JLabel saludo = new JLabel("BIENVENIDO");
		saludo.setForeground(new Color(254, 252, 251));
		saludo.setFont(new Font("Verdana", Font.BOLD, 40));
		add(saludo);
		
		// Errores
		JLabel errorCampoUsuario = new JLabel("!");
		errorCampoUsuario.setFont(new Font("Verdana", Font.PLAIN, 30));
		errorCampoUsuario.setForeground(Color.red);
		add(errorCampoUsuario);
		
		JLabel errorCampoContrasena = new JLabel("!");
		errorCampoContrasena.setFont(new Font("Verdana", Font.PLAIN, 30));
		errorCampoContrasena.setForeground(Color.red);
		add(errorCampoContrasena);
		
		// Botón de Iniciar Sesión
		JButton boton = new JButton("Iniciar Sesión");
		boton.setFont(new Font("Arial", Font.PLAIN, 15));
		add(boton);
		
		//SpringUtilities.makeCompactGrid(panel, 2, 2, 10, 10, 10, 10);
		add(panel);
	}
}
