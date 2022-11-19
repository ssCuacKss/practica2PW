package display;

import business.gestores.GestorPistas;
import business.pista.Dificultad;
import business.kart.Estado;
import java.util.Scanner;

/**
 * @author Carlos Lucena Robles.
 * @author Miguel Raigón Jiménez.
 * @author Pablo Roldán Puebla.
 * @author Paloma Romero Delgado.
 * @author Kamal Abdelkader Haddu.
 * @version 1.0.0
 */

/**
 * Display del Gestor de Pistas
 */

public class DisplayGestorPista{
	
	static Scanner scn;
	
	 /**
	  * Para crear kart nuevo
	  */
	
	public static void nuevoKart() {
		GestorPistas gestor = new GestorPistas();
		scn = new Scanner(System.in);
		
		System.out.println("* Inserte el numero de karts a introducir: ");
		int numkarts = scn.nextInt();
		scn.nextLine();
		
		//Control de numero de karts
		if(numkarts <= 0) {
			System.out.println("\n* Numero de kart invalido");
			return;
		}
		
		int i=0;
		while(i<numkarts) {
			
			//Insertar tipo de los karts
			System.out.println("\n* Inserte el tipo del kart " + (i+1) + " (INFANTIL/ADULTOS): ");
			String temp = scn.nextLine();
			
			boolean tipo;
			
			//Control de tipo
			if(temp.toUpperCase().contains("INFANTIL")) {
				tipo = true;
			}else if(temp.toUpperCase().contains("ADULTOS")) {
				tipo = false;
			}else {
				System.out.println("\n* Tipo introducido erroneo");
				System.out.print("* Vuelva a introducir los datos requeridos\n");
				continue;
			}
			
			//Insertar estado de los karts
			System.out.println("\n* Inserte el estado del kart " + (i+1) + " (DISPONIBLE/RESERVADO/MANTENIMIENTO): ");
			String s = scn.nextLine();
			
			Estado st;
			
			//Control de estado
			if(s.toUpperCase().contains("DISPONIBLE")) {
				st = Estado.valueOf(s.toUpperCase());
			}else if(s.toUpperCase().contains("RESERVADO")) {
				st = Estado.valueOf(s.toUpperCase());
			}else if(s.toUpperCase().contains("MANTENIMIENTO")) {
				st = Estado.valueOf(s.toUpperCase());
			}else {
				System.out.println("\n* Estado introducido erroneo");
				System.out.print("* Vuelva a introducir los datos requeridos\n");
				continue;
			}
			
			//Creamos el kart
			gestor.crearKarts(tipo,st);
			System.out.print("\n* Kart añadido!\n");

			i++;
		}	
		scn.close();
	}
	
	 /**
	  * Para crear pista nueva
	  */
	
