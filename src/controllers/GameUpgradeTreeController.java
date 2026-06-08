package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import models.Player;
import models.UpgradeNode;
import repository.CharacterRepository;

import utils.ScreenManager;
import utils.Session;

import views.GameUpgradeTreeView;
/**
 * Controlador encargado de gestionar la lógica del árbol de habilidades y mejoras del personaje.
 * Administra las reglas de desbloqueo, actualiza los estados visuales en la interfaz grafica 
 * y persiste el progreso en la base de datos.
 * 
 * @author Hugo 
 * @author Emiliano 
 * @version 1.0
 */
public class GameUpgradeTreeController implements ActionListener {

	private GameUpgradeTreeView view;

	private Player player;

	private CharacterRepository repo;
	/**
	 * Constructor del controlador del arbol de mejoras.
	 * Sincroniza el personaje actual de la sesion, inicializa el estado de los nodos, 
	 * carga los desbloqueos previos desde la base de datos y registra los listeners de eventos.
	 * * @param view vista grafica que representa el arbol de habilidades en la interfaz.
	 */
	public GameUpgradeTreeController(GameUpgradeTreeView view) {

		this.view = view;

		player = Session.getPlayer();

		if(player != null) {
		    view.updateTokens(player.getTokens());
		}
		
		this.repo = new CharacterRepository();

		initializeLockedNodes();

		loadUnlockedNodes();
		
		
		registerListeners();
	}
	/**
	 * Registra los listeners de eventos para el boton de regreso y todos los nodos de mejora pertenecientes al arbol.
	 */
	private void registerListeners() {

		view.getBack().addActionListener(e ->{

			view.resetErrorLabel();
			ScreenManager.showPanel("MENU");
			
		});
			

		view.getRootNode().addActionListener(this);

		view.getHeal1().addActionListener(this);

		view.getHeal2().addActionListener(this);

		view.getDamage1().addActionListener(this);

		view.getDamage2().addActionListener(this);

		view.getBlock1().addActionListener(this);

		view.getBlock2().addActionListener(this);

		mouseListeners(view.getBack());
	}
	/**
	 * Habilita por defecto la interactividad de todos los componentes de tipo nodo en la vista.
	 */
	private void initializeLockedNodes() {

		view.getRootNode().setEnabled(true);

		view.getHeal1().setEnabled(true);
		view.getHeal2().setEnabled(true);

		view.getDamage1().setEnabled(true);
		view.getDamage2().setEnabled(true);

		view.getBlock1().setEnabled(true);
		view.getBlock2().setEnabled(true);
	}

	// CARGAR DESBLOQUEOS DESDE SQL
	/**
	 * Mapea el arreglo de mejoras booleanas obtenido del personaje para 
	 * reflejar visualmente en el arbol los nodos que ya han sido desbloqueados.
	 */
	private void loadUnlockedNodes() {

		boolean[] upgrades = player.getUpgrades();

		if (upgrades[0]) {
			unlockVisual(view.getHeal1());
		}

		if (upgrades[1]) {
			unlockVisual(view.getHeal2());
		}

		if (upgrades[2]) {
			unlockVisual(view.getDamage1());
		}

		if (upgrades[3]) {
			unlockVisual(view.getDamage2());
		}

		if (upgrades[4]) {
			unlockVisual(view.getBlock1());
		}

		if (upgrades[5]) {
			unlockVisual(view.getBlock2());
		}
	}
	/**
	 * Aplica los cambios esteticos correspondientes a un nodo para marcarlo como desbloqueado.
	 * Modifica su estado interno, altera su color de relleno a blanco y fuerza el repintado de la vista.
	 * * @param node El componente que se actualizara visualmente.
	 */
	private void unlockVisual(UpgradeNode node) {

		node.setUnlocked(true);

		node.setFillColor(Color.WHITE);

		view.repaint();
	}
	/**
	 * Agrega efectos visuales interactivos al boton de regreso cuando el usuario interactua con el raton.
	 * * @param b el componente  al que se le aplicaran los efectos visuales.
	 */
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
	/**
	 * Captura el nodo que disparo el evento de clic y procede a evaluar su desbloqueo.
	 * * @param e evento de accion del componente modificado.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		UpgradeNode node = (UpgradeNode) e.getSource();

		unlockNode(node);
	}
	/**
	 * Procesa la logica de negocio para desbloquear una habilidad en el arbol.
	 * 
	 * <p>Valida de forma secuencial que el nodo no este previamente desbloqueado, 
	 * que el nodo padre se encuentre activo y que el jugador disponga de tokens suficientes.
	 * Si cumple los requisitos, descuenta el token, aplica la bonificación al personaje, guarda la nueva 
	 * relación en la base de datos y actualiza la visualización del nodo.</p>
	 * * @param node El nodo sobre el cual se intenta realizar la compra de mejora.
	 */
	private void unlockNode(UpgradeNode node) {

		// YA DESBLOQUEADO
		if (node.isUnlocked()) {

			view.setErrorMessage("YA DESBLOQUEADO");
			view.showErrorLabel();

			return;
		}

		// DEPENDENCIA
		if (node.getParentNode() != null && !node.getParentNode().isUnlocked()) {

			view.setErrorMessage("DESBLOQUEA EL ANTERIOR");
			view.showErrorLabel();

			return;
		}

		// TOKENS
		if (player.getTokens() < 1) {

			view.setErrorMessage("SIN TOKENS");
			view.showErrorLabel();

			return;
		}

		view.resetErrorLabel();

		// DESCONTAR TOKENS
		player.setTokens(player.getTokens() - 1);
		
		view.updateTokens(player.getTokens());
		
		// APLICAR UPGRADE
		player.upgrade(node.getUpgradeName());
		
		// GUARDAR EN SQL
		repo.saveUpgrade(player.getId(), node.getUpgradeName());

		// ACTUALIZAR PLAYER SQL
		repo.updatePlayer(player, player.getName());
		
		Session.setPlayer(player);

		// CAMBIO VISUAL
		node.setUnlocked(true);

		node.setFillColor(Color.WHITE);

		view.repaint();
	}
	/**
	 * Vuelve a cargar los datos del personaje desde el repositorio para mantener sincronizados 
	 * los tokens y el estado del arbol con los ultimos cambios del juego.
	 */
	public void refresh() {

	    try {

	        player = Session.loadCharacter();

	        Session.setPlayer(player);

	        view.updateTokens(player.getTokens());

	    } catch (Exception e) {

	        e.printStackTrace();
	    }
	}
	/**
	 * Obtiene la vista del arbol de mejoras vinculada a este controlador.
	 * * @return La instancia actual de  GameUpgradeTreeView.
	 */
	public GameUpgradeTreeView getTreeView() {
		return view;
	}
	
}