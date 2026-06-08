package views;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import utils.CustomJSwing;

/**
 * Vista de créditos del juego.
 *
 * <p>Esta clase muestra la pantalla de créditos mediante una imagen
 * de fondo y proporciona un botón para regresar al menu anterior.</p>
 *
 * @author Hugo  
 * @author Emiliano
 * @version 1.0 
 */

public class GameCreditsView extends JPanel{
	
	private JButton back;
	private Image credits;
	
	
	/**
	 * Obtiene el boton utilizado para regresar a la pantalla anterior.
	 *
	 * @return botón de regreso.
	 */
	
	public JButton getBack() {
		return back;
	}
	/**
	 * Crea e inicializa la vista de creditos.
	 *
	 * <p>Configura el diseño del panel, carga la imagen de creditos
	 * y añade el boton para regresar al menu anterior.</p>
	 */
	public GameCreditsView() {
		setLayout(new BorderLayout());
		
		loadImage();
		
		back = CustomJSwing.createJButton("Regresar");
		add(back, BorderLayout.SOUTH);
		
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
			credits = ImageIO.read(getClass().getResource("/img/creditos.png"));
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
	
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(credits, 0, 0, getWidth(), getHeight(), null);
	}
}
