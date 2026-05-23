package utils;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class CustomJSwing {

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
