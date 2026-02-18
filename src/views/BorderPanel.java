package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class BorderPanel extends JPanel {
	
	public BorderPanel() {
		setLayout(new BorderLayout());
		//setBackground(Color.BLUE);
		Border emptyBorder = BorderFactory.createEmptyBorder(20,10,20,10);
		setBorder(emptyBorder);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.blue);
		add(panelSuperior, BorderLayout.NORTH);
		
		JLabel bienvenida = new JLabel("Bienvenido al juego");
		bienvenida.setForeground(Color.white);
		bienvenida.setFont(new Font("Verdana", Font.BOLD, 15));
		panelSuperior.add(bienvenida);
		
		//JButton b = new JButton("Hola");
		//panelSuperior.add(b);
		
		//JButton b2 = new JButton("Botón 2");
		//panelSuperior.add(b2);
		
		crearPanelCentro();
		
	}
	
	public void crearPanelCentro() {
		Border emptyBorder = BorderFactory.createEmptyBorder(20,10,20,10);
		setBorder(emptyBorder);

		JPanel panelCentro = new JPanel(new BorderLayout());
		panelCentro.setBackground(new Color(0, 31, 84));
		
		JLabel etiquetaUsuario = new JLabel("Ingrese el nombre de usuario");
		etiquetaUsuario.setForeground(new Color(254, 252, 251));
		etiquetaUsuario.setFont(new Font("Verdana", Font.BOLD, 15));
		panelCentro.add(etiquetaUsuario, BorderLayout.NORTH);
		
		JTextField usuario = new JTextField();
		usuario.setFont(new Font("Verdana", Font.PLAIN, 15));
		panelCentro.add(usuario, BorderLayout.CENTER);

		JPanel panelCentroSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelCentro.add(panelCentroSur, BorderLayout.SOUTH);
		panelCentroSur.setBackground(Color.blue);
		
		JButton btnInicio = new JButton("Iniciar sesión");
		panelCentroSur.add(btnInicio);
		
		JButton btnCancelar = new JButton("Cancelar");
		panelCentroSur.add(btnCancelar);
		
		add(panelCentro, BorderLayout.CENTER);
		
		
	
	}
}