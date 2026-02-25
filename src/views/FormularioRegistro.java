package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
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
		
		Color colorFondo = new Color(0, 31, 84);
		
		JLabel titulo = new JLabel("FORMULARIO DE REGISTRO");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		add(titulo, BorderLayout.NORTH);
		
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	
		panelComponentes.setBackground(colorFondo);
		
		String[] campos = {"Nombre de Usuario","Correo Electrónico","Contraseña","Confirmar Contraseña"};
		int numeroCampos = campos.length;
		
		for(int i = 0; i < numeroCampos; i++) {
			
			if(campos[i].equals("Contraseña") || campos[i].equals("Confirmar Contraseña")) {
				JLabel contrasena = new JLabel(campos[i]);
				contrasena.setForeground(Color.WHITE);
				panelComponentes.add(contrasena);
				
				JPasswordField campoContrasena = new JPasswordField(10);
				panelComponentes.add(campoContrasena);
				continue;
			}
			
			JLabel etiqueta = new JLabel(campos[i]);
			etiqueta.setForeground(Color.WHITE);
			panelComponentes.add(etiqueta);
			
			JTextField texto = new JTextField(10);
			panelComponentes.add(texto);
		}
		
		JLabel sexoUsuario = new JLabel("Sexo");
		sexoUsuario.setForeground(Color.WHITE);
		
		panelComponentes.add(sexoUsuario);
		
		JRadioButton hombre = new JRadioButton("Hombre");
		JRadioButton mujer = new JRadioButton("Mujer");
		JRadioButton noDecir = new JRadioButton("Prefiero no decirlo");
		
		hombre.setBackground(colorFondo);
		hombre.setForeground(Color.WHITE);
		
		mujer.setBackground(colorFondo);
		mujer.setForeground(Color.WHITE);
		
		noDecir.setBackground(colorFondo);
		noDecir.setForeground(Color.WHITE);

		ButtonGroup sexos = new ButtonGroup();
		sexos.add(hombre);
		sexos.add(mujer);
		sexos.add(noDecir);
		
		panelComponentes.add(hombre);
		panelComponentes.add(mujer);
		panelComponentes.add(noDecir);
		
		JRadioButton terminosYCondiciones = new JRadioButton("Acepto terminos y condiciones");
		terminosYCondiciones.setBackground(colorFondo);
		terminosYCondiciones.setForeground(Color.WHITE);
		
		panelComponentes.add(terminosYCondiciones);
		
		JLabel carreraPersonaje = new JLabel("Elige tu carrera (tipo de personaje)");
		carreraPersonaje.setForeground(Color.WHITE);
		panelComponentes.add(carreraPersonaje);
		
		JRadioButton ids = new JRadioButton("IDS");
		JRadioButton itc = new JRadioButton("ITC");
		JRadioButton lati = new JRadioButton("LATI");
		JRadioButton ic = new JRadioButton("IC");
		
		ids.setBackground(colorFondo);
		ids.setForeground(Color.WHITE);
		
		itc.setBackground(colorFondo);
		itc.setForeground(Color.WHITE);
		
		lati.setBackground(colorFondo);
		lati.setForeground(Color.WHITE);
		
		ic.setBackground(colorFondo);
		ic.setForeground(Color.WHITE);
		
		ButtonGroup carreras = new ButtonGroup();
		carreras.add(ids);
		carreras.add(itc);
		carreras.add(lati);
		carreras.add(ic);
		
		panelComponentes.add(ids);
		panelComponentes.add(itc);
		panelComponentes.add(lati);
		panelComponentes.add(ic);
		
		JSlider dificultad = new JSlider(1,8,4);
		dificultad.setPaintTicks(true);
		dificultad.setPaintLabels(true);
		
		panelComponentes.add(dificultad);
		
		JScrollPane scroll = new JScrollPane(panelComponentes);
		
		add(scroll);
		
		add(panelComponentes);
	}
	
	/*public void cargarFuente() {
		
		Font fuente = null;
		
		try {
			
		}
	}*/
}
