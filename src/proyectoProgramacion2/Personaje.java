	package proyectoProgramacion2;

public abstract class Personaje {

	protected String nombre;
	protected int vidaMaxima;
	protected int vidaActual;
	protected int poderAtaque;
	protected Arma arma;
	protected int nivel;
	protected int experiencia;
	protected boolean tieneEfecto;
	protected String tipoEfecto;
	protected int DuracionEfecto;
	protected String clase;
	protected boolean estado;
	
	public Personaje() {
		
	}
	public Personaje(String nombre, int vidaMaxima, int vidaActual, int poderAtaque, Arma arma, int nivel,
			int experiencia, boolean tieneEfecto, String tipoEfecto, int duracionEfecto,String clase,boolean estado) {
		super();
		this.nombre = nombre;
		this.vidaMaxima = vidaMaxima;
		this.vidaActual = vidaActual;
		this.poderAtaque = poderAtaque;
		this.arma = arma;
		this.nivel = nivel;
		this.experiencia = experiencia;
		this.tieneEfecto = tieneEfecto;
		this.tipoEfecto = tipoEfecto;
		DuracionEfecto = duracionEfecto;
		this.clase = clase;
		this.estado = estado;
	}
	/**
	 *El metodo dobuelve el nombre del atributo 
	 * @return String nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * El metodo cambua el nombre del atributo
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * El mentodo obtiene la vida maxima del atributo
	 * @return vidaMaxima
	 */
	public int getVidaMaxima() {
		return vidaMaxima;
	}
	/**
	 * El metodo cambia la vidaMaxima del atributo
	 * @param vidaMaxima
	 */
	public void setVidaMaxima(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}
	/**
	 * El metodo obtiene la vidaActual del objetivo
	 * @return vidaActial
	 */
	public int getVidaActual() {
		return vidaActual;
	}
	/**
	 * El metodo cambia la vidaActual del objetivo
	 * @param vidaActual
	 */
	public void setVidaActual(int vidaActual) {
		this.vidaActual = vidaActual;
	}
	/**
	 * El metodo obtiene el poder de ataque del objetivo
	 * @return podeAtaque
	 */
	public int getPoderAtaque() {
		return poderAtaque;
	}
	/**
	 * El metodo cambia de poder de ataque del objetivo
	 * @param poderAtaque
	 */
	public void setPoderAtaque(int poderAtaque) {
		this.poderAtaque = poderAtaque;
	}
	/**
	 * El metodo obtiene el arma del atributo
	 * @return arma
	 */
	public Arma getArma() {
		return arma;
	}
	/**
	 * El metodo cambia el arma del atributo
	 * @param arma
	 */
	public void setArma(Arma arma) {
		this.arma = arma;
	}
	/**
	 * El metodo obtine el nivel
	 * @return nivel
	 */
	public int getNivel() {
		return nivel;
	}
	/**
	 * El metodo cambia el nivel del atributo
	 * @param nivel
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	/**
	 * El metodo obtienen la experiencia del atributo
	 * @return experiencia
	 */
	public int getExperiencia() {
		return experiencia;
	}
	/**
	 * El metodo cambia la experiencia del atributo
	 * @param experiencia
	 */
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
	/**
	 * El metodo obtiene el estado del efecto del atributo
	 * @return tieneEfecto
	 */
	public boolean getTieneEfecto() {
		return tieneEfecto;
	}
	/**
	 * El metodo cambia el estado  de efecto del atributo
	 * @param tieneEfecto
	 */
	public void setTieneEfecto(boolean tieneEfecto) {
		this.tieneEfecto = tieneEfecto;
	}
	/**
	 * El metodo obtiene el tipo de efecto del atributo
	 * @return tipoEfecto
	 */
	public String getTipoEfecto() {
		return tipoEfecto;
	}
	/**
	 * El metodo cambia del tipo de efecto del atributo
	 * @param tipoEfecto
	 */
	public void setTipoEfecto(String tipoEfecto) {
		this.tipoEfecto = tipoEfecto;
	}
	/**
	 * El metodo obtiene la duracion del efecto del atributo
	 * @return DuracionEfecto
	 */
	public int getDuracionEfecto() {
		return DuracionEfecto;
	}
	/**
	 * Ll metodo Cambia la duracion del efecto del atributo
	 * @param duracionEfecto
	 */
	public void setDuracionEfecto(int duracionEfecto) {
		DuracionEfecto = duracionEfecto;
	}
	/**
	 * El metodo obtiene la calse del atributo
	 * @return clase
	 */
	public String getClase() {
		return clase;
	}
	/**
	 * El metodo cambia la calse del atributo
	 * @param clase
	 */
	public void setClase(String clase) {
		this.clase = clase;
	}
	/**
	 * El metodo obtiene el estado de vida en el que se enceunta del atributo
	 * @return
	 */
	public boolean getEstado() {
		return estado;
	}
	/**
	 * El metodo cambia el estado del atributo
	 * @param estado
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	/**
	 * Este metodo se encarga de infigir dano al obgetivo, el parametro "Objetivo" es el personaje el cual va a resivir
	 * el dano.
	 * @param objetivo
	 */
	public abstract void atacar(Personaje objetivo);
	/**
	 * Este metodo se encarga de infligir una habilidad a un objetivo
	 * @param objetivo
	 */
	public abstract void habilidad(Personaje objetivo);
	/**
	 * Este metodo se encarga de reducir la vida del personaje 
	 * @param danio
	 */
	public abstract void recibirDanio(int danio);
	//
	
