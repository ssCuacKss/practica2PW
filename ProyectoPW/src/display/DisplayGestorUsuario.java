package display;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import business.gestores.GestorUsuarios;

/**
 * @author Carlos Lucena Robles.
 * @author Miguel Raigón Jiménez.
 * @author Pablo Roldán Puebla.
 * @author Paloma Romero Delgado.
 * @author Kamal Abdelkader Haddu.
 * @version 1.0.0
 */

/**
 * Display del Gestor de Usuarios
 */

public class DisplayGestorUsuario {

	 static Scanner scn;

	 /**
	  * Para dar de alta a un usuario
	  */
	 public static void altaUsuario() {
		 GestorUsuarios gestor = new GestorUsuarios();
		 scn = new Scanner(System.in);
		 
		 //Datos usuario
		 String nombre = null;
		 String apellidos = null;
		 String correo = null;
		 String fechaNacimiento = null;
		 
		 //Variables para salir del primer do-while
		 int opc;
		 
		 //Bucle para dar de alta usuarios
		 do{
			System.out.println("* Introduzca los datos del nuevo usuario\n");
			System.out.print("Pulse 0 para salir. Para continuar presione cualquier numero: ");
			opc = scn.nextInt();
			scn.nextLine();
			
			//Salida
			if (opc == 0) {
				break;
			}
			
			//Variables para comprobar si el usuario puede darse de alta
			boolean usuario_noExiste = false;
			boolean mayor_edad = false;
			
			//Variables para validar la entrada por teclado (control de errores)
			boolean correo_valido = false; 
			boolean fecha_valida = false;
			
			//Bucle para pedir datos al usuario con control de errores por teclado
			do{					
				System.out.print("\t-> Correo: ");
				correo = scn.nextLine();
				
				System.out.print("\t-> Nombre: ");
				nombre = scn.nextLine();
				
				System.out.print("\t-> Apellidos: ");
				apellidos = scn.nextLine();
									
				System.out.print("\t-> Fecha Nacimiento (dd/MM/yyyy): ");
				fechaNacimiento = scn.nextLine();
				
				//Comprobar si el usuario ya esta registrado		
				if(gestor.UsuarioBuscadoCorreo(correo)) {
					System.out.print("\n* Ya existe un usuario con ese correo registrado\n");
					System.out.print("\n* Vuelva a introducir los datos requeridos\n");
					continue;
				}
				
				//Comprobar si es mayor de edad
				if(!gestor.mayoriaEdad(fechaNacimiento)) {
					System.out.print("\n* Usuario menor de edad, no puede registrarse\n");
					System.out.print("\n* Vuelva a introducir los datos requeridos\n");
					continue;
				}	
				
				//Validar formato correo
				if(!gestor.correoValido(correo)) {
					System.out.print("\n* El correo electronico " + correo + " no es correcto\n");
					System.out.print("\n* Vuelva a introducir los datos requeridos\n");
					continue;
				}
					
				//Comprobamos que el formato de la fecha sea correcto
	            try {
	                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                sdf.setLenient(false);
	                sdf.parse(fechaNacimiento);
	            } catch (ParseException e1) {
	            	System.out.println("\n* Por favor, introduzca una fecha valida\n");
	                continue;
	            }
			
				//Validar fecha nacimiento
				if(!gestor.fechaNacimientoValida(fechaNacimiento)) {
					System.out.print("\n* La fecha de nacimiento " + fechaNacimiento + " no es correcta\n");
					System.out.print("\n* Vuelva a introducir los datos requeridos\n");	
					continue;
				}		
				
				correo_valido = true;
				fecha_valida = true;
				usuario_noExiste = true;
				mayor_edad = true;
				
				//Creamos el usuario
				gestor.altaUsuario(correo, nombre, apellidos, fechaNacimiento);
				System.out.print("\n* Usuario añadido!\n");
				
			}while(!usuario_noExiste && !mayor_edad && !fecha_valida && !correo_valido);
		 			
		 }while(opc != 0);
		 
		 scn.close();
		 
		 return;
	 }
	 
	 /**
	  * Para modificar todos los campos de un usuario menos correo
	  * puesto que correo es PK y si el usuario tiene una reserva hecha,
	  * al cambiar el correo salta error ya que deja de existir
	  */
	 
