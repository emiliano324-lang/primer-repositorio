package miPrimeraVentana;

import views.RegistrationWindow;
import controllers.LoginController;
import views.LoginWindow;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LoginWindow login = new LoginWindow();
		
		new LoginController(login.getView());
		//RegistrationWindow form = new RegistrationWindow();
	}
	
}
