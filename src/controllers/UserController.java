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
import views.GameMenuView;
import views.UserFormDialog;
import views.UsersView;

public class UserController {

	private UsersView view;
	private UserRepository userRepo;
	private CharacterRepository characterRepo;
	private BattleRepository battleRepo;
	private UserTableModel model;
	private PDFExporter pdfExporter;
	
	
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

		        JOptionPane.showMessageDialog(
		            view,
		            "Selecciona un usuario"
		        );

		        return;
		    }

		    User user = model.getUserAt(row);

		    System.out.println(user.getPlayer());
		    
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
		                JOptionPane.INFORMATION_MESSAGE
		            );

		            loadUsers();
		        }

		    }catch(Exception ex) {

		        ex.printStackTrace();

		        JOptionPane.showMessageDialog(
		            view,
		            "Error al eliminar usuario"
		        );
		    }
		});
		
		/*
		view.getBtnDelete().addActionListener(e ->{
			
			boolean deleted = repo.delete(model.getUserAt(view.getSelectedRow()).getId());
			
			if(deleted) {
				model.removeRow(view.getSelectedRow());
			}
			
			int row = view.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(view, "Selecciona un usuario");
				return;
			}
			System.out.println("...");
			
			try {
	            JOptionPane.showMessageDialog(view, "Se eliminó al usuario", "Usuario eliminado", JOptionPane.INFORMATION_MESSAGE);
				
	            //TODO: Eliminar con conexion a base de datos
	            //System.out.println("se removio");
				//repo.delete(row, model.getUserAt(row));
				loadUsers();
			}catch(Exception ex) {
				
			}
		});
		*/
		 view.getBtnPdf().addActionListener(e -> generatePdf());
		
	}
	
	public void loadUsers() {	
		
		//System.out.println("Carga usuarios");
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