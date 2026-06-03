package views;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.UpgradeNode;
import models.UpgradeTree;
import utils.AppFont;
import utils.CustomJSwing;
import utils.Session;

public class GameUpgradeTreeView extends JPanel {

	private GameWindow window;
	private Image skillTreeBackground;

	private JButton back;
	private JLabel lblTokens;

	private JLabel errorLabelNode;
	
	private UpgradeTree tree;

	private UpgradeNode root;

	private UpgradeNode heal1;
	private UpgradeNode heal2;

	private UpgradeNode damage1;
	private UpgradeNode damage2;

	private UpgradeNode block1;
	private UpgradeNode block2;
	
	public GameUpgradeTreeView() {

		setLayout(null);
		setFocusCycleRoot(false);
		setFocusTraversalKeysEnabled(false);
		
		loadImage();
		initializeTree();
		initializeComponents();
		initializeNodes();
	}

	private void initializeTree() {

		tree = new UpgradeTree(50, "ROOT");
		root = tree.getRoot();

		heal1 = new UpgradeNode(25, "CURACIÓN I");
		heal2 = new UpgradeNode(25, "CURACIÓN II");

		damage1 = new UpgradeNode(50, "DAÑO I");
		damage2 = new UpgradeNode(50, "DAÑO II");

		block1 = new UpgradeNode(75, "BLOQUEO I");
		block2 = new UpgradeNode(75, "BLOQUEO II");

		root.setChild1(heal1);
		root.setChild2(damage1);
		root.setChild3(block1);

		heal1.setParentNode(root);
		damage1.setParentNode(root);
		block1.setParentNode(root);

		heal1.setChild2(heal2);
		heal2.setParentNode(heal1);

		damage1.setChild2(damage2);
		damage2.setParentNode(damage1);

		block1.setChild2(block2);
		block2.setParentNode(block1);
	}

	private void initializeComponents() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int screenWidth = screen.width;
		int screenHeight = screen.height;

		back = CustomJSwing.createJButton("Regresar");
		back.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		back.setBounds(20, screenHeight - 100, 500, 50);

		lblTokens = createLabel("Monedas: 0");
		lblTokens.setBounds(screenWidth - 500, screenHeight - 100, 500, 50);

		errorLabelNode = createErrorLabel("");
		
		errorLabelNode.setBounds(0, 100, 1, 30);
		
		errorLabelNode.setVisible(false);
		