	public static void nuevaPista() {
		GestorPistas gestor = new GestorPistas();
		scn = new Scanner(System.in);
		
		System.out.println("* Inserte el numero de pistas a introducir: ");
		int numPistas = scn.nextInt();
		scn.nextLine();
		
		//Control de numero de pistas
		if(numPistas <=0) {
			System.out.println("\n* Numero de pistas erroneo");
			return;
		}
		
		int i=0;
		while(i<numPistas) {

			//Insertar nombre de la pista
			System.out.println("\n* Inserte el nombre de la pista " + (i+1) + " : ");
			String name = scn.nextLine();
			
			//Comprobar si pista ya esta en la base de datos
			if(gestor.comprobarPistaExiste(name)) {
				System.out.print("\n* Ya existe una pista con ese nombre");
				System.out.print("\n* Vuelva a introducir los datos requeridos\n");
				continue;
			}
			
			//Insertar dificultad de las pistas
			System.out.println("\n* Inserte la dificultad de la pista " + name + " (INFANTIL/FAMILIAR/ADULTOS): ");
			String dif = scn.nextLine();
			
			Dificultad st;
			
			//Control de dificultad
			if(dif.toUpperCase().contains("INFANTIL")) {
				st = Dificultad.valueOf(dif.toUpperCase());
			}else if(dif.toUpperCase().contains("FAMILIAR")) {
				st = Dificultad.valueOf(dif.toUpperCase());
			}else if(dif.toUpperCase().contains("ADULTOS")) {
				st = Dificultad.valueOf(dif.toUpperCase());
			}else {
				System.out.println("\n* Dificultad introducida erronea");
				System.out.print("* Vuelva a introducir los datos requeridos\n");
				continue;
			}
			
			//Insertar estado de las pistas
			System.out.println("\n* Inserte el estado de la pista " + name + " (DISPONIBLE/RESERVADA/MANTENIMIENTO): ");
			String s = scn.nextLine();
			
			boolean bo;
			
			//Control de estado
			if(s.toUpperCase().contains("DISPONIBLE")) {
				bo = true;
			}else if(s.toUpperCase().contains("RESERVADA")) {
				bo = false;
			}else if(s.toUpperCase().contains("MANTENIMIENTO")) {
				bo = false;
			}else{
				System.out.println("\n* Estado introducido erroneo");
				System.out.print("* Vuelva a introducir los datos requeridos\n");
				continue;
			}
			
			//Insertar numero maximo de karts
			System.out.println("\n* Inserte el numero de karts maximos que tendrá la pista " + name);
			int max = scn.nextInt();
			scn.nextLine();
			
			//Control de numero maximo de karts
			if(max <= 0) {
				System.out.println("\n* Numero maximo de karts invalido");
				return;
			}
			
			//Creamos la pista
			gestor.crearPistas(name, bo, st, max);
			System.out.print("\n* Pista añadida!\n");
			
			i++;
		}	
		scn.close();
	}
	
	 /**
	  * Asociar y mostrar los karts que no tenian pista asociada
	  * y ahora sí
	  */
	
	public static void asociarKartSinPista() {
		GestorPistas gestor = new GestorPistas();
		System.out.println("* Se han asignado los siguientes karts disponibles a las pistas disponibles: ");
		System.out.print(gestor.unionKartPista().toString());
	}
	
	 /**
	  * Listar todos los karts
	  */
	
	public static void listadoKarts() {
		GestorPistas gestor = new GestorPistas();
		System.out.print("\n* Listado de karts: \n");
		System.out.print(gestor.listadoKarts().toString());
	}
	
	 /**
	  * Listar pistas en mantenimiento
	  */
	
	public static void listaMantenimiento() {
		GestorPistas gestor = new GestorPistas();
		System.out.println("* Pistas en mantenimiento: \n");
		System.out.print(gestor.listadoPistaMantenimiento().toString());
	}
	
	 /**
	  * Listar pistas disponibles segun participantes y dificultad
	  */
	
	public static void listarPistasDisponibles() {
		scn = new Scanner(System.in);
		GestorPistas gestor = new GestorPistas();
		
		System.out.println("* Inserte el numero de participantes infantiles: ");
		int kartsInfantiles = scn.nextInt();
		
		System.out.println("* Inserte el numero de participantes adultos: ");
		int kartsAdultos = scn.nextInt();
		scn.nextLine();
		
		System.out.println("* Inserte la dificultad de la pista (INFANTIL/FAMILIAR/ADULTOS): ");
		String dif = scn.nextLine();
		
		Dificultad st;
		
		//Control de dificultad
		if(dif.toUpperCase().contains("INFANTIL")) {
			st = Dificultad.valueOf(dif.toUpperCase());
		}else if(dif.toUpperCase().contains("FAMILIAR")) {
			st = Dificultad.valueOf(dif.toUpperCase());
		}else if(dif.toUpperCase().contains("ADULTOS")) {
			st = Dificultad.valueOf(dif.toUpperCase());
		}else {
			System.out.println("\n* Estado introducido erroneo");
			return;
		}
		
		System.out.println("\n* Listado de pistas libres: \n");
		System.out.print(gestor.listadoPistaLibre(kartsInfantiles, kartsAdultos,st).toString());
		scn.close();
	}
}
