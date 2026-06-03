package views;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.GameMenuController;
import controllers.GameResultController;
import controllers.GameCombatController;
import controllers.GameCreditsController;
import controllers.GameUpgradeTreeController;
import controllers.LoginController;
import utils.ScreenManager;

public class GameWindow extends JFrame{
	private CardLayout layout;
    private JPanel container;

	public GameWindow() {
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		setUndecorated(true); // Quita bordes y barra
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		gd.setFullScreenWindow(this);
	
		Image icon = tk.getImage("src/img/logo_uabcs.png");
		setIconImage(icon);
		
		ImageIcon iconCursor = new ImageIcon("src/img/cursor.png");
		Cursor cursor = tk.createCustomCursor(iconCursor.getImage(), new Point(0, 0), "Mi Cursor");
		setCursor(cursor);
		
		layout = new CardLayout();

		container = new JPanel(layout);

        add(container);
        ScreenManager.initialize(layout, container);    
        GameMenuView menuView = new GameMenuView();
        GameCombatView gameLoopView = new GameCombatView();
        GameUpgradeTreeView gameUpgradeTreeView = new GameUpgradeTreeView();
		GameCreditsView creditsView = new GameCreditsView();
		GameResultView resultView = new GameResultView();
		
        
        container.add(menuView, "MENU");
        container.add(gameLoopView, "GAME");
        container.add(gameUpgradeTreeView, "SKILLTREE");
        container.add(creditsView, "CREDITS");
        container.add(resultView,"RESULT");
        
        new GameMenuController(menuView,this);
        new GameCombatController(gameLoopView);
        new GameUpgradeTreeController(gameUpgradeTreeView);
        new GameCreditsController(creditsView);
        new GameResultController(resultView);
        
        
        ScreenManager.showPanel("MENU");
		setVisible(true);
	}
}
