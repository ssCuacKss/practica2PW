package display;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import business.gestores.GestorPistas;
import business.gestores.GestorReservas;
import business.gestores.GestorUsuarios;
import business.kart.Estado;
import business.pista.Dificultad;

/**
 * @author Carlos Lucena Robles.
 * @author Miguel Raigón Jiménez.
 * @author Pablo Roldán Puebla.
 * @author Paloma Romero Delgado.
 * @author Kamal Abdelkader Haddu.
 * @version 1.0.0
 */

/**
 * Display del Gestor de Reserva
 */

public class DisplayGestorReserva {

	static Scanner scn;
	
	 /**
	  * Para crear una reserva individual
	  */
	
	public static void crearReservaIndividual() {
		
		GestorReservas gestorReserva = new GestorReservas();
		GestorUsuarios gestorUsuario = new GestorUsuarios();
		GestorPistas gestorPista = new GestorPistas();
		
		scn = new Scanner(System.in);
		
		System.out.print("* Introduzca su correo para realizar la reserva: ");
		String correo = scn.nextLine();
		
		//Control si usuario existe
		boolean comprobarUsuario = gestorUsuario.UsuarioBuscadoCorreo(correo);
		
		if(!comprobarUsuario) {
			System.out.print("* No existe un usuario registrado con ese correo");
			return;
		}
		
		System.out.println("* Introduzca los siguientes datos sobre la reserva: ");
        
		//Datos sobre reserva
        String fechaReserva = null;
        int duracionReserva, numeroAdultos=0, numeroInfantiles=0;
        
        //Variable para salir del do-while
        boolean done = false;
        
        //Bucle para introducir datos de reserva
        do {
            System.out.print("\t-> Introduce la fecha de la reserva (dd/MM/yyyy): ");
            fechaReserva = scn.nextLine();

            System.out.print("\t-> Introduce la duracion de la reserva (60/90/120) minutos: ");
            duracionReserva = scn.nextInt();
            scn.nextLine();

            System.out.print("\t-> Introduce el numero de participantes adultos (incluido usted): ");
            numeroAdultos = scn.nextInt();
            scn.nextLine();

            System.out.print("\t-> Introduce el numero de participantes infantiles: ");
            numeroInfantiles = scn.nextInt();
            scn.nextLine();

            //Comprobamos que el formato de la fecha sea correcto
            Date fecha = new Date();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                fecha = sdf.parse(fechaReserva);
            } catch (ParseException e1) {
            	System.out.println("\n* Por favor, introduzca una fecha valida");
                continue;
            }
            
            //Comprobamos si la fecha de la reserva es posterior a la actual
            if (!gestorReserva.comprobarFecha(fecha)) {
            	System.out.println("\n* No puede realizar una reserva a menos de 1 dia. Por favor, elija otra fecha.");
            	continue;
            }
            
            //Comprobacion de la duracion de la reserva
            if (!(duracionReserva == 60 || duracionReserva == 90 || duracionReserva == 120)) {
            	System.out.println("\n* Por favor, introduzca una duracion de reserva adecuada");
            	continue;
            }
            
            //Comprobacion de los numero de participantes
            if (numeroAdultos <= 0 || numeroInfantiles < 0) {
            	System.out.println("\n* Por favor, introduzca un numero de participantes valido");
            	continue;
            }
                      
            done = true;
            
        }while(done == false);
		
        /**
         * PEDIR PARTICIPANTES Y ELEGIR TIPO RESERVA
		 * Segun el numero de participantes introducidos mostramos las diferentes opciones de tipo
		 * de reserva que puede hacer el usuario (Vease guion de practica Ejercicio 2)
		 * 
		 * Reserva Infantil -> Minimo 1 niño y SOLO niños
		 * Reserva Familiar -> Minimo 1 adulto (Independientemente de que sea o no el usuario que realiza la reserva)
		 * Reverva Adultos -> Minimo 1 adulto y SOLO adultos
		 */
		System.out.println("\n\n* A partir de los participantes dados: ");
		System.out.println("\t->Numero participantes adultos: " + numeroAdultos);
		System.out.println("\t->Numero participantes infantiles: " + numeroInfantiles);
		
		System.out.println("\n* Se le ha habilitado los siguientes tipos de reservas: ");
		
