package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;

import enums.Winner;
import models.Enemy;
import models.Player;
import repository.BattleRepository;
import repository.CharacterRepository;
import utils.ScreenManager;
import utils.Session;
import views.GameCombatView;
import views.GameMenuView;
import views.GameWindow;
/**
 * Controlador encargado de gestionar la logica de un combate por turnos entre el jugador y un enemigo.
 * Administra las acciones de ataque, bloqueo, curación, el flujo de turnos, las animaciones y el fin de la partida.
 * 
 * @author Hugo 
 * @author Emiliano 
 * @version 1.0
 */
public class GameCombatController implements ActionListener {

	GameWindow window;
	GameMenuView menuView;
	GameCombatView combatView;

	BattleRepository battleRepo;
	CharacterRepository characterRepo;

	private int enemyId = 10;
	private int playerHeals;
	private int enemyHeals;
	private int playerBlocks;
	private int enemyBlocks;
	
	Player player = new Player();
	Enemy enemy = new Enemy();

	Random random;
	
	/**
	 * Constructor del controlador de combate.
	 * Inicializa los repositorios, carga el personaje del jugador desde la sesión, el enemigo base,
	 * registra los componentes y establece las cargas de habilidades.
	 * * @param combatView vista de la interfaz grafica del combate.
	 * @param window ventana principal del juego que contiene los subcontroladores.
	 */
	public GameCombatController(GameCombatView combatView, GameWindow window) {
		
		this.window = window;
		
		battleRepo = new BattleRepository();
		characterRepo = new CharacterRepository();

		this.combatView = combatView;

		random = new Random();

		try {
			player = Session.loadCharacter();
			Session.setPlayer(player);

			enemy = characterRepo.loadEnemy(enemyId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		registerListeners();

		combatView.initializePlayer(Session.getPlayer());

		playerHeals = player.getHealCharges(); 
		enemyHeals = enemy.getHealCharges();
		playerBlocks = player.getBlockCharges();
		enemyBlocks = enemy.getBlockCharges();
	}
	
	/**
	 * Registra los listeners de eventos 
	 * para los botones de interacción de la vista de combate.
	 */
	
	public void registerListeners() {

		combatView.getAttack().addActionListener(this);
		combatView.getBlock().addActionListener(this);
		combatView.getHeal().addActionListener(this);
		combatView.getAnalyze().addActionListener(this);
		combatView.getSwitchTurn().addActionListener(this);

		mouseListeners(combatView.getAttack());
		mouseListeners(combatView.getBlock());
		mouseListeners(combatView.getHeal());
		mouseListeners(combatView.getAnalyze());
		mouseListeners(combatView.getSwitchTurn());
	}
	/**
	 * Controla las acciones del jugador y la IA del enemigo.
	 * Evalua el boton presionado, calcula mitigaciones por bloqueo, consume cargas, actualiza la interfaz
	 * y verifica de forma constante si alguna entidad ha muerto para finalizar el encuentro.
	 * * @param e Evento de accion .
	 */
	public void actionPerformed(ActionEvent e) {

		Object b = e.getSource();
		
		String[] actionFramesSelf = new String[4];
		String[] actionFramesFoe = new String[4];
		
		combatView.setSelfFrame(0);
		combatView.setFoeFrame(0);

	    combatView.getAnimation().stop();
	    
	    System.out.println(player.getTurn());
	    
		if(player.getTurn()) {
			   
	    		if(b == combatView.getAttack()) {
		
			    actionFramesSelf = combatView.getAttackFramesSelf();
			    actionFramesFoe = combatView.getDamageFramesFoe();
			    
			    int damage;
			    
			    if(enemy.isBlocking()) {
			    		damage = Math.abs(enemy.getBlockPoints() - player.getAttackPoints());
			    }else {
			    		damage = player.getAttackPoints();
			    }
			    enemy.getDamage(player.getAttackPoints());
			    
			    combatView.topPanelMessage("El enemigo ha recibido " + damage + " pts de daño.");
		    
			    player.setTurn(false);
			    
	    		}else if(b == combatView.getBlock()) {

		    		if(playerBlocks > 0) {
				    	actionFramesSelf = combatView.getBlockFramesSelf();
				    	actionFramesFoe = combatView.getHealFramesFoe();
	
				    	player.block();
				    	playerBlocks--;
				    	
				    	combatView.topPanelMessage(player.getName() + " se cubrió. Le quedan " + playerBlocks + " bloqueos.");
			    
		    		}else {
			    		combatView.topPanelMessage("No tienes cargas de bloqueo : " + playerBlocks + " bloqueos.");
			    		actionFramesSelf = combatView.getIdleFramesSelf();	
			    		actionFramesFoe = combatView.getIdleFramesFoe();				    		
			    	}
			    	
		    		player.setTurn(false);
			    	
		    }else if(b == combatView.getHeal()) {
		
		    		if(playerHeals > 0) {
				    actionFramesSelf = combatView.getHealFramesSelf();
			   		actionFramesFoe = combatView.getIdleFramesFoe();
			   		
			   		player.heal();
			   		playerHeals--;
			   		
			   		combatView.updateHealthBar(player.getHealth());
			   		combatView.topPanelMessage(player.getName() + " ha recibido "+ player.getHealPoints() + " pts de salud. Le quedan " + playerHeals + " curaciones.");
			    	
		    		}else {
			    		combatView.topPanelMessage("No tienes cargas de curacion : " + playerHeals + " curaciones.");
			    		actionFramesSelf = combatView.getIdleFramesSelf();	
			    		actionFramesFoe = combatView.getIdleFramesFoe();
			    	}
			    	
		    		player.setTurn(false);
			    	
		   }else if(b == combatView.getSwitchTurn()) {
			   
			   actionFramesSelf = combatView.getIdleFramesSelf();	
			   actionFramesFoe = combatView.getIdleFramesFoe();
			   combatView.topPanelMessage("Es tu turno de atacar.");
		   }
		   
		}else {
			combatView.topPanelMessage("Cambia de turno");
			actionFramesSelf = combatView.getIdleFramesSelf();
			actionFramesFoe = combatView.getIdleFramesFoe();
		}
		if(b == combatView.getAnalyze()) {
	    	
			combatView.topPanelMessage("Vida del enemigo: " + enemy.getHealth() + "/" + enemy.getMaxHealth()
										+ " | " + enemyBlocks + " bloqueos | " + enemyHeals + " curaciones");
		    	actionFramesSelf = combatView.getIdleFramesSelf();
		    	actionFramesFoe = combatView.getIdleFramesFoe();
		}
	     
	    if(b == combatView.getSwitchTurn() && !player.getTurn()){
				
			enemy.setRandomAction((int)(Math.random() * 3) + 1);
			
			if(enemy.getRandomAction() == 1 && enemy.getBlockCharges() > 0) {
			
				enemy.block();
				
				actionFramesFoe = combatView.getBlockFramesFoe();
				actionFramesSelf = combatView.getIdleFramesSelf();
				
				combatView.topPanelMessage("El enemigo se cubrió" );
				
			}else if(enemy.getRandomAction() == 2 && enemyHeals > 0) {
				
				enemy.heal();
				
				enemyHeals--;
				
				actionFramesFoe = combatView.getHealFramesFoe();
				actionFramesSelf = combatView.getIdleFramesSelf();
				
				combatView.topPanelMessage("El enemigo se ha recibido " + enemy.getHealPoints() + " pts de curación");
				
			}else {
				
				actionFramesFoe = combatView.getAttackFramesFoe();
				actionFramesSelf = combatView.getDamageFramesSelf();
				
				int damage;
				    
			    if(player.isBlocking()) {
			    		damage = Math.abs(player.getBlockPoints() - enemy.getAttackPoints());
			    }else {
			    		damage = enemy.getAttackPoints();
			    }
				
			    player.getDamage(enemy.getAttackPoints());
			    combatView.updateHealthBar(player.getHealth());
				combatView.topPanelMessage(player.getName() + " ha recibido " + damage + " pts de daño");
			}
			player.setTurn(true);
	    }
	    
	    combatView.animateOnce(actionFramesSelf, actionFramesFoe);
	    
	    if(player.isDead() || enemy.isDead()) {
	    	
	    		if(player.isDead()) {
	    			battleRepo.saveBattle(player.getId(), enemyId, Winner.Enemy);
	    			window.getResultController().getView().updateResult("DERROTA");
	    		
	    		}else if (enemy.isDead()){
	    			battleRepo.saveBattle(player.getId(), enemyId, Winner.Player);
	    			window.getResultController().getView().updateResult("VICTORIA");
	    		}
	    		
		    	try {
		    	    player = Session.loadCharacter();
		    	    Session.setPlayer(player);
		    	} catch (IOException ex) {
		    	    ex.printStackTrace();
		    	}
	    	
		    combatView.topPanelMessage("Ha comenzado la pelea");
		    	ScreenManager.showPanel("RESULT");
		    	
		    	restartCombat();
	    }
	}
	/**
	 * Agrega efectos visuales interactivos a los botones al pasar o presionar el raton.
	 * Modifica dinamicamente el texto agregando flechas de selección e intercambia colores de fuente.
	 * * @param b bl componente al que se le aplicaran los efectos visuales.
	 */
	public void mouseListeners(JButton b) {
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
	 * Restablece las condiciones iniciales del combate, recargando el estado del jugador 
	 * desde el repositorio y generando un nuevo enemigo para un proximo encuentro.
	 */
	public void restartCombat() {
		
		try {
			player = Session.loadCharacter();
			
			Session.setPlayer(player);
			
			combatView.updateHealthBar(player.getHealth());
			combatView.initializePlayer(player);
			
			enemy = characterRepo.loadEnemy(enemyId);
			
			player.setTurn(true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Sincroniza y restablece las cargas maximas de curación y bloqueo de ambas entidades 
	 * segun sus estadísticas actuales de juego.
	 */
	public void restartCharges() {
		playerHeals = player.getHealCharges();
		playerBlocks = player.getBlockCharges();
		enemyHeals = enemy.getHealCharges();
		enemyBlocks = enemy.getBlockCharges();
	}
	/**
	 * Vuelve a cargar el personaje de la sesion para actualizar posibles cambios en sus estadisticas 
	 * y refresca la barra de salud visual.
	 */
	public void refreshPlayer() {

	    try {

	        player = Session.loadCharacter();

	        Session.setPlayer(player);

	        combatView.initializePlayer(player);

	        combatView.updateHealthBar(player.getHealth());
	        
	        player.setTurn(true);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
}
