package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class FormularioRegistro extends JFrame{

	public FormularioRegistro() {
		
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLocation(100,100);
		//setBounds(100,100,500,500);
		setResizable(false);
		setTitle("Registro");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/logo_uabcs.png");
		setIconImage(icono);
		
		inicializarComponentes();
		
		setVisible(true);
	}
	
	public void inicializarComponentes() {
		
		JLabel titulo = new JLabel("FORMULARIO DE REGISTRO");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		add(titulo, BorderLayout.NORTH);
		
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	
		panelComponentes.setBackground(new Color(0, 31, 84));
		
		String[] campos = {"Nombre","Apellido Paterno","Apellido Materno","Correo Electrónico","Teléfono","Agrega una descripción general tuya"};
		int numeroCampos = campos.length;
		
		for(int i = 0; i < numeroCampos; i++) {
			
			JLabel etiqueta = new JLabel(campos[i]);
			etiqueta.setForeground(Color.WHITE);
			panelComponentes.add(etiqueta);
			
			JTextField texto = new JTextField(10);
			panelComponentes.add(texto);
			
		}
		JScrollPane scroll = new JScrollPane(panelComponentes);
		scroll.setHorizontalScrollBar(null);
		
		add(panelComponentes);
	}
	
	/*public void cargarFuente() {
		
		Font fuente = null;
		
		try {
			
		}
	}*/
}
