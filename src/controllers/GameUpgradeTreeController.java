package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import models.UpgradeNode;
import utils.ScreenManager;
import views.GameUpgradeTreeView;

public class GameUpgradeTreeController implements ActionListener {

	private GameUpgradeTreeView view;

	public GameUpgradeTreeController(GameUpgradeTreeView view) {

		this.view = view;

		initializeLockedNodes();
		registerListeners();
	}

	private void registerListeners() {
		view.getBack().addActionListener(e -> ScreenManager.showPanel("MENU"));

		view.getRootNode().addActionListener(e -> unlockNode(null));

		view.getHeal1().addActionListener(e -> view.getHeal1().setUnlocked(true));
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
			return;
		}

		node.setUnlocked(true);

		node.setFillColor(Color.WHITE);

		view.repaint();

		System.out.println(node.getUpgradeName() + " desbloqueado");

		enableChildren(node);
	}

	// =====================================================
	// ENABLE CHILDREN
	// =====================================================

	private void enableChildren(UpgradeNode node) {

		// ROOT

		if (node == view.getRootNode()) {

			view.getHeal1().setEnabled(true);

			view.getDamage1().setEnabled(true);

			view.getBlock1().setEnabled(true);
		}

		// HEAL

		if (node == view.getHeal1()) {

			view.getHeal2().setEnabled(true);
		}

		// DAMAGE

		if (node == view.getDamage1()) {

			view.getDamage2().setEnabled(true);
		}

		// BLOCK

		if (node == view.getBlock1()) {

			view.getBlock2().setEnabled(true);
		}
	}
}