		boolean habilitarReservaInfantil = false;
		if (numeroInfantiles >= 1) {
			System.out.println("\t- Reserva Infantil: Dirigido para niños");
			habilitarReservaInfantil = true;
		}
		
		boolean habilitarReservaFamiliar = false;
		if (numeroAdultos >= 1) {
			System.out.println("\t- Reserva Familiar: Dirigido tantos para niños como para adultos");
			habilitarReservaFamiliar = true;
		}
		
		boolean habilitarReservaAdultos = false;
		if (numeroAdultos >= 1 && numeroInfantiles == 0) {
			System.out.println("\t- Reserva Adultos: Exclusivamente para adultos");
			habilitarReservaAdultos = true;
		}
	
		//Pedir al usuario escribir un tipo de dato enum es una operacion sensible, por ello, se usa un bucle para evitar la terminacion del programa debido a un error
		Dificultad publico = null;
		
		//Variable para salir del do-while
		done = true;
		do {
			
			System.out.print("\n* Escriba la reserva deseada (INFANTIL, FAMILIAR, ADULTOS): ");
			String dificultad = scn.nextLine();
			
			try {
				publico = Dificultad.valueOf(dificultad.toUpperCase());
			}catch(IllegalArgumentException e){
				System.out.println("\n* Por favor, escriba una opcion correcta.");
				done = false;
			}

			//Comprobamos que sea correcto el tipo de reserva
			if (!habilitarReservaInfantil && publico == Dificultad.INFANTIL) {
				System.out.println("\n* Por favor, escriba una de las opciones de las habilitadas para usted.");
				done = false;
			}
			
			if (!habilitarReservaFamiliar && publico == Dificultad.FAMILIAR) {
				System.out.println("\n* Por favor, escriba una de las opciones de las habilitadas para usted.");
				done = false;
			}
			
			if (!habilitarReservaAdultos && publico == Dificultad.ADULTOS) {
				System.out.println("\n* Por favor, escriba una de las opciones de las habilitadas para usted.");
				done = false;
			}
			
			if (habilitarReservaInfantil && publico == Dificultad.INFANTIL) {
				done = true;
			}
			
			if (habilitarReservaFamiliar && publico == Dificultad.FAMILIAR) {
				done = true;
			}
			
			if (habilitarReservaAdultos && publico == Dificultad.ADULTOS) {
				done = true;
			}
			
		}while(done == false);
		
		System.out.println("\n* El tipo de reserva elegida es: " + publico);
		System.out.println("\n* Creando reserva...");
		
		/**
		 * HACER RESERVA
		 * Una vez obtenidos todos los datos realizamos la reserva segun el tipo de reserva que ha elegido
		 */
				
		int antiguedad = gestorUsuario.calcularAntiguedad(correo);
		float precio = gestorReserva.PrecioReserva(correo, fechaReserva, duracionReserva, antiguedad);
		float descuento = gestorReserva.descuentoReserva(antiguedad);
		
		//Obtenemos una pista disponible para su reserva segun los datos y restricciones facilitadas
		//Cogemos siempre la primera pista que haya disponible en el array por conveniencia (podemos dejar elegir al usuario)

		//Si no hay ninguna pista disponible la lista estara vacia y por tanto la reserva termina
		String pista = null;
		try {
			 pista = gestorPista.listadoPistaLibre(numeroInfantiles, numeroAdultos, publico).get(0).getNombre();
		}catch(IndexOutOfBoundsException e) {
			System.out.println("\n** No se ha podido realizar su reserva");
			System.out.println("\n** No se ha encontrado ninguna pista disponible para los datos proporcionados. Intentelo de nuevo mas tarde.");
			return;
		}
		
		//Añadimos la fecha de hoy como la fecha de inscripcion de cuando hizo la primera reserva
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currDate = new Date();
		
		gestorUsuario.modificarFechaInscripcionUsuario(correo, sdf.format(currDate));
		
		//Marcamos los karts que se usaran como reservados
		gestorPista.reservarKartsPista(pista, numeroInfantiles, numeroAdultos, Estado.RESERVADO);
		
