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
import javax.swing.JProgressBar;
import javax.swing.Timer;

import utils.AppFont;
import utils.CustomJSwing;

public class GameCombatView extends JPanel {

	GameWindow window;

	private Timer animationIdle;
	private Timer animation;

	private int selfFrame = 0;
	private int foeFrame = 0;
	
	private JProgressBar healthBar;
	
	private JButton attack;
	private JButton block;
	private JButton heal;
	private JButton analyze;

	private JButton back;
	
	private JLabel characterSelf;
	private JLabel characterFoe;

	Image combatBackground;
	
	private String[] idleFramesSelf = {
		"/img/IdleSelf/IdleSelf0.png",
		"/img/IdleSelf/IdleSelf1.png",
		"/img/IdleSelf/IdleSelf2.png",
		"/img/IdleSelf/IdleSelf3.png"
	};
	
	private String[] idleFramesFoe = {
		"/img/IdleFoe/IdleFoe0.png",
		"/img/IdleFoe/IdleFoe1.png",
		"/img/IdleFoe/IdleFoe2.png",
		"/img/IdleFoe/IdleFoe3.png"
	};

	private String[] attackFramesSelf = {
		"/img/AttackSelf/AttackSelf0.png",
		"/img/AttackSelf/AttackSelf1.png",
		"/img/AttackSelf/AttackSelf2.png",
		"/img/AttackSelf/AttackSelf3.png" 
	};

	private String[] attackFramesFoe = {
		"/img/AttackFoe/AttackFoe0.png",
		"/img/AttackFoe/AttackFoe1.png",
		"/img/AttackFoe/AttackFoe2.png",
		"/img/AttackFoe/AttackFoe3.png"
	};
	
	private String[] blockFramesSelf = {
		"/img/BlockSelf/BlockSelf0.png",
		"/img/BlockSelf/BlockSelf1.png",
		"/img/BlockSelf/BlockSelf2.png",
		"/img/BlockSelf/BlockSelf3.png"
	};
	
	private String[] blockFramesFoe = {
		"/img/BlockFoe/BlockFoe0.png",
		"/img/BlockFoe/BlockFoe1.png",
		"/img/BlockFoe/BlockFoe2.png",
		"/img/BlockFoe/BlockFoe3.png"
	};
	
	private String[] healFramesSelf = {
		"/img/HealSelf/HealSelf0.png", 
		"/img/HealSelf/HealSelf1.png", 
		"/img/HealSelf/HealSelf2.png", 
		"/img/HealSelf/HealSelf3.png" 
	};
	
	private String[] healFramesFoe = {
		"/img/HealFoe/HealFoe0.png",
		"/img/HealFoe/HealFoe1.png",
		"/img/HealFoe/HealFoe2.png",
		"/img/HealFoe/HealFoe3.png"
	};
	
	private String[] damageFramesSelf = {
		"/img/DamageSelf/DamageSelf0.png",
		"/img/DamageSelf/DamageSelf1.png",
		"/img/DamageSelf/DamageSelf2.png",
		"/img/DamageSelf/DamageSelf3.png"
	};
	
	private String[] damageFramesFoe = {
		"/img/DamageFoe/DamageFoe0.png",
		"/img/DamageFoe/DamageFoe1.png",
		"/img/DamageFoe/DamageFoe2.png",
		"/img/DamageFoe/DamageFoe3.png"
	};
	
	// GETTERS Y SETTERS
	public JProgressBar getHealthBar() {
		return healthBar;
	}
	
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
	
	public GameWindow getWindow() {
		return window;
	}

	public Timer getAnimationIdle() {
		return animationIdle;
	}

	public Timer getAnimation() {
		return animation;
	}

	public int getSelfFrame() {
		return selfFrame;
	}

	public void setSelfFrame(int selfFrame) {
		this.selfFrame = selfFrame;
	}
	
	public void setFoeFrame(int foeFrame) {
		this.foeFrame = foeFrame;
	}
	
	public int getFoeFrame() {
		return foeFrame;
	}

	public String[] getIdleFramesSelf() {
		return idleFramesSelf;
	}

	public String[] getIdleFramesFoe() {
		return idleFramesFoe;
	}

	public String[] getAttackFramesSelf() {
		return attackFramesSelf;
	}

	public String[] getAttackFramesFoe() {
		return attackFramesFoe;
	}

