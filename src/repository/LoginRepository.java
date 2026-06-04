package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DatabaseConnection;
import enums.Role;
import models.User;
import utils.PasswordUtils;

public class LoginRepository {
	
	public User login(String name, String password) {
		
		String sql = "SELECT id_user, name, email, password, role FROM users WHERE email = ?";
		
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
				user.setId(rs.getInt("id_user"));
				user.setName(rs.getString("name"));
				user.setPassword(hashedPassword);
				user.setEmail(rs.getString("email"));
				
				String role = rs.getString("role");

				user.setRole(Role.valueOf(role));
				return user;
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
