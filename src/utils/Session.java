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
	
	public static void saveCharacter(Player player) {
		if(currentUser == null) 
			return;
		
		CharacterRepository repo = new CharacterRepository();
		repo.updatePlayer(currentUser.getPlayer());
	}
	
	public static void saveCharacter(Enemy enemy) {
		
	}
	
	public static Player loadCharacter() throws IOException {

		Player player;
		
	    if(currentUser == null) {
	        return null;
	    }

	    CharacterRepository repo = new CharacterRepository();

	    player = repo.loadPlayer(currentUser.getId()); 
	    
	    return player;
	    
	    /*
	    int playerId = repo.loadPlayer(player.getId());

	    if(playerId == -1) {
	    	return;
	    }
	    
	    player.setId(playerId);*/
	}
	
	public static void loadEnemy(Enemy enemy) {
		
	}
	
	public static Role getRole() {
		return currentUser.getRole();
	}
}