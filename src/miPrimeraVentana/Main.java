package miPrimeraVentana;

import controllers.HomeController;
import controllers.LoginController;
import controllers.RegistrationController;
import views.LoginWindow;
import views.MainWindow;
import views.RegistrationWindow;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//new HomeController(new MainWindow());

		//LoginWindow login = new LoginWindow();
		//new LoginController(login.getView());

		RegistrationWindow form = new RegistrationWindow();
		RegistrationController formController = new RegistrationController(form);

	}
	
}
