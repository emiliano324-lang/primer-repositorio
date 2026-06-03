package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import models.Player;
import models.UpgradeNode;
import repository.CharacterRepository;

import utils.ScreenManager;
import utils.Session;

import views.GameUpgradeTreeView;

public class GameUpgradeTreeController implements ActionListener {

	private GameUpgradeTreeView view;

	private Player player;

	private CharacterRepository repo;

	public GameUpgradeTreeController(GameUpgradeTreeView view) {

		this.view = view;

		this.player = Session.getCurrentUser().getPlayer();

		this.repo = new CharacterRepository();

		initializeLockedNodes();

		loadUnlockedNodes();
		this.view.updateTokens(player.getTokens());
		registerListeners();
	}

	private void registerListeners() {

		view.getBack().addActionListener(e -> ScreenManager.showPanel("MENU"));

		view.getRootNode().addActionListener(this);

		view.getHeal1().addActionListener(this);

		view.getHeal2().addActionListener(this);

		view.getDamage1().addActionListener(this);

		view.getDamage2().addActionListener(this);

		view.getBlock1().addActionListener(this);

		view.getBlock2().addActionListener(this);

		mouseListeners(view.getBack());
	}

	private void initializeLockedNodes() {

		view.getRootNode().setEnabled(true);

		view.getHeal1().setEnabled(true);
		view.getHeal2().setEnabled(true);

		view.getDamage1().setEnabled(true);
		view.getDamage2().setEnabled(true);

		view.getBlock1().setEnabled(true);
		view.getBlock2().setEnabled(true);
	}

	// CARGAR DESBLOQUEOS DESDE SQL
	private void loadUnlockedNodes() {

		boolean[] upgrades = player.getUpgrades();

		if (upgrades[0]) {
			unlockVisual(view.getHeal1());
		}

		if (upgrades[1]) {
			unlockVisual(view.getHeal2());
		}

		if (upgrades[2]) {
			unlockVisual(view.getDamage1());
		}

		if (upgrades[3]) {
			unlockVisual(view.getDamage2());
		}

		if (upgrades[4]) {
			unlockVisual(view.getBlock1());
		}

		if (upgrades[5]) {
			unlockVisual(view.getBlock2());
		}
	}

	private void unlockVisual(UpgradeNode node) {

		node.setUnlocked(true);

		node.setFillColor(Color.WHITE);

		view.repaint();
	}

	private void mouseListeners(JButton b) {

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

		UpgradeNode node = (UpgradeNode) e.getSource();

		unlockNode(node);
	}

	private void unlockNode(UpgradeNode node) {

		// YA DESBLOQUEADO
		if (node.isUnlocked()) {

			view.setErrorMessage("YA DESBLOQUEADO");

			view.showErrorLabel();

			return;
		}

		// DEPENDENCIA
		if (node.getParentNode() != null && !node.getParentNode().isUnlocked()) {

			view.setErrorMessage("DESBLOQUEA EL ANTERIOR");

			view.showErrorLabel();

			return;
		}

		// TOKENS
		if (player.getTokens() < 1) {

			view.setErrorMessage("SIN TOKENS");

			view.showErrorLabel();

			return;
		}

		view.resetErrorLabel();

		// DESCONTAR TOKENS
		player.setTokens(player.getTokens() - 1);
		view.updateTokens(player.getTokens());

		// APLICAR UPGRADE
		player.upgrade(node.getUpgradeName());

		// GUARDAR EN SQL
		repo.saveUpgrade(player.getId(), node.getUpgradeName());

		// ACTUALIZAR PLAYER SQL
		repo.updatePlayer(player);
		
		Session.setPlayer(player);

		// CAMBIO VISUAL
		node.setUnlocked(true);

		node.setFillColor(Color.WHITE);

		view.repaint();
	}
}