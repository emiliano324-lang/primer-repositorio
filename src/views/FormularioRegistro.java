package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
<<<<<<< HEAD
//import java.util.concurrent.Flow;
=======
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;
>>>>>>> 2f092640ea5265a39005f787fdf9c8cd3ccabe4d

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
//import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
//import javax.swing.JSlider;
import javax.swing.JTextField;
//import javax.swing.border.Border;
//import javax.swing.border.EmptyBorder;

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
		JPanel alinearEtiquetaDatosImportantes = new JPanel();
		alinearEtiquetaDatosImportantes.setLayout(ajustarAlCentro);
		alinearEtiquetaDatosImportantes.setOpaque(false);
		
		JLabel etiquetaDatosImportantes = new JLabel("Datos Importantes");
		etiquetaDatosImportantes.setFont(fuenteSubtitulo);
		etiquetaDatosImportantes.setForeground(Color.WHITE);
		
		alinearEtiquetaDatosImportantes.add(etiquetaDatosImportantes);
		
		panelComponentes.add(alinearEtiquetaDatosImportantes);

		/*String[] campos = {"Nombre de Usuario","Correo Electrónico","Contraseña","Confirmar Contraseña"};
		int numeroCampos = campos.length;*/
		
		JPanel datosImportantes = new JPanel(new GridLayout(1,2,10,0));
		datosImportantes.setOpaque(false);
		
		JPanel columnaEtiquetas = new JPanel();
		columnaEtiquetas.setLayout(new BoxLayout(columnaEtiquetas,BoxLayout.Y_AXIS));
		columnaEtiquetas.setOpaque(false);
		
		JLabel etiquetaNombreUsuario = new JLabel("Nombre de Usuario");
		JLabel etiquetaCorreo = new JLabel("Correo Electrónico");
		JLabel etiquetaContrasena = new JLabel("Contraseña");
		JLabel etiquetaConfirmarContrasena = new JLabel("Confirmar Contraseña");
		
		// Espaciado para alinear las etiquetas con los campos
		etiquetaNombreUsuario.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		etiquetaCorreo.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		etiquetaContrasena.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		etiquetaConfirmarContrasena.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		
		// Color de texto de etiquetas
		etiquetaNombreUsuario.setForeground(Color.WHITE);
		etiquetaCorreo.setForeground(Color.WHITE);
		etiquetaContrasena.setForeground(Color.WHITE);
		etiquetaConfirmarContrasena.setForeground(Color.WHITE);
		
		// Fuente de etiquetas
		etiquetaNombreUsuario.setFont(fuenteNormal);
		etiquetaCorreo.setFont(fuenteNormal);
		etiquetaContrasena.setFont(fuenteNormal);
		etiquetaConfirmarContrasena.setFont(fuenteNormal);
		
		// Añadir etiquetas a la columna de la izquierda
		columnaEtiquetas.add(etiquetaNombreUsuario);
		columnaEtiquetas.add(etiquetaCorreo);
		columnaEtiquetas.add(etiquetaContrasena);
		columnaEtiquetas.add(etiquetaConfirmarContrasena);
		
		datosImportantes.add(columnaEtiquetas);
		
		JPanel columnaCampos = new JPanel();
		columnaCampos.setLayout(new BoxLayout(columnaCampos,BoxLayout.Y_AXIS));
		
		JTextField campoNombreUsuario = new JTextField();
		JTextField campoCorreo = new JTextField();
		JPasswordField campoContrasena = new JPasswordField();
		JPasswordField campoConfirmarContrasena = new JPasswordField();
		
		campoNombreUsuario.setFont(fuenteNormal);
		campoCorreo.setFont(fuenteNormal);
		campoContrasena.setFont(fuenteNormal);
		campoConfirmarContrasena.setFont(fuenteNormal);
		
		columnaCampos.add(campoNombreUsuario);
		columnaCampos.add(campoCorreo);
		columnaCampos.add(campoContrasena);
		columnaCampos.add(campoConfirmarContrasena);
		
		datosImportantes.add(columnaCampos);
		
		panelComponentes.add(datosImportantes);
		
		// APARTADO DE SEXO
		JPanel alinearEtiquetaSexo = new JPanel();
		alinearEtiquetaSexo.setLayout(ajustarAlCentro);
		alinearEtiquetaSexo.setOpaque(false);
		
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
		JPanel alinearEtiquetaPrivacidad = new JPanel();
		alinearEtiquetaPrivacidad.setLayout(ajustarAlCentro);
		alinearEtiquetaPrivacidad.setOpaque(false);

		JLabel etiquetaPrivacidad = new JLabel("Privacidad");
		etiquetaPrivacidad.setFont(fuenteSubtitulo);
		etiquetaPrivacidad.setForeground(Color.WHITE);

		alinearEtiquetaPrivacidad.add(etiquetaPrivacidad);
		
		panelComponentes.add(alinearEtiquetaPrivacidad);
		
		JPanel panelTerminosYCondiciones = new JPanel();
		panelTerminosYCondiciones.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTerminosYCondiciones.setBackground(colorFondo);
		
		JRadioButton terminosYCondiciones = new JRadioButton("Acepto terminos y condiciones");
		terminosYCondiciones.setOpaque(false);
		terminosYCondiciones.setForeground(Color.WHITE);
		terminosYCondiciones.setFont(fuenteNormal);
		
		panelTerminosYCondiciones.add(terminosYCondiciones);
		
		panelComponentes.add(panelTerminosYCondiciones);
		
		// BOTONES DE SALIR Y REGISTRARSE
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelBotones.setBackground(colorFondo);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(fuenteNormal);
		
		JButton btnRegistroButton = new JButton("Registrarme");
		btnRegistroButton.setFont(fuenteNormal);
		
		panelBotones.add(btnSalir);
		panelBotones.add(btnRegistroButton);
		
		panelComponentes.add(panelBotones);
		
		
		
		
		btnRegistroButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Se acciono el boton");
				System.out.println("Se acciono el boton");
				JOptionPane.showMessageDialog(null,"Iniciar sesion","Sesion Iniciada",JOptionPane.INFORMATION_MESSAGE);
			}	
		});
		
		btnSalir.addActionListener(e -> {
			System.out.println("Se acciono el boton");
			System.out.println("Se acciono el boton");
			JOptionPane.showMessageDialog(null,"Salir","Seguro que quieres salir",JOptionPane.INFORMATION_MESSAGE);
		
			
		});
		
		
		
		
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
