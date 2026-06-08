package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import models.Player;
import models.User;
import repository.UserRepository;
import tablemodels.UserTableModel;
import utils.Session;
import views.GameMenuView;
import views.GameWindow;
import views.LoginWindow;
import views.MainWindow;
/**
 * Controlador principal de la pantalla de inicio de la aplicación.
 * Gestiona el intercambio de vistas internas, la interaccion 
 * estetica de los menus laterales, la carga del personaje para iniciar el juego y el cierre seguro de la ventana.
 * 
 * @author Hugo 
 * @author Emiliano 
 * @version 1.0
 */
public class HomeController {

	private MainWindow view;
	private UserController userController;
	/**
	 * Constructor del controlador principal de inicio.
	 * Vincula la vista principal y registra los listeners de eventos para los botones de navegacion.
	 * * @param view vista de la interfaz grafica principal.
	 */
	public HomeController(MainWindow view) {
		
		this.view = view;
		registerListeners();
		
	}
	/**
	 * Registra los listeners de eventos para el menu superior,
	 *  los botones de navegacion lateral y el control de cierre de la ventana.
	 */
	public void registerListeners() {
		
		view.mItemExit.addActionListener(e -> handleClose());
		
		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
			}
		});
		
		view.btnHome.addActionListener(e -> {
			view.showView(MainWindow.HOME);
			updateMenuState(MainWindow.HOME);
		});
		 
		view.btnUsers.addActionListener(e -> { 
			showUsers(); 
		});
		
		view.btnPlay.addActionListener(e -> handlePlay());
		
		changeBackgroundListener(view.btnHome);
		changeBackgroundListener(view.btnUsers);
		changeBackgroundListener(view.btnPlay);
		
	}
	
	
	/**
	 * Agrega un efecto visual interactivo a cualquier componente grafico.
	 * Intercambia los colores cuando el puntero del raton entra o sale del area del componente.
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
	 * Inicializa o reutiliza el subcontrolador de usuarios, refresca la lista de la base de datos,
	 * despliega el panel correspondiente y desactiva el boton de navegación del menu actual.
	 */
	private void showUsers() {
		if(userController == null) {
			userController = new UserController(view.usersPanel);
		}
			
		userController.loadUsers();
		
		view.showView(MainWindow.USERS);
		updateMenuState(MainWindow.USERS);
	}
	/**
	 * Actualiza el estado de habilitación de los botones del menu lateral.
	 * Deshabilita el boto que apunta a la vista actualmente visible para evitar redundancias de click.
	 * * @param viewName nombre identificador de la vista que se acaba de mostrar.
	 */
	private void updateMenuState(String viewName) {
		view.btnUsers.setEnabled(!viewName.equals(MainWindow.USERS));
		view.btnHome.setEnabled(!viewName.equals(MainWindow.HOME));
	}
	/**
	 * Carga el personaje asociado al usuario en sesión, lo establece de forma global,
	 * abre la ventana del entorno del juego y destruye de la memoria la ventana principal actual.
	 */
	private void handlePlay() {

		Player player;
		
		try {
			player = new Session().loadCharacter();
			Session.setPlayer(player);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new GameWindow();
		System.out.println("Se abrio la ventana");
		view.dispose();
		System.out.println("Se cerro esta ventana");
	}
	
	/**
	 * Gestiona la confirmación de salida del sistema mediante un cuadro de dialogo.
	 * Si el usuario confirma, lo redirige a la ventana de Login y cierra la ventana actual.
	 */
	private void handleClose() {
		int option = view.confirmExit();

		if (option == JOptionPane.YES_OPTION) {
			new LoginWindow();
			view.dispose();
		}
	}
	/**
	 * Restablece los colores por defecto del componente grafico (fondo blanco y texto negro).
	 * * @param c el componente gráfico a restablecer.
	 * @param defaultButtonColor Color original de fondo para el componente.
	 */
	private void resetBackground(JComponent c, Color defaultButtonColor) {
		c.setBackground(defaultButtonColor);
		c.setForeground(Color.BLACK);
	}
	/**
	 * Aplica un esquema de color de selección al componente grafico.
	 * * @param c E=el componente grafico cuyo fondo se va a alterar.
	 */
	private void changeBackground(JComponent c) {
		c.setBackground(new Color(3,64,120));
		c.setForeground(Color.WHITE);
	}
}