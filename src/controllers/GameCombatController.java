package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import models.Enemy;
import models.GameCombatSystem;
import models.Player;
import views.GameCombatView;
import views.GameMenuView;
import views.GameWindow;

public class GameCombatController implements ActionListener {

	GameWindow window;
	GameMenuView gameMenuView;
	GameCombatView gameCombatView;

	GameCombatSystem combat;

	public GameCombatController(GameCombatView combatView) {
		this.gameCombatView = combatView;

		initializeCombat();
		registerListeners();

	}

	public void initializeCombat() {

		Player player = new Player("Jugador", 100, 100, 20, 15, 50, false, 1, 0);
		Enemy enemy = new Enemy("Enemigo", 100, 100, 20, 15, 50, false);

		combat = new GameCombatSystem(player, enemy);
	}

	public void registerListeners() {

		gameCombatView.getAttack().addActionListener(this);
		gameCombatView.getBlock().addActionListener(this);
		gameCombatView.getHeal().addActionListener(this);
		// gameCombatView.getAnalyze().addActionListener(this);

		mouseListeners(gameCombatView.getAttack());
		mouseListeners(gameCombatView.getBlock());
		mouseListeners(gameCombatView.getHeal());
		mouseListeners(gameCombatView.getAnalyze());
	}

	public void mouseListeners(JButton b) {
		Color defaultForeground = b.getForeground();
		String defaultText = b.getText();

		b.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				b.setText("-> " + defaultText + " <-");
			}

			public void mouseExited(MouseEvent e) {
				b.setText(defaultText);
			}

			public void mousePressed(MouseEvent e) {
				b.setForeground(Color.LIGHT_GRAY);
			}

			public void mouseReleased(MouseEvent e) {
				b.setForeground(defaultForeground);

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String[] selfFrames = new String[4];

		String[] foeFrames = new String[4];

		if (e.getSource() == gameCombatView.getAttack()) {
			combat.playerAttack();
			selfFrames = gameCombatView.getAttackFramesSelf();
			foeFrames = gameCombatView.getDamageFramesFoe();
		}

		else if (e.getSource() == gameCombatView.getBlock()) {
			combat.playerBlock();
			selfFrames = gameCombatView.getBlockFramesSelf();
		}

		else if (e.getSource() == gameCombatView.getHeal()) {
			combat.playerHeal();
			selfFrames = gameCombatView.getHealFramesSelf();
		}

		animatePlayerAction(selfFrames, foeFrames);

		combat.enemyTurn();

		animateEnemyAction();

		updateView();

		checkGameState();
	}

	private void animatePlayerAction(String[] selfFrames, String[] foeFrames) {

		gameCombatView.setSelfFrame(0);
		gameCombatView.setFoeFrame(0);

		gameCombatView.animateOnce(selfFrames, foeFrames);
	}

	private void animateEnemyAction() {

		String[] selfFrames = new String[4];
		String[] foeFrames = new String[4];

		switch (combat.getEnemyAction()) {

		case 0:
			foeFrames = gameCombatView.getAttackFramesFoe();
			selfFrames = gameCombatView.getDamageFramesSelf();
			break;

		case 1:
			foeFrames = gameCombatView.getBlockFramesFoe();
			break;

		case 2:
			foeFrames = gameCombatView.getHealFramesFoe();
			break;
		}

		gameCombatView.animateOnce(selfFrames, foeFrames);
	}
	
	private void checkGameState() {

		if (combat.getPlayer().isDead()) {

			disableCombat();
		}

		if (combat.getEnemy().isDead()) {

			disableCombat();
		}
	}
	
	private void disableCombat() {

		gameCombatView.getAttack().setEnabled(false);
		gameCombatView.getBlock().setEnabled(false);
		gameCombatView.getHeal().setEnabled(false);
	}
}