	public String[] getBlockFramesSelf() {
		return blockFramesSelf;
	}

	public String[] getBlockFramesFoe() {
		return blockFramesFoe;
	}

	public String[] getHealFramesSelf() {
		return healFramesSelf;
	}

	public String[] getHealFramesFoe() {
		return healFramesFoe;
	}

	public String[] getDamageFramesSelf() {
		return damageFramesSelf;
	}

	public String[] getDamageFramesFoe() {
		return damageFramesFoe;
	}

	public JLabel getCharacterSelf() {
		return characterSelf;
	}

	public JLabel getCharacterFoe() {
		return characterFoe;
	}

	public Image getCombatBackground() {
		return combatBackground;
	}

	// CONSTRUCTOR
	public GameCombatView() {
		setLayout(new BorderLayout());

		loadImage();
		initializeComponents();
		animationIdle(idleFramesSelf, idleFramesFoe);
		
	}

	// MÉTODOS
	private void initializeComponents() {

		JPanel centerPanel = createCenterPanel();
		JPanel southBar = createActionsPanel();
		
		add(centerPanel, BorderLayout.CENTER);
		add(southBar, BorderLayout.SOUTH);
	}
	
	private JPanel createCenterPanel() {
		
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

		return centerPanel;
	}

	private JPanel createActionsPanel() {
		
		JPanel actionsPanel = new JPanel();
		actionsPanel.setOpaque(false);

		healthBar = createProgressBar();
		
		attack = CustomJSwing.createJButton("Atacar");
		block = CustomJSwing.createJButton("Bloquear");
		heal = CustomJSwing.createJButton("Curarse");
		analyze = CustomJSwing.createJButton("Analizar");

		actionsPanel.add(healthBar);
		actionsPanel.add(attack);
		actionsPanel.add(block);
		actionsPanel.add(heal);
		actionsPanel.add(analyze);

		return actionsPanel;
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
			combatBackground = ImageIO.read(getClass().getResource("/img/fondo_pelea.jpg"));
		} catch (IOException ex) {
			System.out.println("La imagen no existe");
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(combatBackground, 0, 0, getWidth(), getHeight(), null);
	}
	
	public void animationIdle(String[] framesSelf, String[] framesFoe) {

		animation  = new Timer(250, new ActionListener() {

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
	public void animateOnce(String[] framesSelf, String[] framesFoe) {

	    animation = new Timer(100, new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {

	            ImageIcon frameSelf = loadIcon(framesSelf[selfFrame], 128 * 3, 192 * 3);
	            ImageIcon frameFoe = loadIcon(framesFoe[foeFrame], 128 * 3, 192 * 3);

	            characterSelf.setIcon(frameSelf);
	            characterFoe.setIcon(frameFoe);

	            selfFrame++;
	            foeFrame++;

	            if (selfFrame >= framesSelf.length ||
	                foeFrame >= framesFoe.length) {

	                animation.stop();

	                selfFrame = 0;
	                foeFrame = 0;
	               
	                animationIdle(idleFramesSelf, idleFramesFoe);
	            }
	        }
	    });

	    animation.start();
	}
	
	public void animateAction(String[] framesSelf, String[] framesFoe) {

		animation  = new Timer(250, null);

	    selfFrame = 0;
	    foeFrame = 0;

	    animation.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {

	            ImageIcon frameSelf = loadIcon(framesSelf[selfFrame], 128 * 3, 192 * 3);
	            ImageIcon frameFoe = loadIcon(framesFoe[foeFrame], 128 * 3, 192 * 3);

	            characterSelf.setIcon(frameSelf);
	            characterFoe.setIcon(frameFoe);

	            selfFrame++;
	            foeFrame++;

	            if (selfFrame >= framesSelf.length ||
	                foeFrame >= framesFoe.length) {

	            	animation.stop();
	                
	                selfFrame = 0;
	                foeFrame = 0;

	                animationIdle(idleFramesSelf, idleFramesFoe);
	            }
	        }
	    });

	    animation.start();
	}

	private JProgressBar createProgressBar() {
		JProgressBar progressBar = new JProgressBar();
		
		// TODO: Cambiar despues para el personaje
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);
		progressBar.setForeground(Color.GREEN);
		progressBar.setValue(progressBar.getMaximum());
		
		return progressBar;
	}
}
