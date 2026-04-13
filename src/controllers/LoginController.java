package controllers;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;

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
		
		view.getBtnLogin().addActionListener(e -> {

        	try {
        		if(validateLogin(view.getTxtFieldUser(),view.getPwdFieldPassword(), view.getLblErrorUser(), view.getLblErrorPassword())) {
        			handleLogin();
        		}
        	}catch (InvalidUserException | InvalidPasswordException ex){
        		
        	}
        });
		
		view.getBtnSignIn().addActionListener(e ->{ 
			handleRegistration();
		});
		
		view.getTxtFieldUser().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				warningUserLabel(view.getTxtFieldUser(), view.getLblErrorUser());

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				warningUserLabel(view.getTxtFieldUser(), view.getLblErrorUser());

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				warningUserLabel(view.getTxtFieldUser(), view.getLblErrorUser());

			}
		});
	}
		
	private void warningUserLabel(JTextField txtUser, JLabel lblErrorUser) {
		if (txtUser.getText().trim().isEmpty()) {
			lblErrorUser.setVisible(true);
		} else {
			lblErrorUser.setVisible(false);
		}
	}

	private boolean validateLogin(JTextField txtFieldUser, JPasswordField pwdFieldPassword, JLabel lblErrorUser,
			JLabel lblErrorPassword) throws InvalidUserException, InvalidPasswordException {

		boolean errorFound = false;

		// Validar usuario
		if (txtFieldUser.getText().trim().isEmpty()) {
			errorFound = true;
			lblErrorUser.setVisible(true);

			throw new InvalidUserException("El usuario no coincide");
		} else {
			lblErrorUser.setVisible(false);
		}

		// Validar contraseña
		if (new String(pwdFieldPassword.getPassword()).trim().isEmpty()) {
			errorFound = true;
			lblErrorPassword.setVisible(true);
			throw new InvalidPasswordException("El usuario o la contraseña no coincide");
		} else {
			lblErrorPassword.setVisible(false);
		}

		return !errorFound;
	}

	private void handleLogin() {
		new MainWindow();

		view.getWindow().dispose();
	}

	private void handleRegistration() {
		new RegistrationWindow();

		view.getWindow().dispose();
	}
}
