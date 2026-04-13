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

public class LoginView extends JPanel {

	LoginWindow window;
	
	Font font;
	
	private JLabel lblWelcome;
	private JLabel lblUser;
	
	private JTextField txtFieldUser;
	private JLabel lblErrorUser;
	private JLabel lblPassword;
	private JPasswordField pwdFieldPassword;
	private JLabel lblErrorPassword;
	private JButton btnLogin;
	private JButton btnSignIn;
	
	
	public LoginWindow getWindow() {
		return window;
	}
	
	public JTextField getTxtFieldUser() {
		return txtFieldUser;
	}

	public JLabel getLblErrorUser() {
		return lblErrorUser;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}

	public JPasswordField getPwdFieldPassword() {
		return pwdFieldPassword;
	}

	public JLabel getLblErrorPassword() {
		return lblErrorPassword;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JButton getBtnSignIn() {
		return btnSignIn;
	}

	public LoginView() {

		initializeComponents();
    		
    }
    
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
        lblUser = new JLabel("Usuario");
        lblUser.setForeground(new Color(254, 252, 251));
        lblUser.setFont(font);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(lblUser, c);

        // Campo usuario
        txtFieldUser = new JTextField(15);
        txtFieldUser.setFont(font);
        txtFieldUser.setBackground(Color.WHITE);

        c.gridx = 1;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(txtFieldUser, c);

        // Error usuario
        lblErrorUser = new JLabel("Error: este campo es obligatorio");
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
        lblErrorPassword = new JLabel("Error: este campo es obligatorio");
        lblErrorPassword.setFont(new Font("Verdana", Font.PLAIN, 15));
        lblErrorPassword.setForeground(Color.red);
        lblErrorPassword.setVisible(false);
        
        c.gridy = 4;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(lblErrorPassword, c);

        // Boton
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 15));
        
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        add(btnLogin, c);
        
        btnSignIn = new JButton("Registrarme");
        btnSignIn.setFont(new Font("Arial", Font.PLAIN, 15));
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        
        add(btnSignIn, c);
	}
}