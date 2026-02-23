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

public class GridBagPanel extends JPanel {

    public GridBagPanel() {

        setBackground(new Color(0, 31, 84));
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

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
        etiquetaUsuario.setFont(new Font("Verdana", Font.BOLD, 18));

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(etiquetaUsuario, c);

        // Campo usuario
        JTextField usuario = new JTextField(15);
        usuario.setFont(new Font("Verdana", Font.PLAIN, 15));
        usuario.setBackground(Color.WHITE);

        c.gridx = 1;
        c.weightx = 1.0;  // 👈 importante
        c.fill = GridBagConstraints.HORIZONTAL; // 👈 importante
        add(usuario, c);

        // Error usuario
        JLabel errorCampoUsuario = new JLabel("!");
        errorCampoUsuario.setFont(new Font("Verdana", Font.PLAIN, 30));
        errorCampoUsuario.setForeground(Color.red);

        c.gridx = 2;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(errorCampoUsuario, c);

        // Contraseña
        JLabel etiquetaContrasena = new JLabel("Contraseña");
        etiquetaContrasena.setForeground(new Color(254, 252, 251));
        etiquetaContrasena.setFont(new Font("Verdana", Font.BOLD, 18));

        c.gridx = 0;
        c.gridy = 2;
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
        JLabel errorCampoContrasena = new JLabel("!");
        errorCampoContrasena.setFont(new Font("Verdana", Font.PLAIN, 30));
        errorCampoContrasena.setForeground(Color.red);

        c.gridx = 2;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        add(errorCampoContrasena, c);

        // Boton
        JButton iniciarSesion = new JButton("Iniciar Sesión");
        iniciarSesion.setFont(new Font("Arial", Font.PLAIN, 15));

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        add(iniciarSesion, c);
    }
}