		if (publico == Dificultad.INFANTIL) {
		
			gestorReserva.crearReservaInfantil(correo, numeroInfantiles, fechaReserva, duracionReserva, descuento, precio, pista);
			
			System.out.println("\n\tRESUMEN RESERVA:");
			System.out.println("\t---------------------");
			System.out.println("\t - Usuario: " + correo);
			System.out.println("\t - Tipo reserva: " + publico );
            System.out.println("\t - Fecha: " + fechaReserva);
            System.out.println("\t - Duracion: " + duracionReserva + " minutos");
            System.out.println("\t - Nº Participantes Infantiles: " + numeroInfantiles);
            System.out.println("\t - Descuento: " + descuento + " %");
            System.out.println("\t - Precio: " + precio + " euros");
            System.out.println("\t - Pista asignada: " + pista);
        
		}else if(publico == Dificultad.FAMILIAR) {

			gestorReserva.crearReservaFamiliar(correo, numeroInfantiles, numeroAdultos, fechaReserva, duracionReserva, descuento, precio, pista);
			
			System.out.println("\n\tRESUMEN RESERVA:");
			System.out.println("\t---------------------");
			System.out.println("\t - Usuario: " + correo);
			System.out.println("\t - Tipo reserva: " + publico );
            System.out.println("\t - Fecha: " + fechaReserva);
            System.out.println("\t - Duracion: " + duracionReserva + " minutos");
            System.out.println("\t - Nº Participantes Infantiles: " + numeroInfantiles);
            System.out.println("\t - Nº Participantes Adultos: " + numeroInfantiles);
            System.out.println("\t - Descuento: " + descuento + " %");
            System.out.println("\t - Precio: " + precio + " euros");
            System.out.println("\t - Pista asignada: " + pista);
			
		}else if(publico == Dificultad.ADULTOS) {
			
			gestorReserva.crearReservaAdultos(correo, numeroAdultos, fechaReserva, duracionReserva, descuento, precio, pista);
			
			System.out.println("\n\tRESUMEN RESERVA:");
			System.out.println("\t---------------------");
			System.out.println("\t - Usuario: " + correo);
			System.out.println("\t - Tipo reserva: " + publico );
            System.out.println("\t - Fecha: " + fechaReserva);
            System.out.println("\t - Duracion: " + duracionReserva + " minutos");
            System.out.println("\t - Nº Participantes Adultos: " + numeroAdultos);
            System.out.println("\t - Descuento: " + descuento + " %");
            System.out.println("\t - Precio: " + precio + " euros");
            System.out.println("\t - Pista asignada: " + pista);
		}
		
	}
	
	 /**
	  * Para crear un bono
	  */
	public static void crearReservaBono() {
			
			GestorReservas gestorReserva = new GestorReservas();
			GestorUsuarios gestorUsuario = new GestorUsuarios();
			GestorPistas gestorPista = new GestorPistas();
			
			scn = new Scanner(System.in);
			
			System.out.print("\n* Introduzca su correo para realizar la reserva: ");
			String correo = scn.nextLine();
			
			//Control si usuario existe
			boolean comprobarUsuario = gestorUsuario.UsuarioBuscadoCorreo(correo);
			
			if(!comprobarUsuario) {
				System.out.print("\n* No existe un usuario registrado con ese correo");
				return;
			}
			
			//Creamos el bono y nos devuelve el id del bono creado
			int idBono = gestorReserva.crearBono(correo);
			
			System.out.println("* Se creara un bono con ID: " + idBono);
			
			Dificultad publico = null;
			
			//Variable para salir del do-while
			boolean done = false;
			do {
				System.out.println("\n* Elija un tipo de Bono: ");
			
				System.out.println("\t- Bono Infantil: Solo para reservas infantiles. Dirigido solo a niños");
				System.out.println("\t- Bono Familiar: Solo para reservas familiares. Dirigido tantos para niños como para adultos");
				System.out.println("\t- Bono Adultos: Exclusivo para reservas adultas. Dirigido solo a adultos");
				
				System.out.print("\n* Escriba el tipo de Bono que desea (INFANTIL, FAMILIAR, ADULTOS): ");
				String dificultad = scn.nextLine();
				
				try {
					publico = Dificultad.valueOf(dificultad.toUpperCase());	
				}catch(IllegalArgumentException e){
					System.out.println("\n* Por favor, escriba una opcion correcta.");
					continue;
				}
				
				done = true;
						
			}while(done == false);
			
			System.out.println("\n* El tipo de bono elegido es: " + publico);
			System.out.println("\n* Introduzca los datos de las 5 sesiones del bono: ");
			
			for(int i=1 ; i<=5 ; i++) {
	
				System.out.println("\n* SESION " + i);
		        
				//Datos de la reserva
		        String fechaReserva = null;
		        int duracionReserva, numeroAdultos=0, numeroInfantiles = 0;
		        
		        //Variable para salir del do-while
		        done = false;
		        do {
		            System.out.print("\t-> Introduzca la fecha de la reserva (dd/MM/yyyy): ");
		            fechaReserva = scn.nextLine();

		            System.out.print("\t-> Introduzca la duracion de la reserva (60/90/120) minutos: ");
		            duracionReserva = scn.nextInt();
		            scn.nextLine();

		            if (publico == Dificultad.ADULTOS || publico == Dificultad.FAMILIAR) {
		            	System.out.print("\t-> Introduzca el numero de participantes adultos (incluido usted): ");
			            numeroAdultos = scn.nextInt();
			            scn.nextLine();
		            }

		            if (publico == Dificultad.INFANTIL || publico == Dificultad.FAMILIAR) {
		            	System.out.print("\t-> Introduzca el numero de participantes infantiles: ");
			            numeroInfantiles = scn.nextInt();
			            scn.nextLine();
		            }

		            //Comprobamos que el formato de la fecha sea correcto
		            Date fecha = new Date();
		            try {
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		                sdf.setLenient(false);
		                fecha = sdf.parse(fechaReserva);
		            } catch (ParseException e1) {
		            	System.out.println("\n* Por favor, introduzca una fecha valida");
		                continue;
		            }
		            
		            //Comprobamos si la fecha de la reserva es posterior a la actual
		            if (!gestorReserva.comprobarFecha(fecha)) {
		            	System.out.println("\n* No puede realizar una reserva a menos de 1 dia. Por favor, elija otra fecha.");
		            	continue;
		            }
		            
		            //Comprobacion de la duracion de la reserva
		            if (!(duracionReserva == 60 || duracionReserva == 90 || duracionReserva == 120)) {
		            	System.out.println("\n* Por favor, introduzca una duracion de reserva adecuada");
		            	continue;
		            }
		            
		            //Comprobacion de los numero de participantes
		            if (publico == Dificultad.ADULTOS && numeroAdultos <= 0) {
		            	System.out.println("\n* Por favor, introduzca un numero de participantes valido");
		            	continue;
		            }else if ( publico == Dificultad.INFANTIL && numeroInfantiles <= 0) {
		            	System.out.println("\n* Por favor, introduzca un numero de participantes valido");
		            	continue;
		            }else if ( publico == Dificultad.FAMILIAR && (numeroAdultos <=0 || numeroInfantiles < 0) ) {
		            	System.out.println("\n* Por favor, Introduzca un numero de participantes valido");
		            	continue;
		            }
		                      
		            done = true;
		            
		        }while(done == false);
				
				System.out.println("\n* Creando reserva...");
				
				float precio = gestorReserva.PrecioReservaBono(correo, fechaReserva, duracionReserva);
				
				//Todos los bonos tienen un descuento de un 5%
				float descuento = 5;
				
				//Obtenemos una pista disponible para su reserva segun los datos y restricciones facilitadas
				//Cogemos siempre la primera pista que haya disponible en el array por conveniencia (podemos dejar elegir al usuario)

				//Si no hay ninguna pista disponible la lista estara vacia y por tanto la reserva termina
				String pista = null;
				try {
					 pista = gestorPista.listadoPistaLibre(numeroInfantiles, numeroAdultos, publico).get(0).getNombre();
				}catch(IndexOutOfBoundsException e) {
					System.out.println("\n** No se ha podido realizar su reserva");
					System.out.println("\n** No se ha encontrado ninguna pista disponible para los datos proporcionados. Intentelo de nuevo mas tarde.\n");
					
					//Cancelamos el bono si uno de las sesiones no se puede introducir
					gestorReserva.cancelarBono(idBono);
					return;
				}
				
				//Añadimos la fecha de hoy como la fecha de inscripcion de cuando hizo la primera reserva
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date currDate = new Date();
				
				gestorUsuario.modificarFechaInscripcionUsuario(correo, sdf.format(currDate));
				
				//Marcamos los karts que se usaran como reservados
				gestorPista.reservarKartsPista(pista, numeroInfantiles, numeroAdultos, Estado.RESERVADO);
				
				if (publico == Dificultad.INFANTIL) {
				
					gestorReserva.crearReservaInfantilBono(idBono, correo, numeroInfantiles, fechaReserva, duracionReserva, descuento, precio, pista);
					
					System.out.println("\n\tSESION " + i + " - BONO ID: " + idBono);
					System.out.println("\t---------------------");
					System.out.println("\t - Usuario: " + correo);
					System.out.println("\t - Tipo reserva: " + publico );
		            System.out.println("\t - Fecha: " + fechaReserva);
		            System.out.println("\t - Duracion: " + duracionReserva + " minutos");
		            System.out.println("\t - Nº Participantes Infantiles: " + numeroInfantiles);
		            System.out.println("\t - Descuento: " + descuento + " %");
		            System.out.println("\t - Precio: " + precio + " euros");
		            System.out.println("\t - Pista asignada: " + pista);
		        
				}else if(publico == Dificultad.FAMILIAR) {

					gestorReserva.crearReservaFamiliarBono(idBono,correo, numeroInfantiles, numeroAdultos, fechaReserva, duracionReserva, descuento, precio, pista);
					
					System.out.println("\n\tSESION " + i + " - BONO ID: " + idBono);
					System.out.println("\t---------------------");
					System.out.println("\t - Usuario: " + correo);
					System.out.println("\t - Tipo reserva: " + publico );
		            System.out.println("\t - Fecha: " + fechaReserva);
		            System.out.println("\t - Duracion: " + duracionReserva + " minutos");
		            System.out.println("\t - Nº Participantes Infantiles: " + numeroInfantiles);
		            System.out.println("\t - Nº Participantes Adultos: " + numeroInfantiles);
		            System.out.println("\t - Descuento: " + descuento + " %");
		            System.out.println("\t - Precio: " + precio + " euros");
		            System.out.println("\t - Pista asignada: " + pista);
					
				}else if(publico == Dificultad.ADULTOS) {
					
					gestorReserva.crearReservaAdultosBono(idBono, correo, numeroAdultos, fechaReserva, duracionReserva, descuento, precio, pista);
					
					System.out.println("\n\tSESION " + i + " - BONO ID: " + idBono);
					System.out.println("\t---------------------");
					System.out.println("\t - Usuario: " + correo);
					System.out.println("\t - Tipo reserva: " + publico );
		            System.out.println("\t - Fecha: " + fechaReserva);
		            System.out.println("\t - Duracion: " + duracionReserva + " minutos");
		            System.out.println("\t - Nº Participantes Adultos: " + numeroAdultos);
		            System.out.println("\t - Descuento: " + descuento + " %");
		            System.out.println("\t - Precio: " + precio + " euros");
		            System.out.println("\t - Pista asignada: " + pista);
				}
			}	
		}
	
		/**
		 * Para listar reservas futuras
		 */
		public static void listarReservasFuturas() {
			GestorReservas gestorReserva = new GestorReservas();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			
			//Convertimos la fecha actual a tipo String
			Date currDate = new Date();
			String fecha = sdf.format(currDate);
			
			System.out.println("\n* A partir de la fecha de hoy: " + fecha + " se disponen de las siguientes reservas: ");
			
			System.out.println("\n\n* RESERVAS INFANTILES");
			System.out.println("-----------------------");
			
			if (gestorReserva.listarReservasInfantilFuturas().isEmpty()) {
				System.out.println("* No hay reservas infantiles futuras para la fecha dada");
			}else {
				System.out.println(gestorReserva.listarReservasInfantilFuturas().toString());
			}
			
			System.out.println("\n\n* RESERVAS FAMILIARES");
			System.out.println("-----------------------");
			
			if (gestorReserva.listarReservasFamiliarFuturas().isEmpty()) {
				System.out.println("* No hay reservas familiares futuras para la fecha dada");
			}else {
				System.out.println(gestorReserva.listarReservasFamiliarFuturas().toString());
			}
			
			System.out.println("\n\n* RESERVAS ADULTOS");
			System.out.println("-----------------------");
			
			if (gestorReserva.listarReservasAdultosFuturas().isEmpty()) {
				System.out.println("* No hay reservas adultos futuras para la fecha dada");
			}else {
				System.out.println(gestorReserva.listarReservasAdultosFuturas().toString());
			}
		}
		
		/**
		 * Para listar reservas segun fecha y pista
		 */
		
		public static void listarReservasFechaPista() {
			GestorReservas gestorReserva = new GestorReservas();
			GestorPistas gestorPista = new GestorPistas();
			scn = new Scanner(System.in);
			
			//Variable para salir del do-while
			boolean done = false;
			
			//Datos de la reserva y pista
			String fechaReserva, pista;
			
	        do {
	            System.out.print("\n-> Introduzca a partir de que fecha a consultar (dd/MM/yyyy): ");
	            fechaReserva = scn.nextLine();

	            System.out.print("-> Introduzca el nombre de la pista a consultar: ");
	            pista = scn.nextLine();

	            //Comprobamos que el formato de la fecha sea correcto
	            try {
	                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                sdf.setLenient(false);
	                sdf.parse(fechaReserva);
	            } catch (ParseException e1) {
	            	System.out.println("\n* Por favor, introduzca una fecha valida");
	                continue;
	            }
	           
	            if (!gestorPista.comprobarPistaExiste(pista)) {
	            	System.out.println("\n* Por favor, introduzca una pista existente");
	                continue;
	            }
	                      
	            done = true;
	            
	        }while(done == false);
			
	        System.out.println("\n\n* A partir de la fecha " + fechaReserva + " y para la pista " + pista + " se disponen de las siguientes reservas: ");
			
			System.out.println("\n\n* RESERVAS INFANTILES");
			System.out.println("-----------------------");
			
			if (gestorReserva.listarReservasInfantil(fechaReserva, pista).isEmpty()) {
				System.out.println("\n* No hay reservas infantiles futuras para la fecha y pista dadas");
			}else {
				System.out.println(gestorReserva.listarReservasInfantil(fechaReserva, pista).toString());
			}
			
			System.out.println("\n\n* RESERVAS FAMILIARES");
			System.out.println("-----------------------");
			
			if (gestorReserva.listarReservasFamiliar(fechaReserva, pista).isEmpty()) {
				System.out.println("\n* No hay reservas familiares futuras para le fecha y pistas dadas");
			}else {
				System.out.println(gestorReserva.listarReservasFamiliar(fechaReserva, pista).toString());
			}
			
			System.out.println("\n\n* RESERVAS ADULTOS");
			System.out.println("-----------------------");
			
			if (gestorReserva.listarReservasAdultos(fechaReserva, pista).isEmpty()) {
				System.out.println("\n* No hay reservas adultos futuras para le fecha y pistas dadas");
			}else {
				System.out.println(gestorReserva.listarReservasAdultos(fechaReserva, pista).toString());
			}
	        
		}
		
		
		/**
		 * Para borrar un bono
		 */
		
		public static void borrarBono() {
			GestorUsuarios gestorUsuario = new GestorUsuarios();
			GestorReservas gestorReserva = new GestorReservas();
			scn = new Scanner(System.in);
			
			System.out.print("* Introduzca su correo para buscar los bonos disponibles: ");
			String correo = scn.nextLine();
			
			//Control si usuario existe
			boolean comprobarUsuario = gestorUsuario.UsuarioBuscadoCorreo(correo);
			if(!comprobarUsuario) {
				System.out.print("* No existe un usuario registrado con ese correo. ");
				return;
			}
			
			//Comprobar que usuario tiene bono
			if (gestorReserva.listarBonosUsuario(correo).isEmpty()) {
				System.out.println("* El usuario no tiene ningun bono");
				return;
			}
			
			System.out.println("* El usuario dispone de los siguientes ID bonos: ");
			System.out.println(gestorReserva.listarBonosUsuario(correo).toString());
			
			System.out.println("* Introduzca el id del bono a borrar: ");
			int id = scn.nextInt();
			
			if (!gestorReserva.listarBonosUsuario(correo).contains(id)) {
				System.out.println("* Introduzca un id de bono valido");
				return;
			}
			
			System.out.print("\n* Pulse un 0 para cancelar con el borrado del bono. Pulse cualquier otro numero para continuar. ");
			int opc = scn.nextInt();
			
			if (opc == 0) {
				return;
			}
			
			System.out.println("\n* Se han borrado con exito las reservas asociadas al bono con ID:" + id);
			gestorReserva.cancelarReservaBono(id);
			
			System.out.println("\n* Se ha borrado el bono con ID:" + id);
			gestorReserva.cancelarBono(id);	
		}
}
