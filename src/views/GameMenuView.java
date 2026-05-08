package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.AppFont;

public class GameMenuView extends JPanel{
	
	GameWindow window;
	
	private JLabel lblTitle;
	private JButton btnPlay;
	private JButton btnExit;
	private JButton btnCredits;
	//private JButton btnSettings;
	
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
	
	private void initializeComponents() {
		setLayout(new GridBagLayout());
		
		createIcon();
		createButtons();
	}

	private void createButtons() {
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.weightx = 1.0;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 20, 10, 20);
		c.fill = GridBagConstraints.NONE;

		c.gridy = 1;
		btnPlay = createButton("JUGAR");
		add(btnPlay, c);
		
		c.gridy = 2;
		btnCredits = createButton("CREDITOS");
		add(btnCredits, c);
		
		c.gridy = 3;
		btnExit = createButton("SALIR");
		add(btnExit, c);
	}
	
	private void createIcon() {
		GridBagConstraints c = new GridBagConstraints();

	    c.gridx = 0;
	    c.gridy = 0;

	    c.weightx = 1;
	    c.weighty = 1;
	    
	    c.anchor = GridBagConstraints.NORTHWEST;

	    c.insets = new Insets(10, 20, 40, 0);

	    ImageIcon icon = new ImageIcon("src/img/TECHNARYAN TITULO.png");

	    Image img = icon.getImage().getScaledInstance(900, 193, Image.SCALE_SMOOTH);
 
	    lblTitle = new JLabel(new ImageIcon(img));

	    add(lblTitle, c);
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
	
	
	private JButton createButton(String text) {
		
		JButton button = new JButton(text);
		
		button.setForeground(new Color(254, 252, 251));
		button.setFont(AppFont.normal());

		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setOpaque(false);
		
		return button;
	}
}
