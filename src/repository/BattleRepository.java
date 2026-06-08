package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import config.DatabaseConnection;
import enums.Winner;
/**
 * Repositorio encargado de gestionar la persistencia y el historial de las batallas 
 * entre los personajes y los enemigos en la base de datos.
 * @author Hugo 
 * @author Emiliano 
 * @version 1.0
 */
public class BattleRepository {
	/**
	 * Guarda el registro de una batalla completada en la base de datos.
	 * Almacena los identificadores del personaje, del enemigo y el resultado final.
	 * * @param idCharacter id del personaje del jugador.
	 * @param idEnemy Identificador único del enemigo enfrentado.
	 * @param winner El ganador de la batalla representado por el enum.
	 */
	public void saveBattle(int idCharacter, int idEnemy, Winner winner) {
		
		String sql = "INSERT INTO battles (id_character, id_enemy, winner) VALUES (?,?,?)";
		
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			){
			
			pst.setInt(1, idCharacter);
			pst.setInt(2, idEnemy);
			pst.setString(3, winner.name());
			
			pst.executeUpdate();	
			
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	/**
	 * Elimina todo el historial de batallas asociado a un personaje especifico.
	 * * @param idCharacter id del personaje cuyas batallas se van a borrar.
	 * @return true si se elimino al menos un registro de batalla con exito; false en caso contrario.
	 */
	public boolean deleteBattle(int idCharacter) {
		
		String sql = "DELETE FROM battles WHERE id_character = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();

				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, idCharacter);

			int affectedRows = pst.executeUpdate();

			if (affectedRows > 0) {

				System.out.println("Player eliminado");

				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

}
