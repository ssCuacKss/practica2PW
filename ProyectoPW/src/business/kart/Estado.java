package business.kart;

	/**
	 * Enumeracion que indica el estado del kart
	 */
public enum Estado {
	/**
	 * El kart puede ser usado
	 */
	DISPONIBLE,
	/**
	 * El kart esta reservado por otro cliente
	 */
	RESERVADO,
	/**
	 * El kart se encuentra inoperativo
	 */
	MANTENIMIENTO
}