	 public static void modificarUsuario() {
		 GestorUsuarios gestor = new GestorUsuarios();
		 scn = new Scanner(System.in);
		 System.out.print("\n* Introduzca el correo del usuario a modificar: ");
		 String correo = scn.nextLine();
		 
		 if(gestor.UsuarioBuscadoCorreo(correo)) {

			System.out.print("* Se ha encontrado al usuario con correo " + correo + "\n");
			
			//Variables para validar la entrada por teclado (control de errores)
			boolean fecha_valida = false;
			
			//Bucle para pedir datos al usuario con control de errores por teclado
			do{	
				System.out.print("Introduzca el nuevo nombre: ");
				String nombre = scn.nextLine();
				System.out.print("Introduzca los nuevos apellidos: ");
				String apellidos = scn.nextLine();
				System.out.print("Introduzca la nueva fecha de nacimiento: ");
				String fechaNacimiento = scn.nextLine();
				
				//Comprobamos que el formato de la fecha sea correcto
	            try {
	                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                sdf.setLenient(false);
	                sdf.parse(fechaNacimiento);
	            } catch (ParseException e1) {
	            	System.out.println("\n* Por favor, introduzca una fecha valida\n");
	            	continue;
	            }
			
				//Validar fecha nacimiento
				if(!gestor.fechaNacimientoValida(fechaNacimiento)) {
					System.out.print("\n* La fecha de nacimiento " + fechaNacimiento + " no es correcta\n");
					System.out.print("\n* Vuelva a introducir los datos requeridos\n");
					continue;
				}
				
				fecha_valida = true;
				
				//Modificamos el usuario
				gestor.modificarUsuario(correo, nombre, apellidos, fechaNacimiento);
				System.out.print("\n* Usuario modificado!\n");
			
			}while(!fecha_valida);
				
		 }else {
			 System.out.print("* No se ha encontrado al usuario con correo " + correo + "\n");
		 }
		 scn.close();
	 }
	 
	 /**	  
	  * Para modificar correo de un usuario
	  * 
	  * Funciona pero puesto que correo es PK, si el usuario tiene una reserva hecha,
	  * al cambiar el correo salta error ya que deja de existir
	  */

	 public static void modificarCorreoUsuario() {
		 GestorUsuarios gestor = new GestorUsuarios();
		 scn = new Scanner(System.in);
		 System.out.print("\n* Introduzca el correo del usuario a modificar: ");
		 String correo = scn.nextLine();
		 
		 if(gestor.UsuarioBuscadoCorreo(correo)) {
			System.out.print("* Se ha encontrado al usuario con correo " + correo + "\n");
			
			//Variable para validar la entrada por teclado (control de errores)
			boolean correo_valido = false; 
			
			//Bucle para pedir datos al usuario con control de errores por teclado
			do{
				System.out.print("Introduzca el nuevo correo: ");
				String correo_nuevo = scn.nextLine();
				
				//Validar formato correo
				if(!gestor.correoValido(correo_nuevo)) {
					System.out.print("\n* El correo electronico " + correo_nuevo + " no es correcto\n");
					System.out.print("\n* Vuelva a introducir los datos requeridos\n");
					continue;
				}
				
				correo_valido = true;
				
				//Modificamos correo del usuario
				gestor.modificarCorreoUsuario(correo, correo_nuevo);
				System.out.print("\n* Correo del usuario modificado!\n");
			
			}while(!correo_valido);
			
		 }else {
			 System.out.print("* No se ha encontrado al usuario con correo " + correo + "\n");
		 }
		 scn.close();
	 }

	 
	 /**
	  * Para modificar nombre de un usuario
	  */
	 
	 public static void modificarNombreUsuario() {
		 GestorUsuarios gestor = new GestorUsuarios();
		 scn = new Scanner(System.in);
		 System.out.print("\n* Introduzca el correo del usuario a modificar: ");
		 String correo = scn.nextLine();
		 		 
		 if(gestor.UsuarioBuscadoCorreo(correo)) {
			System.out.print("* Se ha encontrado al usuario con correo " + correo + "\n");
			
			System.out.print("Introduzca el nuevo nombre: ");
			String nombre = scn.nextLine();
			
			//Modificamos nombre del usuario
			gestor.modificarNombreUsuario(correo, nombre);
			System.out.print("\n* Nombre del usuario modificado!\n");
				
		 }else {
			 System.out.print("* No se ha encontrado al usuario con correo " + correo + "\n");
		 }
		 scn.close();
	 }
	 
	 /**
	  * Para modificar apellidos de un usuario
	  */
	 
	 public static void modificarApellidosUsuario() {
		 GestorUsuarios gestor = new GestorUsuarios();
		 scn = new Scanner(System.in);
		 System.out.print("\n* Introduzca el correo del usuario a modificar: ");
		 String correo = scn.nextLine();
		 
		 if(gestor.UsuarioBuscadoCorreo(correo)) {
			System.out.print("* Se ha encontrado al usuario con correo " + correo + "\n");
	 
			System.out.print("Introduzca los nuevos apellidos: ");
			String apellidos = scn.nextLine();
			
			//Modificamos apellidos del usuario
			gestor.modificarApellidosUsuario(correo, apellidos);
			System.out.print("\n* Apellidos del usuario modificados!\n");
			
		 }else {
			 System.out.print("* No se ha encontrado al usuario con correo " + correo + "\n");
		 }
		 scn.close();
	 }
	 
	 /**
	  * Para modificar fecha de nacimiento de un usuario
	  */
	 
