package display;
import java.util.Scanner;

public class MenuUsuario{	
	
		static Scanner scn;
	
	public int menuUsuarios() { //Menu impreso por pantalla para la gestion de usuarios
		scn = new Scanner(System.in);
		
		System.out.println("");
		System.out.println("");
		System.out.println("|//////////////////////////////////////|");
		System.out.println("			MENU DE USUARIOS");
		System.out.println("|//////////////////////////////////////|");
		System.out.println("1 - Alta de un usuario");
		System.out.println("2 - Modificar un usuario");
		System.out.println("3 - Eliminar un usuario");
		System.out.println("4 - Buscar usuario por correo");
		System.out.println("5 - Lista de usuarios");
		System.out.println("0 - Volver al menu principal");
		System.out.println("|//////////////////////////////////////|");
		System.out.print("Introduce una opción (0-5): ");
		int opc= scn.nextInt();
		return opc;
	}

	public int menuUsuariosModificar() //Menu impreso por pantalla para la modificacion de usuarios
	{
		scn = new Scanner (System.in);
		System.out.println("");
		System.out.println("");
		System.out.println("|//////////////////////////////////////|");
		System.out.println("	MENU DE MODIFICACION DE USUARIOS");
		System.out.println("|//////////////////////////////////////|");
		System.out.println("1 - Modificar usuario completo");
		System.out.println("2 - Modificar nombre");
		System.out.println("3 - Modificar apellidos");
		System.out.println("4 - Modificar fecha de nacimiento");
		System.out.println("5 - Modificar fecha de inscripcion");
		System.out.println("0 - Volver al menu de gestion de usuarios");
		System.out.println("|//////////////////////////////////////|");
		System.out.print("Introduce una opción (0-5): ");
		int opc= scn.nextInt();
		return opc;
	}
	
	public void menuUsuariosPrincipal() //menu de usuarios principal
	{
		int opc;
		do {
			opc = menuUsuarios();
			
			switch(opc)
			{
			
			case 1:
				//alta de usuario
				DisplayGestorUsuario.altaUsuario();
				break;
				
			case 2:
				//modificar usuario
				MenuUsuario ModificarAux = new MenuUsuario();
				ModificarAux.menuUsuariosModificarPrincipal();
				break;
				
			case 3:
				//Eliminar usuario
				DisplayGestorUsuario.eliminarUsuario();
				break;
				
			case 4:
				//Buscar usuario
				DisplayGestorUsuario.UsuarioBuscadoCorreo();
				break;
				
			case 5:
				//Listar usuario
				DisplayGestorUsuario.ListadoUsuarios();
				break;
				
			case 0:
				//salir del menu del gestor de usuarios
				System.out.println("Saliendo del menu de gestion de usuarios...");
				break;
				
			default:
				//control de errores al introducir una opcion distinta de las existentes
				System.out.println("ERROR. Introduzca una opcion correcta");
			}
		}while(opc!=0);
}
	
	public void menuUsuariosModificarPrincipal() //menu de modificacion de usuarios
	{
		int opc;
		do
		{
			opc = menuUsuariosModificar();
			switch(opc)
			{
			case 1: //modificar todos los atributos de un usuario
				DisplayGestorUsuario.modificarUsuario();
				break;
			
			case 2:
				DisplayGestorUsuario.modificarNombreUsuario();
				break;
				
			case 3:
				DisplayGestorUsuario.modificarApellidosUsuario();
				break;
				
			case 4:
				DisplayGestorUsuario.modificarFechaNacimientoUsuario();
				break;
				
			case 5:
				DisplayGestorUsuario.modificarFechaInscripcionUsuario();
				break;
				
			case 0:
				//salir del menu del gestor de usuarios
				System.out.println("Saliendo del menu de modificacion de usuario...");
				break;
				
			default:
				//control de errores al introducir una opcion distinta de las existentes
				System.out.println("ERROR. Introduzca una opcion correcta");
			}
		}while(opc!=0);
	}
	

}