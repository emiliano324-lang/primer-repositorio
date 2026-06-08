package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DatabaseConnection;
import enums.Role;
import models.User;
import utils.PasswordUtils;
/**
 * Repositorio encargado de gestionar la autenticación de usuarios en el sistema.
 * Consulta las credenciales en la base de datos y valida la seguridad de los accesos.
 * @author Hugo 
 * @author Emiliano 
 * @version 1.0
 */
public class LoginRepository {
	/**
	 * Realiza la validación de credenciales para el inicio de sesión de un usuario.
	 * <p>Busca al usuario mediante su correo electrónico (mapeado en el parámetro 'name') y, 
	 * si existe, compara la contraseña en texto plano con el hash almacenado utilizando </p>
	 * 
	 *  @param name el correo electrónico introducido por el usuario para identificarse.
	 * @param password La contraseña en texto plano que se va a verificar.
	 * @return Un objeto completamente cargado si las credenciales son válidas; si el usuario no existe o si la contraseña es incorrecta.
	 */
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
