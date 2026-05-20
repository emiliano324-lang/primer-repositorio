package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import utils.AppFont;

public class GameSkillTreeView extends JPanel{

	Image skillTreeBackground;
	
	JButton back;
	
	public GameSkillTreeView() {
		setLayout(new BorderLayout());
		
		loadImage();
		initializeComponents();
	}
	
	public void initializeComponents() {
		JPanel menuPanel = new JPanel(new BorderLayout());
		
		back = createButton("Regresar");
		
		menuPanel.add(back);
		add(menuPanel,BorderLayout.SOUTH);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(skillTreeBackground, 0, 0, getWidth(), getHeight(), null);
	}
	
	private void loadImage() {
		
		try {
			skillTreeBackground = ImageIO.read(getClass().getResource("/img/fondo arbol de habilidades.jpg"));
		} catch (IOException ex) {
			System.out.println("La imagen no existe");
		}
	}
	
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
	
	private JButton createButton(String text) {
		JButton button = new JButton(text);

		button.setFont(AppFont.normal());
		button.setForeground(new Color(254, 252, 251));

		/*button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorderPainted(false);*/

		button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		return button;
	}
	
}
