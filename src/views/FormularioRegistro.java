package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.concurrent.Flow;

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
import javax.swing.border.EmptyBorder;

public class FormularioRegistro extends JFrame{

	public FormularioRegistro() {
		
		setSize(450, 450);
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
		Font fuenteNormal = new Font("Verdana", Font.BOLD, 12);
		Font fuenteSubtitulo = new Font("Verdana", Font.BOLD, 16);
		FlowLayout ajustarAlCentro = new FlowLayout(FlowLayout.CENTER);
		
		// TITULO "FORMULARIO DE REGISTRO"
		JPanel barraSuperior = new JPanel();
		barraSuperior.setBackground(colorFondo);
		barraSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel titulo = new JLabel("FORMULARIO DE REGISTRO");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Verdana", Font.BOLD, 20));

		barraSuperior.add(titulo);
		add(barraSuperior, BorderLayout.NORTH);
		
		// CREAR FORMULARIO
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	
		panelComponentes.setBackground(colorFondo);
		
		// APARTADO DE DATOS IMPORTANTE (NOMBRE DE USUARIO, CORREO, CONTRASEÑA, ETC.)
		JLabel etiquetaDatosImportantes = new JLabel("Datos Importantes");
		etiquetaDatosImportantes.setFont(fuenteSubtitulo);
		etiquetaDatosImportantes.setForeground(Color.WHITE);
		
		panelComponentes.add(etiquetaDatosImportantes);

		String[] campos = {"Nombre de Usuario","Correo Electrónico","Contraseña","Confirmar Contraseña"};
		int numeroCampos = campos.length;
		
		for(int i = 0; i < numeroCampos; i++) {
			
			if(campos[i].equals("Contraseña") || campos[i].equals("Confirmar Contraseña")) {
				
				JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
				fila.setBackground(colorFondo);

				JLabel contrasena = new JLabel(campos[i]);
				
				contrasena.setForeground(Color.WHITE);
				contrasena.setFont(fuenteNormal);
				
				panelComponentes.add(contrasena);
				
				JPasswordField campoContrasena = new JPasswordField(15);
				campoContrasena.setFont(fuenteNormal);
				
				fila.add(contrasena);
				fila.add(campoContrasena);
				
				panelComponentes.add(fila);
				continue;
			}
			
			JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
			fila.setBackground(colorFondo);

			JLabel etiqueta = new JLabel(campos[i]);
			etiqueta.setForeground(Color.WHITE);
			panelComponentes.add(etiqueta);
			etiqueta.setFont(fuenteNormal);
			
			JTextField texto = new JTextField(15);
			panelComponentes.add(texto);
			texto.setFont(fuenteNormal);
			
			fila.add(etiqueta);
			fila.add(texto);
			
			panelComponentes.add(fila);
		}
		
		// APARTADO DE SEXO
		JPanel alinearEtiquetaSexo = new JPanel();
		alinearEtiquetaSexo.setLayout(ajustarAlCentro);
		alinearEtiquetaSexo.setOpaque(false);
		//alinearEtiquetaSexo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		JLabel etiquetaSexo = new JLabel("Sexo");
		etiquetaSexo.setFont(fuenteSubtitulo);
		etiquetaSexo.setForeground(Color.WHITE);
		
		alinearEtiquetaSexo.add(etiquetaSexo);
		
		panelComponentes.add(alinearEtiquetaSexo);
		
		// PANEL DE OPCIÓN DE SEXO
		JPanel panelSexo = new JPanel();
		panelSexo.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelSexo.setBackground(colorFondo);
		
		JRadioButton hombre = new JRadioButton("Hombre");
		JRadioButton mujer = new JRadioButton("Mujer");
		JRadioButton noDecir = new JRadioButton("Prefiero no decirlo");
		
		hombre.setOpaque(false);
		hombre.setFont(fuenteNormal);
		hombre.setForeground(Color.WHITE);
		
		mujer.setOpaque(false);
		mujer.setFont(fuenteNormal);
		mujer.setForeground(Color.WHITE);
		
		noDecir.setOpaque(false);
		noDecir.setFont(fuenteNormal);
		noDecir.setForeground(Color.WHITE);

		ButtonGroup sexos = new ButtonGroup();
		
		sexos.add(hombre);
		sexos.add(mujer);
		sexos.add(noDecir);
		
		panelSexo.add(hombre);
		panelSexo.add(mujer);
		panelSexo.add(noDecir);
		
		panelComponentes.add(panelSexo);
		
		// APARTADO DE PRIVACIDAD
		JLabel etiquetaPrivacidad = new JLabel("Privacidad");
		etiquetaPrivacidad.setFont(fuenteSubtitulo);
		etiquetaPrivacidad.setForeground(Color.WHITE);

		panelComponentes.add(etiquetaPrivacidad);
		
		JPanel panelTerminosYCondiciones = new JPanel();
		panelTerminosYCondiciones.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTerminosYCondiciones.setBackground(colorFondo);
		
		JRadioButton terminosYCondiciones = new JRadioButton("Acepto terminos y condiciones");
		terminosYCondiciones.setOpaque(false);
		terminosYCondiciones.setForeground(Color.WHITE);
		terminosYCondiciones.setFont(fuenteNormal);
		
		panelTerminosYCondiciones.add(terminosYCondiciones);
		
		panelComponentes.add(panelTerminosYCondiciones);
		
		/*
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
		*/
		
		JScrollPane scroll = new JScrollPane(panelComponentes);
		scroll.setHorizontalScrollBar(null);
		
		add(scroll);
	}
}
