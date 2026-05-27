package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import models.Player;
import models.UpgradeNode;
import utils.ScreenManager;
import utils.Session;
import views.GameUpgradeTreeView;

public class GameUpgradeTreeController implements ActionListener {

	private GameUpgradeTreeView view;
	private Player player;
	
	public GameUpgradeTreeController(GameUpgradeTreeView view) {

		player = Session.getCurrentUser().getPlayer();
		
		this.view = view;

		initializeLockedNodes();
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

		if (node.isUnlocked()) {
			view.getErrorLabelNode().setText("YA DESBLOQUEADO");
			view.showErrorLabel();
			return;
		}else if(!node.getParentNode().isUnlocked()) {
			view.getErrorLabelNode().setText("DEBES DESBLOQUEAR EL ANTERIOR");
			view.showErrorLabel();
			return;
		}
		
		view.resetErrorLabel();
		
		player.upgrade(node.getUpgradeName());
		
		node.setUnlocked(true);

		node.setFillColor(Color.WHITE);

		view.repaint();

	}

}