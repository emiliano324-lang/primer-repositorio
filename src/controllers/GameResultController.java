package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import utils.ScreenManager;
import views.GameResultView;
/**
 * Controlador encargado de gestionar la logica y los eventos de la pantalla de resultados del combate.
 * Controla la visualizacion del desenlace de la partida y el retorno al menu principal.
 */

public class GameResultController {
	
	private GameResultView view;
	/**
	 * Constructor del controlador de resultados.
	 * Vincula la vista correspondiente y activa los listeners de eventos.
	 * * @param view vista de la interfaz grafica de los resultados de la partida.
	 */
	public GameResultController(GameResultView view) {
		this.view = view;
		
		registerListeners();
	}
	/**
	 * Registra los listeners de eventos para los componentes interactivos de la vista de resultados.
	 */
	public void registerListeners() {
		view.getBtnExit().addActionListener(e -> ScreenManager.showPanel("MENU"));
	
		mouseListeners(view.getBtnExit());
	}
	/**
	 * Obtiene la vista asociada a este controlador.
	 * Utilizado externamente para actualizar de forma dinamica el mensaje de victoria o derrota.
	 * * @return La instancia gameResultView.
	 */
	public GameResultView getView() {
		return view;
	}
	/**
	 * Agrega efectos visuales interactivos al boton cuando el usuario interactúa con el raton.
	 * Modifica dinamicamente el texto agregando flechas de selección e intercambia colores de fuente.
	 * * @param b el componente boton al que se le aplicaran los efectos visuales.
	 */
	public void mouseListeners(JButton b) {
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
