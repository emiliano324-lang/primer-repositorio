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
/**
 * Ventana principal de la aplicación.
 *
 * <p>Esta clase administra todas las vistas del juego,
 * permitiendo cambiar entre el menu principal,
 * combate, arbol de mejoras, creditos y pantalla de resultados.</p>
 *
 * <p>Además, inicializa los controladores asociados a cada vista y
 * configura la ventana en modo de pantalla completa.</p>
 *
 * @author Hugo 
 * @author emiliano
 * @version 1.0
 */
public class GameWindow extends JFrame {

	private CardLayout layout;
	private JPanel container;

	private GameCombatController combatController;
	private GameMenuController menuController;
	private GameUpgradeTreeController treeController;
	private GameCreditsController creditsController;
	private GameResultController resultController;
	/**
	 * Crea e inicializa la ventana principal del juego.
	 *
	 * <p>Configura la aplicación en pantalla completa, establece el icono
	 * y cursor personalizados, crea las vistas principales del juego,
	 * registra los controladores correspondientes y muestra el menú
	 * principal al iniciar la aplicación.</p>
	 */
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
		container.add(resultView, "RESULT");

		menuController = new GameMenuController(menuView, this);
		combatController = new GameCombatController(gameLoopView, this);
		treeController = new GameUpgradeTreeController(gameUpgradeTreeView);
		creditsController = new GameCreditsController(creditsView);
		resultController = new GameResultController(resultView);

		ScreenManager.showPanel("MENU");
		setVisible(true);
	}
	/**
	 * Obtiene el controlador del menu principal.
	 *
	 * @return controlador encargado de la vista del menu.
	 */
	public GameMenuController getMenuController() {
		return menuController;
	}
	/**
	 * Obtiene el controlador del sistema de combate.
	 *
	 * @return controlador encargado de la logica de combate.
	 */
	public GameCombatController getCombatController() {
		return combatController;
	}
	/**
	 * Obtiene el controlador del arbol de mejoras.
	 *
	 * @return controlador encargado de gestionar las mejoras del jugador.
	 */
	public GameUpgradeTreeController getTreeController() {
		return treeController;
	}
	/**
	 * Obtiene el controlador de la pantalla de creditos.
	 *
	 * @return controlador encargado de la vista de creditos.
	 */
	public GameCreditsController getCreditsController() {
		return creditsController;
	}
	/**
	 * Obtiene el controlador de la pantalla de creditos.
	 *
	 * @return controlador encargado de la vista de creditos.
	 */
	public GameResultController getResultController() {
		return resultController;
	}
}
