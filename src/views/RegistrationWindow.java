package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import config.Config;
import enums.Role;
import enums.Sex;
/**
 * Ventana de registro de usuarios.
 *
 * <p>Esta clase proporciona una interfaz grafica que permite crear
 * nuevas cuentas dentro del sistema. El formulario recopila información
 * personal, credenciales de acceso y preferencias del usuario.</p>
 *
 * <p>Incluye validaciones visuales mediante etiquetas de error y
 * controles para la selección de sexo y rol.</p>
 *
 * @author Hugo 
 * @author Emilinano
 * @version 1.0
 */
public class RegistrationWindow extends JFrame {

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

	private ButtonGroup grpRoles;
	private JRadioButton rbAdmin;
	private JRadioButton rbClient;

	

	public ButtonGroup getGrpSexes() {
		return grpSexes;
	}

	/**
	 * Obtiene el botón para regresar a la ventana anterior.
	 *
	 * @return botón de regreso.
	 */
	public JButton getBtnExit() {
		return btnExit;
	}

	public LoginWindow getWindow() {
		return window;
	}

	public void setWindow(LoginWindow window) {
		this.window = window;
	}
	/**
	 * Obtiene el botón utilizado para registrar un nuevo usuario.
	 *
	 * @return botón de registro.
	 */
	public JButton getBtnRegistrate() {
		return btnRegistrate;
	}
	/**
	 * Obtiene el la etiqueta de error del nombre.
	 *
	 * @return boton de registro.
	 */
	public JLabel getLblErrorFieldName() {
		return lblErrorFieldName;
	}
	/**
	 * Obtiene la campo de texto de nombre.
	 * 
	 * @return campo de texto nombre.
	 */
	public JTextField getTxtFieldName() {
		return txtFieldName;
	}

	/**
	 * Obtiene la etiquete de error de email.
	 * 
	 * @return etiqueta de error de email.
	 */
	public JLabel getLblErrorFieldEmail() {
		return lblErrorFieldEmail;
	}

	/**
	 * Obtene el campo de texto del email.
	 * 
	 * @return campo de texto de email.
	 */
	public JTextField getTxtFieldEmail() {
		return txtFieldEmail;
	}
	
	/**
	 *  Obtnene el campo de texto de la contraseña.
	 *  
	 * @return campo de texto de la contraseña.
	 */
	public String getPwdPassword() {
		return String.valueOf(pwdPassword.getPassword());
	}

	/**
	 *  Obtnene el campo de texto de confirmacion de  contraseña
	 *  
	 * @return campo de texto de la confirmacion de contraseña.
	 */
	public String getPwdConfirmPassword() {
		return String.valueOf(pwdConfirmPassword.getPassword());
	}

	/**
	 * Obtiene la etiqueta de error de la contraseña.
	 * 
	 * @return etiqueta de error de contraseña.
	 */
	public JLabel getLblErrorFieldPassword() {
		return lblErrorFieldPassword;
	}
	/**
	 * Obtiene la etiqueta de error de la confirmacion de lacontraseña.
	 * 
	 * @return etiqueta de error de confirmacion de contraseña.
	 */
	public JLabel getLblErrorFieldConfirmPassword() {
		return lblErrorFieldConfirmPassword;
	}
	/**
	 * Obtiene el nombre de usuario ingresado.
	 *
	 * @return nombre de usuario.
	 */
	public String getName() {
		return txtFieldName.getText();
	}
	/**
	 * Obtiene el correo electrónico ingresado.
	 *
	 * @return correo electrónico.
	 */
	public String getEmail() {
		return txtFieldEmail.getText();
	}
	/**
	 * Obtiene la contraseña ingresada.
	 *
	 * @return contraseña del usuario.
	 */
	public String getPassword() {
		return String.valueOf(pwdPassword.getPassword());
	}
	/**
	 * Obtiene la contraseña de confirmación ingresada.
	 *
	 * @return contraseña de confirmación.
	 */

