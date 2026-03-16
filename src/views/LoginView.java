package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LoginView extends JPanel {

	LoginWindow window;
	Font font;
	
    public LoginView() {
    		
    		font = new Font("Verdana", Font.BOLD, 18);
    		
        setBackground(new Color(0, 31, 84));
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        // Bienvenida
        JLabel welcome = new JLabel("BIENVENIDO");
        welcome.setForeground(new Color(254, 252, 251));
        welcome.setFont(new Font("Verdana", Font.BOLD, 45));

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(welcome, c);

        // Usuario
        JLabel lblUser = new JLabel("Usuario");
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
        JTextField txtFieldUser = new JTextField(15);
        txtFieldUser.setFont(font);
        txtFieldUser.setBackground(Color.WHITE);

        c.gridx = 1;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(txtFieldUser, c);

        // Error usuario
        JLabel lblErrorUser = new JLabel("Error: este campo es obligatorio");
        lblErrorUser.setFont(new Font("Verdana", Font.PLAIN, 15));
        lblErrorUser.setForeground(Color.red);
        lblErrorUser.setVisible(false);

        c.gridy = 2;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(lblErrorUser, c);

        // Contraseña
        JLabel lblPassword = new JLabel("Contraseña");
        lblPassword.setForeground(new Color(254, 252, 251));
        lblPassword.setFont(font);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        add(lblPassword, c);

        // Campo contraseña
        JPasswordField pwdFieldPassword = new JPasswordField(15);
        pwdFieldPassword.setFont(new Font("Verdana", Font.PLAIN, 15));
        pwdFieldPassword.setBackground(Color.WHITE);

        c.gridx = 1;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(pwdFieldPassword, c);

        // Error contraseña
        JLabel lblErrorPassword = new JLabel("Error: este campo es obligatorio");
        lblErrorPassword.setFont(new Font("Verdana", Font.PLAIN, 15));
        lblErrorPassword.setForeground(Color.red);
        lblErrorPassword.setVisible(false);
        
        c.gridy = 4;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(lblErrorPassword, c);

        // Boton
        JButton btnlogin = new JButton("Iniciar Sesión");
        btnlogin.setFont(new Font("Arial", Font.PLAIN, 15));
        
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        add(btnlogin, c);
        
        JButton btnSingIn = new JButton("Registrarme");
        btnSingIn.setFont(new Font("Arial", Font.PLAIN, 15));
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        
        add(btnSingIn, c);
        
        
        txtFieldUser.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				warningUserLavel(txtFieldUser, lblErrorUser);
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				warningUserLavel(txtFieldUser, lblErrorUser);
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				warningUserLavel(txtFieldUser, lblErrorUser);
				
			}
		});
        
        
        
        
        
        
        
        btnSingIn.addActionListener(e ->{
        	handleRegistration();
        });
        
        
        btnlogin.addActionListener(e -> {

            boolean errorFound = false;

            // Validar usuario
            if (txtFieldUser.getText().trim().isEmpty()) {
                lblErrorUser.setVisible(true);
                errorFound = true;
            } else {
                lblErrorUser.setVisible(false);
            }

            // Validar contraseña
            if (new String(pwdFieldPassword.getPassword()).trim().isEmpty()) {
                lblErrorPassword.setVisible(true);
                errorFound = true;
            } else {
                lblErrorPassword.setVisible(false);
            }

            // Si no hay errores
            if (!errorFound) {
                JOptionPane.showMessageDialog(
                        null,
                        "Sesión iniciada correctamente",
                        "Iniciado",
                        JOptionPane.INFORMATION_MESSAGE
                );
                //handleLogin();
                handleLogin();
            }
        });
    }
    
    
    private void warningUserLavel(JTextField txtUser, JLabel lblErrorUser) {
    	if (txtUser.getText().trim().isEmpty()) {
            lblErrorUser.setVisible(true);
        } else {
            lblErrorUser.setVisible(false);
        }
    }
    
    private void handleLogin() {
		new MainWindow();
		
		window.dispose();
	}
    
    private void handleRegistration() {
    		new RegistrationWindow();
    		
    		window.dispose();
    }
}