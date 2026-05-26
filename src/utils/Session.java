package utils;

import models.Player;
import models.User;
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
		if(currentUser == null) return;
		
		UserRepository repo = new UserRepository();
		repo.updatePlayer(currentUser.getPlayer());
		
	}
	public static void loadCharacter(Player player) {

	    if(currentUser == null) {
	        return;
	    }

	    UserRepository repo = new UserRepository();

	    player = currentUser.getPlayer();

	    int playerId = repo.savePlayer(player);

	    player.setId(playerId);
	}
	
	
	/*
	public static String getRole( ) {
		return currentUser.getRole();
	}*/
}