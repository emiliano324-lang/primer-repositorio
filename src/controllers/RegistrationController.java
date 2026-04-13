package controllers;

import java.awt.TextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import views.LoginView;
import views.LoginWindow;
import views.MainWindow;
import views.RegistrationWindow;				

public class RegistrationController {

    private RegistrationWindow registration;

    // ✅ CONSTRUCTOR CORRECTO
    public RegistrationController(RegistrationWindow registration) {
        this.registration = registration;
        initController();
    }

    // ✅ INICIALIZAR LISTENERS
    private void initController() {
        registrationListener();
    }

    private void registrationListener() {

        // NOMBRE
        registration.getTxtFieldName().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validateName(); }
            public void removeUpdate(DocumentEvent e) { validateName(); }
            public void changedUpdate(DocumentEvent e) { validateName(); }
        });

        // EMAIL
        registration.getTxtFieldEmail().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validateEmail(); }
            public void removeUpdate(DocumentEvent e) { validateEmail(); }
            public void changedUpdate(DocumentEvent e) { validateEmail(); }
        });

        // BOTÓN REGISTRARSE
        registration.getBtnRegistrate().addActionListener(e -> register());

        // BOTÓN SALIR
        // necesitas getter en la vista si quieres usarlo aquí
    }

    // ================= VALIDACIONES =================

    private void validateName() {
        JTextField txt = registration.getTxtFieldName();
        JLabel lbl = registration.getLblEmptyFieldName();

        lbl.setVisible(txt.getText().trim().isEmpty());
    }

    private void validateEmail() {
        JTextField txt = registration.getTxtFieldEmail();

        JLabel empty = registration.getLblEmptyFieldEmail();
        JLabel invalid = registration.getLblUnvalidEmail();

        if (txt.getText().trim().isEmpty()) {
            empty.setVisible(true);
            invalid.setVisible(false);
        } else if (!txt.getText().contains("@")) {
            empty.setVisible(false);
            invalid.setVisible(true);
        } else {
            empty.setVisible(false);
            invalid.setVisible(false);
        }
    }

    // ================= REGISTRO =================

    private void register() {

        validateName();
        validateEmail();

        boolean error =
                registration.getLblEmptyFieldName().isVisible() ||
                registration.getLblEmptyFieldEmail().isVisible() ||
                registration.getLblUnvalidEmail().isVisible();

        if (!error) {
            JOptionPane.showMessageDialog(registration, "Registro exitoso");
            handleBack();
        }
    }

    // ================= NAVEGACIÓN =================

    private void handleBack() {
        new LoginView();
        registration.dispose();
    }

    public void handleClose() {
        int option = JOptionPane.showConfirmDialog(registration, "Seguro que quieres salir?");
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
