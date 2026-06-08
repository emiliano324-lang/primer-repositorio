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
/**
 * Controlador encargado de gestionar el proceso de autenticación e inicio de sesión de los usuarios.
 * Valida los campos de texto, consulta las credenciales con el repositorio, inicializa los datos 
 * de la sesión global del usuario y redirige a la ventana correspondiente según su rol.
 * @author Hugo 
 * @author Emiliano 
 * @version 1.0
 */
public class LoginController {

	private LoginView view;
	private LoginRepository repository;
	/**
	 * Constructor del controlador de inicio de sesion.
	 * Inicializa el repositorio de autenticación, asigna la vista y activa los listeners de eventos.
	 * * @param view Vista de la interfaz gráfica del Login.
	 */
	public LoginController(LoginView view) {
		repository = new LoginRepository();
		this.view = view;
		registerListeners();
	}

	/**
	 * Registra los listeners de eventos para los botones de inicio de sesion, registro de nuevos usuarios y efectos visuales.
	 */
	public void registerListeners() {

		view.getBtnLogin().addActionListener(e -> handleLogin());
		view.getBtnSignIn().addActionListener(e -> handleRegistration());

		changeBackgroundListener(view.getBtnLogin());
		changeBackgroundListener(view.getBtnSignIn());
	}
	/**
	 * Agrega un efecto visual interactivo a los componentes de la interfaz.
	 * Intercambia de manera dinamica los colores de fondo y fuente cuando el puntero entra o sale.
	 * * @param c el componente grafico al que se le aplicara el efecto.
	 */
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
	/**
	 * Valida de manera superficial que los campos de correo y contraseña no se encuentren vacios.
	 * Muestra etiquetas de error visuales en la vista si alguna regla no se cumple.
	 * * @param user Objeto temporal que contiene las entradas capturadas en texto plano.
	 * @return true si ambos campos contienen texto valido; false si alguno está vacío.
	 */
	private boolean validateLogin(User user) {
		
		view.resetErrorLabels();
		boolean valid = true;

		if (user.getEmail().trim().isEmpty()) {
			view.showLblErrorUser();
			valid = false;
		}

		if (user.getPassword().trim().isEmpty()) {
			view.showLblErrorPassword("Error: Este campo es obligatorio");
			valid = false;

		}

		return valid;
	}
	/**
	 * Procesa el flujo principal para iniciar sesión en la aplicación.
	 * <p>Recupera y valida las entradas de texto. Si son validas, delega al repositorio la verificacion 
	 * criptografica. En caso de exito, carga el personaje Player asociado, construye la sesion global, 
	 * y evalúa el rol de acceso asignado: si es Admin despliega el Dashboard principal MainWindow, 
	 * de lo contrario, redirige al entorno del juego GameWindow. Al finalizar destruye la pantalla de login.</p>
	 */
	private void handleLogin() {

		if (!validateLogin(new User(view.getEmail(), view.getPassword()))) {
			return;
		}
		User user = repository.login(view.getEmail(), view.getPassword());

		if (user == null) {
			view.showLblErrorPassword("Credenciales incorrectas");
			return;
		}

		CharacterRepository characterRepo = new CharacterRepository();

		try {
			Player player = characterRepo.loadPlayer(user.getId());
			
			user.setPlayer(player);

			Session.setPlayer(player);
			
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
	/**
	 * Redirige al usuario hacia la pantalla de registro del sistema.
	 * Inicializa el controlador de registro y destruye la ventana de login actual.
	 */
	private void handleRegistration() {
		new RegistrationController(new RegistrationWindow());

		SwingUtilities.getWindowAncestor(view).dispose();
	}
	/**
	 * Restablece los colores por defecto del componente grafico.
	 * * @param c el componente gráfico  a restablecer.
	 * @param defaultButtonColor Color original de fondo para el componente.
	 */
	private void resetBackground(JComponent c, Color defaultButtonColor) {
		c.setBackground(defaultButtonColor);
		c.setForeground(Color.BLACK);
	}
	/**
	 * Restablece los colores por defecto del componente gráfico .
	 * * @param c el componente gráfico a restablecer.
	 * @param defaultButtonColor Color original de fondo para el componente.
	 */
	private void changeBackground(JComponent c) {
		c.setBackground(new Color(3, 64, 120));
		c.setForeground(Color.WHITE);
	}
}
