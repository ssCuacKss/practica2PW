package display;
import java.util.Scanner;



public class MenuReservas{
	
	static Scanner scn;
	
	public int menuReservas() { //Funcion que imprime por pantalla el menu de reservas, recoge la opcion introducida por el usuario y la retorna al menu de reservas principal
		scn = new Scanner(System.in);
		
		System.out.println("");
		System.out.println("");
		System.out.println("|//////////////////////////////////////|");
		System.out.println("			MENU DE RESERVAS");
		System.out.println("|//////////////////////////////////////|");
		System.out.println("1 - Reserva individual");
		System.out.println("2 - Reserva bono");
		System.out.println("3 - Listar reservas futuras");
		System.out.println("4 - Listar reservas de pistas por fecha");
		System.out.println("5 - Cancelar bono");
		System.out.println("0 - Volver al menu principal");
		System.out.println("|//////////////////////////////////////|");
		System.out.print("Introduce una opción (0-5): ");
		int opc= scn.nextInt();
		return opc;
	}

	public void menuReservasPrincipal()
	{
		int opc;
		do {
			opc = menuReservas();
			
			switch(opc)
			{
			
			case 1:
				//Reserva individual
				DisplayGestorReserva.crearReservaIndividual();
				break;
				
			case 2:
				//Reserva bono
				DisplayGestorReserva.crearReservaBono();
				break;
				
			case 3:
				//Listar reservas futuras
				DisplayGestorReserva.listarReservasFuturas();
				break;
			case 4:
				//Listar reservas de pistas por fecha
				DisplayGestorReserva.listarReservasFechaPista();
				break;
				
			case 5:
				//Cancelar bono
				DisplayGestorReserva.borrarBono();
				break;
				
			case 0:
				//salir del menu del gestor de pistas
				System.out.println("Saliendo del menu de gestion de reservas...");
				break;
				
			default:
				//control de errores al introducir una opcion distinta de las existentes
				System.out.println("ERROR. Introduzca una opcion correcta");
			}
		}while(opc!=0);
	}
}