package exceptions;
/**
 * Excepción personalizada utilizada para indicar que un usuario no es válido, 
 * ya sea por inconsistencias en sus datos, problemas de registro o violación de reglas de negocio.
 * <p>Al extender de {@link Exception}, funciona como una excepción de tipo 
 * "checked" (verificada), requiriendo su captura explícitamente mediante bloques 
 * {@code try-catch} o su declaración en la firma de los métodos con {@code throws}.</p>
 */
public class InvalidUserException extends Exception {
	/**
	 * Construye una nueva excepción con un mensaje específico sobre la validación fallida.
	 * * @param message El mensaje descriptivo que explica la razón por la cual el usuario no es válido.
	 */
	public InvalidUserException(String message) {
		super(message);
	}
}
