package views;

import java.awt.BasicStroke; // NUEVO
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints; // NUEVO
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.AppFont;

public class GameSkillTreeView extends JPanel {

	GameWindow window;

	private Image skillTreeBackground;

	private JButton back;
	private JLabel lblTokens;

	public JButton getBack() {
		return back;
	}

	public GameSkillTreeView() {

		setLayout(new BorderLayout());

		loadImage();
		initializeComponents();
	}

	public void initializeComponents() {

		JPanel menuPanel = new JPanel(new BorderLayout());
		menuPanel.setOpaque(false);

		back = createButton("Regresar");
		lblTokens = createLabel("Monedas: " + 10);

		menuPanel.add(back, BorderLayout.WEST);
		menuPanel.add(lblTokens, BorderLayout.EAST);

		add(menuPanel, BorderLayout.SOUTH);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(skillTreeBackground, 0, 0, getWidth(), getHeight(), null);
		g2.setStroke(new BasicStroke(8));

		// COLORES
		Color yellow = new Color(255, 221, 64);
		Color green = new Color(0, 255, 0);
		Color red = new Color(255, 40, 40);
		Color cyan = new Color(80, 230, 255);

		// POSICIONES

		// Nodo inicial
		int rootX = 170;
		int rootY = 330 + 100;

		// Rama curacion
		int heal1X = 680;
		int heal1Y = 120 + 100;

		int heal2X = 1120;
		int heal2Y = 120 + 100;

		// Rama daño
		int damage1X = 680;
		int damage1Y = 330 + 100;

		int damage2X = 1120;
		int damage2Y = 330 + 100;

		// Rama bloqueo
		int block1X = 680;
		int block1Y = 540 + 100;

		int block2X = 1120;
		int block2Y = 540 + 100;

		// LÍNEAS AMARILLAS PRINCIPALES
		g2.setColor(yellow);

		g2.drawLine(rootX + 40, rootY + 40, 500, rootY + 40); 	// Línea horizontal central
		g2.drawLine(500, 220 + 40, 500, 580 + 100); 			// Línea vertical
		g2.drawLine(500, 220 + 40, heal1X, heal1Y + 40); 		// Conexión superior
		g2.drawLine(500, rootY + 40, damage1X, damage1Y + 40); 	// Conexión media
		g2.drawLine(500, 580 + 100, block1X, block1Y + 40); 	// Conexión inferior

		// CONEXIONES DE HABILIDADES

		// Curación
		g2.setColor(green);
		g2.drawLine(heal1X + 80, heal1Y + 40, heal2X, heal2Y + 40);
		
		// Daño
		g2.setColor(red);
		g2.drawLine(damage1X + 80, damage1Y + 40, damage2X, damage2Y + 40);

		// Bloqueo
		g2.setColor(cyan);
		g2.drawLine(block1X + 80, block1Y + 40, block2X, block2Y + 40);

		// NODOS
		drawNode(g2, rootX, rootY, yellow); // Raiz
		
		drawNode(g2, heal1X, heal1Y, green); // Curacion I
		drawNode(g2, heal2X, heal2Y, green); // Curacion II

		drawNode(g2, damage1X, damage1Y, red); // Daño I
		drawNode(g2, damage2X, damage2Y, red); // Daño II

		drawNode(g2, block1X, block1Y, cyan); // Bloqueo I
		drawNode(g2, block2X, block2Y, cyan); // Bloqueo II

		// TEXTO
		g2.setFont(AppFont.normal());
		g2.setColor(Color.WHITE);

		// Curación
		g2.drawString("CURACIÓN I", heal1X - 90, heal1Y - 20);
		g2.drawString("CURACIÓN II", heal2X - 110, heal2Y - 20);

		// Daño
		g2.drawString("DAÑO I", damage1X - 40, damage1Y - 20);
		g2.drawString("DAÑO II", damage2X - 40, damage2Y - 20);

		// Bloqueo
		g2.drawString("BLOQUEO I", block1X - 70, block1Y - 20);
		g2.drawString("BLOQUEO II", block2X - 80, block2Y - 20);
	}

	private void drawNode(Graphics2D g2, int x, int y, Color color) {

		g2.setColor(color);
		g2.fillOval(x, y, 80, 80);

		// Centro negro
		g2.setColor(Color.BLACK);
		g2.fillOval(x + 12, y + 12, 56, 56);
	}

	private void loadImage() {

		try {
			skillTreeBackground = ImageIO
					.read(getClass().getResource("/img/fondo arbol de habilidades.jpg"));

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

	private JLabel createLabel(String text) {

		JLabel label = new JLabel(text);

		label.setFont(AppFont.normal());
		label.setForeground(new Color(254, 252, 251));

		return label;
	}

	private JButton createButton(String text) {

		JButton button = new JButton(text);

		button.setFont(AppFont.normal());
		button.setForeground(new Color(254, 252, 251));

		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorderPainted(false);

		button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		return button;
	}
}