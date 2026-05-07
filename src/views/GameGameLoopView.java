package views;

import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameGameLoopView extends JPanel {
	GameWindow window;
	
	private JButton attack;
	private JButton defend;
	private JButton healing;
	private JButton analizing;
	
	public JButton getAttack() {
		return attack;
	}

	public void setAttack(JButton attack) {
		this.attack = attack;
	}

	public JButton getDefend() {
		return defend;
	}
	
	public void setDefend(JButton defend) {
		this.defend = defend;
	}

	public JButton getHealing() {
		return healing;
	}

	public void setHealing(JButton healing) {
		this.healing = healing;
	}

	public JButton getAnalizing() {
		return analizing;
	}

	Image combatBackground;
	
	
	public GameGameLoopView() {
		loadImage();
		initializeComponents();
	}

	private void initializeComponents() {
		// TODO Auto-generated method stub
		
	}


	private void loadImage() {
		// TODO Auto-generated method stub
		
	}
}
