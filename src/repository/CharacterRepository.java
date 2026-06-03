	package repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.DatabaseConnection;
import models.Enemy;
import models.Player;
import models.User;

public class CharacterRepository {

	// CREATE
	public void createPlayer(User user) {

		String sql = "INSERT INTO characters (id_character, id_user, name, level, health, max_health, attack_points, defense_points, heal_points, tokens)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pst.setInt(1, user.getId());
			pst.setInt(2, user.getId());
			pst.setString(3, user.getName());

			pst.setInt(4, 1);
			pst.setInt(5, 100);
			pst.setInt(6, 100);

			pst.setInt(7, 10);
			pst.setInt(8, 5);
			pst.setInt(9, 15);

			pst.setInt(10, 0);

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();

			if (rs.next()) {

				int generatedId = rs.getInt(1);

				System.out.println("Player creado con ID: " + generatedId);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// READ
	public Player loadPlayer(int idUser) throws IOException {

		String sql = "SELECT * FROM characters WHERE id_user = ?";

		try (Connection connection = DatabaseConnection.getConnection();

				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, idUser);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				boolean[] upgrades = loadUpgrades(rs.getInt("id_character"), connection);

				Player player = new Player(

						rs.getString("name"),

						rs.getInt("max_health"), rs.getInt("health"),

						rs.getInt("attack_points"), rs.getInt("defense_points"), rs.getInt("heal_points"),

						rs.getInt("level"), rs.getInt("tokens"),

						false, 3, 3, upgrades, false, false);

				player.setId(rs.getInt("id_character"));

				return player;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	// UPDATE
	public boolean updatePlayer(Player player) {

		String sql = "UPDATE characters " + "SET health = ?, " + "max_health = ?, " + "attack_points = ?, "
				+ "defense_points = ?, " + "heal_points = ?, " + "level = ? " + "WHERE id_character = ?";

		try (Connection connection = DatabaseConnection.getConnection();

				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, player.getHealth());
			pst.setInt(2, player.getMaxHealth());
			pst.setInt(3, player.getAttackPoints());
			pst.setInt(4, player.getBlockPoints());
			pst.setInt(5, player.getHealPoints());
			pst.setInt(6, player.getLevel());

			pst.setInt(7, player.getId());

			int affectedRows = pst.executeUpdate();

			return affectedRows > 0;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	// DELETE
		public boolean deletePlayer(int idUser) {

			String sql = "DELETE FROM characters WHERE id_user = ?";

			try (Connection connection = DatabaseConnection.getConnection();

					PreparedStatement pst = connection.prepareStatement(sql)) {

				pst.setInt(1, idUser);

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
	
	public void saveUpgrade(int idCharacter, String upgradeName) {
		String findUpgradeSql = "SELECT id_upgrade FROM upgrades WHERE name = ?";

		String insertSql = "INSERT INTO character_has_upgrades (id_character, id_upgrade) VALUES (?, ?)";

		try (Connection connection = DatabaseConnection.getConnection();

				PreparedStatement findPst = connection.prepareStatement(findUpgradeSql)) {

			findPst.setString(1, upgradeName);

			ResultSet rs = findPst.executeQuery();

			if (rs.next()) {

				int idUpgrade = rs.getInt("id_upgrade");

				try (PreparedStatement insertPst = connection.prepareStatement(insertSql)) {

					insertPst.setInt(1, idCharacter);

					insertPst.setInt(2, idUpgrade);

					insertPst.executeUpdate();
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public boolean deleteUpgrades(int idCharacter) {

		String sql = "DELETE FROM character_has_upgrades WHERE id_character = ?";

		try (Connection connection = DatabaseConnection.getConnection();

				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, idCharacter);

			int affectedRows = pst.executeUpdate();

			if (affectedRows > 0) {

				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	private boolean[] loadUpgrades(int idCharacter, Connection connection) {

		boolean[] upgrades = { false, false, false, false, false, false };

		String sql = "SELECT u.name FROM character_has_upgrades chu "
				+ "INNER JOIN upgrades u ON chu.id_upgrade = u.id_upgrade "
				+ "WHERE chu.id_character = ?";

		try (PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, idCharacter);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				String upgrade = rs.getString("name");

				switch (upgrade.toUpperCase()) {

				case "CURACIÓN I":
					upgrades[0] = true;
					break;

				case "CURACIÓN II":
					upgrades[1] = true;
					break;

				case "DAÑO I":
					upgrades[2] = true;
					break;

				case "DAÑO II":
					upgrades[3] = true;
					break;

				case "BLOQUEO I":
					upgrades[4] = true;
					break;

				case "BLOQUEO II":
					upgrades[5] = true;
					break;
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return upgrades;
	}
	
	public Enemy loadEnemy(int idEnemy) throws IOException{
		
		String sql = "SELECT * FROM enemies WHERE id_enemy = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, idEnemy);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				Enemy enemy = 
						new Enemy(rs.getString("name"),
								rs.getInt("max_health"),
								rs.getInt("health"),
								rs.getInt("attack_points"), 
								rs.getInt("defense_points"),
								rs.getInt("heal_points"), 
								0,
								false, 
								5,
								5, 
								false, 
								false);
				
				return enemy;
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
			
	}
}