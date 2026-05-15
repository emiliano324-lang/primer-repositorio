package proyectoProgramacion2;

import java.util.Random;

public class Caballero extends Personaje {

	public Caballero(String nombre, int vidaMaxima, int vidaActual, int poderAtaque, Arma arma, int nivel, int experiencia,
			boolean tieneEfecto, String tipoEfecto, int duracionEfecto,String clase,boolean estado) {
		super(nombre, vidaMaxima, vidaActual, poderAtaque, arma, nivel, experiencia, tieneEfecto, tipoEfecto, duracionEfecto,clase, estado);
		// TODO Auto-generated constructor stub
	}
	public static Registro[] historial = new Registro[100];
	public static int indiceHistorial = 0;
	@Override
	public void atacar(Personaje objetivo) {
		Random rng = new Random();
		int probAtk =  rng.nextInt(100);
		int probCritico = rng.nextInt(100);
		int dano = 0;
		
		double  multCritico = 1.5;
		int danoVentaja = (int ) (poderAtaque* 0.20);
		int danoConArma = (int) (poderAtaque + arma.getDanoExtra());
		int danoTalc = (int) (poderAtaque);
		//El ataque fallo
		if(fallo(probAtk)) {
			System.err.println("El ataque ha fallado");
			registrarAccion("Ataque fallado",this , objetivo, 0, false, true);
			return;
		}
		
		if(critico(probCritico)) {
			System.out.println("Dano Critico!");
			if(objetivo.getClase().equals("Tanque")) {
				if(tipoEfecto == null || !tipoEfecto.equals("TIROALACABEZA")) {
					//Dano critico con ventaja y con armas
					dano = (int) ((danoConArma+danoVentaja)*multCritico);
					
				}else {
					//Dano con ventaje con critico sin arma
					dano = (int) (poderAtaque+danoVentaja*multCritico);
					
				}
				registrarAccion("Ataque Critico",this , objetivo, dano, true, false);
				System.out.print("Dano Con Ventaja");
				System.out.println("Dano --> "+ dano);
				objetivo.recibirDanio(dano);
				return;
			}
			if(tipoEfecto == null || !tipoEfecto.equals("TIROALACABEZA")){
				//dano sin ventaja con critico sin tiro a la cebeza
				dano = (int) (danoConArma*multCritico);
			}else {
				//dano critico sin ventaja con tiro a la cebeza
				dano = (int) (danoTalc*multCritico);
			}
			registrarAccion("Ataque Critico",this , objetivo, dano, true, false);
			System.out.println("Dano "+ dano);
			objetivo.recibirDanio(dano);
			return;
		}
		if(tipoEfecto == null || !tipoEfecto.equals("TIROALACABEZA")){
			//dano sin critico sin ventaja sin tiro a la cebeza 
			dano = (int) (danoConArma);
		}else {
			//dano sin critico sin ventaja con tiro a la cebeza
			dano = danoTalc ;
		}
		registrarAccion("Ataque normal",this , objetivo, dano, false, false);
		System.out.println("Dano "+ dano);
		objetivo.recibirDanio(dano);
		return;
	}
	public void habilidad(Personaje objetivo) {
		
		if(objetivo.getTieneEfecto()) {
			System.out.println("EL OBJETIVO YA TIENE UN EFECTO");
			return;
		}
		objetivo.setTipoEfecto("ESTOCADA");
		objetivo.setTieneEfecto(true);
		objetivo.setDuracionEfecto(3);
		System.out.println("Se ha aplicado ESTOCADA por 3 turnos");
	}
	@Override
	public void recibirDanio(int danio) {
		this.vidaActual -= danio;
		if(vidaActual < 0) vidaActual = 0;
		
	}

	

	public boolean fallo(int probAtk) {
		 
		return probAtk > arma.getPrecision();
	}
	
	public boolean critico(int probCritico) {
		
		return probCritico > arma.getProbCritico();
	}

	@Override
	public void actualizarEfecto() {
		// TODO Auto-generated method stub
		
	}
	public static void registrarAccion(String accion, Personaje atacante, Personaje objetivo, int danio, boolean critico, boolean fallo) {

	    if (indiceHistorial < historial.length) {
	        historial[indiceHistorial] = new Registro(accion, atacante, objetivo, danio, critico, fallo);
	        indiceHistorial++;
	    }
	}
	public void imprimirAccion() {
		for (int i = 0; i < indiceHistorial; i++) {
			if(historial[i] != null) {
				historial[i].toString();
			}
		}
	}
}
