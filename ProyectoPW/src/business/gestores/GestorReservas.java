package business.gestores;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;


import business.reserva.AbstractReservaDTO;
import business.reserva.ReservaAdultosDTO;
import business.reserva.ReservaFamiliarDTO;
import business.reserva.ReservaInfantilDTO;
import data.DAO.reserva.ReservaAdultosDAO;
import data.DAO.reserva.ReservaDAO;
import data.DAO.reserva.ReservaFamiliarDAO;
import data.DAO.reserva.ReservaInfantilDAO;


/**
 * @author Carlos Lucena Robles.
 * @author Miguel Raigón Jiménez.
 * @author Pablo Roldán Puebla.
 * @author Paloma Romero Delgado.
 * @author Kamal Abdelkader Haddu.
 * @version 1.0.0
 */


/**
 * Implementación del Gestor de Reservas que realiza reservas individuales y de bonos segun determinadas condiciones
 * 
 *
 */

public class GestorReservas{
	
	   /**
		* Metodo publico que crea una reserva de tipo infantil
		* @param usuario Correo del usuario que realiza la reserva
		* @param participantes_infantiles Numero de participantes infantiles
		* @param fecha Fecha para la reserva
		* @param duracion Duracion de la reserva
		* @param descuento Descuento que puede ser aplicado a la reserva
		* @param precio Precio de la reserva
		* @param pista Pista en la que se realizará la reserva
		*/

		public void crearReservaInfantil(String usuario, int participantes_infantiles, String fecha, int duracion, float descuento, float precio, String pista) {
			
			ReservaInfantilDTO reservaInfantil = new ReservaInfantilDTO();
			
			reservaInfantil.setDescuento(descuento);
			reservaInfantil.setDuracion(duracion);
			reservaInfantil.setFecha(fecha);
			reservaInfantil.setIdPista(pista);
			reservaInfantil.setIdUsuario(usuario);
			reservaInfantil.setPrecio(precio);
			reservaInfantil.setParticipantesInfantiles(participantes_infantiles);
			
			ReservaInfantilDAO reserva = new ReservaInfantilDAO();
			reserva.crearReservaInfantil(reservaInfantil);
			
		}
		
		/**
		 * Metodo publico que crea una reserva de tipo familiar
		 * @param usuario Correo del usuario que realiza la reserva
		 * @param participantes_infantiles Numero de participantes infantiles
		 * @param participantes_adultos Numero de participantes adultos
		 * @param fecha Fecha para la reserva
		 * @param duracion Duracion de la reserva
		 * @param descuento Descuento que puede ser aplicado a la reserva
		 * @param precio Precio de la reserva
		 * @param pista Pista en la que se realizará la reserva
		 */		
		
		public void crearReservaFamiliar(String usuario, int participantes_infantiles, int participantes_adultos,  String fecha, int duracion, float descuento, float precio, String pista) {
			
			ReservaFamiliarDTO reservaFamiliar = new ReservaFamiliarDTO();
			
			reservaFamiliar.setDescuento(descuento);
			reservaFamiliar.setDuracion(duracion);
			reservaFamiliar.setFecha(fecha);
			reservaFamiliar.setIdPista(pista);
			reservaFamiliar.setIdUsuario(usuario);
			reservaFamiliar.setPrecio(precio);
			reservaFamiliar.setParticipantesInfantiles(participantes_infantiles);
			reservaFamiliar.setParticipantesAdultos(participantes_adultos);
			
			ReservaFamiliarDAO reserva = new ReservaFamiliarDAO();
			reserva.crearReservaFamiliar(reservaFamiliar);
			
		}
	
		/**
		 * Metodo publico que crea una reserva de tipo adulto
		 * @param usuario Correo del usuario que realiza la reserva
		 * @param participantes_adultos Numero de participantes adultos
		 * @param fecha Fecha para la reserva
		 * @param duracion Duracion de la reserva
		 * @param descuento Descuento que puede ser aplicado a la reserva
		 * @param precio Precio de la reserva
		 * @param pista Pista en la que se realizará la reserva
		 */	
		
