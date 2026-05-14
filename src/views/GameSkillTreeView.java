package views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameSkillTreeView extends JPanel{

	Image skillTreeBackground;
	
	public GameSkillTreeView() {
		loadImage();
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
	
}
