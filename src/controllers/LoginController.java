package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import models.Player;
import models.User;
import repository.CharacterRepository;
import repository.LoginRepository;
import utils.Session;
import views.GameWindow;
import views.LoginView;
import views.MainWindow;
import views.RegistrationWindow;

public class LoginController {

	private LoginView view;
	private LoginRepository repository;

	public LoginController(LoginView view) {
		repository = new LoginRepository();
		this.view = view;
		registerListeners();
	}

	public void registerListeners() {

		view.getBtnLogin().addActionListener(e -> handleLogin());
		view.getBtnSignIn().addActionListener(e -> handleRegistration());

		changeBackgroundListener(view.getBtnLogin());
		changeBackgroundListener(view.getBtnSignIn());
	}

	public void changeBackgroundListener(JComponent c) {

		c.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				changeBackground(c);
			}

			public void mouseExited(MouseEvent e) {
				resetBackground(c, Color.WHITE);
			}
		});
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

		if (!validateLogin(new User(view.getUsername(), view.getPassword()))) {
			return;
		}

		User user = repository.login(view.getUsername(), view.getPassword());

		if (user == null) {
			view.showLblErrorPassword("Credenciales incorrectas");
			return;
		}

		CharacterRepository characterRepo = new CharacterRepository();

		try {
			Player player = characterRepo.loadPlayer(user.getId());
			
			System.out.println(user);
			System.out.println(player);


			user.setPlayer(player);

			System.out.println(user.getPlayer());
			
			Session.login(user);
			JOptionPane.showMessageDialog(view.getWindow(), "Se inició la sesión", "Sesión iniciada",
					JOptionPane.INFORMATION_MESSAGE);

			
			
			
			if (Session.getRole().name().equals("ADMIN")) {
				new HomeController(new MainWindow());

			} else {
				new GameWindow();
			}

			view.getWindow().dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void handleRegistration() {
		new RegistrationController(new RegistrationWindow());

		SwingUtilities.getWindowAncestor(view).dispose();
	}

	private void resetBackground(JComponent c, Color defaultButtonColor) {
		c.setBackground(defaultButtonColor);
		c.setForeground(Color.BLACK);
	}

	private void changeBackground(JComponent c) {
		c.setBackground(new Color(3, 64, 120));
		c.setForeground(Color.WHITE);
	}
}
