package exceptions;

public class InvalidUserException extends Exception {

	public InvalidUserException() {
		super("El correo no coincide");
		
	}
}
