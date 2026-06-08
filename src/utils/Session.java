package utils;

import java.io.IOException;

import enums.Role;
import models.Enemy;
import models.Player;
import models.User;
import repository.CharacterRepository;
import repository.UserRepository;
/**
 * Gestiona la sesión global del usuario y el personaje seleccionado en el juego.
 * 
 * @author Hugo 
 * @author Emiliano
 * @version 1.0
 */
public class Session {
	
	private static User currentUser;
	private static Player currentPlayer;
	/**
	 * Inicia la sesion del usuario especificado.
	 * @param user el usuario que inicia sesion.
	 */
	public static void login(User user) {
		currentUser = user;
	}
	/** @return El usuario actualmente autenticado. */
	public static User getCurrentUser() {
		return currentUser;
	}
	/** Cierra la sesion del usuario actual. */
	public static void logout() {
		currentUser = null;
	}
	/** @return true si hay un usuario con sesión activa, false de lo contrario. */
	public static boolean isLoggedIn() {
		return currentUser != null;
	}
	/**
	 * Asigna el personaje activo en la sesión.
	 * @param player el personaje del jugador.
	 */
	public static void setPlayer(Player player) {
	     currentPlayer = player;
	}
	/** @return El personaje activo de la sesión. */
	public static Player getPlayer() {
		return currentPlayer;
	}
	/**
	 * Guarda el progreso del personaje en el repositorio si hay un usuario conectado.
	 * @param player el personaje que se va a guardar.
	 */
	public static void saveCharacter(Player player) {
		if(currentUser == null) 
			return;
		
		CharacterRepository repo = new CharacterRepository();
		repo.updatePlayer(player, player.getName());
	}
	
	/**
	 * Carga el personaje asociado al usuario actual desde el repositorio.
	 * @return El personaje cargado, o null si no hay un usuario conectado.
	 * @throws IOException si ocurre un error al leer los datos.
	 */
	public static Player loadCharacter() throws IOException {

		Player player;
		
	    if(currentUser == null) {
	        return null;
	    }

	    CharacterRepository repo = new CharacterRepository();

	    player = repo.loadPlayer(currentUser.getId()); 
	    
	    return player;
	}
	
	/** @return El rol del usuario actualmente autenticado. */
	public static Role getRole() {
		return currentUser.getRole();
	}
}