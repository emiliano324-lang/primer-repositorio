package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import utils.AppFont;

public class GameGameLoopView extends JPanel {
	
	GameWindow window;
	
	private JButton attack;
	private JButton block;
	private JButton heal;
	private JButton analyze;
	
	Image combatBackground;
	
	// GETTERS Y SETTERS
	public JButton getAttack() {
		return attack;
	}

	public JButton getBlock() {
		return block;
	}
	
	public JButton getHeal() {
		return heal;
	}

	public JButton getAnalyze() {
		return analyze;
	}
	
	// CONSTRUCTOR
	public GameGameLoopView() {
		setLayout(new BorderLayout());
		
		loadImage();
		initializeComponents();
	}
	
	// MÉTODOS
	private void initializeComponents() {
		
		JPanel actionBar = new JPanel();
		actionBar.setOpaque(false);
		//actionBar.setBackground(Color.BLACK);
		
		attack = createButton("Atacar");
		block = createButton("Bloquear");
		heal = createButton("Curarse");
		analyze = createButton("Analizar");
		
		actionBar.add(attack);
		actionBar.add(block);
		actionBar.add(heal);
		actionBar.add(analyze);
		
		add(actionBar, BorderLayout.SOUTH);
	}

	private void loadImage() {
		try {
			combatBackground = ImageIO.read(getClass().getResource("/img/fondo pelea.jpg"));
		} catch (IOException ex) {
			System.out.println("La imagen no existe");
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(combatBackground, 0, 0, getWidth(), getHeight(), null);
	}
	
	private JButton createButton(String text) {
		JButton button = new JButton(text);
		
		button.setFont(AppFont.normal());
		button.setForeground(new Color(254, 252, 251));
		
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorderPainted(false);
		
		button.setBorder(BorderFactory.createEmptyBorder(50,20,50,20));
		
		return button;
	}
}
