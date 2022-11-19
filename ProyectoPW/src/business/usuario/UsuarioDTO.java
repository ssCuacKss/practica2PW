package business.usuario;

import java.util.Date;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * @author Carlos Lucena Robles.
 * @author Miguel Raigón Jiménez.
 * @author Pablo Roldán Puebla.
 * @author Paloma Romero Delgado.
 * @author Kamal Abdelkader Haddu.
 * @version 1.0.0
 */

/**
 * Implementación de la clase usuarioDTO
 *
 */

public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = -366946940529534569L;
	private String nombre_;
	private String apellidos_;
	private String correo_;
	private Date fechaNacimiento_;
	private Date fechaInscripcion_;
	
	/**
	 * Constructor vacio (default)
	 */
	
	public UsuarioDTO() {
		this.nombre_ = null;
		this.apellidos_ = null;
		this.correo_ = null;
		this.fechaNacimiento_ = null;
		this.fechaInscripcion_ = null;
	}
	
	/**
	 * Constructor parametrizado
	 * @param correo Correo del usuario
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param fechaNacimiento Fecha de nacimiento del usuario
	 * @param fechaInscripcion Fecha de inscripcion del usuario
	 */
	
	public UsuarioDTO(String correo, String nombre, String apellidos, String fechaNacimiento) {
		this.correo_ = correo;
		this.nombre_ = nombre;
		this.apellidos_ = apellidos;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			this.fechaNacimiento_ = sdf.parse(fechaNacimiento);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Getter nombre del usuario
	 * @return Nombre del usuario
	 */
	public String getNombre() {
		return nombre_;
	}
	
	/**
	 * Getter apellidos del usuario
	 * @return Apellidos del usuario
	 */
	public String getApellidos() {
		return apellidos_;
	}
	
	/**
	 * Getter correo del usuario
	 * @return Correo del usuario
	 */
	public String getCorreo() {
		return correo_;
	}
	
	/**
	 * Getter fecha de nacimiento del usuario
	 * @return Fecha de nacimiento del usuario
	 */
	public String getFechaNacimiento() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fechaNacimiento_);
	}
	
	/**
	 * Getter fecha de inscripción
	 * @return Fecha de inscripción
	 */
	public String getFechaInscripcion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fechaInscripcion_);
	}

	/**
	 * Setter del nombre del usuario
	 * @param nombre Nombre del usuario
	 */
	public void setNombre(String nombre) {
		this.nombre_ = nombre;
	}
	
	/**
	 * Setter de los apellidos del usuario
	 * @param apellidos Apellidos del usuario
	 */
	public void setApellidos(String apellidos) {
		this.apellidos_ = apellidos;
	}

	/**
	 * Setter del correo del usuario
	 * @param correo Correo del usaurio
	 */
	public void setCorreo(String correo) {
		this.correo_ = correo;
	}
	
	/**
	 * Setter de la fecha de nacimiento del usuario
	 * @param fechaNacimiento Fecha de nacimiento del usuario
	 */
	public void setFechaNacimiento(String fechaNacimiento) {	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			this.fechaNacimiento_ = sdf.parse(fechaNacimiento);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Setter de la fecha de inscripcion
	 * @param fecha_inscripcion Fecha de inscripcion
	 */
	public void setFechaInscripcion(String fecha_inscripcion) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			this.fechaInscripcion_ = sdf.parse(fecha_inscripcion);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Convierte todos los atributos de la clase en cadena
	 * Sobrescribe el método toString de la clase Object
	 * @return Cadena con los atributos de la clase
	 */
	@Override
	public String toString() {
		String usuarioInfo = "\n Nombre: " + this.nombre_ + "\n" + "Apellidos: " + this.apellidos_ + "\n" + "Correo: " + this.correo_ + "\n" + "Fecha nacimiento: " + this.getFechaNacimiento() + "\n" + "Fecha inscripcion: " + this.getFechaInscripcion() + "\n";
		return usuarioInfo;
	}
	
}