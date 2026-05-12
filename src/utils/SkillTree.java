package utils;

public class SkillTree {

	private SkillNode root;
	
	public SkillTree(int value, String skillName) {
		this.root = new SkillNode(value, skillName);
	}

	public SkillNode getRoot() {
		return root;
	}

	public void setRoot(SkillNode root) {
		this.root = root;
	}

	private void addNode(SkillNode node, SkillNode root) {

		if (root == null) {
			this.setRoot(root);
			root.setParent(null);;

		} else {
			
			if (node.getValue() < root.getValue()) {

				if (root.getChild1() == null) {
					root.setChild1(node);
					root.getChild1().setParent(root);

				} else {
					addNode(node, root.getChild1());
				}

			} else if (node.getValue() == root.getValue()) {

			    if (root.getChild2() == null) {
			        root.setChild2(node);
			        root.getChild2().setParent(root);

			    } else {
			        addNode(node, root.getChild2());
			    }

			} else {
				if (root.getChild3() == null) {
					root.setChild3(node);
					root.getChild3().setParent(root);

				} else {
					addNode(node, root.getChild3());
				}
			}
		}
	}
	
}