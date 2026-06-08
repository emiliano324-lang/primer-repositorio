package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import utils.ScreenManager;
import views.GameMenuView;
import views.GameWindow;
import views.LoginWindow;
/**
 * Controlador encargado de gestionar la logica, la navegación y los eventos del menu principal del juego.
 * Administra las transiciones hacia el combate, el arbol de habilidades, los creditos y el cierre de sesion.
 */
public class GameMenuController {

    private GameMenuView view;
    private GameWindow window;
    /**
     * Constructor del controlador del menu del juego.
     * Vincula la vista, la ventana y activa los listeners de eventos para los botones del menu.
     * * @param view vista de la interfaz grafica del menu principal.
     * @param window ventana principal del juego que contiene los subcontroladores necesarios.
     */
    public GameMenuController(GameMenuView view, GameWindow window) {

        this.view = view;
        this.window = window;

        gameMenuListener();
    }
    /**
     * Registra las acciones de navegacion para cada uno de los botones del menu.
     * Se encarga de refrescar las estadisticas del jugador al iniciar un combate o abrir el arbol de habilidades,
     * así como de cerrar la ventana actual al salir de la sesion.
     */
    private void gameMenuListener() {

        view.getBtnPlay().addActionListener(e -> {
        	
	        	window.getCombatController().refreshPlayer();
	        	window.getCombatController().restartCharges();
	        	
	        	ScreenManager.showPanel("GAME");

        });
        
        view.getBtnSkillTree().addActionListener(e ->  {
        	
	        	window.getTreeController().refresh();
	        	
	        	ScreenManager.showPanel("SKILLTREE");	
        });
        
        view.getBtnCredits().addActionListener(e ->  ScreenManager.showPanel("CREDITS"));
        
        view.getBtnExit().addActionListener(e -> {
        		new LoginWindow();
        		window.dispose();
        });
        
        mouseListeners(view.getBtnPlay());
        mouseListeners(view.getBtnSkillTree());
        mouseListeners(view.getBtnCredits());
        mouseListeners(view.getBtnExit());
    }
    /**
     * Agrega efectos visuales interactivos al boton cuando el usuario interactúa con el raton.
     * Modifica dinámicamente el texto agregando flechas de selección e intercambia colores de fuente.
     * * @param b el componente al que se le aplicaran los efectos visuales.
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
