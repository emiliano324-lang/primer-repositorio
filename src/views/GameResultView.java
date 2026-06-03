package views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.CustomJSwing;

public class GameResultView extends JPanel{
	
	private GameWindow window;
	private JLabel lblResult;
	private JButton btnExit;
	
	Image resultBackgrown;
	
	
	
	
	
	public GameResultView() {
		loadImage();
		initializeComponents();
		
		
	}
	
	private void initializeComponents() {
		setLayout(new GridBagLayout());
		createButton();
		
	}
	
	public void showResult() {
		
		
		
		
	}
	
	public JButton getBtnExit() {
		return btnExit;
	}
	
	public void setBtnExit(JButton btnExit) {
		this.btnExit = btnExit;
	}
	
	
	
	public void createButton() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = 1.0;
		c.anchor = GridBagConstraints.SOUTH;
		c.insets = new Insets(10, 20, 10, 20);
		c.fill = GridBagConstraints.NONE;
		
		
		c.gridy = 1;
		btnExit = CustomJSwing.createMenuButton("Salir");
		add(btnExit, c);
		
	}
	
	
	
	
	
	
	
	
	private void loadImage() {
		
		try {
			resultBackgrown = ImageIO.read(getClass().getResource("/img/resultBackgrown1.jpg"));
		} catch (IOException ex) {
			System.out.println("La imagen no existe");
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(resultBackgrown, 0, 0, getWidth(), getHeight(), null);
	}
	
	
	
}