	 public static void modificarFechaNacimientoUsuario() {
		 GestorUsuarios gestor = new GestorUsuarios();
		 scn = new Scanner(System.in);
		 System.out.print("\n* Introduzca el correo del usuario a modificar: ");
		 String correo = scn.nextLine();
		 
		 if(gestor.UsuarioBuscadoCorreo(correo)) {
			System.out.print("* Se ha encontrado al usuario con correo " + correo + "\n");
			
			//Variable para validar la entrada por teclado (control de errores)
			boolean fecha_valida = false;
			
			//Bucle para pedir datos al usuario con control de errores por teclado
			do{	
				System.out.print("Introduzca la nueva fecha de nacimiento: ");
				String fechaNacimiento = scn.nextLine();
				
				//Comprobamos que el formato de la fecha sea correcto
	            try {
	                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                sdf.setLenient(false);
	                sdf.parse(fechaNacimiento);
	            } catch (ParseException e1) {
	            	System.out.println("\n* Por favor, introduzca una fecha valida\n");
	            }
			
				//Validar fecha nacimiento
				if(!gestor.fechaNacimientoValida(fechaNacimiento)) {
					System.out.print("\n* La fecha de nacimiento " + fechaNacimiento + " no es correcta\n");
					System.out.print("\n* Vuelva a introducir los datos requeridos\n");
					continue;
				}
				
				fecha_valida = true;
				
				//Modificamos fecha de nacimiento del usuario
				gestor.modificarFechaNacimientoUsuario(correo, fechaNacimiento);
				System.out.print("\n* Fecha de nacimiento del usuario modificada!\n");
			
			}while(!fecha_valida);
				
		 }else {
			 System.out.print("* No se ha encontrado al usuario con correo " + correo + "\n");
		 }
		 scn.close();
	 }
	 
	 /**
	  * Para modificar fecha de inscripcion de un usuario
	  */
	 
	 public static void modificarFechaInscripcionUsuario() {
		 GestorUsuarios gestor = new GestorUsuarios();
		 scn = new Scanner(System.in);
		 System.out.print("\n* Introduzca el correo del usuario a modificar: ");
		 String correo = scn.nextLine();
		 
		 if(gestor.UsuarioBuscadoCorreo(correo)) {
			System.out.print("* Se ha encontrado al usuario con correo " + correo + "\n");
			
			System.out.print("Introduzca la nueva fecha de inscripcion: ");
			String fechaInscripcion = scn.nextLine();
			
			//Comprobamos que el formato de la fecha sea correcto
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                sdf.parse(fechaInscripcion);
            } catch (ParseException e1) {
            	System.out.println("\n* Por favor, introduzca una fecha valida\n");
            }
					
			//Modificamos la fecha de inscripcion del usuario
			gestor.modificarFechaInscripcionUsuario(correo, fechaInscripcion);
			System.out.print("\n* Fecha de inscripcion del usuario modificada!\n");
					
		 }else {
			 System.out.print("* No se ha encontrado al usuario con correo " + correo + "\n");
		 }
		 scn.close();
	 }
	 
	 /**
	  * Para eliminar un usuario
	  */
	 
	 public static void eliminarUsuario() {
		 GestorUsuarios gestor = new GestorUsuarios();
		 scn = new Scanner(System.in);
		 System.out.print("\n* Introduzca el correo del usuario a eliminar: ");
		 String correo = scn.nextLine();
		 
		 if(gestor.UsuarioBuscadoCorreo(correo)) {

			System.out.print("* Se ha encontrado al usuario con correo " + correo + "\n");
	 
			//Eliminamos usuario
			gestor.eliminarUsuario(correo);
			System.out.print("\n* Usuario borrado!\n");
				
		 }else {
			 System.out.print("* No se ha encontrado al usuario con correo " + correo + "\n");
		 }
		 scn.close();
	 }
	 
	 /**
	  * Para obtener un usuario concreto
	  */
	 
	 public static void UsuarioBuscadoCorreo() {
		 GestorUsuarios gestor = new GestorUsuarios();
		 scn = new Scanner(System.in);
		 System.out.print("\n* Introduzca el correo del usuario buscado: ");
		 String correo = scn.nextLine();
		 
		 if(gestor.UsuarioBuscadoCorreo(correo)) {
			System.out.print("* Se ha encontrado al usuario con correo " + correo + "\n");
			
			//Buscamos usuario
			gestor.UsuarioBuscadoCorreo(correo);
			System.out.print("\n* Usuario encontrado!\n");
			
		 }else {
			 System.out.print("* No se ha encontrado al usuario con correo " + correo + "\n");
		 }
		 scn.close();
	 }
	 
	 /**
	  * Para listar todos los usuarios
	  */
	 
	 public static void ListadoUsuarios() {
		 GestorUsuarios gestor = new GestorUsuarios();		
		 System.out.print("\n* Listado de los usuarios registrados: \n");
		 System.out.print(gestor.listadoUsuarios().toString());
	 }
}