	public String getConfirmPassword() {
		return String.valueOf(pwdConfirmPassword.getPassword());
	}
	/**
	 * Obtiene el sexo seleccionado por el usuario.
	 *
	 * @return valor del enum correspondiente a la opción seleccionada.
	 */
	public Sex getSex() {

		if (rbMan.isSelected()) {
			return Sex.MALE;

		} else if (rbWoman.isSelected()) {
			return Sex.FEMALE;
		}

		return Sex.OTHER;
	}
	/**
	 * Obtiene el rol seleccionado para el usuario.
	 *
	 * @return rol seleccionado.
	 */
	public Role getRole() {
		if(rbAdmin.isSelected()) {
			return Role.ADMIN;
		}
		
		return Role.CLIENT;
	}
	/**
	 * Crea e inicializa la ventana de registro.
	 *
	 * <p>Configura las propiedades principales de la ventana y genera
	 * todos los componentes necesarios para completar el proceso de registro.</p>
	 */
	public RegistrationWindow() {

		setSize(450, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setLocation(100,100);
		// setBounds(100,100,500,500);
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
	/**
	 * Inicializa y organiza todos los componentes graficos del formulario.
	 *
	 * <p>Este metodo crea las secciones de datos personales, sexo,
	 * rol, privacidad y botones de accion necesarios para el registro
	 * de usuarios.</p>
	 */
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
		componentsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		componentsPanel.setBackground(customBlue);

		// APARTADO DE DATOS IMPORTANTE (NOMBRE DE USUARIO, CORREO, CONTRASEÑA, ETC.)
		JPanel importantDataSection = new JPanel(alignToCenter);

		importantDataSection.setOpaque(false);
		importantDataSection.add(createLabel("Datos Importantes", subtitleFont));

		// CREAR PANEL GRID DE DOS COLUMNAS
		JPanel importantDataGrid = new JPanel(new GridLayout(1, 2, 10, 0));
		importantDataGrid.setOpaque(false);

		// COLUMNA IZQUIERDA
		JPanel labelsColumn = new JPanel();
		labelsColumn.setLayout(new BoxLayout(labelsColumn, BoxLayout.Y_AXIS));
		labelsColumn.setOpaque(false);

		// COLUMNA DERECHA
		JPanel fieldsColumn = new JPanel();
		fieldsColumn.setLayout(new BoxLayout(fieldsColumn, BoxLayout.Y_AXIS));

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

		// PANEL DE ROL
		JPanel roleSection = new JPanel(alignToCenter);

		roleSection.setOpaque(false);
		roleSection.add(createLabel("Rol", subtitleFont));

		JPanel roleFlowPanel = new JPanel(alignToCenter);
		roleFlowPanel.setOpaque(false);

		grpRoles = new ButtonGroup();

		rbAdmin = createJRadioButton("Administrador", grpRoles, roleFlowPanel);
		rbClient = createJRadioButton("Cliente", grpRoles, roleFlowPanel);

		

		// APARTADO DE PRIVACIDAD
		JPanel privacySection = new JPanel(alignToCenter);

		privacySection.setOpaque(false);
		privacySection.add(createLabel("Privacidad", subtitleFont));

		JPanel termsAndConditionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		termsAndConditionsPanel.setOpaque(false);

		JRadioButton rbTermsAndConditions = createJRadioButton("Acepto terminos y condiciones", null,
				termsAndConditionsPanel);

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

		// AÑADIR APARTADO ROLES
		componentsPanel.add(roleSection);
		componentsPanel.add(roleFlowPanel);

		// AÑADIR APARTADO DE PRIVACIDAD
		componentsPanel.add(privacySection);
		componentsPanel.add(termsAndConditionsPanel);

		// AÑADIR BOTONES DE REGISTRAR Y SALIR
		exitAndRegisterButtons.add(btnExit);
		exitAndRegisterButtons.add(btnRegistrate);
		componentsPanel.add(exitAndRegisterButtons);
	}

	
	// CREAR COMPONENTES
	/**
	 * Crea una etiqueta personalizada.
	 *
	 * @param lblText texto que mostrara la etiqueta.
	 * @param font fuente utilizada para mostrar el texto.
	 * @return etiqueta configurada.
	 */
	private JLabel createLabel(String lblText, Font font /* JPanel panel */) {

		JLabel label = new JLabel(lblText);

		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setVisible(true);
		label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		return label;
	}
	/**
	 * Crea un campo de texto y lo agrega al panel indicado.
	 *
	 * @param panel panel donde se agregara el componente.
	 * @return campo de texto creado.
	 */
	private JTextField createTextField(JPanel panel) {

		JTextField textField = new JTextField();

		textField.setFont(new Font("Verdana", Font.BOLD, 12));

		panel.add(textField);

		return textField;
	}
	/**
	 * Crea un campo de contraseña y lo agrega al panel indicado.
	 *
	 * @param panel panel donde se agregara el componente.
	 * @return campo de contraseña creado.
	 */
	private JPasswordField createPasswordField(JPanel panel) {

		JPasswordField passwordField = new JPasswordField();

		passwordField.setFont(new Font("Verdana", Font.BOLD, 12));

		panel.add(passwordField);

		return passwordField;
	}
	/**
	 * Crea un boton personalizado con efectos visuales al pasar el cursor.
	 *
	 * @param lblText texto del boton.
	 * @param font fuente utilizada.
	 * @param panel panel donde se agregara el boton.
	 * @return boton configurado.
	 */
	private JButton createJButton(String lblText, Font font, JPanel panel) {

		JButton button = new JButton(lblText);

		Color defaultButtonColor = button.getBackground();

		button.setFont(font);

		panel.add(button);

		button.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				changeBackground(button);
			}

			public void mouseExited(MouseEvent e) {
				resetBackground(button, defaultButtonColor);
			}

		});

		return button;
	}
	/**
	 * Crea un botón de opción y lo registra en un grupo si se proporciona.
	 *
	 * @param lblText texto mostrado en el boton.
	 * @param grpButtons grupo de botones al que pertenece.
	 * @param panel panel donde sera agregado.
	 * @return boton de opción configurado.
	 */
	private JRadioButton createJRadioButton(String lblText, ButtonGroup grpButtons, JPanel panel) {

		JRadioButton button = new JRadioButton(lblText);

		button.setOpaque(false);
		button.setFont(new Font("Verdana", Font.BOLD, 12));
		button.setForeground(Color.WHITE);

		if (grpButtons != null) {
			grpButtons.add(button);
		}

		if (panel != null) {
			panel.add(button);
		}

		return button;
	}
	/**
	 * Crea una etiqueta para mostrar mensajes de error.
	 *
	 * @param panel panel donde se agregara la etiqueta.
	 * @return etiqueta de error creada.
	 */
	private JLabel createErrorLabel(JPanel panel) {

		JLabel errorLabel = new JLabel();

		errorLabel.setFont(new Font("Verdana", Font.BOLD, 11));
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);

		panel.add(errorLabel);

		return errorLabel;
	}

	// CAMBIO DE FONDO
	/**
	 * Cambia temporalmente los colores de un componente cuando el cursor
	 * se posiciona sobre el.
	 *
	 * @param c componente a modificar.
	 */
	private void changeBackground(JComponent c) {
		c.setBackground(Color.BLACK);
		c.setForeground(Color.WHITE);
	}
	/**
	 * Restaura los colores originales de un componente.
	 *
	 * @param c componente a restaurar.
	 * @param defaultButtonColor color original del componente.
	 */
	private void resetBackground(JComponent c, Color defaultButtonColor) {
		c.setBackground(defaultButtonColor);
		c.setForeground(Color.BLACK);
	}
	/**
	 * Oculta todas las etiquetas de error del formulario.
	 *
	 * <p>Se utiliza antes de realizar una nueva validación de los datos
	 * ingresados por el usuario.</p>
	 */
	public void resetErrorLabels() {
		lblErrorFieldName.setVisible(false);
		lblErrorFieldEmail.setVisible(false);
		lblErrorFieldPassword.setVisible(false);
		lblErrorFieldConfirmPassword.setVisible(false);
	}

}
