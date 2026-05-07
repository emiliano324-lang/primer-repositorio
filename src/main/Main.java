package main;

import controllers.HomeController;
import controllers.LoginController;
import controllers.RegistrationController;
import views.GameMenuView;
import views.GameWindow;
import views.LoginWindow;
import views.MainWindow;
import views.RegistrationWindow;

public class Main {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//GameWindow window = new GameWindow();
		new HomeController(new MainWindow());

		//LoginWindow login = new LoginWindow();
		//new LoginController(login.getView());
		//new RegistrationController(new RegistrationWindow());
		//RegistrationWindow form = new RegistrationWindow();
		//RegistrationController formController = new RegistrationController(form);

	}
	
}
