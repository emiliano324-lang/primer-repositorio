package views;

import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Ventana extends JFrame{
	
	public Ventana() {
		setSize(500,500); // Tamaño
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Termina la ejecución al cerrar ventana.
		setLocation(100,100); // Lugar donde aparecerá la ventana
		
		//Establece la ubicación y el tamaño de la ventana 
		//setBounds(100,100,500,500);
		
		setResizable(false); // Redimensionable
		
		setTitle("Proyecto Programacion III"); // Titulo 
		//Coloca la ventana al centro de la pantalla
		
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		//Image icono = tk.getImage("src\img\logo_uabcs.png");
		LoginView miPanel = new LoginView();
		add(miPanel);
		
		setVisible(true); //Establece visibilidad
	}
}