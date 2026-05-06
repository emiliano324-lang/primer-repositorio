 package controllers;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import models.User;
import views.LoginView;
import views.MainWindow;
import views.RegistrationWindow;

public class LoginController {

	private LoginView view;
	
	public LoginController(LoginView view) {
	    this.view = view;
	    registerListeners();
	}
	 
	public void registerListeners() {
		
		view.getBtnLogin().addActionListener(e -> handleLogin() );
		view.getBtnSignIn().addActionListener(e -> handleRegistration());
	}

	private boolean validateLogin(User user) {

		view.resetErrorLabels();
	    boolean valid = true;

	    if (user.getName().trim().isEmpty()) {
	        view.showLblErrorUser();
	        valid = false;
	    }

	    if (user.getPassword().trim().isEmpty()) {
	        view.showLblErrorPassword("Error: Este campo es obligatorio");
	        valid = false;
	    }

	    return valid;
	}
	
	private void handleLogin() {
	    User user = new User(view.getUsername(), view.getPassword());

	    if (validateLogin(user)) {

	        if(user.getName().equals("admin") && user.getPassword().equals("1234")) {

	            JOptionPane.showMessageDialog(view.getWindow(),
	                "Se inició la sesión", "Sesión Iniciada",
	                JOptionPane.INFORMATION_MESSAGE);

	            new HomeController(new MainWindow());
	        	SwingUtilities.getWindowAncestor(view).dispose();

	        } else {
	            view.showLblErrorPassword("Error: Datos Incorrectos");
	        }
	    }
	}
 
	private void handleRegistration() {
		new RegistrationController(new RegistrationWindow());
			
		SwingUtilities.getWindowAncestor(view).dispose();	}
}
