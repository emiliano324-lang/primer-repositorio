package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    /**
     *  Hashea una contraseña
     * @param plainTextPassword
     * @return contraseña hasheada
     */
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     *  Verifica una contraseña con el hash almacenado
     * @param plainPassword
     * @param hashedPassword
     * @return true si esta hasheada false si no.
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}