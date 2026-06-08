package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.User;
import repository.BattleRepository;
import repository.CharacterRepository;
import repository.UserRepository;
import services.PDFExporter;
import tablemodels.UserTableModel;
import utils.Session;

import views.UserFormDialog;
import views.UsersView;
/**
 * Controlador encargado de la gestión de usuarios (CRUD) desde el panel de administración.
 * Coordina las operaciones de adición, edición, eliminación lógica en cascada (mejoras, batallas, personaje y usuario),
 * la carga de datos en la tabla de la interfaz y la exportación de reportes a formato PDF.
 * 
 */
public class UserController {

	private UsersView view;
	private UserRepository userRepo;
	private CharacterRepository characterRepo;
	private BattleRepository battleRepo;
	private UserTableModel model;
	private PDFExporter pdfExporter;
	
	/**
	 * Constructor del controlador de usuarios.
	 * Asigna la vista principal de administración, inicializa los componentes de servicio 
	 * y registra los listeners de eventos para las operaciones del panel.
	 * * @param view vista grafica que contiene la tabla y los controles de gestión de usuarios.
	 */
	public UserController(UsersView view) {
		
		this.view = view;
		
		userRepo = new UserRepository();
		pdfExporter = new PDFExporter();
		
		this.view.getBtnAdd().addActionListener(e -> {
			openForm(null);
		});
		
		this.view.getBtnEdit().addActionListener(e -> {
			int row = view.getSelectedRow();
			
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Selecciona un usuario");
				return;
			}
			
			openForm(model.getUserAt(row));
			
		});
		
		view.getBtnDelete().addActionListener(e -> {

		    int row = view.getSelectedRow();

		    if(row == -1) {
		        JOptionPane.showMessageDialog(view, "Selecciona un usuario");
		        return;
		    }

		    User user = model.getUserAt(row);

		    if(user.getId() == Session.getCurrentUser().getId()) {
		        JOptionPane.showMessageDialog(
		                view,
		                "No puedes eliminar tu propio usuario mientras tienes la sesión iniciada.",
		                "Operación no permitida",
		                JOptionPane.WARNING_MESSAGE);
		        return;
		    }
		    
		    try {
			    	battleRepo = new BattleRepository();
			    	characterRepo = new CharacterRepository();
			    	
			    	characterRepo.deleteUpgrades(user.getId());
			    	
			    	battleRepo.deleteBattle(user.getId());
		    	
		        characterRepo.deletePlayer(user.getId());
		        
		        boolean deleted = userRepo.delete(user.getId());

		        if(deleted) {

		            model.removeRow(row);

		            JOptionPane.showMessageDialog(
		            		view, 
		            		"Se eliminó al usuario y su personaje",
		            		"Usuario eliminado",
		            		JOptionPane.INFORMATION_MESSAGE);

		            loadUsers();
		        }

		    }catch(Exception ex) {

		        ex.printStackTrace();

		        JOptionPane.showMessageDialog(view,"Error al eliminar usuario");
		    }
		});
		
		 view.getBtnPdf().addActionListener(e -> generatePdf());
	}
	/**
	 * Recupera la lista actualizada de usuarios desde el repositorio y la vuelca en el
	 * modelo de la tabla para refrescar los datos visibles en la interfaz gráfica.
	 */
	public void loadUsers() {	
		
		try {
			List<User> users = userRepo.getUsers();
			
			if(model == null) {
				model = new UserTableModel(users);
				view.setTableModel(model);
			}else {
				model.setUsers(users);
			}
			
		}catch (IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
	}
	/**
	 * Abre el cuadro de diálogo modal del formulario para la inserción o edición de un usuario.
	 * Si el formulario determina que los datos fueron guardados con éxito, procesa la persistencia
	 * correspondiente y actualiza la vista de la tabla.
	 * * @param user el objeto que se desea editar, o null si se trata de una inserción nueva.
	 */
	private void openForm(User user) {

	    UserFormDialog dialog = new UserFormDialog(null);
	    UserFormController controller = new UserFormController(dialog, user);

	    dialog.setVisible(true);

	    if(controller.isSaved()) {
	        User savedUser = controller.getUser();
	        
	        try {
	            if(user == null) {
	                userRepo.save(savedUser);
	            } else {
	                int row = view.getSelectedRow();
	                userRepo.update(row, savedUser);
	            }
	            loadUsers();

	        } catch(Exception e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(view, e.getMessage());
	        }
	    }
	}
	/**
	 * Despliega un selector de archivos para guardar el reporte, delega al servicio
	 * la estructuración del documento con la lista de usuarios y, de ser compatible con el sistema operativo, 
	 * abre el archivo PDF generado de forma automatica.
	 */
	public void generatePdf() {
		
		File file = view.selectPdfFile();
		
		if(file == null) {
			return;
		}
		try {
			pdfExporter.exportUsers(userRepo.getUsers(), file);
			
			if(Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Error al exportar");
		}
	}
}