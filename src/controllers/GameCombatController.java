package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;

import enums.Winner;
import models.Enemy;
import models.Player;
import models.UpgradeNode;
import repository.BattleRepository;
import repository.CharacterRepository;
import utils.ScreenManager;
import utils.Session;
import views.GameCombatView;
import views.GameMenuView;
import views.GameWindow;

public class GameCombatController implements ActionListener {

	GameWindow window;
	GameMenuView menuView;
	GameCombatView combatView;

	BattleRepository battleRepo;
	CharacterRepository characterRepo;

	int enemyId = 10;

	Player player = new Player();
	Enemy enemy = new Enemy();

	Random random;

	public GameCombatController(GameCombatView combatView) {
		battleRepo = new BattleRepository();
		characterRepo = new CharacterRepository();

		this.combatView = combatView;

		random = new Random();

		try {
			player = Session.loadCharacter();
			Session.setPlayer(player);

			System.out.println("cargas vida player = " + player.getHealCharges());
			System.out.println("cargas bloqueo player = " + player.getBlockCharges());

			enemy = characterRepo.loadEnemy(enemyId);
		} catch (IOException e) {
			e.printStackTrace();
		}

		player.setTurn(true);

		registerListeners();

		combatView.initializePlayer(Session.getPlayer());

	}

	public void registerListeners() {

		combatView.getAttack().addActionListener(this);
		combatView.getBlock().addActionListener(this);
		combatView.getHeal().addActionListener(this);
		combatView.getAnalyze().addActionListener(this);

		mouseListeners(combatView.getAttack());
		mouseListeners(combatView.getBlock());
		mouseListeners(combatView.getHeal());
		mouseListeners(combatView.getAnalyze());
	}

	public void actionPerformed(ActionEvent e) {

		Object b = e.getSource();
		
		String[] actionFramesSelf = new String[4];
		String[] actionFramesFoe = new String[4];
		
		combatView.setSelfFrame(0);
		combatView.setFoeFrame(0);

	    combatView.getAnimation().stop();
	    
	    if(player.getTurn()) {
		   
	    	if(b == combatView.getAttack()) {

		    	actionFramesSelf = combatView.getAttackFramesSelf();
			    actionFramesFoe = combatView.getDamageFramesFoe();
			    
			    enemy.getDamage(player.getAttackPoints());
			    
			    combatView.topPanelMessage("El enemigo ha recibido " + player.getAttackPoints() + " pts de daño.");
		    }else if(b == combatView.getBlock()) {

		    	if(player.getBlockCharges() > 0) {
			    	actionFramesSelf = combatView.getBlockFramesSelf();
			    	actionFramesFoe = combatView.getHealFramesFoe();

			    	player.block();
			    	
			    	combatView.topPanelMessage(player.getName() + " se cubrió. Le quedan " + player.getBlockCharges() + " bloqueos.");
		    	}else {
		    		combatView.topPanelMessage("No tienes cargas de bloqueo : " + player.getBlockCharges() + " bloqueos.");
		    		actionFramesSelf = combatView.getIdleFramesSelf();	
		    		actionFramesFoe = combatView.getIdleFramesFoe();				    		
		    	}
		    	
		    	
		    }else if(b == combatView.getHeal()) {

		    	if(player.getHealCharges() > 0) {
			    	actionFramesSelf = combatView.getHealFramesSelf();
			   		actionFramesFoe = combatView.getIdleFramesFoe();
			   		
			   		player.heal();
			   		
			   		combatView.updateHealthBar(player.getHealth());
			   		combatView.topPanelMessage(player.getName() + " ha recibido "+ player.getHealPoints() + " pts de salud. Le quedan " + player.getHealCharges() + " curaciones.");
		    	}else {
		    		combatView.topPanelMessage("No tienes cargas de curacion : " + player.getHealCharges() + " curaciones.");
		    		actionFramesSelf = combatView.getIdleFramesSelf();	
		    		actionFramesFoe = combatView.getIdleFramesFoe();
		    	}
		    	
		    }else if(b == combatView.getAnalyze()) {
		    	
		    	combatView.topPanelMessage("Vida actual del enemigo: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
		   
		    	actionFramesSelf = combatView.getIdleFramesSelf();
		    	actionFramesFoe = combatView.getIdleFramesFoe();
		    	
		    	player.block();
		    	
		    	player.setTurn(true);
		    	
		    }else {
		    	
	    		actionFramesSelf = combatView.getDamageFramesSelf();
	    		actionFramesFoe = combatView.getAttackFramesFoe();
		    }
		
	    	player.setTurn(false);
	    	
		}else {
				
			enemy.setRandomAction((int)(Math.random() * 3) + 1);
			
			if(enemy.getRandomAction() == 1 && enemy.getBlockCharges() > 0) {
			
				enemy.block();
				actionFramesFoe = combatView.getBlockFramesFoe();
				actionFramesSelf = combatView.getIdleFramesSelf();

			}else if(enemy.getRandomAction() == 2 && enemy.getBlockCharges() > 0) {
				
				enemy.heal();
				actionFramesFoe = combatView.getHealFramesFoe();
				actionFramesSelf = combatView.getIdleFramesSelf();
				
			}else {
				
				player.getDamage(enemy.getAttackPoints());
				
				combatView.updateHealthBar(player.getHealth());
				
				actionFramesFoe = combatView.getAttackFramesFoe();
				actionFramesSelf = combatView.getDamageFramesSelf();
			}
			
			player.setTurn(true);
		}
	    
	    combatView.animateOnce(actionFramesSelf, actionFramesFoe);
	    
	    if(player.isDead()) {
	    	battleRepo.saveBattle(player.getId(), enemyId, Winner.Enemy);
	    	
	    	try {
	    	    player = Session.loadCharacter();
	    	    Session.setPlayer(player);
	    	} catch (IOException ex) {
	    	    ex.printStackTrace();
	    	}
	    	
	    	ScreenManager.showPanel("RESULT");
	    	
	    	restartCombat();
	    	
	    }else if(enemy.isDead()) {
	    	
	    	battleRepo.saveBattle(player.getId(), enemyId, Winner.Player);
	    	
	    	try {
	    	    player = Session.loadCharacter();
	    	    Session.setPlayer(player);
	    	} catch (IOException ex) {
	    	    ex.printStackTrace();
	    	}
	    	
	    	ScreenManager.showPanel("RESULT");
	    	
	    	restartCombat();
	    }

	}

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
	
	public void restartCombat() {
		
		try {
			player = Session.loadCharacter();
			
			player.setTurn(true);
			
			Session.setPlayer(player);
			
			combatView.updateHealthBar(player.getHealth());
			
			combatView.initializePlayer(player);
			
			enemy = characterRepo.loadEnemy(enemyId);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void refreshPlayer() {

	    try {

	        player = Session.loadCharacter();

	        Session.setPlayer(player);

	        combatView.initializePlayer(player);

	        combatView.updateHealthBar(player.getHealth());

	    } catch (IOException e) {

	        e.printStackTrace();
	    }
	}
	
}