	public abstract void imprimirAccion();
	/**
	 * Este metodo actualiza todos los efectos en el cual los personajes ayan sido afectado 
	 * tambien verifica si un personaje murio
	 */
	public void actualizarEfecto() {
	
		if (!tieneEfecto) return;

		switch(tipoEfecto) {
	    	case "SILENCIO":
	            DuracionEfecto--;

	            if (DuracionEfecto <= 0) {
	                tieneEfecto = false;
	                tipoEfecto = null;
	            }
	        break;
	        case "DESESPERANZA":
	        	arma.setProbCritico(0);
	        	DuracionEfecto--;

	            if (DuracionEfecto <= 0) {
	                tieneEfecto = false;
	                tipoEfecto = null;
	                arma.setProbCritico(this.arma.probCritico); 
	            }
	       break;
	       case "ESTOCADA":
	    	   DuracionEfecto--;
	    	   recibirDanio(15);
	    	   if (DuracionEfecto <= 0) {
	    		   tieneEfecto = false;
	               tipoEfecto = null;
	           }
	       break;
	       case "TIROALACABEZA":
	    	   DuracionEfecto--;
	    	   if (DuracionEfecto <= 0) {
	    		   tieneEfecto = false;
	               tipoEfecto = null;
	           }
	       break;
	    }
		if(vidaActual <= 0) {
			estado = false;
		}	
	}
	/**
	 * Este metodo obtiene la xp del personaje actual que tenga el jugador y suma dependiendo de las acciones que el haga
	 * Tambien se encarga de subir de nivel dependiendo de la xp obtenida
	 * @param personajeActual
	 */
	public void actualizarXp(Personaje personajeActual) {
		int xp = personajeActual.getExperiencia();
		personajeActual.setExperiencia(xp + 10);
		if(xp == 30 && personajeActual.getNivel() != 2) {
			System.out.println("NUEVO NIVEL ");
			personajeActual.setNivel(2);
			personajeActual.setPoderAtaque((int)(personajeActual.getPoderAtaque()*0.15)+personajeActual.getPoderAtaque());
			personajeActual.setVidaMaxima((int)(personajeActual.getVidaMaxima()*0.20)+personajeActual.getVidaMaxima());
			this.vidaActual = vidaMaxima;
		}
		if(xp == 60 && personajeActual.getNivel() != 3) {
			System.out.println("NUEVO NIVEL ");
			personajeActual.setNivel(3);
			personajeActual.setPoderAtaque((int)(personajeActual.getPoderAtaque()*0.15)+personajeActual.getPoderAtaque());
			personajeActual.setVidaMaxima((int)(personajeActual.getVidaMaxima()*0.20)+personajeActual.getVidaMaxima());
			this.vidaActual = vidaMaxima;
		}
		if(xp == 100&& personajeActual.getNivel() != 4) {
			System.out.println("NUEVO NIVEL ");
			personajeActual.setNivel(4);
			personajeActual.setPoderAtaque((int)(personajeActual.getPoderAtaque()*0.15)+personajeActual.getPoderAtaque());
			personajeActual.setVidaMaxima((int)(personajeActual.getVidaMaxima()*0.20)+personajeActual.getVidaMaxima());
			this.vidaActual = vidaMaxima;
		}
		if(xp == 150 && personajeActual.getNivel() != 5) {
			System.out.println("NUEVO NIVEL ");
			personajeActual.setNivel(5);
			personajeActual.setPoderAtaque((int)(personajeActual.getPoderAtaque()*0.15)+personajeActual.getPoderAtaque());
			personajeActual.setVidaMaxima((int)(personajeActual.getVidaMaxima()*0.20)+personajeActual.getVidaMaxima());
			this.vidaActual = vidaMaxima;
		}
	}
	
}
