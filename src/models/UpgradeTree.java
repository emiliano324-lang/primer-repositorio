package models;

public class UpgradeTree {

	private UpgradeNode root;

	public UpgradeTree(int value, String upgradeName) {
		this.root = new UpgradeNode(value, upgradeName);
	}

	public UpgradeNode getRoot() {
		return root;
	}

	public void setRoot(UpgradeNode root) {
		this.root = root;
	}

	private void addNode(UpgradeNode node, UpgradeNode root) {

		if (root == null) {
			this.setRoot(root);
			root.setParentNode(null);
			;

		} else {

			if (node.getValue() < root.getValue()) {

				if (root.getChild1() == null) {
					root.setChild1(node);
					root.getChild1().setParentNode(root);

				} else {
					addNode(node, root.getChild1());
				}

			} else if (node.getValue() == root.getValue()) {

				if (root.getChild2() == null) {
					root.setChild2(node);
					root.getChild2().setParentNode(root);

				} else {
					addNode(node, root.getChild2());
				}

			} else {
				if (root.getChild3() == null) {
					root.setChild3(node);
					root.getChild3().setParentNode(root);

				} else {
					addNode(node, root.getChild3());
				}
			}
		}
	}
}