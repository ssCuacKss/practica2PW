package business.reserva;

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
	 * Implementación de la clase reserva Infantil
	 *
	 */

	@SuppressWarnings("serial")
	public class ReservaInfantilDTO extends AbstractReservaDTO implements Serializable{
		
		private int participantesInfantiles_;
		
		public ReservaInfantilDTO(){
			participantesInfantiles_ =-1;
		}
		
		public int getParticipantesInfantiles() {
			return participantesInfantiles_;
		}

		public void setParticipantesInfantiles(int participantesInfantiles) {
			this.participantesInfantiles_ = participantesInfantiles;
		}
		
		@Override
		public String toString() {
			String reservaInfo = (
								   "\t\n\n\n - Usuario: " + this.idUsuario_ 
								 + "\t\n - Fecha: " + this.getFecha() 
								 + "\t\n - Duracion: " + this.duracion_ 
								 + "\t\n - Participantes infantiles: " + this.participantesInfantiles_
								 + "\t\n - Descuento: " + this.descuento_
								 + "\t\n - Precio: " + this.precio_ 
					             + "\t\n - Pista: " + this.idPista_);
			return reservaInfo;
		}
		
		
	}
