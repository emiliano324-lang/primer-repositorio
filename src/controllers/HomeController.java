package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.User;
import repository.UserRepository;
import tablemodels.UserTableModel;
import views.GameMenuView;
import views.GameWindow;
import views.LoginWindow;
import views.MainWindow;

public class HomeController {

	private MainWindow view;
	private UserController userController;
	
	public HomeController(MainWindow view) {
		
		this.view = view;
		registerListeners();
		
	}
	
	public void registerListeners() {
		
		view.mItemExit.addActionListener(e -> handleClose());
		
		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
			}
		});
		
		view.btnHome.addActionListener(e -> {
			view.showView(MainWindow.HOME);
			updateMenuState(MainWindow.HOME);
		});
		 
		view.btnUsers.addActionListener(e -> { 
			showUsers(); 
		});
		
		view.btnPlay.addActionListener(e -> handlePlay());
		
	}
	

	
	private void showUsers() {
		if(userController == null) {
			userController = new UserController(view.usersPanel);
		}
			
		userController.loadUsers();
		
		view.showView(MainWindow.USERS);
		updateMenuState(MainWindow.USERS);
	}
	
	private void updateMenuState(String viewName) {
		view.btnUsers.setEnabled(!viewName.equals(MainWindow.USERS));
		view.btnHome.setEnabled(!viewName.equals(MainWindow.HOME));
	}
	private void handlePlay() {
		new GameWindow();
		System.out.println("Se abrio la ventana");
		view.dispose();
		System.out.println("Se cerro esta ventana");
	}
	private void handleClose() {
		int option = view.confirmExit();

		if (option == JOptionPane.YES_OPTION) {
			new LoginWindow();
			view.dispose();
		}
	}

   
}