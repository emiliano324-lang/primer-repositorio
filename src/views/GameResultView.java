package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.AppFont;
import utils.CustomJSwing;
/**
 * Vista encargada de mostrar el resultado final de la partida.
 *
 * <p>Esta clase presenta un mensaje indicando si el jugador ha obtenido
 * una victoria o una derrota y proporciona un botón para salir de la
 * pantalla de resultados.</p>
 *
 *
 * @author Hugo 
 * @author Emiliano
 * @version 1.0
 */
public class GameResultView extends JPanel{
	
	private GameWindow window;
	private JLabel lblResult;
	private JButton btnExit;
	
	Image resultBackgrown;
	/**
	 * Crea e inicializa la vista de resultados.
	 *
	 * <p>Carga los recursos graficos necesarios y configura los componentes
	 * visuales de la interfaz.</p>
	 */
	public GameResultView() {
		loadImage();
		initializeComponents();
		
	}
	/**
	 * Inicializa los componentes grAficos de la vista.
	 *
	 * <p>Configura el diseño principal y crea los elementos necesarios
	 * para mostrar el resultado de la partida y las opciones disponibles.</p>
	 */
	private void initializeComponents() {
		setLayout(new BorderLayout());
		showResult();
		createButton();
		
	}
	/**
	 * Crea y configura la etiqueta utilizada para mostrar el resultado
	 * de la partida.
	 *
	 * <p>La etiqueta se posiciona en el centro de la vista y utiliza
	 * la fuente principal definida para los títulos.</p>
	 */
	public void showResult() {

		lblResult = new JLabel("", JLabel.CENTER);

		lblResult.setFont(AppFont.title());

		//lblResult.setForeground(new Color(254, 252, 251));
	
		add(lblResult, BorderLayout.CENTER);
	}
	/**
	 * Obtiene el botón utilizado para salir de la pantalla de resultados.
	 *
	 * @return botón de salida.
	 */
	public JButton getBtnExit() {
		return btnExit;
	}
	
	
	/**
	 * Crea y agrega el botOn de salida a la interfaz.
	 *
	 * <p>El boton se coloca en la parte inferior de la ventana y permite
	 * abandonar la pantalla de resultados.</p>
	 */
	public void createButton() {
		btnExit = CustomJSwing.createMenuButton("Salir");
		
		add(btnExit, BorderLayout.SOUTH);
		
	}
	/**
	 * Crea una imagen desde los recursos del proyecto.
	 * 
	 * <p>La imagen se obtiene desde los recursos del proyecto y se almacena
	 * para ser renderizada posteriormente en la interfaz gráfica.</p>
	 * 
	 *  <p>Si la imagen no puede cargarse, se mostrara un mensaje de error
	 * en la consola.</p>
	 */
	private void loadImage() {
		
		try {
			resultBackgrown = ImageIO.read(getClass().getResource("/img/resultBackgrown1.jpg"));
		} catch (IOException ex) {
			System.out.println("La imagen no existe");
		}
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
		
		g2.drawImage(resultBackgrown, 0, 0, getWidth(), getHeight(), null);
	}
	/**
	 * Actualiza el resultado mostrado al jugador.
	 *
	 * <p>Si el resultado corresponde a una derrota, el texto se muestra
	 * en color rojo. Para cualquier otro resultado, se utiliza el color
	 * predeterminado de la interfaz.</p>
	 *
	 * @param text resultado que se mostrara en pantalla.
	 */
	public void updateResult(String text) {
		if(text.equalsIgnoreCase("DERROTA")) {
			lblResult.setForeground(Color.RED);
		}else {
			lblResult.setForeground(new Color(254, 252, 251));
		}
		
		lblResult.setText(text);
	}
}
