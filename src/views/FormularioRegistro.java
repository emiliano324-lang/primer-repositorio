package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

//import java.util.concurrent.Flow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.concurrent.Flow;

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

	LoginWindow window;
	
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
		Font fuenteSubtitulo = new Font("Verdana", Font.BOLD, 16);
		Font fuenteNormal = new Font("Verdana", Font.BOLD, 12);
		FlowLayout ajustarAlCentro = new FlowLayout(FlowLayout.CENTER);
		
		// CREAR TITULO "FORMULARIO DE REGISTRO"
		JPanel barraSuperior = new JPanel();
		
		barraSuperior.setBackground(colorFondo);
		barraSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel titulo = new JLabel("FORMULARIO DE REGISTRO");
		
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Verdana", Font.BOLD, 20));
		
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

		// CREAR PANEL GRID DE DOS COLUMNAS
		JPanel datosImportantes = new JPanel(new GridLayout(1,2,10,0));
		datosImportantes.setOpaque(false);
		
		// COLUMNA IZQUIERDA
		JPanel columnaEtiquetas = new JPanel();
		columnaEtiquetas.setLayout(new BoxLayout(columnaEtiquetas,BoxLayout.Y_AXIS));
		columnaEtiquetas.setOpaque(false);
		
		// COLUMNA DERECHA
		JPanel columnaCampos = new JPanel();
		columnaCampos.setLayout(new BoxLayout(columnaCampos,BoxLayout.Y_AXIS));
		
		// ETIQUETAS
		JLabel lblName = createLabel("Nombre de Usuario", columnaEtiquetas);
		JLabel lblEmail = createLabel("Email", columnaEtiquetas);
		JLabel lblPassword = createLabel("Contraseña", columnaEtiquetas);
		JLabel lblConfirmPassword = createLabel("Confirmar contraseña", columnaEtiquetas);
		
		// CAMPOS Y SU ETIQUETA DE ERROR
		JTextField txtFieldName = createTextField(columnaCampos);
		JLabel lblEmptyFieldName = createErrorLabel("El nombre es obligatorio", columnaCampos);
		
		JTextField txtFieldEmail = createTextField(columnaCampos);
		JLabel lblEmptyFieldEmail = createErrorLabel("El correo es obligatorio", columnaCampos);
		JLabel lblUnvalidEmail = createErrorLabel("Correo no válido", columnaCampos);
		
		JPasswordField pwdPassword = createPasswordField(columnaCampos);
		JLabel lblEmptyFieldPassword = createErrorLabel("La contraseña es obligatoria", columnaCampos);

		JPasswordField pwdConfirmPassword = createPasswordField(columnaCampos);
		JLabel lblEmptyFieldConfirmPassword = createErrorLabel("Debes confirmar la contraseña", columnaCampos);
		JLabel lblErrorUnequalPasswords = createErrorLabel("Las contraseñas no coinciden", columnaCampos);
		
		// AÑADIR AMBAS COLUMNAS EN EL PANEL
		datosImportantes.add(columnaEtiquetas);
		datosImportantes.add(columnaCampos);
		
		// APARTADO DE SEXO
		JPanel alinearEtiquetaSexo = new JPanel();
		alinearEtiquetaSexo.setLayout(ajustarAlCentro);
		alinearEtiquetaSexo.setOpaque(false);
		
		JLabel etiquetaSexo = new JLabel("Sexo");
		etiquetaSexo.setFont(fuenteSubtitulo);
		etiquetaSexo.setForeground(Color.WHITE);
		
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
		
		alinearEtiquetaSexo.add(etiquetaSexo);
		
		// APARTADO DE PRIVACIDAD
		JPanel alinearEtiquetaPrivacidad = new JPanel();
		alinearEtiquetaPrivacidad.setLayout(ajustarAlCentro);
		alinearEtiquetaPrivacidad.setOpaque(false);

		JLabel etiquetaPrivacidad = new JLabel("Privacidad");
		etiquetaPrivacidad.setFont(fuenteSubtitulo);
		etiquetaPrivacidad.setForeground(Color.WHITE);

		alinearEtiquetaPrivacidad.add(etiquetaPrivacidad);
		
		JPanel panelTerminosYCondiciones = new JPanel();
		panelTerminosYCondiciones.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTerminosYCondiciones.setBackground(colorFondo);
		
		JRadioButton terminosYCondiciones = new JRadioButton("Acepto terminos y condiciones");
		terminosYCondiciones.setOpaque(false);
		terminosYCondiciones.setForeground(Color.WHITE);
		terminosYCondiciones.setFont(fuenteNormal);
		
		panelTerminosYCondiciones.add(terminosYCondiciones);
		
		// BOTONES DE SALIR Y REGISTRARSE
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelBotones.setBackground(colorFondo);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(fuenteNormal);
		
		JButton btnRegistroButton = new JButton("Registrarme");
		btnRegistroButton.setFont(fuenteNormal);
		
		// AÑADIR TITULO
		barraSuperior.add(titulo);
		add(barraSuperior, BorderLayout.NORTH);
		
		// AÑADIR PANEL "DATOS IMPORTANTES"
		panelComponentes.add(alinearEtiquetaDatosImportantes);
		
		//AÑADIR AMBOS PANELES AL GRID
		panelComponentes.add(datosImportantes);
		
		// Label sexo
		panelComponentes.add(alinearEtiquetaSexo);
		
		panelComponentes.add(panelSexo);
		
		panelComponentes.add(alinearEtiquetaPrivacidad);
		panelComponentes.add(panelTerminosYCondiciones);
		
		// AÑADIR BOTONES DE REGISTRAR Y SALIR
		panelBotones.add(btnSalir);
		panelBotones.add(btnRegistroButton);
		panelComponentes.add(panelBotones);
		
		btnRegistroButton.addActionListener(e -> {

		    boolean hayError = false;

		    // Validar nombre
		    if (txtFieldName.getText().trim().isEmpty()) {
		        lblEmptyFieldName.setVisible(true);
		        hayError = true;

		    } else {
		        lblEmptyFieldName.setVisible(false);
		    }

		    // Validar correo
		    if (txtFieldEmail.getText().trim().isEmpty()) {
			    	lblEmptyFieldEmail.setVisible(true);
		        hayError = true;
		        
		    } else if (!txtFieldEmail.getText().contains("@")) {
		    		lblEmptyFieldEmail.setVisible(false);
			    	lblUnvalidEmail.setVisible(true);
		    		
		    } else {
		    		lblEmptyFieldEmail.setVisible(false);
			    	lblUnvalidEmail.setVisible(false);

		    }

		    // Validar contraseña
		    String pass = new String(pwdPassword.getPassword());
		    String confirmPass = new String(pwdConfirmPassword.getPassword());

		    if (pass.isEmpty()) {
		    		lblEmptyFieldPassword.setVisible(true);
		        hayError = true;
		    } else {
		    		lblEmptyFieldPassword.setVisible(false);
		    }

		    if (confirmPass.isEmpty()) {
		        lblEmptyFieldConfirmPassword.setVisible(true);
		        hayError = true;
		    } else {
		    		lblEmptyFieldConfirmPassword.setVisible(false);
		    }

		    if (!confirmPass.isEmpty() && !pass.equals(confirmPass)) {
		    		lblErrorUnequalPasswords.setVisible(true);
		        hayError = true;
		    } else {
		    		lblErrorUnequalPasswords.setVisible(false);
		    }

		    // Validar términos
		    if (!terminosYCondiciones.isSelected()) {
		        JOptionPane.showMessageDialog(null,
		                "Debes aceptar los términos y condiciones",
		                "Error",
		                JOptionPane.WARNING_MESSAGE);
		        hayError = true;
		    }

		    // Si todo está correcto
		    if (!hayError) {
		        JOptionPane.showMessageDialog(null,
		                "Registro exitoso",
		                "Éxito",
		                JOptionPane.INFORMATION_MESSAGE);
		        handleLogin();
		    }

		});
		
		JScrollPane scroll = new JScrollPane(panelComponentes);
		scroll.setHorizontalScrollBar(null);
		
		add(scroll);
	}
	
	private JLabel createLabel(String lblText, JPanel panel) {
		
		JLabel label = new JLabel(lblText);
		
		label.setFont(new Font("Verdana", Font.BOLD, 12));
		label.setForeground(Color.WHITE);
		label.setVisible(true);
		label.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

		panel.add(label);
		
		return label;
	}
	
	private JTextField createTextField(JPanel panel) {
		
		JTextField textField = new JTextField();
		
		textField.setFont(new Font("Verdana", Font.BOLD, 12));
		
		panel.add(textField);
		
		return textField;
	}
	
	private JPasswordField createPasswordField(JPanel panel) {
		
		JPasswordField passwordField = new JPasswordField();
		
		passwordField.setFont(new Font("Verdana", Font.BOLD, 12));
		
		panel.add(passwordField);
		
		return passwordField;
	}
	
	private JLabel createErrorLabel(String lblText, JPanel panel) {
		
		JLabel errorLabel = new JLabel(lblText);
		
		errorLabel.setFont(new Font("Verdana", Font.BOLD, 11));
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		
		panel.add(errorLabel);
		
		return errorLabel;
	}
	
	 private void handleLogin() {
		new MainWindow();
		
		window.dispose();
	}
}
