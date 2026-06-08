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
import models.Enemy;
import models.Player;
import models.User;
/**
 * Repositorio encargado de gestionar la persistencia y operaciones CRUD de los usuarios en la base de datos.
 * 
 * @author Hugo 
 * @author Emiliano 
 * @version 1.0
 */
public class UserRepository {
	
	CharacterRepository characterRepo = new CharacterRepository();
	/**
	 * Guarda un nuevo usuario en la base de datos si no existe uno con el mismo nombre o email.
	 * Encripta la contraseña con BCrypt y crea automáticamente su personaje asociado.
	 * * @param user El objeto con la información a registrar.
	 * @return true si el usuario se registró con éxito; false si ya existe o hubo un problema.
	 * @throws IOException Si ocurre un error en la comunicación con los repositorios.
	 */
	public boolean save(User user) throws IOException {

		if(searchUser(user.getName(), user.getEmail()) != null) {
			return false;
		}
		
		List<User> users = getUsers();

		String sql = "INSERT INTO users (name, password, email, sex, role) VALUES (?,?,?,?,?)";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

			pst.setString(1, user.getName());
			pst.setString(2, hashedPassword);
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getSex().name());
			pst.setString(5, user.getRole().name());

			int affectedRows = pst.executeUpdate();

			if (affectedRows > 0) {

				ResultSet rs = pst.getGeneratedKeys();

				if (rs.next()) {

					int generatedId = rs.getInt(1);

					user.setId(generatedId);

					characterRepo.createPlayer(user);				
				}

				users.add(user);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return true;
	}
	/**
	 * Obtiene la lista completa de usuarios registrados en el sistema.
	 * * @return Una lista de objetos.
	 * @throws IOException Si ocurre un error al procesar el mapeo de datos.
	 */
	public List<User> getUsers() throws IOException {

		List<User> users = new ArrayList<User>();

		try (Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM users");) {
			while (rs.next()) {

				int id = rs.getInt("id_user");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Sex sex = Sex.valueOf(rs.getString("sex"));
				Role role = Role.valueOf(rs.getString("role"));
				// String imagePath = rs.getString("imagePath");

				User user = new User(id, name, email, sex, role);

				users.add(user);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return users;
	}
	/**
	 * Elimina un usuario de la base de datos utilizando su ID.
	 * * @param id identificador del usuario a eliminar (id_user).
	 * @return true si se eliminó correctamente; false en caso contrario.
	 */
	public boolean delete(int id) {

		String sql = "DELETE FROM users WHERE id_user = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Usuario eliminado");
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}
	/**
	 * Actualiza los datos de un usuario existente en la base de datos y sincroniza su personaje.
	 * * @param index Índice de referencia en el flujo de la aplicación.
	 * @param updatedUser Objeto {@link User} modificado con los nuevos valores.
	 * @return true si la actualización en base de datos fue exitosa; false de lo contrario.
	 * @throws IOException Si ocurre un error al sincronizar con el repositorio de personajes.
	 */
	public boolean update(int index, User updatedUser) throws IOException {

		String sql = "UPDATE users SET name = ?, password = ?, email = ?, sex = ?, role = ? WHERE id_user = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			String hashedPassword = BCrypt.hashpw(updatedUser.getPassword(), BCrypt.gensalt());

			pst.setString(1, updatedUser.getName());
			pst.setString(2, hashedPassword);
			pst.setString(3, updatedUser.getEmail());
			pst.setString(4, updatedUser.getSex().name());
			pst.setString(5, updatedUser.getRole().name());
			// pst.setString(5, updatedUser.getImagePath());
			pst.setInt(6, updatedUser.getId());

			int affectedRows = pst.executeUpdate();
			System.out.println("Filas modificadas: " + affectedRows);

			if (affectedRows > 0) {
				
				characterRepo.updatePlayer(characterRepo.loadPlayer(updatedUser.getId()), updatedUser.getName());
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	/**
	 * Busca un usuario en la base de datos que coincida con el nombre o el email proporcionados.
	 * * @param name Nombre de usuario a buscar.
	 * @param email Correo electrónico a buscar.
	 * @return Un objeto {@link User} si se encuentra coincidencia; null en caso contrario.
	 */
	public User searchUser(String name, String email) {
		
		String sql = "SELECT * FROM users WHERE name = ? OR email = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)){
			
			pst.setString(1, name);
			pst.setString(2, email);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
				User user = new User(
						rs.getString("name"),
						rs.getString("email"),
						Sex.valueOf(rs.getString("sex")),
						Role.valueOf(rs.getString("role"))
						);
				return user;
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	/**
	 * Verifica si las credenciales (nombre o email) pertenecen a otro usuario diferente al actual.
	 * Útil para evitar duplicados durante el proceso de edición de perfiles.
	 * * @param name Nombre de usuario que se desea validar.
	 * @param email Correo electrónico que se desea validar.
	 * @param currentUserId Identificador del usuario actual que está realizando la edición.
	 * @return true si existe otro registro con ese nombre o email; false de lo contrario.
	 */
	public boolean existsOtherUser(String name, String email, int currentUserId) {

	    String sql = "SELECT id_user FROM users WHERE (name = ? OR email = ?)  AND id_user <> ? ";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement pst = connection.prepareStatement(sql)) {

	        pst.setString(1, name);
	        pst.setString(2, email);
	        pst.setInt(3, currentUserId);

	        ResultSet rs = pst.executeQuery();

	        return rs.next();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return false;
	}
}