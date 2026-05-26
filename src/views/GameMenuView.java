package views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.CustomJSwing;

public class GameMenuView extends JPanel{
	
	private GameWindow window;
	private JLabel lblTitle;
	private JButton btnPlay;
	private JButton btnSkillTree;
	private JButton btnCredits;
	private JButton btnExit;
	private JLabel lblAvailable;
	//private JButton btnSettings;
	
	Image mainMenuBackground;
	
	public GameMenuView() {
		loadImage();
		initializeComponents();
	}
	
	public GameWindow getWindow() {
		return window;
	}

	public JButton getBtnPlay() {
		return btnPlay;
	}

	public JButton getBtnSkillTree() {
		return btnSkillTree;
	}
	
	public JButton getBtnCredits() {
		return btnCredits;
	}

	public JButton getBtnExit() {
		return btnExit;
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
		btnPlay = CustomJSwing.createMenuButton("JUGAR");
		add(btnPlay, c);
		
		c.gridy = 2;
		btnSkillTree = CustomJSwing.createMenuButton("MEJORAS");
		add(btnSkillTree, c);
		
		//TODO: poner un label de cuantas mejoras tienes disponibles.
		/*
		c.gridy = 3;
		lblavailable = createButton("");
		add(lblavailable, c);
		*/
		c.gridy = 4;
		btnCredits = CustomJSwing.createMenuButton("CREDITOS");
		add(btnCredits, c);
		
		c.gridy = 5;
		btnExit = CustomJSwing.createMenuButton("SALIR");
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

	    ImageIcon icon = loadIcon("/img/TECHNARYAN TITULO.png", 900, 193);

	    Image img = icon.getImage().getScaledInstance(900, 193, Image.SCALE_SMOOTH);
 
	    lblTitle = new JLabel(new ImageIcon(img));

	    add(lblTitle, c);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(mainMenuBackground, 0, 0, getWidth(), getHeight(), null);
	}
	
	private void loadImage() {
		
		try {
			mainMenuBackground = ImageIO.read(getClass().getResource("/img/fondoPrincipal.png"));
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
	
	
	
