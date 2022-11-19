package business.kart;

import java.io.Serializable;

/**
 * @author Carlos Lucena Robles.
 * @author Miguel Raigón Jiménez.
 * @author Pablo Roldán Puebla.
 * @author Paloma Romero Delgado.
 * @author Kamal Abdelkader Haddu.
 * @version 1.0.0
 */

/**
 * Implementación de la clase KartDTO
 */

public class KartDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id_;
	private boolean type_;
	Estado status_;
	String pista_;
	
	/**
	 * Constructor vacio (default)
	 */
	
	public KartDTO() {
		this.id_ = -1;
		this.type_ = false;
		this.status_ = Estado.MANTENIMIENTO;
		this.pista_ = null;
	
	}
	
	/**
	 * Constructor parametrizado
	 * @param id Identificador del kart
	 * @param type Tipo de kart (True para niños o False para adultos)
	 * @param status Estado del kart (Disponible, Reservado, Mantenimiento)
	 */
	
	public KartDTO(int id, boolean type, Estado status){
		this.id_ = id;
		this.type_ = type;
		this.status_ = status;
		this.pista_ = null;
	}
	
	/**
	 * Getter indentificador del kart
	 * @return Id del kart
	 */
	public int getId() {
		return id_;
	}
	
	/**
	 * Getter tipo de kart
	 * @return True si es para niños o False si es para adultos
	 */
	public boolean geType() {
		return type_;
	}
	
	/**
	 * Getter del estado del kart
	 * @return Estado del kart (Disponible, Reservado, Mantenimiento)
	 */
	public Estado getStatus() {
		return status_;
	}
	
	/**
	 * Getter de la pista a la que el kart está asociado
	 * @return Pista a la que el kart está asociado
	 */
	public String getPista() {
		return this.pista_;
	}
	
	/**
	 * Setter de la identificacion del kart.
	 * @param id Nueva ID para el kart.
	 */
	public void setId(int id) {
		id_ = id;
	}
	
	/**
	 * Setter del tipo de kart
	 * @param type True si es para niños o False si es para adultos.
	 */
	public void seType(boolean type) {
		type_ = type;
	}
	
	/**
	 * Setter del estado del kart
	 * @param kart Disponible, Reservado, Mantenimiento
	 */
	public void setStatus(Estado status) {
		status_ = status;
	}
	
	/**
	 * Setter de la pista a la que el kart está asociado
	 * @param status
	 */
	public void setPista(String pista) {
		pista_ = pista;
	}
	
	/**
	 * Convierte todos los atributos de la clase en cadena.
	 * Sobrescribe el método toString de la clase Object.
	 * @return Cadena con los atributos de la clase
	 */
	@Override
	public String toString() {
		
		String type;
		if(this.type_ == false){
			type = "Adultos";
		}else {
			type = "Infantil";
		}
		
		String kartInfo = "\n\n\t- Id: " + this.id_ 
					   + "\n\t- Tipo kart: " + type 
					   +"\n\t- Estado kart: " + this.status_
					   + "\n\t- Pista asociada: " + this.pista_ + "\n";
		return kartInfo;
	}
}
