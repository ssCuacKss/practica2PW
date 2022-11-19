package display;
import java.util.Scanner;



public class MenuPistas{
	
	static Scanner scn;
	
	public int menuPistas() { //Funcion que imprime por pantalla el menu de pistas, recoge la opcion introducida por el usuario y la retorna al menu de pistas principal
		scn = new Scanner(System.in);
		
		System.out.println("");
		System.out.println("");
		System.out.println("|//////////////////////////////////////|");
		System.out.println("			MENU DE PISTAS");
		System.out.println("|//////////////////////////////////////|");
		System.out.println("1 - Crear karts");
		System.out.println("2 - Crear pistas");
		System.out.println("3 - Asociar kart a pista");
		System.out.println("4 - Listar pistas en mantenimiento");
		System.out.println("5 - Listar pistas disponibles");
		System.out.println("6 - Listar karts");
		System.out.println("0 - Volver al menu principal");
		System.out.println("|//////////////////////////////////////|");
		System.out.print("Introduce una opción (0-6): ");
		int opc= scn.nextInt();
		return opc;
	}

	public void menuPistasPrincipal()
	{
		int opc;
		do {
			
			opc = menuPistas();
			
			switch(opc)
			{
			case 1:
				//crear kart
				DisplayGestorPista.nuevoKart();
				break;
				
			case 2:
				//crear pista
				DisplayGestorPista.nuevaPista();
				break;
				
			case 3:
				//asociar kart a pista
				DisplayGestorPista.asociarKartSinPista();
				break;
			case 4:
				//listar pistas en mantenimiento
				DisplayGestorPista.listaMantenimiento();
				break;
			case 5:
				//listar pistas disponibles
				DisplayGestorPista.listarPistasDisponibles();
				break;
			case 6:
				//listar karts
				DisplayGestorPista.listadoKarts();
				break;
			case 0:
				//salir del menu del gestor de pistas
				System.out.println("Saliendo del menu de gestion de pistas...");
				break;
				
			default:
				//control de errores al introducir una opcion distinta de las existentes
				System.out.println("ERROR. Introduzca una opcion correcta");
			}
		}while(opc!=0);
	}
}