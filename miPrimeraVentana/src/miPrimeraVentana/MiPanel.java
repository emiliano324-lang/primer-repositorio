package miPrimeraVentana;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MiPanel extends JPanel{
	
	public MiPanel() {
		//Esta opción utiliza un color predefinido
		//setBackground(Color.BLUE);
		
		JButton boton = new JButton("Mi boton");
		add(boton);
		
		//Esta opción genera un color a partir de RGB
		setBackground(new Color(210,165,35));
		
	}
	
}
