package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import utils.AppFont;

public class GameGameLoopView extends JPanel {

	GameWindow window;

	private Timer animation;
	public int selfFrame = 0; // cambiar a private despues
	public int foeFrame = 0; // cambiar a private despues

	public String[] idleFramesSelf = {
		"/img/IdleSelf/IdleSelf0.png",
		"/img/IdleSelf/IdleSelf1.png",
		"/img/IdleSelf/IdleSelf2.png",
		"/img/IdleSelf/IdleSelf3.png"
	};
	
	public String[] idleFramesFoe = {
		"/img/IdleFoe/IdleFoe0.png",
		"/img/IdleFoe/IdleFoe1.png",
		"/img/IdleFoe/IdleFoe2.png",
		"/img/IdleFoe/IdleFoe3.png"
	};

	public String[] attackFramesSelf = {
		"/img/AttackSelf/AttackSelf0.png",
		"/img/AttackSelf/AttackSelf1.png",
		"/img/AttackSelf/AttackSelf2.png",
		"/img/AttackSelf/AttackSelf3.png" 
	};

	public String[] damageFramesFoe = {
		"/img/DamageFoe/DamageFoe0.png",
		"/img/DamageFoe/DamageFoe1.png",
		"/img/DamageFoe/DamageFoe2.png",
		"/img/DamageFoe/DamageFoe3.png",
	};
	
	private JButton attack;
	private JButton block;
	private JButton heal;
	private JButton analyze;

	private JLabel characterSelf;
	private JLabel characterFoe;

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
		animate(idleFramesSelf, idleFramesFoe);
		// animateFoe();
	}

	// MÉTODOS
	private void initializeComponents() {

		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;

		ImageIcon iconSelf = loadIcon("/img/IdleSelf/IdleSelf0.png", 128 * 3, 192 * 3);
		characterSelf = new JLabel(iconSelf);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.SOUTHWEST;

		gbc.insets = new Insets(180, 0, 0, 0);

		centerPanel.add(characterSelf, gbc);

		ImageIcon iconFoe = loadIcon("/img/IdleFoe/IdleFoe0.png", 128 * 2, 192 * 2);
		characterFoe = new JLabel(iconFoe);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.NORTHEAST;

		gbc.insets = new Insets(200, 0, 0, 0);

		centerPanel.add(characterFoe, gbc);

		add(centerPanel, BorderLayout.CENTER);

		JPanel actionBar = new JPanel();
		actionBar.setOpaque(false);

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

	private ImageIcon loadIcon(String path, int w, int h) {

		try {
			Image icon = ImageIO.read(getClass().getResource(path));
			icon = icon.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			return new ImageIcon(icon);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	private void loadImage() {
		try {
			combatBackground = ImageIO.read(getClass().getResource("/img/fondo pelea.jpg"));
		} catch (IOException ex) {
			System.out.println("La imagen no existe");
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(combatBackground, 0, 0, getWidth(), getHeight(), null);
	}

	public void animate(String[] framesSelf, String[] framesFoe) {

		animation = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon frameSelf = loadIcon(framesSelf[selfFrame], 128 * 3, 192 * 3);
				ImageIcon frameFoe = loadIcon(framesFoe[foeFrame], 128 * 3, 192 * 3);
				characterSelf.setIcon(frameSelf);
				characterFoe.setIcon(frameFoe);
				selfFrame++;
				foeFrame++;

				if (selfFrame >= framesSelf.length) {
					selfFrame = 0;
				}

				if (foeFrame >= framesFoe.length) {
					foeFrame = 0;
				}
			}
		});

		animation.start();
	}

	private JButton createButton(String text) {
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
}
