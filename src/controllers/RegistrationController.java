package controllers;

import java.awt.TextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

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
import models.User;
import repository.UserRepository;
import views.LoginView;
import views.LoginWindow;
import views.MainWindow;
import views.RegistrationWindow;				

public class RegistrationController {

    private RegistrationWindow view;
    private UserRepository repository;

    // ✅ CONSTRUCTOR CORRECTO
    public RegistrationController(RegistrationWindow view) {
        this.view = view;
        this.repository = new UserRepository();
        initController();
    }

    // ✅ INICIALIZAR LISTENERS
    private void initController() {
        registrationListener();
    }

    private void registrationListener() {

        // NOMBRE
        view.getTxtFieldName().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validateName(); }
            public void removeUpdate(DocumentEvent e) { validateName(); }
            public void changedUpdate(DocumentEvent e) { validateName(); }
        });

        // EMAIL
        view.getTxtFieldEmail().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validateEmail(); }
            public void removeUpdate(DocumentEvent e) { validateEmail(); }
            public void changedUpdate(DocumentEvent e) { validateEmail(); }
        });
        
        // BOTÓN REGISTRARSE
        view.getBtnRegistrate().addActionListener(e -> register());

        // BOTÓN SALIR
        // necesitas getter en la vista si quieres usarlo aquí
    }

    // ================= VALIDACIONES =================

    private boolean validateRegistration(User user) {
    	
    	boolean errorFound = false;
    	
    	// Validar nombre
    	if(user.getName().trim().isEmpty()) {

    		errorFound = true;
    		
    		view.getLblEmptyFieldName().setVisible(true);
    	}
    	
    	// Validar correo (campo vacío)
    	if(user.getEmail().trim().isEmpty()) {
    		
    		errorFound = true;
    		
    		view.getLblEmptyFieldEmail().setVisible(true);
    	
    	// Validar correo (si no contiene @)
    	}else if(!user.getEmail().contains("@")) {
    		
    		errorFound = true;
    		
    		view.getLblUnvalidEmail().setVisible(true);
    	}
    	
    	// Validar contraseña
    	if(user.getPassword().trim().isEmpty()) {
    		errorFound = true;
    		
    		view.getLblEmptyFieldPassword().setVisible(true);
    		
    	}else if(!user.getPassword().isEmpty() && !user.getPassword().equals(user.getConfirmPassword())) {
    		errorFound = true;
    		
    		view.getLblErrorUnequalPasswords().setVisible(true);
    	}
    	
    	return !errorFound;
    }
    
    private void validateName() {
        JTextField txt = view.getTxtFieldName();
        JLabel lbl = view.getLblEmptyFieldName();

        lbl.setVisible(txt.getText().trim().isEmpty());
    }

    private void validateEmail() {
        JTextField txt = view.getTxtFieldEmail();

        JLabel empty = view.getLblEmptyFieldEmail();
        JLabel invalid = view.getLblUnvalidEmail();

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
    
    /*private void validatePassword() {
    	
    	JTextField pwd = view.getPwdPassword();
    	JTextField confirmPwd = view.getPwdConfirmPassword();
    	
    	if(pwd.getText().trim().isEmpty()) {
    		view.getLblEmptyFieldPassword().setVisible(true);
    		
    	}else if(!pwd.getText().equals(confirmPwd.getText())) {
    		view.getLblErrorUnequalPasswords().setVisible(true);;
    	}
    	
    	if (confirmPwd.getText().trim().isEmpty()) {
    		view.getLblEmptyFieldConfirmPassword().setVisible(true);
    	}
    	
    }*/

    // ================= REGISTRO =================

    private void register() {

    	view.resetErrorLabels();
    	
    	User user = new User(view.getName(), view.getEmail(), view.getPassword(), view.getConfirmPassword(), view.getSexo());
    	
    	if(validateRegistration(user)) {
    		
    		try {
    			repository.save(user);
    			JOptionPane.showMessageDialog(view, "Registro exitoso");
    			
    			new HomeController(new MainWindow());
    			view.dispose();
    			
    		}catch(IOException e) {
    			JOptionPane.showMessageDialog(view, e.getMessage());;
    		}
    	}
    }
    // ================= NAVEGACIÓN =================

    private void handleBack() {
        new LoginView();
        view.dispose();
    }

    public void handleClose() {
        int option = JOptionPane.showConfirmDialog(view, "Seguro que quieres salir?");
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