		public void crearReservaAdultos(String usuario, int participantes_adultos, String fecha, int duracion, float descuento, float precio, String pista) {
		
		ReservaAdultosDTO reservaAdultos = new ReservaAdultosDTO();
		
		reservaAdultos.setDescuento(descuento);
		reservaAdultos.setDuracion(duracion);
		reservaAdultos.setFecha(fecha);
		reservaAdultos.setIdPista(pista);
		reservaAdultos.setIdUsuario(usuario);
		reservaAdultos.setPrecio(precio);
		reservaAdultos.setParticipantesAdultos(participantes_adultos);
		
		ReservaAdultosDAO reserva = new ReservaAdultosDAO();
		reserva.crearReservaAdulto(reservaAdultos);
		
	}
		
		
		/**
		 * Metodo publico que crea un bono
		 * @param usuario Correo del usuario que realiza la reserva
		 * @return int Ultimo id del bono usado
		 */	
		
		public int crearBono(String usuario) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date currDate = new Date();
			
			//Pasamos la fecha actual a ms y le sumamos 1 año en milisegundos
			long ms = (long) (currDate.getTime() + 3.154e+10);
	
			//Obtenemos la fecha de caducidad en tipo Date y lo pasamos a tipo string
			Date fechaCaducidad = new Date(ms);
			String fecha;
		
			fecha = sdf.format(fechaCaducidad);
	        
			ReservaDAO reserva = new ReservaDAO();
			
			//Obtenemos el ultimo id del bono usado para devolverlo y crear el siguiente
			int id = reserva.consultarUltimoIdBono();
			
			reserva.insertarBono(id+1,usuario, fecha);
			