		add(back);
		add(lblTokens);
		add(errorLabelNode);
	}

	private void initializeNodes() {

		root.setBounds(170, 430, 80, 80);

		heal1.setBounds(680, 220, 80, 80);
		heal2.setBounds(1120, 220, 80, 80);

		damage1.setBounds(680, 430, 80, 80);
		damage2.setBounds(1120, 430, 80, 80);

		block1.setBounds(680, 640, 80, 80);
		block2.setBounds(1120, 640, 80, 80);

		configureNode(root, new Color(255, 221, 64));
		root.setUnlocked(true);

		configureNode(heal1, Color.GREEN);
		configureNode(heal2, Color.GREEN);

		configureNode(damage1, Color.RED);
		configureNode(damage2, Color.RED);

		configureNode(block1, new Color(80, 230, 255));
		configureNode(block2, new Color(80, 230, 255));

		add(root);

		add(heal1);
		add(heal2);

		add(damage1);
		add(damage2);

		add(block1);
		add(block2);
	}
	private void configureNode(UpgradeNode node, Color color) {
		
		node.setFocusPainted(false);
		node.setContentAreaFilled(false);
		node.setBorderPainted(false);

		node.setRequestFocusEnabled(false);
		node.setFocusable(false);
		node.setOpaque(false);
		node.setForeground(color);
		node.setBackground(new Color(0, 0, 0, 0));
		
		node.setText("");
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.drawImage(skillTreeBackground, 0, 0, getWidth(), getHeight(), null);

		g2.setStroke(new BasicStroke(8));

		// COLORS
		Color yellow = new Color(255, 221, 64);
		Color green = new Color(0, 255, 0);
		Color red = new Color(255, 40, 40);
		Color cyan = new Color(80, 230, 255);

		// POSITIONS
		int rootX = 170;
		int rootY = 430;

		int heal1X = 680;
		int heal1Y = 220;

		int heal2X = 1120;
		int heal2Y = 220;

		int damage1X = 680;
		int damage1Y = 430;

		int damage2X = 1120;
		int damage2Y = 430;

		int block1X = 680;
		int block1Y = 640;

		int block2X = 1120;
		int block2Y = 640;

		// Lineas principales
		g2.setColor(yellow);

		g2.drawLine(rootX + 40, rootY + 40, 500, rootY + 40);

		g2.drawLine(500, 260, 500, 680);

		g2.drawLine(500, 260, heal1X, heal1Y + 40);

		g2.drawLine(500, rootY + 40, damage1X, damage1Y + 40);

		g2.drawLine(500, 680, block1X, block1Y + 40);

		// Lineas Conexiones
		g2.setColor(green);

		g2.drawLine(heal1X + 80, heal1Y + 40, heal2X, heal2Y + 40);

		g2.setColor(red);

		g2.drawLine(damage1X + 80, damage1Y + 40, damage2X, damage2Y + 40);

		g2.setColor(cyan);

		g2.drawLine(block1X + 80, block1Y + 40, block2X, block2Y + 40);

		// Dibujar nodos
		drawNode(g2, rootX, rootY, yellow, root);

		drawNode(g2, heal1X, heal1Y, green, heal1);
		drawNode(g2, heal2X, heal2Y, green, heal2);

		drawNode(g2, damage1X, damage1Y, red, damage1);
		drawNode(g2, damage2X, damage2Y, red, damage2);

		drawNode(g2, block1X, block1Y, cyan, block1);
		drawNode(g2, block2X, block2Y, cyan, block2);

		g2.setFont(AppFont.normal());

		g2.setColor(Color.WHITE);

		g2.drawString("CURACIÓN I", heal1X - 90, heal1Y - 20);
		g2.drawString("CURACIÓN II", heal2X - 110, heal2Y - 20);

		g2.drawString("DAÑO I", damage1X - 40, damage1Y - 20);
		g2.drawString("DAÑO II", damage2X - 40, damage2Y - 20);

		g2.drawString("BLOQUEO I", block1X - 70, block1Y - 20);
		g2.drawString("BLOQUEO II", block2X - 80, block2Y - 20);
	}

	private void drawNode(Graphics2D g2, int x, int y, Color borderColor, UpgradeNode node) {

		g2.setColor(borderColor);
		g2.fillOval(x, y, 80, 80);

		if (node.isUnlocked()) {
			g2.setColor(Color.WHITE);

		} else {
			g2.setColor(Color.BLACK);
		}
		g2.fillOval(x + 12, y + 12, 56, 56);
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
	
	public void updateTokens(int tokens) {
		
	    lblTokens.setText("Monedas: " + tokens);
	    
	    this.revalidate();
	    this.repaint();
	}
	
	private JLabel createLabel(String text) {

		JLabel label = new JLabel(text);

		label.setFont(AppFont.normal());

		label.setForeground(new Color(254, 252, 251));

		return label;
	}

	private JLabel createErrorLabel(String text) {

		JLabel label = new JLabel(text);

		label.setFont(AppFont.normal());

		label.setForeground(Color.RED);

		return label;
	}
	
	public void setErrorMessage(String message) {

		errorLabelNode.setText(message);

		errorLabelNode.setSize(errorLabelNode.getPreferredSize());

		int x = (getWidth() - errorLabelNode.getWidth()) / 2;

		errorLabelNode.setLocation(x, 100);

		errorLabelNode.setVisible(true);
	}
	
	public void showErrorLabel() {
		errorLabelNode.setVisible(true);
	}
	
	public void resetErrorLabel() {
		errorLabelNode.setVisible(false);
	}
	
	public JButton getBack() {
		return back;
	}

	public UpgradeTree getTree() {
		return tree;
	}

	public UpgradeNode getRootNode() {
		return root;
	}

	public UpgradeNode getHeal1() {
		return heal1;
	}

	public UpgradeNode getHeal2() {
		return heal2;
	}

	public UpgradeNode getDamage1() {
		return damage1;
	}

	public UpgradeNode getDamage2() {
		return damage2;
	}

	public UpgradeNode getBlock1() {
		return block1;
	}

	public UpgradeNode getBlock2() {
		return block2;
	}
	
	public JLabel getErrorLabelNode() {
		return errorLabelNode;
	}

}