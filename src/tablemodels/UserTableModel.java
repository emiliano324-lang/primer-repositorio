package tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.User;
/**
 * Modelo de datos personalizado para gestionar y mostrar una lista de objetos.
 * @author Hugo 
 * @author Emiliano 
 * @version 1.0
 */
public class UserTableModel extends AbstractTableModel{

	private List<User> users;
	
	private final String[] columns = {
			"Nombre",
			"Email",
			"Sexo",
			"Rol"
	};
	/**
	 * Constructor del modelo de tabla.
	 * * @param users Lista inicial de usuarios que se mostraran en la tabla.
	 */
	public UserTableModel(List<User> users) {
		this.users = users;
	}
	/**
	 * Devuelve la cantidad de filas en la tabla, que equivale al número de usuarios.
	 * * @return Cantidad de usuarios en la lista.
	 */
	@Override
	public int getRowCount() {
		return users.size();
	}
	/**
	 * Devuelve la cantidad de columnas definidas en la tabla.
	 * * @return Número total de columnas de cabecera.
	 */
	@Override
	public int getColumnCount() {
		return columns.length;
	}	
	/**
	 * Devuelve la cantidad de columnas definidas en la tabla.
	 * * @return Número total de columnas de cabecera.
	 */
	public String getColumnName(int column) {
		return columns[column];
	}
	/**
	 * Devuelve la cantidad de columnas definidas en la tabla.
	 * * @return Número total de columnas de cabecera.
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		User user = users.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return user.getName();
		case 1:
			return user.getEmail();
		case 2:
			return user.getSex();
		case 3:
			return user.getRole();
		}
		
		return null;
	}
	/**
	 * Devuelve la cantidad de columnas definidas en la tabla.
	 * * @return Número total de columnas de cabecera.
	 */
	public User getUserAt(int row) {
		return users.get(row);
	}
	/**
	 * Reemplaza la lista actual de usuarios por una nueva y refresca visualmente la tabla entera.
	 * * @param users Nueva lista de usuarios.
	 */
	public void setUsers(List<User> users) {
		this.users = users;
		fireTableDataChanged();
	}
	/**
	 * Reemplaza la lista actual de usuarios por una nueva y refresca visualmente la tabla entera.
	 * * @param users Nueva lista de usuarios.
	 */
	public void removeRow(int row) {
		users.remove(row);
		fireTableRowsDeleted(row, row);
	}

	/**
	 * Reemplaza la lista actual de usuarios por una nueva y refresca visualmente la tabla entera.
	 * * @param users Nueva lista de usuarios.
	 */
	public void addRow(User user) {
		int row = users.size();
		users.add(user);
		fireTableRowsInserted(row, row);
	}
	/**
	 * Reemplaza la lista actual de usuarios por una nueva y refresca visualmente la tabla entera.
	 * * @param users Nueva lista de usuarios.
	 */
	public void updateRow(int row, User user) {
		users.set(row, user);
		fireTableRowsUpdated(row, row);
	}
	
}
