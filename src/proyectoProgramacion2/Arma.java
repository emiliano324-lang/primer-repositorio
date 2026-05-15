package proyectoProgramacion2;


/**
 * @return
 */
public class Arma {
	
	protected String nombre;
	protected int danoExtra;
	protected double precision;
	protected double probCritico;
	
	public Arma() {
		
	}
	/**
	 * @param String nombre, in danoExtra, double precision, doubel probCritico
	 */
	public Arma(String nombre, int danoExtra, double precision, double probCritico) {
	
		this.nombre = nombre;
		this.danoExtra = danoExtra;
		this.precision = precision;
		this.probCritico = probCritico;
	}
	/**
	 * Este metodo obtiene el nombre del atributo
	 * @return String nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Este metodo cambia el nombre del atributo
	 * @param String nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Este metodo obtiene el dano extra
	 * @return int danoExtra
	 */
	public int getDanoExtra() {
		return danoExtra;
	}
	/**
	 * Este metodo cambia el dano extra
	 * @param int danoExtra
	 */
	public void setDanoExtra(int danoExtra) {
		this.danoExtra = danoExtra;
	}
	/**
	 * Este metodo obtiene la precision del atributo
	 * @return double precision
	 */
	public double getPrecision() {
		return precision;
	}
	/**
	 * Este metodo cambia la precision del artibuto
	 * @param double precision
	 */
	public void setPrecision(double precision) {
		this.precision = precision;
	}
	/**
	 * Este metodo obtiene la probabilidad de critico
	 * @return double probCritico
	 */
	public double getProbCritico() {
		return probCritico;
	}
	/**
	 * Este metodo cambia el danoCritico
	 * @param double danoCritico
	 */
	public void setProbCritico(double probCritico) {
		this.probCritico = probCritico;
	}
	
	
	
	
}
