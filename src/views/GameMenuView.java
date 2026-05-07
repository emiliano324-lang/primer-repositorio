package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameMenuView extends JPanel{
	
	GameWindow window;
	
	private JButton btnPlay;
	private JButton btnExit;
	private JButton btnCredits;
	private JButton btnOpcions;
	
	
	Image mainMenuBackground;
	
	public GameMenuView() {
		loadImage();
		initializeComponents();
	}
	
	public GameWindow getWindow() {
		return window;
	}

	public void setWindow(GameWindow window) {
		this.window = window;
	}

	public JButton getBtnPlay() {
		return btnPlay;
	}

	public void setBtnPlay(JButton btnPlay) {
		this.btnPlay = btnPlay;
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public void setBtnExit(JButton btnExit) {
		this.btnExit = btnExit;
	}
	public JButton getBtnCredits() {
		return btnCredits;
	}
	public void setBtnCredits(JButton btnCredits) {
		this.btnCredits = btnCredits;
	}
	public JButton getBtnOpcions() {
		return btnOpcions;
	}
	public void setBtnOpcions(JButton btnOpcions) {
		this.btnOpcions = btnOpcions;
	}
	private void initializeComponents() {
		GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

		
		btnPlay = new JButton("PLAY");
		btnPlay.setForeground(new Color(254, 252, 251));
		btnPlay.setFont(new Font("Verdana", Font.BOLD, 45));
		
		add(btnPlay,c);
	}
	
	
	
	
	
	
	public void paintComponent(Graphics g) {
		
	
		
		
		
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(mainMenuBackground, 0, 0, getWidth(), getHeight(), null);
	}
	
	private void loadImage() {
		
		try {
			mainMenuBackground = ImageIO.read(new File("src/img/fondoPrincipal.png"));
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
