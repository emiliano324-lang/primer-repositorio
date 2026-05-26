package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.Player;
import models.User;
import repository.UserRepository;
import views.LoginWindow;
import views.MainWindow;
import views.RegistrationWindow;				

public class RegistrationController {

    private RegistrationWindow view;
    private UserRepository repository;

    public RegistrationController(RegistrationWindow view) {
        this.view = view;
        this.repository = new UserRepository();
        registrationListener();
    }

    private void registrationListener() {

        // NOMBRE
        view.getTxtFieldName().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
            	validateName(); 
            }
            public void removeUpdate(DocumentEvent e) {
            	validateName(); 
            }
            public void changedUpdate(DocumentEvent e) {
            	validateName(); 
            }
        });

        // EMAIL
        view.getTxtFieldEmail().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { 
            validateEmail(); 
            }
            public void removeUpdate(DocumentEvent e) { 
            	validateEmail(); 
            }
            public void changedUpdate(DocumentEvent e) { 
            	validateEmail(); 
           }
        });
        
        view.getBtnSelectImage().addActionListener(e -> view.chooseImage());
        
        view.getBtnRegistrate().addActionListener(e -> register());

        view.getBtnExit().addActionListener(e -> handleBack());
        
    }

    // ================= VALIDACIONES =================

    private boolean validateRegistration(User user) {
    	
		boolean errorFound = false;

		// Validar nombre
		if (user.getName().trim().isEmpty()) {

			errorFound = true;

			view.getLblErrorFieldName().setText("El nombre es obligatorio");
			view.getLblErrorFieldName().setVisible(true);
		}

		// Validar correo (campo vacío)
		if (user.getEmail().trim().isEmpty()) {

			errorFound = true;

			view.getLblErrorFieldEmail().setText("El correo es obligatorio");
			view.getLblErrorFieldEmail().setVisible(true);

			// Validar correo (si no contiene @)
		} else if (!user.getEmail().contains("@")) {

			errorFound = true;

			view.getLblErrorFieldEmail().setText("El correo no es válido");
			view.getLblErrorFieldEmail().setVisible(true);
		}

		// Validar contraseña
		if (user.getPassword().trim().isEmpty()) {
			errorFound = true;

			view.getLblErrorFieldPassword().setText("La contraseña es obligatoria");
			view.getLblErrorFieldPassword().setVisible(true);

		} else if (!user.getPassword().isEmpty() && !user.getPassword().equals(view.getConfirmPassword())) {
			errorFound = true;

			view.getLblErrorFieldConfirmPassword().setText("Las contraseñas no coinciden");
			view.getLblErrorFieldConfirmPassword().setVisible(true);
		}

		return !errorFound;
    }
    
    private void validateName() {
        JTextField txt = view.getTxtFieldName();
        JLabel error = view.getLblErrorFieldName();
        
        if(txt.getText().trim().isEmpty()) {
        	error.setText("El nombre es obligatorio");
        	error.setVisible(true);
        }else {
        	error.setVisible(false);
        }
    }

    private void validateEmail() {
        JTextField txt = view.getTxtFieldEmail();
        JLabel error = view.getLblErrorFieldEmail();

        if (txt.getText().trim().isEmpty()) {
        	error.setText("El correo es obligatorio");
            error.setVisible(true);
        } else if (!txt.getText().contains("@")) {
        	error.setText("El correo no es válido");
            error.setVisible(false);
        } else {
            error.setVisible(false);
        }
    }

    // ================= REGISTRO =================

    private void register() {

	    	view.resetErrorLabels();
	    	
	    	User user = new User(view.getName(), view.getEmail(), view.getPassword(), view.getSex());
	    	String imagePathString = saveImage();
	    	
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
    

    
    private String saveImage() {
    	
	    	try {
	    		String original = view.getSelectedImagePath();
	    		
	    		if(original == null)
	    			return null;
	    		
	    		File source = new File(original);
	    		
	    		String extension = original.substring(original.lastIndexOf("."));
	    		
	    		String newName = UUID.randomUUID() + extension;
	    		
	    		String folder = "." + File.separator + "images";
	    		
	    		File directory = new File(folder);
	    		
	    		if(!directory.exists()) {
	    			directory.mkdir();
	    		}
	    		
	    		Path destination = Paths.get(folder, newName);
	    		
	    		Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
	    		
	    		return destination.toString();
	    		
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    		return null;
	    	}
    }
    
    private void handleBack() {
        new LoginWindow();
        view.dispose();
    }

    public void handleClose() {
        int option = JOptionPane.showConfirmDialog(view, "Seguro que quieres salir?");
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
