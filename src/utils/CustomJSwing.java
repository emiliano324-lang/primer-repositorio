package utils;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
/**
 * Utilidad para crear y personalizar componentes de Swing de forma estandarizada.
 *  @author Hugo  
 * @author Emiliano
 * @version 1.0
 */
public class CustomJSwing {
	/**
	 * Crea un botóo básico transparente con la fuente normal del sistema.
	 * * @param text Texto que mostrará el botón.
	 * @return boton configurado.
	 */
	public static JButton createJButton(String text) {
		JButton button = new JButton(text);

		button.setFont(AppFont.normal());
		button.setForeground(new Color(254, 252, 251));

		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorderPainted(false);

		button.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));

		return button;
	}
	/**
	 * Crea un botón diseñado especificamente para menus, usando una fuente de tamaño titulo.
	 * *@param text Texto que mostrara el boton de menu.
	 * @return Un objeto boton configurado para menus.
	 */
	public static JButton createMenuButton(String text) {
		JButton button = new JButton(text);

		button.setFont(AppFont.title());
		button.setForeground(new Color(254, 252, 251));

		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorderPainted(false);

		button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		return button;
	}
}
