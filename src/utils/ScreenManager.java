package utils;

import java.awt.CardLayout;
import javax.swing.JPanel;
/**
 * Gestor centralizado para cambiar de pantalla en la aplicación usando CardLayout.
 */
public class ScreenManager {

    private static CardLayout layout;
    private static JPanel container;

    /**
     * Inicializa el gestor con el layout y el panel contenedor principal.
     * @param l el CardLayout que controla el intercambio de pantallas.
     * @param c el JPanel contenedor donde se alojan las pantallas.
     */
    public static void initialize(CardLayout l, JPanel c) {

        layout = l;
        container = c;
    }

    /**
     * Agrega una nueva pantalla al contenedor bajo un nombre.
     * @param panel el panel que representa la pantalla.
     * @param name el nombre identificador de la pantalla.
     */
    public static void addPanel(JPanel panel, String name) {

        container.add(panel, name);
    }

    /**
     * Muestra la pantalla correspondiente al nombre indicado.
     * * @param name el nombre de la pantalla que se quiere mostrar.
     */
    public static void showPanel(String name) {

        layout.show(container, name);
    }
    
    
    
}
	
	
	
	
	

