package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import models.User;
import utils.Session;
/**
 * Ventana principal del sistema.
 *
 * <p>Esta clase proporciona la interfaz principal de la aplicacion,
 * permitiendo la navegación entre diferentes vistas.
 * También incluye una barra de menu y una barra
 * de navegación para acceder a las funcionalidades disponibles.</p>
 *
 *
 * @author Hugo 
 * @author Emiliano
 * 
 * @version 1.0
 */
public class MainWindow extends JFrame {
	/**
	 * Identificador de la vista principal.
	 */
	public static final String HOME = "HOME";
	/**
	 * Identificador de la vista de usuarios.
	 */
	public static final String USERS = "USERS";
	
	public JMenuItem mItemExit;
	public JButton btnUsers;
	public JButton btnHome;
	public UsersView usersPanel;
	public JButton btnPlay;
	
	private CardLayout cardLayout;
	private JPanel container;
	private User user;
	/**
	 * Crea e inicializa la ventana principal del sistema.
	 *
	 * <p>Configura las propiedades basicas de la ventana, crea la barra
	 * de menu, la barra de navegación y las vistas disponibles para el usuario.</p>
	 */
	public MainWindow() {
		setSize(500, 500);
		setTitle("Ventana Principal");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setMenu();

		JPanel panel = new JPanel();
		add(panel);
		
		createNavbar();
		createViews();
		
		setVisible(true);
	}
	/**
	 * Crea la barra de navegación principal.
	 *
	 * <p>La barra contiene botones que permiten acceder a las distintas
	 * secciones de la aplicación, como inicio, usuarios y juego.</p>
	 */
	public void createNavbar() {
		JPanel navbar = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		navbar.setBackground(new Color(10,17,40));
		
		btnHome = new JButton("INICIO");
		btnUsers = new JButton("USUARIOS");
		btnPlay = new JButton("JUGAR");
		
		btnHome.setBackground(Color.WHITE);
		btnUsers.setBackground(Color.WHITE);
		btnPlay.setBackground(Color.WHITE);
		
		navbar.add(btnHome);
		navbar.add(btnUsers);
		navbar.add(btnPlay);
		
		add(navbar, BorderLayout.NORTH);
	}
	/**
	 * Crea y registra las vistas administradas.
	 *
	 * <p>Inicializa la vista de inicio y la vista de usuarios,
	 * agregándolas al contenedor principal para permitir la navegación
	 * entre ellas.</p>
	 */
	private void createViews() {
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		
		JPanel homePanel = new JPanel();
		homePanel.setBackground(new Color(0, 31, 84));
		
		JLabel lblWelcome = new JLabel("Bienvenido al Sistema");
		
		lblWelcome.setForeground(new Color(254, 252, 251));
		lblWelcome.setFont(new Font("Verdana", Font.BOLD, 30));
		
		homePanel.add(lblWelcome);
		
		usersPanel = new UsersView();
		
		container.add(homePanel, HOME);
		container.add(usersPanel, USERS);
		
		add(container, BorderLayout.CENTER);
	}
	/**
	 * Muestra una vista especifica dentro del contenedor principal.
	 *
	 * @param view identificador de la vista que se desea mostrar.
	 */
	public void showView(String view) {
		cardLayout.show(container, view);
	}
	/**
	 * Crea y configura la barra de menu de la aplicación.
	 *
	 * <p>Incluye opciones basicas como abrir, guardar y salir,
	 * ademas de menus adicionales para funcionalidades complementarias.</p>
	 */
	public void setMenu() {
	    JMenuBar mb = new JMenuBar();
	    setJMenuBar(mb);

	    JMenu menuFile = new JMenu("File");
	    menuFile.setMnemonic(KeyEvent.VK_F);
	    mb.add(menuFile);

	    JMenuItem mItemOpen = new JMenuItem("Open");
	    mItemOpen.setMnemonic(KeyEvent.VK_O);
	    menuFile.add(mItemOpen);

	    JMenuItem mItemSave = new JMenuItem("Save");
	    mItemSave.setMnemonic(KeyEvent.VK_S);
	    menuFile.add(mItemSave);

	    menuFile.addSeparator();
	    
	    mItemExit = new JMenuItem("Exit");
	    mItemExit.setMnemonic(KeyEvent.VK_E);
	    menuFile.add(mItemExit);

	    JMenu menuOtherOption = new JMenu("Other Option");
	    menuOtherOption.setMnemonic(KeyEvent.VK_O);
	    mb.add(menuOtherOption);

	    JMenu menuOption1 = new JMenu("Option 1");
	    menuOtherOption.add(menuOption1);

	    JMenuItem mItemOption3 = new JMenuItem("Option 3");
	    menuOption1.add(mItemOption3);

	    JMenuItem mItemOption2 = new JMenuItem("Option 2");
	    menuOtherOption.add(mItemOption2);
	}
	/**
	 * Muestra un cuadro de dialogo para confirmar la salida de la aplicacion.
	 *
	 * <p>Advierte al usuario que los datos actuales podrian perderse
	 * antes de abandonar la ventana.</p>
	 *
	 * @return valor seleccionado por el usuario.
	 */
	public int confirmExit() {
	    return JOptionPane.showConfirmDialog(
	        this,
	        "¿Seguro que deseas regresar? Se perderán todos los datos",
	        "¿Seguro?",
	        JOptionPane.YES_NO_OPTION
	    );
	}
}