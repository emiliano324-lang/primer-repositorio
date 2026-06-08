package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * Vista de inicio de sesion de la aplicacion.
 *
 * <p>Esta clase proporciona la interfaz grafica que permite a los
 * usuarios autenticarse mediante correo electrónico y contraseña.
 * También muestra mensajes de error cuando los datos ingresados
 * son inválidos o incompletos.</p>
 *
 * @author Hugo 
 * @author Emiliano
 * @version 1.0
 */
public class LoginView extends JPanel {

	private LoginWindow window;
	
	Font font;
	
	private JLabel lblWelcome;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblErrorUser;
	private JLabel lblErrorPassword;
	
	private JTextField txtFieldEmail;
	private JPasswordField pwdFieldPassword;

	private JButton btnLogin;
	private JButton btnSignIn;
	/**
	 * Obtiene la ventana asociada a esta vista.
	 *
	 * @return ventana de inicio de sesión.
	 */
	public LoginWindow getWindow() {
		return window;
	}
	/**
	 * Obtiene el campo de texto del correo electrónico.
	 *
	 * @return campo de correo electrónico.
	 */
	public JTextField getTxtFieldEmail() {
		return txtFieldEmail;
	}
	/**
	 * Obtiene la etiqueta de error asociada al correo electrónico.
	 *
	 * @return etiqueta de error del correo.
	 */
	public JLabel getLblErrorUser() {
		return lblErrorUser;
	}
	/**
	 * Obtiene la etiqueta correspondiente a la contraseña.
	 *
	 * @return etiqueta de contraseña.
	 */
	public JLabel getLblPassword() {
		return lblPassword;
	}
	/**
	 * Obtiene el campo de contraseña.
	 *
	 * @return campo de contraseña.
	 */
	public JPasswordField getPwdFieldPassword() {
		return pwdFieldPassword;
	}
	/**
	 * Obtiene la etiqueta utilizada para mostrar errores relacionados
	 * con la contraseña.
	 *
	 * @return etiqueta de error de contraseña.
	 */
	public JLabel getLblErrorPassword() {
		return lblErrorPassword;
	}
	/**
	 * Obtiene el boton para iniciar sesión.
	 *
	 * @return botón de inicio de sesión.
	 */
	public JButton getBtnLogin() {
		return btnLogin;
	}
	/**
	 * Obtiene el boton para registrarse.
	 *
	 * @return boton de registro.
	 */
	public JButton getBtnSignIn() {
		return btnSignIn;
	}
	/**
	 * Obtiene el correo electronico ingresado por el usuario.
	 *
	 * @return correo electronico escrito en el formulario.
	 */
	public String getEmail() {
		return txtFieldEmail.getText();
	}
	/**
	 * Obtiene la contraseña ingresada por el usuario.
	 *
	 * @return contraseña escrita en el formulario.
	 */
	public String getPassword() {
		return String.valueOf(pwdFieldPassword.getPassword());
	}
	/**
	 * Crea e inicializa la vista de inicio de sesion.
	 *
	 * @param window ventana que contiene esta vista.
	 */
	public LoginView(LoginWindow window) {
		this.window = window;		
		initializeComponents();
		
    }
	/**
	 * Inicializa y configura todos los componentes graficos de la vista.
	 *
	 * <p>Este metodo crea las etiquetas, campos de texto, botones y
	 * mensajes de error necesarios para el proceso de autenticación.</p>
	 */
	private void initializeComponents() {
		
		font = new Font("Verdana", Font.BOLD, 18);
		
        setBackground(new Color(0, 31, 84));
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        // Bienvenida
        lblWelcome = new JLabel("BIENVENIDO");
        lblWelcome.setForeground(new Color(254, 252, 251));
        lblWelcome.setFont(new Font("Verdana", Font.BOLD, 45));

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(lblWelcome, c);

        // Usuario
        lblEmail = new JLabel("Correo");
        lblEmail.setForeground(new Color(254, 252, 251));
        lblEmail.setFont(font);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(lblEmail, c);

        // Campo usuario
        txtFieldEmail = new JTextField(15);
        txtFieldEmail.setFont(font);
        txtFieldEmail.setBackground(Color.WHITE);

        c.gridx = 1;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(txtFieldEmail, c);

        // Error usuario
        lblErrorUser = new JLabel("Error: Este campo es obligatorio");
        lblErrorUser.setFont(new Font("Verdana", Font.PLAIN, 15));
        lblErrorUser.setForeground(Color.red);
        lblErrorUser.setVisible(false);

        c.gridy = 2;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(lblErrorUser, c);

        // Contraseña
        lblPassword = new JLabel("Contraseña");
        lblPassword.setForeground(new Color(254, 252, 251));
        lblPassword.setFont(font);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        add(lblPassword, c);

        // Campo contraseña
        pwdFieldPassword = new JPasswordField(15);
        pwdFieldPassword.setFont(new Font("Verdana", Font.PLAIN, 15));
        pwdFieldPassword.setBackground(Color.WHITE);

        c.gridx = 1;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(pwdFieldPassword, c);

        // Error contraseña
        lblErrorPassword = new JLabel("");
        lblErrorPassword.setFont(new Font("Verdana", Font.PLAIN, 15));
        lblErrorPassword.setForeground(Color.red);
        lblErrorPassword.setVisible(false);
        
        c.gridy = 4;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(lblErrorPassword, c);

        // Boton
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 15));
        
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        
        add(btnLogin, c);
        
        btnSignIn = new JButton("Registrarme");
        btnSignIn.setBackground(Color.WHITE);
        btnSignIn.setFont(new Font("Arial", Font.PLAIN, 15));
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        
        add(btnSignIn, c);
	}
	/**
	 * Oculta todas las etiquetas de error mostradas en la interfaz.
	 *
	 * <p>Se utiliza normalmente antes de realizar una nueva validación
	 * de los datos ingresados por el usuario.</p>
	 */
	public void resetErrorLabels() {
		lblErrorUser.setVisible(false);
		lblErrorPassword.setVisible(false);
	}
	/**
	 * Muestra el mensaje de error asociado al campo de correo electrónico.
	 */
	public void showLblErrorUser() {
		lblErrorUser.setVisible(true);
	}
	/**
	 * Muestra un mensaje de error relacionado con la contraseña.
	 *
	 * @param message mensaje de error que se mostrará al usuario.
	 */
	public void showLblErrorPassword(String message) {
		lblErrorPassword.setText(message);
		lblErrorPassword.setVisible(true);
	}
}