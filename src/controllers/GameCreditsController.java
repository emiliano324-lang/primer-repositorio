package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import utils.ScreenManager;
import views.GameCreditsView;
//import views.GameUpgradeTreeView;
/**
 * Controlador encargado de gestionar la logica y los eventos de la vista de creditos.
 * Administra la navegación de regreso al menu principal y los efectos visuales de los botones.
 * * @author Hugo 
 * @author Emiliano 
 * @version 1.0
 */
public class GameCreditsController {

	private GameCreditsView view;
	/**
	 * Constructor del controlador de creditos.
	 * Asigna la vista correspondiente y activa los listeners de eventos.
	 * * @param view Vista de la interfaz gráfica de los créditos.
	 */
	public GameCreditsController(GameCreditsView view) {

		this.view = view;

		registerListeners();
	}
	
	/**
	 * Registra los listeners para los componentes interactivos de la vista de créditos.
	 */
	public void registerListeners() {
		view.getBack().addActionListener(e -> ScreenManager.showPanel("MENU"));
		
		mouseListeners(view.getBack());
	}
	/**
	 * Agrega efectos visuales interactivos al botón cuando el usuario interactua con el raton.
	 * Modifica dinámicamente el texto agregando flechas de seleccion e intercambia colores de fuente.
	 * * @param b El componente al que se le aplicarán los efectos visuales.
	 */
	private void mouseListeners(JButton b) {
		Color defaultForeground = b.getForeground();
		String defaultText = b.getText();
		
		b.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				b.setText("-> " + defaultText + " <-");
			}

			public void mouseExited(MouseEvent e) {
				b.setText(defaultText);
			}
			
			public void mousePressed(MouseEvent e) {
				b.setForeground(Color.LIGHT_GRAY);
			}

			public void mouseReleased(MouseEvent e) {
				b.setForeground(defaultForeground);
			}
		});
	}
}
