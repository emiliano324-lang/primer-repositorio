package exceptions;
/**
 * Excepción personalizada utilizada para indicar que una contraseña no cumple 
 * con las reglas de validación o seguridad requeridas por el sistema.
 * <p>Al heredar de {@link Exception}, se clasifica como una excepción de tipo 
 * "checked" (verificada), lo que obliga al desarrollador a gestionarla explícitamente 
 * mediante bloques {@code try-catch} o declararla con la palabra clave {@code throws}.</p>
 */
public class InvalidPasswordException extends Exception {
	/**
	 * Construye una nueva excepción con un mensaje detallado que explica la causa del fallo.
	 * * @param message El mensaje descriptivo que detalla la razón por la cual la contraseña es inválida.
	 */
	public InvalidPasswordException(String message) {
		super(message);
	}
}
