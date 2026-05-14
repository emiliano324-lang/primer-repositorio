package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DatabaseConnection;
import models.User;
import utils.PasswordUtils;

public class LoginRepository {
	
	public User login(String name, String password) {
		
		String sql = "SELECT id, name, email, password FROM users WHERE name = ?";
		
		try (
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
		){
			
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
				String hashedPassword = rs.getString("password");
				
				boolean correctPassword = PasswordUtils.checkPassword(password, hashedPassword);
				
				if(!correctPassword) {
					return null;
				}
				
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(hashedPassword);
				user.setEmail(rs.getString("email"));
				
				return user;
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
