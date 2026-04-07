package exceptions;

public class InvalidPasswordException extends Exception {
	
	public InvalidPasswordException() {
		super("La contraseña o el correo es incorrecto");
	}
}
