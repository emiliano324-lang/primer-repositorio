package proyectoProgramacion2;
/**
 * Esta clase es la encargade de tomar registro de todas las acciones hechas por un personaje
 */
public class Registro {

    private String accion;
    private Personaje atacante;
    private Personaje objetivo;
    private int danio;
    private boolean critico;
    private boolean fallo;
    
    
    public Registro(String accion, Personaje atacante, Personaje objetivo, int danio, boolean critico, boolean fallo) {
        this.accion = accion;
        this.atacante = atacante;
        this.objetivo = objetivo;
        this.danio = danio;
        this.critico = critico;
        this.fallo = fallo;
    }
    /**
     * Este metodo sirve para mandar un String para posteriormente guardarlo
     */
    public String toString() {
        return  "---------------------------------------------\n" +
                "Acción: " + accion + "\n" +
                "Atacante: " + atacante.getNombre() + "\n" +
                "Objetivo: " + objetivo.getNombre() + "\n" +
                "Daño: " + danio + "\n" +
                "Crítico: " + (critico ? "Sí" : "No") + "\n" +
                "Falló: " + (fallo ? "Sí" : "No") + "\n";
    }
    
}
