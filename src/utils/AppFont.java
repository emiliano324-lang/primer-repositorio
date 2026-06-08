package utils;

import java.awt.Font;
/**
 * Utilidad para gestionar la fuente de la aplicacion.
 * Carga una fuente personalizada desde los recursos o usa una por defecto si falla.
 */
public class AppFont {

	private static Font base;

	static {
		try {
			base = Font.createFont(
					Font.TRUETYPE_FONT, 
					AppFont.class.getResourceAsStream("/assets/fonts/Press_Start_2P/PressStart2P-Regular.ttf"));

		}catch(Exception e) {
			base = new Font("Times New Roman", Font.PLAIN, 14);
		}
	}
	/** @return Fuente en tamaño normal. */
	public static Font normal() {
		return base.deriveFont(30f);
	}
	/** @return Fuente en estilo negrita y tamaño mediano. */
	public static Font bold() {
		return base.deriveFont(Font.BOLD, 14f);
	}
	/** @return Fuente en tamaño pequeño. */
	public static Font small() {
		return base.deriveFont(20f);
	}
	/** @return Fuente en estilo negrita y tamaño grande para títulos. */
	public static Font title() {
		return base.deriveFont(Font.BOLD, 45f);
	}
}