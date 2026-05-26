package repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import config.DatabaseConnection;
import enums.Role;
import enums.Sex;
import models.Player;
import models.User;

public class UserRepository {

	public void save(User user) throws IOException {
		
		List<User> users = getUsers();
		
		String sql = "INSERT INTO users (name, password, email, sex, role) VALUES (?,?,?,?,?)";
		
		try(
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)
		){
			
			String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());;
			
			pst.setString(1, user.getName());
			pst.setString(2, hashedPassword);
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getSex().name());
			pst.setString(5, user.getRole().name());
			//pst.setString(5, user.getImagePath());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				users.add(user);
				System.out.println("Nuevo usuario guardado");
			}
			
			System.out.println("Filas modificadas: " + affectedRows);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	

	public List<User> getUsers() throws IOException{
		
		List<User> users = new ArrayList<User>();
		
		try(
			Connection connection = DatabaseConnection.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users");
		){
			while(rs.next()) {
				
				int id = rs.getInt("id_user");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Sex sex = Sex.valueOf(rs.getString("sex"));
				Role role = Role.valueOf(rs.getString("role"));
				//String imagePath = rs.getString("imagePath");
				
				User user = new User(id, name, email, sex, role);
				
				users.add(user);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return users;
	}
	
	public boolean delete(int id) {
		
		String sql = "DELETE FROM users WHERE id_user = ?";
		
		try(
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			if(affectedRows > 0) {
				System.out.println("Usuario eliminado");
				return true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public boolean update(int index, User updatedUser) throws IOException {

		String sql = "UPDATE users SET name = ?, password = ?, email = ?, sex = ? WHERE id_user = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)){
		
			String hashedPassword = BCrypt.hashpw(updatedUser.getPassword(), BCrypt.gensalt());;
			
			pst.setString(1,  updatedUser.getName());
			pst.setString(2, hashedPassword);
			pst.setString(3,  updatedUser.getEmail());
			pst.setString(4,  updatedUser.getSex().name());
			pst.setString(5, updatedUser.getRole().name());
			//pst.setString(5, updatedUser.getImagePath());
			pst.setInt(6,  updatedUser.getId());
			
			int affectedRows = pst.executeUpdate();
			System.out.println("Filas modificadas: " + affectedRows);
			
			if(affectedRows > 0) {
				return true;
			}
				
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean updatePlayer(Player player) {

	    String sql = """
	        UPDATE players
	        SET
	            health = ?,
	            attackPoints = ?,
	            blockPoints = ?,
	            healPoints = ?,
	            level = ?,
	            tokens = ?
	        WHERE id_character = ?
	    """;

	    try(
	        Connection connection =
	            DatabaseConnection.getConnection();

	        PreparedStatement pst =
	            connection.prepareStatement(sql)
	    ){

	        pst.setInt(1, player.getHealth());
	        pst.setInt(2, player.getAttackPoints());
	        pst.setInt(3, player.getBlockPoints());
	        pst.setInt(4, player.getHealPoints());
	        pst.setInt(5, player.getLevel());
	        pst.setInt(6, player.getTokens());

	        pst.setInt(7, player.getId());

	        int affectedRows =
	            pst.executeUpdate();

	        return affectedRows > 0;

	    }catch(SQLException ex) {
	        ex.printStackTrace();
	    }

	    return false;
	}
	public int savePlayer(Player player) {

	    String sql = """
	        INSERT INTO players
	        (maxHealth, health, attackPoints,
	         blockPoints, healPoints,
	         level, tokens)
	        VALUES (?, ?, ?, ?, ?, ?, ?)
	    """;

	    try(
	        Connection connection =
	            DatabaseConnection.getConnection();

	        PreparedStatement pst =
	            connection.prepareStatement(
	                sql,
	                Statement.RETURN_GENERATED_KEYS
	            )
	    ){

	        pst.setInt(1, player.getMaxHealth());
	        pst.setInt(2, player.getHealth());
	        pst.setInt(3, player.getAttackPoints());
	        pst.setInt(4, player.getBlockPoints());
	        pst.setInt(5, player.getHealPoints());
	        pst.setInt(6, player.getLevel());
	        pst.setInt(7, player.getTokens());

	        pst.executeUpdate();

	        ResultSet rs = pst.getGeneratedKeys();

	        if(rs.next()) {
	            return rs.getInt(1);
	        }

	    }catch(SQLException ex) {
	        ex.printStackTrace();
	    }

	    return -1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}