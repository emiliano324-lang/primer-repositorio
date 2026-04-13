package controllers;

import javax.swing.JOptionPane;

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

		view.getBtnSignIn().addActionListener(e ->{ handleRegistration(); });
		
		/*view.getTxtFieldUser().getDocument().addDocumentListener(new DocumentListener() {

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
		}*/
	}

	private boolean validateLogin(User user) throws InvalidUserException, InvalidPasswordException {

		view.resetErrorLabels();
		
		boolean errorFound = false;

		// Validar usuario
		if (user.getName().trim().isEmpty()) {
			errorFound = true;
			
			view.showLblErrorUser();

			throw new InvalidUserException("El usuario no coincide");
			
		} else {
			view.getLblErrorUser().setVisible(false);
		}

		// Validar contraseña
		if (user.getPassword().trim().isEmpty()) {
			errorFound = true;
			
			view.showLblErrorPassword("Error: Este campo es obligatorio");
			
			throw new InvalidPasswordException("La contraseña no coincide");
			
		} else {
			view.getLblErrorPassword().setVisible(false);
		}

		return !errorFound;
	}

	private void handleLogin() {
		User user = new User(view.getUsername(), view.getPassword());
		
		try {
			if(validateLogin(user)) {
				JOptionPane.showMessageDialog(view.getWindow(), "Se inició la sesión", "Sesión Iniciada",
						JOptionPane.INFORMATION_MESSAGE);
				
				new MainWindow();
				
				view.getWindow().dispose();
			}
		} catch (InvalidUserException | InvalidPasswordException ex) {
			view.showLblErrorPassword("Error: Datos Incorrectos");
		}
		
	}

	private void handleRegistration() {
		new RegistrationWindow();

		view.getWindow().dispose();
	}
}
