package utils;

import java.io.IOException;

import enums.Role;
import models.Enemy;
import models.Player;
import models.User;
import repository.CharacterRepository;
import repository.UserRepository;

public class Session {
	
	private static User currentUser;
	private static Player currentPlayer;
	
	public static void login(User user) {
		currentUser = user;
	}
	
	public static User getCurrentUser() {
		return currentUser;
	}
	
	public static void logout() {
		currentUser = null;
	}
	
	public static boolean isLoggedIn() {
		return currentUser != null;
	}
	
	public static void setPlayer(Player player) {
	     currentPlayer = player;
	}
	
	public static Player getPlayer() {
		return currentPlayer;
	}
	
	public static void saveCharacter(Player player) {
		if(currentUser == null) 
			return;
		
		CharacterRepository repo = new CharacterRepository();
		repo.updatePlayer(player, player.getName());
	}
	
	
	public static Player loadCharacter() throws IOException {

		Player player;
		
	    if(currentUser == null) {
	        return null;
	    }

	    CharacterRepository repo = new CharacterRepository();

	    player = repo.loadPlayer(currentUser.getId()); 
	    
	    return player;
	}
	
	public static void loadEnemy(Enemy enemy) {
		
	}
	
	public static Role getRole() {
		return currentUser.getRole();
	}
}