package business.gestores;

import business.usuario.UsuarioDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import data.DAO.*;


/**
 * @author Carlos Lucena Robles.
 * @author Miguel Raigón Jiménez.
 * @author Pablo Roldán Puebla.
 * @author Paloma Romero Delgado.
 * @author Kamal Abdelkader Haddu.
 * @version 1.0.0
 */

/**
 * Implementación de la clase GestorUsuarios
 */

public class GestorUsuarios{
		
	/**
	 * Metodo publico que da de alta a un usuario
	 * @param correo Correo del usuario
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param fechaNacimiento Fecha de nacimiento del usuario
	 * @param fechaInscripcion Fecha de inscripcion del usuario
	 * @return boolean
	 */
	
	public boolean altaUsuario(String correo, String nombre, String apellidos, String fechaNacimiento) {
		
		UsuarioDTO usuario = new UsuarioDTO(correo, nombre, apellidos, fechaNacimiento);
		
		//Si ya existe un usuario con ese correo devuelve false
		List<UsuarioDTO> usuarios = listadoUsuarios();
		for(int i = 0; i<usuarios.size(); i++) {
			if(usuarios.get(i).getCorreo().equals(correo)) {
				return false;
			}
		}
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.altaUsuario(usuario);
		
		return true;
	}
	
	/**
	 * Metodo publico que mira la mayoria de edad de un usuario
	 * @param fecha Fecha de nacimiento del usuario
	 * @return boolean
	 */
	
	public boolean mayoriaEdad(String fecha) {
		
		Date currDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		Date fecha_nac = new Date();
		
		try {
			fecha_nac = sdf.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long diff = currDate.getTime() - fecha_nac.getTime(); 
		long d = (1000l*60*60*24*365);
		long years = Math.round(diff/d);
		int edad = (int) years;
		
		//Si es mayor de edad se devuelve true
		if (edad > 0 && edad < 18) {
			return false;
		}
		return true;
	}
	
	/**
	 * Metodo publico que modifica todos los campos de un usuario
	 * @param correo Correo del usuario
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param fechaNacimiento Fecha de nacimiento del usuario
	 * @param fechaInscripcion Fecha de inscripcion del usuario
	 */

	public void modificarUsuario(String correo, String nombre, String apellidos, String fechaNacimiento) {
		
		UsuarioDTO usuario = new UsuarioDTO(correo, nombre, apellidos, fechaNacimiento);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.modificarUsuario(usuario);
	}
	
	/**
	 * Metodo publico que modifica correo de un usuario
	 * @param correo Correo del usuario
	 * @param correo_nuevo Nuevo correo del usuario
	 * 
	 * Funciona pero puesto que correo es PK, si el usuario tiene una reserva hecha,
	 * al cambiar el correo salta error ya que deja de existir
	 */
	public void modificarCorreoUsuario(String correo, String correo_nuevo) {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.modificarCorreoUsuario(correo, correo_nuevo);
	}

	
	/**
	 * Metodo publico que modifica nombre de un usuario
	 * @param correo Correo del usuario
	 * @param nombre Nombre del usuario
	 */
	
	public void modificarNombreUsuario(String correo, String nombre) {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.modificarNombreUsuario(correo, nombre);
	}
	
	/**
	 * Metodo publico que modifica apellidos de un usuario
	 * @param correo Correo del usuario
	 * @param apellidos Apellidos del usuario
	 */
	
	public void modificarApellidosUsuario(String correo, String apellidos) {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.modificarApellidosUsuario(correo, apellidos);
	}

	/**
	 * Metodo publico que modifica fecha de nacimiento de un usuario
	 * @param correo Correo del usuario
	 * @param fechaNacimiento Fecha de nacimiento del usuario
	 */
	
	public void modificarFechaNacimientoUsuario(String correo, String fechaNacimiento) {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.modificarFechaNacimientoUsuario(correo, fechaNacimiento);
	}
	
	/**
	 * Metodo publico que modifica fecha de inscripcion de un usuario
	 * @param correo Correo del usuario
	 * @param fechaInscripcion Fecha de inscripcion del usuario
	 */
	
	public void modificarFechaInscripcionUsuario(String correo, String fechaInscripcion) {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.modificarFechaInscripcionUsuario(correo, fechaInscripcion);
	}
	
	/**
	 * Metodo publico que elimina un usuario
	 * @param correo Correo del usuario
	 */

	public void eliminarUsuario(String correo) {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.eliminarUsuario(correo);
	}
	
	/**
	 * Metodo publico que busca un usuario en concreto
	 * @param correo Correo del usuario
	 * @return boolean
	 */
	
	public boolean UsuarioBuscadoCorreo(String correo) {
		
		//Si no existe un usuario con ese correo devuelve false
		List<UsuarioDTO> usuarios = listadoUsuarios();
		
		for(UsuarioDTO it : usuarios) {
			if(it.getCorreo().equals(correo)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Metodo publico que devuelve lista de todos los usuarios
	 * @return Listado de todos los usuarios
	 */
	
	public List<UsuarioDTO> listadoUsuarios() {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.obtenerUsuarios();
	}
	
	/**
	 * Metodo publico que comprueba el correo de un usuario
	 * @param correo Correo del usuario
	 * @return boolean
	 */
	
	public boolean correoValido(String correo) {
		List<String> dominios = new ArrayList<>(List.of("@gmail.es", "@gmail.com", "@hotmail.com", "@hotmail.es", "@outlook.com", "outlook.es", "@live.com", "@uco.es"));
		
		for (String it : dominios) {
			if (correo.contains(it) && correo.endsWith(it)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metodo publico que comprueba la fecha de nacimiento de un usuario
	 * @param fechaNacimiento fecha de nacimiento del usuario
 	 * @return boolean
	 */
	
	public boolean fechaNacimientoValida(String fechaNacimiento) {
		Date fechaActual = new Date();
		Date fecha = new Date();
		
		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		
		int YearActual = 0, YearFechaNacimiento = 0;
		
		try {
			fecha = sdf.parse(fechaNacimiento);
			YearActual = Integer.parseInt(getYearFormat.format(fechaActual));
		    YearFechaNacimiento = Integer.parseInt(getYearFormat.format(fecha));
		    
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException f) {
			f.printStackTrace();
		}
        
		 if (YearFechaNacimiento > YearActual || YearFechaNacimiento < (YearActual - 120)) {
				return false;
		}
		return true;
	}
	
	/**
	 * Metodo publico que calcula el numero de años que un usuario lleva registrado
	 * @param correo Correo del usuario
	 * @return years entero que indica el numero de años que lleva un usuario registrado
	 */
	
	public int calcularAntiguedad(String correo) {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		String fecha_Inscripcion = usuarioDAO.calcularAntiguedad(correo);
		
		//Comprobamos si el valor es el por defecto
		if (fecha_Inscripcion.equals("1/1/1900")) {
			return 0;
		}
		
		long years = 0;
		
		Date currDate = new Date();
		Date fecha = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			fecha = sdf.parse(fecha_Inscripcion);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long diff = currDate.getTime() - fecha.getTime();
		long d = (1000l*60*60*24*365);
		years = Math.round(diff/d);
		
		return (int) years;
	}
}