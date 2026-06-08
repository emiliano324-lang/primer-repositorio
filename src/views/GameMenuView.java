package views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.CustomJSwing;
/**
 * Vista principal del menu del juego.
 *
 * <p>Esta clase representa la pantalla inicial de la aplicación,
 * permitiendo al usuario iniciar una partida, acceder al arbol
 * de mejoras, visualizar los créditos o salir del juego.</p>
 *
 *
 * @author Hugo 
 *@author Emiliano
 * @version 1.0
 */
public class GameMenuView extends JPanel{
	
	private GameWindow window;
	private JLabel lblTitle;
	private JButton btnPlay;
	private JButton btnSkillTree;
	private JButton btnCredits;
	private JButton btnExit;
	private JLabel lblAvailable;
	//private JButton btnSettings;
	
	Image mainMenuBackground;
	/**
	 * Crea e inicializa la vista del menu principal.
	 *
	 * <p>Carga los recursos graficos necesarios y configura todos los
	 * componentes de la interfaz.</p>
	 */
	public GameMenuView() {
		loadImage();
		initializeComponents();
	}
	/**
	 * Obtiene la ventana principal asociada al menu.
	 *
	 * @return ventana principal del juego.
	 */
	public GameWindow getWindow() {
		return window;
	}
	/**
	 * Obtiene el boton para iniciar una partida.
	 *
	 * @return boton de jugar.
	 */
	public JButton getBtnPlay() {
		return btnPlay;
	}
	/**
	 * Obtiene el boton para acceder al árbol de mejoras.
	 *
	 * @return boton de mejoras.
	 */
	public JButton getBtnSkillTree() {
		return btnSkillTree;
	}
	/**
	 * Obtiene el boton para visualizar los créditos.
	 *
	 * @return botón de creditos.
	 */
	public JButton getBtnCredits() {
		return btnCredits;
	}
	/**
	 * Obtiene el botun para salir del juego.
	 *
	 * @return boton de salida.
	 */
	public JButton getBtnExit() {
		return btnExit;
	}
	/**
	 * Inicializa los componentes graficos del menu principal.
	 *
	 * <p>Configura el diseño de la vista y crea los elementos visuales
	 * necesarios, como el título y los botones del menú.</p>
	 */
	private void initializeComponents() {
		setLayout(new GridBagLayout());
		
		createIcon();
		createButtons();
	}
	/**
	 * Crea y posiciona los botones del menu principal.
	 *
	 * <p>Los botones permiten acceder a las principales funcionalidades
	 * del juego, como iniciar una partida, gestionar mejoras,
	 * o salir de la aplicación.</p>
	 */
	private void createButtons() {
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.weightx = 1.0;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 20, 10, 20);
		c.fill = GridBagConstraints.NONE;

		c.gridy = 1;
		btnPlay = CustomJSwing.createMenuButton("JUGAR");
		add(btnPlay, c);
		
		c.gridy = 2;
		btnSkillTree = CustomJSwing.createMenuButton("MEJORAS");
		add(btnSkillTree, c);

		c.gridy = 4;
		btnCredits = CustomJSwing.createMenuButton("CREDITOS");
		add(btnCredits, c);
		
		c.gridy = 5;
		btnExit = CustomJSwing.createMenuButton("SALIR");
		add(btnExit, c);
	}
	/**
	 * Crea y posiciona el título grafico del juego.
	 *
	 * <p>La imagen del titulo se carga desde los recursos del proyecto
	 * y se muestra en la parte superior del menú principal.</p>
	 */
	private void createIcon() {
		GridBagConstraints c = new GridBagConstraints();

	    c.gridx = 0;
	    c.gridy = 0;

	    c.weightx = 1;
	    c.weighty = 1;
	    
	    c.anchor = GridBagConstraints.NORTHWEST;

	    c.insets = new Insets(10, 20, 40, 0);

	    ImageIcon icon = loadIcon("/img/TECHNARYAN TITULO.png", 900, 193);

	    Image img = icon.getImage().getScaledInstance(900, 193, Image.SCALE_SMOOTH);
 
	    lblTitle = new JLabel(new ImageIcon(img));

	    add(lblTitle, c);
	}
	/**
	 *  Dibuja los componentes graficos personalizados de la vista.
	 * 
	 * Este método se encarga de renderizar la imagen de fondo del combate
	 * ajustándola al tamaño actual del panel.</p>
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(mainMenuBackground, 0, 0, getWidth(), getHeight(), null);
	}
	/**
	 * Crea una imagen desde los recursos del proyecto.
	 * 
	 * <p>La imagen se obtiene desde los recursos del proyecto y se almacena
	 * para ser renderizada posteriormente en la interfaz grafica.</p>
	 * 
	 *  <p>Si la imagen no puede cargarse, se mostrara un mensaje de error
	 * en la consola.</p>
	 */
	private void loadImage() {
		
		try {
			mainMenuBackground = ImageIO.read(getClass().getResource("/img/fondoPrincipal.png"));
		} catch (IOException ex) {
			System.out.println("La imagen no existe");
		}
	}
	
	/**
	 * Carga una imagen desde los recursos del proyecto.
	 * 
	 *  <p>La imagen cargada se utiliza para representar personajes,
	 * animaciones u otros elementos gráficos de la interfaz.</p>
	 * 
	 * @param path ruta de la imagen	
	 * @param w anchura de la imagen
	 * @param h altura de la imagen
	 * @return ImageIcon con la imagen deseada o null si ocurre un error.
	 */
	private ImageIcon loadIcon(String path, int w, int h) {

		try {
			Image icon = ImageIO.read(getClass().getResource(path));
			icon = icon.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			return new ImageIcon(icon);
		} catch (Exception ex) {
			System.out.println("No está la imagen del ícono");
		}

		return null;
	}
}
	
	
	
