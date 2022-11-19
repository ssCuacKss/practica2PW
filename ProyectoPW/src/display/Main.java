package display;
import java.util.Scanner;
/**
 * @author Carlos Lucena Robles.
 * @author Miguel Raigón Jiménez.
 * @author Pablo Roldán Puebla.
 * @author Paloma Romero Delgado.
 * @author Kamal Abdelkader Haddu.
 * @version 1.0.0
 */

public class Main {

	static Scanner scn;
	
	public static int menu(){ //Funcion que imprime por pantalla el menu, recoge la opcion introducida por el usuario y la retorna al main
		scn =  new Scanner (System.in);
		System.out.println("");
		System.out.println("");
		System.out.println("|//////////////////////////////////////|");
		System.out.println("			MENU PRINCIPAL");
		System.out.println("|//////////////////////////////////////|");
		System.out.println("1 - Gestion de usuarios");
		System.out.println("2 - Gestion de pistas");
		System.out.println("3 - Gestion de reservas");
		System.out.println("0 - Salir");
		System.out.println("|//////////////////////////////////////|");
		System.out.print("Introduce una opción (0-3): ");
		int opc= scn.nextInt();	
		scn.nextLine();
		return opc;
	}
	
	public static void main(String[] args) {
		
		   int opc;

		   do{
			   
			   opc = menu(); //igualo la variable opc a lo que retorne la funcion menu()
		        
		        switch(opc){
		                
		            case 1: //Gestor Usuarios
						MenuUsuario UsuarioAux = new MenuUsuario();
						UsuarioAux.menuUsuariosPrincipal();
		            	break;
		                
		            case 2: //Gestor Pistas
		            	MenuPistas PistaAux = new MenuPistas();
		            	PistaAux.menuPistasPrincipal();
		            	break;
		            
		            case 3: //Gestor Reservas
		            	MenuReservas ReservaAux = new MenuReservas();
		            	ReservaAux.menuReservasPrincipal();
		            	
		            	break;
		            
		            case 0: //Salir del programa
		                System.out.println("Saliendo del programa...");
		            break;
		            
		            default: //Control de errores
		                System.out.println("ERROR. Introduzca una opcion correcta");
		        	}

		    }while(opc!=0);
	}
}