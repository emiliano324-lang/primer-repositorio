package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

//import java.util.concurrent.Flow;
//import java.util.concurrent.Flow;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class RegistrationWindow extends JFrame{
	
	LoginWindow window;
	
	private JButton btnRegistrate;
	private JButton btnExit;
	private JTextField txtFieldName;
	private JTextField txtFieldEmail;
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfirmPassword;
	private JLabel lblErrorFieldName;
	private JLabel lblErrorFieldEmail;
	private JLabel lblErrorFieldPassword;
	private JLabel lblErrorFieldConfirmPassword;
	private ButtonGroup grpSexes;
	private JRadioButton rbMan;
	private JRadioButton rbWoman;
	private JRadioButton rbDoNotSay;
	
	public JButton getBtnExit() {
		return btnExit;
	}
	public LoginWindow getWindow() {
		return window;
	}

	public void setWindow(LoginWindow window) {
		this.window = window;
	}

	public JButton getBtnRegistrate() {
		return btnRegistrate;
	}

	public JLabel getLblErrorFieldName() {
		return lblErrorFieldName;
	}

	public JTextField getTxtFieldName() {
		return txtFieldName;
	}

	public JLabel getLblErrorFieldEmail() {
		return lblErrorFieldEmail;
	}

	public JTextField getTxtFieldEmail() {
		return txtFieldEmail;
	}

	public JPasswordField getPwdPassword() {
		return pwdPassword;
	}

	public JPasswordField getPwdConfirmPassword() {
		return pwdConfirmPassword;
	}

	public JLabel getLblErrorFieldPassword() {
		return lblErrorFieldPassword;
	}

	public JLabel getLblErrorFieldConfirmPassword() {
		return lblErrorFieldConfirmPassword;
	}

	public String getName() {
		return txtFieldName.getText();
	}
	
	public String getEmail() {
		return txtFieldEmail.getText();
	}
	
	public String getPassword() {
		return String.valueOf(pwdPassword.getPassword());
	}
	
	public String getConfirmPassword() {
		return String.valueOf(pwdConfirmPassword.getPassword());
	}
	
	public String getSex() {
		
		if(rbMan.isSelected()) {
			return "Masculino";
			
		}else if(rbWoman.isSelected()) {
			return "Femenino";
		}

		return "No definido";
	}
	
	public RegistrationWindow() {
		
		setSize(450, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLocation(100,100);
		//setBounds(100,100,500,500);
		setResizable(false);
		setTitle("Registro");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icon = tk.getImage("src/img/logo_uabcs.png");
		setIconImage(icon);
		
		initializeComponents();
		
		// VALIDACIONES DE CERRAR VENTANA
		addWindowListener(new WindowAdapter() {

		});
		
		setVisible(true);
	}
	
	public void initializeComponents() {
		
		Color customBlue = new Color(0, 31, 84);
		Font subtitleFont = new Font("Verdana", Font.BOLD, 16);
		Font textFont = new Font("Verdana", Font.BOLD, 12);
		FlowLayout alignToCenter = new FlowLayout(FlowLayout.CENTER);
		Color defaultButtonColor;
		
		// CREAR TITULO "FORMULARIO DE REGISTRO"
		JPanel superiorBar = new JPanel(alignToCenter);

		superiorBar.setBackground(customBlue);

		JLabel title = new JLabel("FORMULARIO DE REGISTRO");
		
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Verdana", Font.BOLD, 20));
		
		// CREAR FORMULARIO
		JPanel componentsPanel = new JPanel();
		
		componentsPanel.setLayout(new BoxLayout(componentsPanel, BoxLayout.Y_AXIS));
		componentsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		componentsPanel.setBackground(customBlue);
		
		// APARTADO DE DATOS IMPORTANTE (NOMBRE DE USUARIO, CORREO, CONTRASEÑA, ETC.)
		JPanel importantDataSection = new JPanel(alignToCenter);
		
		importantDataSection.setOpaque(false);
		importantDataSection.add(createLabel("Datos Importantes", subtitleFont));

		// CREAR PANEL GRID DE DOS COLUMNAS
		JPanel importantDataGrid = new JPanel(new GridLayout(1,2,10,0));
		importantDataGrid.setOpaque(false);
		
		// COLUMNA IZQUIERDA
		JPanel labelsColumn = new JPanel();
		labelsColumn.setLayout(new BoxLayout(labelsColumn,BoxLayout.Y_AXIS));
		labelsColumn.setOpaque(false);
		
		// COLUMNA DERECHA
		JPanel fieldsColumn = new JPanel();
		fieldsColumn.setLayout(new BoxLayout(fieldsColumn,BoxLayout.Y_AXIS));
		
		// ETIQUETAS
		labelsColumn.add(createLabel("Nombre de Usuario", textFont));
		labelsColumn.add(createLabel("Email", textFont));
		labelsColumn.add(createLabel("Contraseña", textFont));
		labelsColumn.add(createLabel("Confirmar contraseña", textFont));
		
		// CAMPOS Y SU ETIQUETA DE ERROR
		txtFieldName = createTextField(fieldsColumn);
		lblErrorFieldName = createErrorLabel(fieldsColumn);
		
		txtFieldEmail = createTextField(fieldsColumn);
		lblErrorFieldEmail = createErrorLabel(fieldsColumn);
		
		pwdPassword = createPasswordField(fieldsColumn);
		lblErrorFieldPassword = createErrorLabel(fieldsColumn);

		pwdConfirmPassword = createPasswordField(fieldsColumn);
	    lblErrorFieldConfirmPassword = createErrorLabel(fieldsColumn);
		
		// AÑADIR AMBAS COLUMNAS EN EL PANEL
		importantDataGrid.add(labelsColumn);
		importantDataGrid.add(fieldsColumn);
		
		// APARTADO DE SEXO
		JPanel sexSection = new JPanel(alignToCenter);

		sexSection.setOpaque(false);
		sexSection.add(createLabel("Sexo", subtitleFont));
		
		// PANEL DE OPCIÓN DE SEXO
		JPanel sexFlowPanel = new JPanel(alignToCenter);
		
		sexFlowPanel.setOpaque(false);
		
		grpSexes = new ButtonGroup();
		
		rbMan = createJRadioButton("Hombre", grpSexes, sexFlowPanel);
		rbWoman = createJRadioButton("Mujer", grpSexes, sexFlowPanel);
		rbDoNotSay = createJRadioButton("Prefiero no decir", grpSexes, sexFlowPanel);
		
		// APARTADO DE PRIVACIDAD
		JPanel privacySection = new JPanel(alignToCenter);
		
		privacySection.setOpaque(false);
		privacySection.add(createLabel("Privacidad", subtitleFont));
		
		JPanel termsAndConditionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		termsAndConditionsPanel.setOpaque(false);
		
		JRadioButton rbTermsAndConditions = createJRadioButton("Acepto terminos y condiciones", null, termsAndConditionsPanel);
		
		// BOTONES DE SALIR Y REGISTRARSE
		JPanel exitAndRegisterButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		exitAndRegisterButtons.setBackground(customBlue);
		
		btnExit = createJButton("Atras", textFont, exitAndRegisterButtons);
		btnRegistrate = createJButton("Registrarme", textFont, exitAndRegisterButtons);
		
		// BARRA VERTICAL DE SCROLL
		JScrollPane scroll = new JScrollPane(componentsPanel);
		scroll.setHorizontalScrollBar(null);
		
		add(scroll);
		
		// AÑADIR TITULO
		superiorBar.add(title);
		add(superiorBar, BorderLayout.NORTH);
		
		// AÑADIR APARTADO DE DATOS IMPORTANTES
		componentsPanel.add(importantDataSection);
		componentsPanel.add(importantDataGrid);
		
		// AÑADIR APARTADO SEXO
		componentsPanel.add(sexSection);
		componentsPanel.add(sexFlowPanel);
		
		// AÑADIR APARTADO DE PRIVACIDAD
		componentsPanel.add(privacySection);
		componentsPanel.add(termsAndConditionsPanel);
		
		// AÑADIR BOTONES DE REGISTRAR Y SALIR
		exitAndRegisterButtons.add(btnExit);
		exitAndRegisterButtons.add(btnRegistrate);
		componentsPanel.add(exitAndRegisterButtons);
	}
		
	// CREAR COMPONENTES
	private JLabel createLabel(String lblText, Font font /*JPanel panel*/) {
		
		JLabel label = new JLabel(lblText);
		
		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setVisible(true);
		label.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		
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
	
	private JButton createJButton(String lblText, Font font, JPanel panel) {
		
		JButton button = new JButton(lblText);
		
		Color defaultButtonColor = button.getBackground();
		
		button.setFont(font);
		
		panel.add(button);
		
		button.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered (MouseEvent e) {
				changeBackground(button);
			}
		
			public void mouseExited(MouseEvent e) {
				resetBackground(button, defaultButtonColor);
			}
			
		});
		
		return button;
	}

	private JRadioButton createJRadioButton(String lblText, ButtonGroup grpButtons, JPanel panel) {
		
		JRadioButton button = new JRadioButton(lblText);
		
		button.setOpaque(false);
		button.setFont(new Font("Verdana", Font.BOLD, 12));
		button.setForeground(Color.WHITE);
		
		if(grpButtons != null) {
			grpButtons.add(button);
		} 	
		
		if(panel != null) {
			panel.add(button);
		}
		
		return button;
	}
	
	private JLabel createErrorLabel(JPanel panel) {
		
		JLabel errorLabel = new JLabel();
		
		errorLabel.setFont(new Font("Verdana", Font.BOLD, 11));
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		
		panel.add(errorLabel);
		
		return errorLabel;
	} 
	
	// CAMBIO DE FONDO
	private void changeBackground(JComponent c) {
		c.setBackground(Color.BLACK);
		c.setForeground(Color.WHITE);
	}
	
	private void resetBackground(JComponent c, Color defaultButtonColor) {
		c.setBackground(defaultButtonColor);
		c.setForeground(Color.BLACK);
	}
	
	public void resetErrorLabels() {
		lblErrorFieldName.setVisible(false);
	    lblErrorFieldEmail.setVisible(false);
	    lblErrorFieldPassword.setVisible(false);
	    lblErrorFieldConfirmPassword.setVisible(false);
	}
}
