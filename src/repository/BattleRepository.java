package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import config.DatabaseConnection;
import enums.Winner;

public class BattleRepository {

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

}
