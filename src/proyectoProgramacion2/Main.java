package proyectoProgramacion2;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
/**
 * El siguiente juego consiste de un juego de peleas donde cada personaje podra elegir un personaje de los 8 disponibles
 * al tener los 6 personajes seleccionados el combate empezara , el jugador podra elejir su personaje con el cual atacar,
 * en el combate los jugadores tendran solo un turno en el cual pueden, usar una avilidad, atacar, cambiar de personaje,
 * cuando ese turno aya acabado, solo puede ver un resumen de lo que paso y cambiar de turno.
 * El juego termina cuando los 3 personajes de cualquier jugador aya sido eliminado.
 * @author Hugo
 * @version 1.0
 */
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		boolean salirJuego = true;
		
		//Ciclo principal del juego donde se puede salir al terminar una partida;
		while(salirJuego) {
		
			//arma Atributos(nombre,danoExt,precicion,probCrit)
			//perosnajes Atributos(nombre,Hp,vidaActual,PA,arma,nivel,xp,tieneEfecto,TipoEfecto,duracionEfecto)
			
			Arma weaponRanger = new Arma("Pistola",15,80,25);
			Personaje ranger = new Ranger("Mycol",100, 100, 16, weaponRanger, 1, 0, false, null, 0,"Ranger",true);
			Personaje rangerClon = new Ranger("Mycol",100, 100, 16, weaponRanger, 1, 0, false, null, 0,"Ranger",true);
			
			Arma weaponMago = new Arma("Libro de Hechizos",20,70,15);
			Personaje mago = new Mago("Extarium", 120, 120, 13, weaponMago, 1, 0, false, null, 0,"Mago",true);
			Personaje magoClon = new Mago("Extarium", 120, 120, 13, weaponMago, 1, 0, false, null, 0,"Mago",true);
			
			Arma weaponTanque = new Arma("Martillo de Guerra", 10, 70, 5);
			Personaje tanque = new Tanque("Brutor", 150, 150, 12, weaponTanque, 1, 0, false, null, 0,"Tanque",true);
			Personaje tanqueClon = new Tanque("Brutor", 150, 150, 12, weaponTanque, 1, 0, false, null, 0,"Tanque",true);
			
			Arma weaponCaballero = new Arma("Espada Larga",18,85,10);
			Personaje caballero = new Caballero("Gael", 130, 130, 15, weaponCaballero, 1, 0, false, null, 0,"Caballero",true);
			Personaje caballeroClon = new Caballero("Gael", 130, 130, 15, weaponCaballero, 1, 0, false, null, 0,"Caballero",true);
			
			Personaje[] personajesDisponibles = new Personaje[8];
			personajesDisponibles[0] = caballero;
			personajesDisponibles[1] = caballeroClon;
			personajesDisponibles[2] = mago;
			personajesDisponibles[3] = magoClon;
			personajesDisponibles[4] = ranger;
			personajesDisponibles[5] = rangerClon;
			personajesDisponibles[6] = tanque;
			personajesDisponibles[7] = tanqueClon;
			
			Personaje[] p1 = new Personaje[3];
			Personaje[] p2 = new Personaje[3];
			//ciclioDonde se desarrolla todo el juego principal
			boolean cicloMenu = true;
			boolean turno = true;
			imprimirLogo();
			in.nextLine();
			limpearConsola();
			
			while(cicloMenu) {
			System.out.println("┌────────────────────────────┐\r\n"
							 + "│  MENÚ PRINCIPAL            │\r\n"
							 + "│ 1)Ver personajes           │\r\n"
							 + "│ 2)Elegir P1                │\r\n"
							 + "│ 3)Elegir P2                │\r\n"
							 + "│ 4)Iniciar combate          │\r\n"
							 + "│ 5)Ver reglas               │\r\n"
							 + "│ 6)Salir                    │\r\n"
							 + "└────────────────────────────┘");	
			String eleccionMenu = in.next();
			limpearConsola();
			
			switch(eleccionMenu) {
			case "1":
			    limpearConsola();
			    System.out.println("====================================================");
			    System.out.println("                PERSONAJES DISPONIBLES  ");
			    System.out.println("====================================================\n");

			    for (int i = 0; i < personajesDisponibles.length; i++) {
			    	if(personajesDisponibles[i] != null) {
			    		System.out.println(">>  " + (i + 1) + ") " + personajesDisponibles[i].getNombre()+" >> "+personajesDisponibles[i].getClase());
			    	}
			    }	
			    System.out.println("\n====================================================");
			    System.out.println("                DESCRIPCION DE PERSONAJES  ");
			    System.out.println("====================================================\n");
				System.out.println("MAGO >> VidaMax: " + mago.getVidaMaxima() + 
				                   " >> Poder de Ataque: " + mago.getPoderAtaque() + 
				                   " >> Arma: " + mago.getArma().getNombre() + 
				                   " >> Critico: " + mago.getArma().getProbCritico() + "%" +
				                   " >> Precision: " + mago.getArma().getPrecision() + "%" +
				                   " >> Daño Extra: " + mago.getArma().getDanoExtra()+
				                   "\n>> Habilidad: Silencio ---> El portador de esta maldicion sera\n"
				                   + " silenciado incapacitando la conjuracion de Habilidades por 2 Turnos\n");
		
				System.out.println(" >> RANGER >> VidaMax: " + ranger.getVidaMaxima() + 
				                   " >> Poder de Ataque: " + ranger.getPoderAtaque() + 
				                   " >> Arma: " + ranger.getArma().getNombre() + 
				                   " >> Critico: " + ranger.getArma().getProbCritico() + "%" +
				                   " >> Precision: " + ranger.getArma().getPrecision() + "%" +
				                   " >> Daño Extra: " + ranger.getArma().getDanoExtra()+
				                   "\n>> Habilidad: Disparo a la Cabeza ---> Las armas del portador de esta Habilidad\n"
				                   + "quedaran inutilizadas haciendo solo el dano dase en cada Ataque por 3 Turnos\n");
				
				System.out.println("CABALLERO >> VidaMax: " + caballero.getVidaMaxima() + 
				                   " >> Poder de Ataque: " + caballero.getPoderAtaque() + 
				                   " >> Arma: " + caballero.getArma().getNombre() + 
				                   " >> Critico: " + caballero.getArma().getProbCritico() + "%" +
				                   " >> Precision: " + caballero.getArma().getPrecision() + "%" +
				                   " >> Daño Extra: " + caballero.getArma().getDanoExtra()+
				                   "\n>> Hablilidad: Estocada ---> El enemigo afectado por Estocada se desangrara \n"
				                   + "y perdera 15 de vida por 3 Turnos\n");
		
				System.out.println("TANQUE >> VidaMax: " + tanque.getVidaMaxima() + 
				                   " >> Poder de Ataque: " + tanque.getPoderAtaque() + 
				                   " >> Arma: " + tanque.getArma().getNombre() + 
				                   " >> Critico: " + tanque.getArma().getProbCritico() + "%" +
				                   " >> Precision: " + tanque.getArma().getPrecision() + "%" +
				                   " >> Daño Extra: " + tanque.getArma().getDanoExtra() 
				                   + "\n>> Habilidad: Desesperanza ---> Los golpes Criticos del enemigo afectado por\n"
				                   + "Desesperanza seran inutilizados por 4 Turnos ");
		
				

			    System.out.println("\n====================================================");
			    System.out.print(" Presiona ENTER para regresar al menú...");
			    
			    in.nextLine(); 
			    in.nextLine(); 
			    limpearConsola();
			    break;
			case "2":
				int espacioP1 = 0;
				while(espacioP1 < 3) {
					if(p1[0] == null || p1[1] == null || p1[2] == null) {
						System.out.println("====================================================\r\n"
						+ "              ELIJE TUS PERSONAJES\r\n"
						+ "====================================================\n\n"
						+ "PERSONAJES DISPONIBLES");
						for (int i = 0; i < personajesDisponibles.length; i++) {
							if(personajesDisponibles[i] != null) {
								System.out.println(">>  " + (i + 1) + ") " + personajesDisponibles[i].getNombre()+" >> "+personajesDisponibles[i].getClase());
							}
						}
						int eleccionPersonajeP1 = 0;
						
						while(true) {
							 try {
							        eleccionPersonajeP1 = in.nextInt() - 1;
							        in.nextLine();
							        //primera validacion donde se valida esta en rango o si el objeto seleccionado es null
							        if(eleccionPersonajeP1 < 0 || eleccionPersonajeP1 >= personajesDisponibles.length
							        		|| personajesDisponibles[eleccionPersonajeP1] == null){
							            System.out.println(" Selecciona un personaje válido.");
							            continue; 
							        }

							        boolean repetido = false;

							       //segunda validacion donde se comprueba si ya se selecciono el personaje e impide tomarlo de nuevo
							        for(int i = 0; i < p1.length; i++){
							            if(p1[i] != null && p1[i].getClase().equals(personajesDisponibles[eleccionPersonajeP1].getClase())){
							            	 System.out.println("Ya seleccionaste un personaje de esa clase: "
						                                + personajesDisponibles[eleccionPersonajeP1].getClase());
							                repetido = true;
							                break;
							            }
							        }
						
							        if(repetido){
							            continue; 
							        }
							        break;

							    } catch (InputMismatchException e) {
							        System.out.println("Ingresa un número válido.");
							        in.nextLine(); 
							    }
							
							}
						
						p1[espacioP1] = personajesDisponibles[eleccionPersonajeP1]; 
						System.out.println("✔ Has seleccionado: " + p1[espacioP1].getNombre() + " (" + p1[espacioP1].getClase() + ")");
						espacioP1++;
						personajesDisponibles[eleccionPersonajeP1] = null;
						if(espacioP1 == 3) {
							break;
						}
						limpearConsola();
					    System.out.println("Equipo del Jugador 1 completo:");
					    for (int k = 0; k < p1.length; k++) {
					    	if(p1[k] != null) {
					    		System.out.println((k + 1) + ") " + p1[k].getNombre() + " >> " + p1[k].getClase());
					    	}
					    }
						//cuando el jugador alla elejido 3 personajes se sale de la seleccion;
						if(p1[0] != null && p1[1] != null&& p1[2] != null ) {
							break;
						}
				}else {
					in.nextLine();
					System.err.println("==========================\n"
							+ "Ya has elejido tus 3 personajes"
							+ "\tPRECIONA <=E N T E R=> PARA CONTINUAR");
					in.nextLine();
					limpearConsola();
					break;
				}
				}
				break;
			case "3":
				int espacioP2 = 0;
				while(espacioP2 < 3) {
					if(p2[0] == null || p2[1] == null || p2[2] == null) {
						System.out.println("====================================================\r\n"
						+ "              ELIJE TUS PERSONAJES\r\n"
						+ "====================================================\n\n"
						+ "PERSONAJES DISPONIBLES");
						for (int i = 0; i < personajesDisponibles.length; i++) {
							if(personajesDisponibles[i] != null) {
								System.out.println(">>  " + (i + 1) + ") " + personajesDisponibles[i].getNombre()+" >> "+personajesDisponibles[i].getClase());
							}
						}
						int eleccionPersonajeP2 = 0;
						
						while(true) {
							 try {
							        eleccionPersonajeP2 = in.nextInt() - 1;
							        in.nextLine();
							        //primera validacion donde se valida esta en rango o si el objeto seleccionado es null
							        if(eleccionPersonajeP2 < 0 || eleccionPersonajeP2 >= personajesDisponibles.length
							        		|| personajesDisponibles[eleccionPersonajeP2] == null){
							            System.out.println(" Selecciona un personaje válido.");
							            continue; 
							        }

							        boolean repetido = false;

							       //segunda validacion donde se comprueba si ya se selecciono el personaje e impide tomarlo de nuevo
							        for(int i = 0; i < p2.length; i++){
							            if(p2[i] != null && p2[i].getClase().equals(personajesDisponibles[eleccionPersonajeP2].getClase())){
							            	 System.out.println("Ya seleccionaste un personaje de esa clase: "
						                                + personajesDisponibles[eleccionPersonajeP2].getClase());
							                repetido = true;
							                break;
							            }
							        }
						
							        if(repetido){
							            continue; 
							        }
							        break;

							    } catch (InputMismatchException e) {
							        System.out.println("Ingresa un número válido.");
							        in.nextLine(); 
							    }
							
							}
						
						p2[espacioP2] = personajesDisponibles[eleccionPersonajeP2]; 
						System.out.println("✔ Has seleccionado: " + p2[espacioP2].getNombre() + " (" + p2[espacioP2].getClase() + ")");
						espacioP2++;
						personajesDisponibles[eleccionPersonajeP2] = null;
						if(espacioP2 == 3) {
							break;
						}
						limpearConsola();
					    System.out.println("Equipo del Jugador 2 completo:");
					    for (int k = 0; k < p2.length; k++) {
					    	if(p2[k] != null) {
					    		System.out.println((k + 1) + ") " + p2[k].getNombre() + " >> " + p2[k].getClase());
					    	}
					    }
						//cuando el jugador alla elejido 3 personajes se sale de la seleccion;
						if(p2[0] != null && p2[1] != null&& p2[2] != null ) {
							break;
						}
				}else {
					in.nextLine();
					System.err.println("==========================\n"
							+ "Ya has elejido tus 3 personajes"
							+ "\tPRECIONA <=E N T E R=> PARA CONTINUAR");
					in.nextLine();
					limpearConsola();
					break;
				}
				}
				break;
			case "4":
					if(!(p1[0] == null && p1[1] == null && p1[2] == null ||p2[0] == null && p2[1] == null && p2[2] == null) ) {
						iniciarJuego(p1, p2);
						if(p1[0].getEstado() == false &&p1[1].getEstado() == false &&p1[2].getEstado() == false ) {
							System.out.println("Player 2 GANO");
						}else {
							System.out.println("Player 1 GANO");
						}
						cicloMenu = false;
					}else {
						System.err.println("Tienene que escojer sus personajes");
					}
				break;
			case "5":
					System.out.println("====================================================\r\n"
							+ "              R E G L A S\r\n"
							+ "====================================================\n");

					System.out.println("╔══════════════════════════════════════════════╗\n" +
					"║                OBJETIVO GENERAL              ║\n" +
					"╚══════════════════════════════════════════════╝\n" +
					"► Derrota a los 3 personajes del equipo rival.\n" +
					"► Gana el jugador que elimine a todos los personajes del oponente.\n\n" +
					
					"╔══════════════════════════════════════════════╗\n" +
					"║                    JUGADORES                 ║\n" +
					"╚══════════════════════════════════════════════╝\n" +
					"► Cada jugador deberá elegir 3 personajes diferentes para su equipo.\n\n" +
					
					"╔══════════════════════════════════════════════╗\n" +
					"║                   PERSONAJES                 ║\n" +
					"╚══════════════════════════════════════════════╝\n" +
					"► Existen 4 clases con características únicas:\n" +
					"   - Mago\n" +
					"   - Ranger\n" +
					"   - Caballero\n" +
					"   - Tanque\n" +
					"► Cada uno posee estadísticas distintas.\n" +
					"► Los personajes pueden subir de nivel durante los combates.\n\n" +
					
					"╔══════════════════════════════════════════════╗\n" +
					"║                      ARMAS                   ║\n" +
					"╚══════════════════════════════════════════════╝\n" +
					"► Cada luchador posee un arma según su clase.\n" +
					"► El arma otorga estadísticas adicionales (daño, precisión, crítico).\n\n" +
					
					"╔══════════════════════════════════════════════╗\n" +
					"║                    ATAQUES                   ║\n" +
					"╚══════════════════════════════════════════════╝\n" +
					"► Un ataque puede:\n" +
					"   - Fallar\n" +
					"   - Ser normal\n" +
					"   - Ser crítico\n" +
					"► El resultado depende de las estadísticas del personaje y su arma.\n\n" +
					
					"╔══════════════════════════════════════════════╗\n" +
					"║                   VENTAJAS                   ║\n" +
					"╚══════════════════════════════════════════════╝\n" +
					"► Las clases tienen ventaja según el tipo rival:\n" +
					"   Caballero  ▶ fuerte contra ▶ Ranger\n" +
					"   Ranger     ▶ fuerte contra ▶ Mago\n" +
					"   Mago       ▶ fuerte contra ▶ Tanque\n" +
					"   Tanque     ▶ fuerte contra ▶ Caballero\n" +
					"► Cuando existe ventaja, el atacante obtiene +20% de daño extra.\n");
					in.nextLine(); 
					System.out.print("\tPRECIONA <=E N T E R=> PARA CONTINUAR");
					in.nextLine();
					limpearConsola();
				break;
			case "6":
					System.out.println("====================================================\r\n"
				+ "              SLIENDO... \r\n"
				+ "====================================================\n");
					salirJuego = false;
					cicloMenu = false;
				break;
				default:
			
			}
			
			
			}
		}
	}
	
	public static void limpearConsola() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	public static void imprimirLogo() {
		System.out.println("====================================================\r\n"
				+ "              J U E G O   D E   P E L E A S\r\n"
				+ "====================================================\n"
				+ "\tPRECIONA <=E N T E R=> PARA CONTINUAR");
	}
	public static void menuJugador(boolean turno,Personaje p1,Personaje p2) {
		System.out.print("===== TURNO DEL JUGADOR "+(turno ? "1":"2")+" =====\r\n"
				+ "Personaje activo	 "+(turno ?p1.getNombre():p2.getNombre())+"  \r\n"
				+ "Vida:                    "+(turno ? (p1.getVidaActual()+"/"+p1.getVidaMaxima()):(p2.getVidaActual()+"/"+p2.getVidaMaxima()))+"\r\n"
				+ "Nivel:"+(turno ? p1.getNivel():p2.getNivel())+" 	   	 xp: "+(turno?p1.getExperiencia():p2.getExperiencia())
				+ "\nAtaque:"+(turno?p1.getPoderAtaque():p2.getPoderAtaque())+"\r\n"
				+ "\n1) Atacar\r\n"
				+ "2) Usar habilidad\r\n"
				+ "3) Ver registro de combate\r\n"
				+ "4) Cambiar personaje\r\n"
				+ "5) Terminar Turno\r\n"
				+ ":");
	}
	
	public static void iniciarJuego(Personaje[] p1,Personaje[] p2) {
		Scanner in = new Scanner(System.in);
		boolean turno = true;
		Personaje personajeActualP1 = null;
		Personaje personajeActualP2 = null;
		
		
		System.out.println("Jugador 1 escoje tu personaje:");	
		personajeActualP1 = seleccionarPersonaje(turno, p1, p2);
		turno = false;
		limpearConsola();
		System.out.println("Jugador 2 escoje tu personaje:");
		personajeActualP2 = seleccionarPersonaje(turno, p1, p2);
		turno = true;
		limpearConsola();
		boolean turnoUsado = false;
		boolean ciclioTurno = true;
		boolean hayGanador = false;
		while(!hayGanador) {
			
			
			if (turno) {
				if(personajeActualP1.getEstado() == false) {
					System.out.println("El personaje murio elige otro");
					
				}
			}else {
				if (personajeActualP1.getEstado() == false) {
					System.out.println("El personaje murio elige otro");
				}
			}
			
			while(ciclioTurno) {
				ciclioTurno = true;
				menuJugador(turno,personajeActualP1,personajeActualP2);
				String opcion = in.next();
			
						
				
				switch(opcion) {
				case "1":
					if(!turnoUsado) {
						if (turno) {
							personajeActualP1.atacar(personajeActualP2);
							turnoUsado = true;
							personajeActualP1.actualizarXp(personajeActualP1);
						}else {
							personajeActualP2.atacar(personajeActualP1);
							turnoUsado = true;
							personajeActualP2.actualizarXp(personajeActualP2);
						}
					}else {
						System.err.println("No te quedan acciones");
					}
					
				break;
				case "2":
					if (!turnoUsado) {
						if (turno) {
							if(!(personajeActualP1.getTipoEfecto() != null && personajeActualP1.getTipoEfecto().equals("SILENCIO"))) {
								personajeActualP1.habilidad(personajeActualP2);
								turnoUsado = true;
								personajeActualP1.actualizarXp(personajeActualP1);
							}else {
								System.out.println("El personaje esta silenciado");
							}
						}else {
							if(!(personajeActualP2.getTipoEfecto() != null && personajeActualP2.getTipoEfecto().equals("SILENCIO"))) {
							personajeActualP2.habilidad(personajeActualP1);
							turnoUsado = true;
							personajeActualP2.actualizarXp(personajeActualP2);
							}else {
								System.out.println("El personaje esta silenciado");
							}
						}
					}else {
						System.err.println("No te quedan acciones");
					}
				break;
				case "3":
					imprimirRegistro(turno, p1, p2);
				break;
				case "4":
					if(!turnoUsado) {
						if (turno) {
						    personajeActualP1 = seleccionarPersonaje(turno, p1, p2);
						    
						} else {
						    personajeActualP2 = seleccionarPersonaje(turno, p1, p2);
						}
					}else{
						System.err.println("No te quedan acciones");
					}
				break;
				case "5":
					if (turno) {
						turno = false;
						turnoUsado = false;
						ciclioTurno = false;
						
					}else {
						turno = true;
						turnoUsado = false;
						ciclioTurno = false;
					}	
				break;
				default:		
				}
				if(turno) {
					personajeActualP1.actualizarXp(personajeActualP1);
				}else {
					personajeActualP2.actualizarXp(personajeActualP2);
				}

				System.out.println("\n    ↓    ");
				System.out.println("    ↓    ");
				System.out.println("    ↓    ");
			}
			
			ciclioTurno = true;
			mostrarRecuentoActual(turno, p1, p2, personajeActualP1, personajeActualP2);
		}	
	}
	
	public static Personaje seleccionarPersonaje(boolean turno,Personaje[] p1,Personaje[] p2) {
		Scanner in = new Scanner(System.in);
		while(true) {
			
			System.out.println("===== SELECCIONA UN PERSONAJE  =====\r\n");
			mostrarPersonajesJugador(turno, p1,p2);
			String seleccionar = in.next();
			if(turno) {
				switch(seleccionar) {
				case "1":
					if(p1[0].getEstado() == true) {
						return p1[0];
					}else {
						System.out.println("Este personaje no esta disponible");
					}
					
				case "2":
					if(p1[1].getEstado() == true) {
						return p1[1];
					}else {
						System.out.println("Este personaje no esta disponible");
					}
				case "3":
					if(p1[2].getEstado() == true) {
						return p1[2];
					}else {
						System.out.println("Este personaje no esta disponible");
					}
				default:
					System.err.println("Ingresa un valor correcto");
				}
			}else {
				switch(seleccionar) {
				case "1":
					if(p2[0].getEstado() == true) {
						return p2[0];
					}else {
						System.out.println("Este personaje no esta disponible");
					}
				case "2":
					if(p2[1].getEstado() == true) {
						return p2[1];
					}else {
						System.out.println("Este personaje no esta disponible");
					}
				case "3":
					if(p2[2].getEstado() == true) {
						return p2[2];
					}else {
						System.out.println("Este personaje no esta disponible");
					}
				default:
					System.err.println("Ingresa un valor correcto");
				}
				
			}
			
		}
	}
	
	public static void mostrarPersonajesJugador(boolean turno, Personaje[]p1,Personaje[]p2) {
		if (turno) {
			for (int i = 0; i < p1.length; i++) {
				if(p1[i] !=  null) {
					System.out.println((i+1)+")"+p1[i].getNombre()+" "+p1[i].getClase());
				}
			}
		}else {
			for (int i = 0; i < p2.length; i++) {
				if(p2[i] !=  null) {
					System.out.println((i+1)+")"+p2[i].getNombre()+p2[i].getClase());
				}
			}
		}
	}
	public static void mostrarRecuentoActual(boolean turno,Personaje[]p1,Personaje[]p2,Personaje pA1,Personaje pA2) {
		Scanner in = new Scanner(System.in);
		
		//este tramo decodigo sirve para verificar que tipo de dano es el que tiene
		for (int i = 0; i < p1.length; i++) {
			p1[i].actualizarEfecto();
		}
		
		for (int i = 0; i < p2.length; i++) {
			p2[i].actualizarEfecto();
		}
		
		
		System.out.println("\n=====================================");
		System.out.println("         RESUMEN DEL ATAQUE");
		System.out.println("=====================================");
		System.out.println("VICTIMA\n"
				 			+ "JUGADOR "+(turno ? "1": "2"));
		System.out.println("PERSONAJE "+(turno? pA1.getNombre():pA2.getNombre())+" "
		 					+(turno ?pA1.getVidaMaxima()+ " ---> "+pA1.getVidaActual() : pA2.getVidaMaxima()+ " ---> "+pA2.getVidaActual() )
		 					+"\nDANO TOTAL OBTENIDO ---> "+ (turno ?(pA1.getVidaMaxima()-pA1.getVidaActual()): (pA2.getVidaMaxima()-pA2.getVidaActual())) );   
		System.out.println("\n=====================================");
		System.out.println("         RESUMEN GENERAL");
		System.out.println("=====================================");
		 
		System.out.println("PLAYER 1"); 
		for (int i = 0; i < p1.length; i++) {
			if(p1[i] != null) {
				
				System.out.println(p1[i].getNombre()+" ("+p1[i].getClase()+") "
								+ (!p1[i].getTieneEfecto()?" Sin Alteraciones" 
								:p1[i].getTipoEfecto()+" Turnos Restantes "+p1[i].getDuracionEfecto())+" HP "+ p1[i].getVidaMaxima()+ " ---> "+p1[i].getVidaActual());
			 }
		}
		System.out.println("\nPLAYER 2"); 
		for (int i = 0; i < p2.length; i++) {
			if(p2[i] != null) {
				System.out.println(p2[i].getNombre()+" ("+p2[i].getClase()+") "
								+ (!p2[i].getTieneEfecto()?" Sin Alteraciones" 
								:p2[i].getTipoEfecto()+" Turnos Restantes "+p2[i].getDuracionEfecto())+" HP "+ p2[i].getVidaMaxima()+ " ---> "+p2[i].getVidaActual());
			}
		}
		
		
		
		System.out.println("\"\\tPRECIONA <=E N T E R=> PARA CONTINUAR\"");
		in.nextLine();
		limpearConsola();	 
	}
	
	public static void imprimirRegistro(boolean turno,Personaje[] p1,Personaje[]p2) {
		
		if(turno) {
			for (int i = 0; i < p1.length; i++) {
				System.out.print(p1[i].getNombre());
				p1[i].imprimirAccion();
				System.out.println("  ⬆");
				System.out.println("  ⬆");
				System.out.println("  ⬆");
			}	
		}else {
			for (int i = 0; i < p2.length; i++) {
				System.out.print(p2[i].getNombre());
				p2[i].imprimirAccion();
				System.out.println("  ⬆");
				System.out.println("  ⬆");
				System.out.println("  ⬆");
			}
		}
	}
		
}
	
	