			return id+1;
		}
		
		/**
		 * Metodo publico que crea una bono de reservas de tipo infantil
		 * @param idBono ID del bono
		 * @param usuario Correo del usuario que realiza la reserva
		 * @param participantes_infantiles Numero de participantes infantiles
		 * @param fecha Fecha para la reserva
		 * @param duracion Duracion de la reserva
		 * @param descuento Descuento que puede ser aplicado a la reserva
		 * @param precio Precio de la reserva
		 * @param pista Pista en la que se realizará la reserva
		 */		
		 	
		public void crearReservaInfantilBono(int idBono, String usuario, int participantes_infantiles, String fecha, int duracion, float descuento, float precio, String pista) {
			
			ReservaInfantilDTO reservaInfantil = new ReservaInfantilDTO();
			
			reservaInfantil.setDescuento(descuento);
			reservaInfantil.setDuracion(duracion);
			reservaInfantil.setFecha(fecha);
			reservaInfantil.setIdPista(pista);
			reservaInfantil.setIdUsuario(usuario);
			reservaInfantil.setPrecio(precio);
			reservaInfantil.setParticipantesInfantiles(participantes_infantiles);
			
			ReservaInfantilDAO reserva = new ReservaInfantilDAO();
			reserva.crearReservaInfantilBono(reservaInfantil, idBono);
			
		}
	
		/**
		 * Metodo publico que crea una bono de reservas de tipo familiar
		 * @param idBono Id del bono
		 * @param usuario Correo del usuario que realiza la reserva
		 * @param participantes_infantiles Numero de participantes infantiles
		 * @param participantes_adultos Numero de participantes adultos
		 * @param fecha Fecha para la reserva
		 * @param duracion Duracion de la reserva
		 * @param descuento Descuento que puede ser aplicado a la reserva
		 * @param precio Precio de la reserva
		 * @param pista Pista en la que se realizará la reserva
		 */			
		
		public void crearReservaFamiliarBono(int idBono, String usuario, int participantes_infantiles, int participantes_adultos,  String fecha, int duracion, float descuento, float precio, String pista) {
			
			ReservaFamiliarDTO reservaFamiliar = new ReservaFamiliarDTO();
			
			reservaFamiliar.setDescuento(descuento);
			reservaFamiliar.setDuracion(duracion);
			reservaFamiliar.setFecha(fecha);
			reservaFamiliar.setIdPista(pista);
			reservaFamiliar.setIdUsuario(usuario);
			reservaFamiliar.setPrecio(precio);
			reservaFamiliar.setParticipantesInfantiles(participantes_infantiles);
			reservaFamiliar.setParticipantesAdultos(participantes_adultos);
			
			ReservaFamiliarDAO reserva = new ReservaFamiliarDAO();
			reserva.crearReservaFamiliarBono(reservaFamiliar, idBono);
			
		}
	
		/**
		 * Metodo publico que crea una bono de reservas de tipo adulto
		 * @param idBono Id del bono
		 * @param usuario Correo del usuario que realiza la reserva
		 * @param participantes_adultos Numero de participantes adultos
		 * @param fecha Fecha para la reserva
		 * @param duracion Duracion de la reserva
		 * @param descuento Descuento que puede ser aplicado a la reserva
		 * @param precio Precio de la reserva
		 * @param pista Pista en la que se realizará la reserva
		 */			
		
		public void crearReservaAdultosBono(int idBono, String usuario, int participantes_adultos, String fecha, int duracion, float descuento, float precio, String pista) {
		
		ReservaAdultosDTO reservaAdultos = new ReservaAdultosDTO();
		
		reservaAdultos.setDescuento(descuento);
		reservaAdultos.setDuracion(duracion);
		reservaAdultos.setFecha(fecha);
		reservaAdultos.setIdPista(pista);
		reservaAdultos.setIdUsuario(usuario);
		reservaAdultos.setPrecio(precio);
		reservaAdultos.setParticipantesAdultos(participantes_adultos);
		
		ReservaAdultosDAO reserva = new ReservaAdultosDAO();
		reserva.crearReservaAdultoBono(reservaAdultos, idBono);
		
	}
		
		/**
		 * Metodo publico que modifica una reserva de tipo infantil
		 * @param participantes_infantiles Numero de participantes infantiles
		 * @param fecha Fecha para la reserva
		 * @param duracion Duracion de la reserva
		 * @param pista Pista en la que se realizará la reserva
		 * @param usuario Correo del usuario que desea modificar la reserva
		 */		
	
		public void modificarReservaInfantil(int participantes_infantiles, String fecha, int duracion, String pista, String usuario) {
			
			ReservaInfantilDTO reservaInfantil = new ReservaInfantilDTO();
			
			reservaInfantil.setIdUsuario(usuario);
			reservaInfantil.setDuracion(duracion);
			reservaInfantil.setFecha(fecha);
			reservaInfantil.setParticipantesInfantiles(participantes_infantiles);
			reservaInfantil.setIdPista(pista);
			
			ReservaInfantilDAO reserva = new ReservaInfantilDAO();
			reserva.modificarReservaInfantil(reservaInfantil);
			
		}
		
		/**
		 * Metodo publico que modifica una reserva de tipo familiar
		 * @param participantes_infantiles Numero de participantes infantiles
		 * @param participantes_adultos Numero de participantes adultos
		 * @param fecha Fecha para la reserva
		 * @param duracion Duracion de la reserva
		 * @param pista Pista en la que se realizará la reserva
		 * @param usuario Correo del usuario que desea modificar la reserva
		 */		
		
		public void modificarReservaFamiliar(int participantes_infantiles, int participantes_adultos, String fecha, int duracion, String pista, String usuario) {
			
			ReservaFamiliarDTO reservaFamiliar = new ReservaFamiliarDTO();
			
			reservaFamiliar.setIdUsuario(usuario);
			reservaFamiliar.setDuracion(duracion);
			reservaFamiliar.setFecha(fecha);
			reservaFamiliar.setParticipantesAdultos(participantes_adultos);
			reservaFamiliar.setParticipantesInfantiles(participantes_infantiles);
			reservaFamiliar.setIdPista(pista);
			
			ReservaFamiliarDAO reserva = new ReservaFamiliarDAO();
			reserva.modificarReservaFamiliar(reservaFamiliar);
			
		}
	
		/**
		 * Metodo publico que modifica una reserva de tipo adulto
		 * @param participantes_adultos Numero de participantes adultos
		 * @param fecha Fecha para la reserva
		 * @param duracion Duracion de la reserva
		 * @param pista Pista en la que se realizará la reserva
		 * @param usuario Correo del usuario que desea modificar la reserva
		 */			
		
		public void modificarReservaAdultos(int participantes_adultos, String fecha, int duracion, String pista, String usuario) {
		
		ReservaAdultosDTO reservaAdultos = new ReservaAdultosDTO();
		
		reservaAdultos.setIdUsuario(usuario);
		reservaAdultos.setDuracion(duracion);
		reservaAdultos.setFecha(fecha);
		reservaAdultos.setParticipantesAdultos(participantes_adultos);
		reservaAdultos.setIdPista(pista);
		
		ReservaAdultosDAO reserva = new ReservaAdultosDAO();
		reserva.modificarReservaAdulto(reservaAdultos);
		
		}
		
		/**
		 * Metodo publico que cancela una reserva individual
		 * @param usuario Usuario que cancela la reserva
		 * @param fecha Fecha de la reserva a cancelar
		 */		
		
		public void cancelarReserva(String usuario, String fecha){
			
			AbstractReservaDTO abstractReserva = new AbstractReservaDTO();
			abstractReserva.setIdUsuario(usuario);
			abstractReserva.setFecha(fecha);
			
			ReservaDAO reserva = new ReservaDAO();
			reserva.borrarReserva(abstractReserva);
			
		}
		
		/**
		 * Metodo publico que cancela una reserva de un bono
		 * @param usuario Usuario que cancela la reserva
		 * @param fecha Fecha de la reserva a cancelar
		 */		
		
		public void cancelarReservaBono(int id){
			
			ReservaDAO reserva = new ReservaDAO();
			reserva.borrarReservaBono(id);
			
		}
		
		/**
		 * Metodo publico que cancela un bono
		 * @param usuario Usuario que cancela la reserva del bono
		 */		
		
		public void cancelarBono(int id){
			
			ReservaDAO reserva = new ReservaDAO();
			reserva.borrarBono(id);
			
		}
		
		/**
		 * Metodo publico que devuelve lista de todas las reservas futuras infantiles
		 * @return Listado de todas las reservas infantiles futuras
		 */
		
		public List<ReservaInfantilDTO> listarReservasInfantilFuturas() {
			
			ReservaInfantilDAO reservaInfantil = new ReservaInfantilDAO();
			
			return reservaInfantil.consultarReservasInfantil();
			
		}
		
		/**
		 * Metodo publico que devuelve lista de todas las reservas futuras familiares
		 * @return Listado de todas las reservas familiares futuras
		 */			
		
		public List<ReservaFamiliarDTO> listarReservasFamiliarFuturas() {
			
			ReservaFamiliarDAO reservaFamiliar = new ReservaFamiliarDAO();
			
			return reservaFamiliar.consultarReservasFamiliar();
			
		}
		
		/**
		 * Metodo publico que devuelve lista de todas las reservas futuras de adultos
		 * @return Listado de todas las reservas de adultos futuras
		 */			
		
		public List<ReservaAdultosDTO> listarReservasAdultosFuturas() {
			
			ReservaAdultosDAO reservaAdultos = new ReservaAdultosDAO();
			
			return reservaAdultos.consultarReservasAdultos();
			
		}
		
		/**
		 * Metodo publico que devuelve lista de todas las reservas infantiles
		 * @return Listado de todas las reservas infantiles
		 */		
		
		public List<ReservaInfantilDTO> listarReservasInfantil(String fecha, String pista) {
			
			ReservaInfantilDAO reservaInfantil = new ReservaInfantilDAO();
			
			return reservaInfantil.consultarReservasInfantilbyFechaPista(fecha, pista);
			
			
		}
		
		/**
		 * Metodo publico que devuelve lista de todas las reservas familiares
		 * @return Listado de todas las reservas familiares
		 */		
		
		public List<ReservaFamiliarDTO> listarReservasFamiliar(String fecha, String pista) {
			
			ReservaFamiliarDAO reservaFamiliar = new ReservaFamiliarDAO();
			
			return reservaFamiliar.consultarReservasFamiliarbyFechaPista(fecha, pista);
			
			
		}
		
		/**
		 * Metodo publico que devuelve lista de todas las reservas de adultos
		 * @return Listado de todas las reservas de adultos
		 */		
		
		public List<ReservaAdultosDTO> listarReservasAdultos(String fecha, String pista) {
			
			ReservaAdultosDAO reservaAdultos = new ReservaAdultosDAO();
			
			return reservaAdultos.consultarReservasAdultosbyFechaPista(fecha, pista);
			
		}
		
		
		/**
		 * Metodo publico que devuelve lista de todos los bonos que tiene un usuario
		 * @return Listado de todos los bonos que tiene un usuario
		 */			
		
		public List<Integer> listarBonosUsuario(String usuario){
			
			ReservaDAO reserva = new ReservaDAO();
			
			return reserva.consultarBono(usuario);
		}
		
		/**
		 * Metodo publico que calcula el precio de una reserva, aplicando el descuento en funcion de la antiguedad del usuario
		 * @param usuario Usuario que ha realizado la reserva
		 * @param fecha Fecha de la reserva
		 * @param duracion Duración de la reserva
		 * @param antiguedad Antiguedad del usuario que ha realizado la reserva
		 * @return precio Flotante que indica el precio final de la reserva
		 */		
		
		public float PrecioReserva(String usuario, String fecha, int duracion, int antiguedad) {
			
			float precio = 1;
			
		    if (descuentoReserva(antiguedad) == 10) {
		    	precio = 0.9f;
		    }
			
			if(duracion == 60) {
				precio = 20 * precio;
				
			}
			else if(duracion == 90) {
				precio = 30 * precio;
			}
			else {
				precio = 40 * precio;			
			}
			
			return precio;
			
		}
		
		/**
		 * Metodo publico que calcula el precio de un bono, aplicando el descuento correspondiente
		 * @param usuario Usuario que ha realizado la reserva
		 * @param fecha Fecha de la reserva
		 * @param duracion Duración de la reserva
		 * @return precio Flotante que indica el precio final del bono
		 */			
		
		public float PrecioReservaBono(String usuario, String fecha, int duracion) {
				
			float precio = 0.95f;
			
			if(duracion == 60) {
				precio = 20 * precio;
				
			}
			else if(duracion == 90) {
				precio = 30 * precio;
			}
			else {
				precio = 40 * precio;			
			}
			return precio;
				
		}
		
		/**
		 * Metodo publico que calcula el descuento para una reserva en funcion de la antiguedad del usuario
		 * @param antiguedad Antiguedad del usuario que ha realizado la reserva
		 * @return descuento Flotante que indica el descuento a aplicar
		 */	
		
		public float descuentoReserva(int antiguedad) {
			
			float descuento = 0;
			if(antiguedad > 2) {
				
				descuento = 10f;
			}
			
			return descuento;
		}
		
		/**
		 * Metodo publico que comprueba si la fecha de la reserva realizada es posterior a la fecha actual
		 * @param fechaReserva Fecha para la reserva
		 * @return boolean Flotante que indica si la fecha de la reserva es posterior a la actual o no
		 */			
		
		public boolean comprobarFecha(Date fechaReserva) {
			Date currDate = new Date();
			
			//Si la fecha de la reserva es posterior a la actual
			if (fechaReserva.compareTo(currDate) > 0) {
				return true;
			}
		
			return false;
		}
	
}
