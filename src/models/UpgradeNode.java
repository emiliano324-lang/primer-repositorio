package models;

import java.awt.Color;

import javax.swing.JButton;

public class UpgradeNode extends JButton {

	private int value;

	private String upgradeName;

	private boolean unlocked;

	private Color fillColor = Color.BLACK;

	private UpgradeNode parentNode;

	private UpgradeNode child1;
	private UpgradeNode child2;
	private UpgradeNode child3;

	public UpgradeNode(int value, String upgradeName) {
	
		this.value = value;
		this.upgradeName = upgradeName;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getUpgradeName() {
		return upgradeName;
	}

	public void setUpgradeName(String upgradeName) {
		this.upgradeName = upgradeName;
	}

	public UpgradeNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(UpgradeNode parentNode) {
		this.parentNode = parentNode;
	}

	public UpgradeNode getChild1() {
		return child1;
	}

	public void setChild1(UpgradeNode child1) {
		this.child1 = child1;
	}

	public UpgradeNode getChild2() {
		return child2;
	}

	public void setChild2(UpgradeNode child2) {
		this.child2 = child2;
	}

	public UpgradeNode getChild3() {
		return child3;
	}

	public void setChild3(UpgradeNode child3) {
		this.child3 = child3;
	}

	public boolean isUnlocked() {
		return unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
}