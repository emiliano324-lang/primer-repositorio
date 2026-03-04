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

public class LoginView extends JPanel {

	LoginWindow window;
	Font font;
	
    public LoginView() {
    	
    		this.window = window;
    		font = new Font("Verdana", Font.BOLD, 18);
    		
        setBackground(new Color(0, 31, 84));
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        // Bienvenida
        JLabel saludo = new JLabel("BIENVENIDO");
        saludo.setForeground(new Color(254, 252, 251));
        saludo.setFont(new Font("Verdana", Font.BOLD, 45));

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(saludo, c);

        // Usuario
        JLabel etiquetaUsuario = new JLabel("Usuario");
        etiquetaUsuario.setForeground(new Color(254, 252, 251));
        etiquetaUsuario.setFont(font);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(etiquetaUsuario, c);

        // Campo usuario
        JTextField usuario = new JTextField(15);
        usuario.setFont(font);
        usuario.setBackground(Color.WHITE);

        c.gridx = 1;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(usuario, c);

        // Error usuario
        JLabel errorCampoUsuario = new JLabel("Error: este campo es obligatorio");
        errorCampoUsuario.setFont(new Font("Verdana", Font.PLAIN, 15));
        errorCampoUsuario.setForeground(Color.red);
        errorCampoUsuario.setVisible(false);

        c.gridy = 2;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(errorCampoUsuario, c);

        // Contraseña
        JLabel etiquetaContrasena = new JLabel("Contraseña");
        etiquetaContrasena.setForeground(new Color(254, 252, 251));
        etiquetaContrasena.setFont(font);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        add(etiquetaContrasena, c);

        // Campo contraseña
        JPasswordField contrasena = new JPasswordField(15);
        contrasena.setFont(new Font("Verdana", Font.PLAIN, 15));
        contrasena.setBackground(Color.WHITE);

        c.gridx = 1;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(contrasena, c);

        // Error contraseña
        JLabel errorCampoContrasena = new JLabel("Error: este campo es obligatorio");
        errorCampoContrasena.setFont(new Font("Verdana", Font.PLAIN, 15));
        errorCampoContrasena.setForeground(Color.red);
        errorCampoContrasena.setVisible(false);
        
        c.gridy = 4;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(errorCampoContrasena, c);

        // Boton
        JButton iniciarSesion = new JButton("Iniciar Sesión");
        iniciarSesion.setFont(new Font("Arial", Font.PLAIN, 15));
        
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        add(iniciarSesion, c);
        
        iniciarSesion.addActionListener(e -> {

            boolean hayError = false;

            // Validar usuario
            if (usuario.getText().trim().isEmpty()) {
                errorCampoUsuario.setVisible(true);
                hayError = true;
            } else {
                errorCampoUsuario.setVisible(false);
            }

            // Validar contraseña
            if (new String(contrasena.getPassword()).trim().isEmpty()) {
                errorCampoContrasena.setVisible(true);
                hayError = true;
            } else {
                errorCampoContrasena.setVisible(false);
            }

            // Si no hay errores
            if (!hayError) {
                JOptionPane.showMessageDialog(
                        null,
                        "Sesión iniciada correctamente",
                        "Iniciado",
                        JOptionPane.INFORMATION_MESSAGE
                );
                //handleLogin();
                handleRegistration();
            }
        });
    }
    
    private void handleLogin() {
		new MainWindow();
		
		window.dispose();
	}
    
    private void handleRegistration() {
    		new FormularioRegistro();
    		
    		window.dispose();
    }
}