package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DatabaseConnection;
import models.User;

public class LoginRepository {
	
	public User login(String name, String password) {
		
		String sql = "SELECT id, name, email, password FROM users WHERE name = ? AND password = ?";
		
		try (
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			
			stmt.setString(1, name);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				
				return user;
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
