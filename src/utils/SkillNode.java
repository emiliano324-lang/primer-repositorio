package utils;

import javax.swing.JButton;

public class SkillNode extends JButton{

	// Fields
	private int value;
	private String skillName;
	private SkillNode parent;
	private SkillNode child1;
	private SkillNode child2;
	private SkillNode child3;

	// Getters & Setters
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public SkillNode getParent() {
		return parent;
	}
	public void setParent(SkillNode parent) {
		this.parent = parent;
	}
	public SkillNode getChild1() {
		return child1;
	}
	public void setChild1(SkillNode child1) {
		this.child1 = child1;
	}
	public SkillNode getChild2() {
		return child2;
	}
	public void setChild2(SkillNode child2) {
		this.child2 = child2;
	}
	public SkillNode getChild3() {
		return child3;
	}
	public void setChild3(SkillNode child3) {
		this.child3 = child3;
	}
	
	// Constructors
	public SkillNode(int value, String skillName) {
		this.value = value;
		this.skillName = skillName;
	}
	/*public SkillNode(int value, String skillName, SkillNode node1) {
		this.value = value;
		this.skillName = skillName;
		this.node1 = node1;
	}
	public SkillNode(int value, String skillName, SkillNode node1, SkillNode node2, SkillNode node3) {
		this.value = value;
		this.skillName = skillName;
		this.node1 = node1;
		this.node2 = node2;
		this.node3 = node3;
	}*/
}
