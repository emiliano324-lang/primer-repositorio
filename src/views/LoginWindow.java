package views;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controllers.LoginController;
/**
 * Ventana principal del sistema de inicio de sesión.
 *
 * <p>Esta clase crea y configura la ventana que contiene la interfaz
 * de autenticación del usuario. Además, inicializa la vista y el
 * controlador responsables del proceso de inicio de sesión.</p>
 *
 *
 * @author Hugo 
 * @author Emiliano
 * @version 1.0
 */
public class LoginWindow extends JFrame { 
	
	private LoginView view;
	/**
	 * Obtiene la vista asociada a la ventana de inicio de sesion.
	 *
	 * @return vista de inicio de sesion.
	 */
	public LoginView getView() {
		return view;
	}
	
	/**
	 * Crea e inicializa la ventana de inicio de sesion.
	 *
	 * <p>Configura el tamaño, titulo, icono, cursor personalizado y demás
	 * propiedades de la ventana. También crea la vista de autenticacion,
	 * la agrega al contenedor principal e inicializa el controlador
	 * encargado de gestionar la lógica del inicio de sesión.</p>
	 */
	public LoginWindow() {
		
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Iniciar Sesión");
		setLocationRelativeTo(null);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/logo_uabcs.png");
		setIconImage(icono);

		ImageIcon iconoCursor = new ImageIcon("src/img/cursor.png");
		Cursor miCursor = tk.createCustomCursor(iconoCursor.getImage(), new Point(0, 0), "Mi Cursor");
		setCursor(miCursor);

		view = new LoginView(this);
		add(view);

		new LoginController(view);
		
		setVisible(true);
	}
	
}