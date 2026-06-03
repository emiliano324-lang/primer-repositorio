package views;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import utils.CustomJSwing;

public class GameCreditsView extends JPanel{
	
	private JButton back;
	private Image credits;
	
	public JButton getBack() {
		return back;
	}
	
	public GameCreditsView() {
		setLayout(new BorderLayout());
		
		loadImage();
		
		back = CustomJSwing.createJButton("Regresar");
		add(back, BorderLayout.SOUTH);
		
	}
	
	private void loadImage() {
		
		try {
			credits = ImageIO.read(getClass().getResource("/img/creditos.png"));
		} catch (IOException ex) {
			System.out.println("La imagen no existe");
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(credits, 0, 0, getWidth(), getHeight(), null);
	}
}
