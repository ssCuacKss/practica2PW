package business.gestores;

import business.kart.Estado;
import data.DAO.KartDAO;
import data.DAO.PistaDAO;
import business.kart.KartDTO;
import business.pista.Dificultad;
import business.pista.PistaDTO;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Carlos Lucena Robles.
 * @author Miguel Raigón Jiménez.
 * @author Pablo Roldán Puebla.
 * @author Paloma Romero Delgado.
 * @author Kamal Abdelkader Haddu.
 * @version 1.0.0
 */

/**
 * Implementacion del Gestor de Pistas 
 * Implementa la creacion de kart o pistas, la asignacion de kart a pista y
 * el listado de pistas en mantenimiento o libres
 */

public class GestorPistas {

	/**
	 * Metodo publico que crea kart nuevo
	 * @param type Tipo de kart
	 * @param estado Estado de kart
	 */
	
	public void crearKarts(boolean type, Estado estado) {
		
			KartDTO kart = new KartDTO(0,type,estado);
			KartDAO kartOperation = new KartDAO();
			kartOperation.crearKart(kart);
	}
	
	/**
	 * Metodo publico que crea pista nueva
	 * @param Nombre Nombre de la pista
	 * @param estado Estado de la pista
	 * @param dif Dificultad de la pista
	 * @param max_karts Numero maximo de karts
	 */
	
	public void crearPistas(String nombre,boolean estado, Dificultad dif, int max_karts) {
		
			PistaDTO pista = new PistaDTO(nombre,estado,dif,max_karts);
			PistaDAO pistaOperation = new PistaDAO();
			pistaOperation.CrearPista(pista);
	}
	
	/**
	 * Metodo publico que asocia kart a pista
	 * @param nombre Nombre de la pista
	 * @param dificultadPista Dificultad de la pista
	 * @param maxKarts Numero maximo de karts
	 * @param asocAmmount Cantidad asociada
	 * @param idKart Id del kart
	 * @param tipoKart Tipo de kart
	 * @return boolean
	 */
	
	public boolean asociarKartAPista(String nombre, String dificultadPista, int maxKarts, int asocAmmount, int idKart, boolean tipoKart) {
		
		KartDAO kartOperation = new KartDAO();
		
		//Se comprueba si al añadir un kart mas se supera la capacidad de la pista
		if (asocAmmount > maxKarts) {
			return false;
		}
		
		if (tipoKart == true && (dificultadPista == Dificultad.INFANTIL.name() || dificultadPista == Dificultad.FAMILIAR.name())) {
			kartOperation.actualizarPistaKart(nombre, idKart);
			return true;
			
		}else if (tipoKart == false && (dificultadPista == Dificultad.ADULTOS.name())) {
			kartOperation.actualizarPistaKart(nombre, idKart);
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Metodo publico que une los karts que no esten asignados a ninguna pista
	 * y disponibles a alguna pista disponible
	 * @return karts Lista de karts
	 */
	
	public List<KartDTO> unionKartPista(){
		
		//Se devuelven la lista de karts que se han asignado a una pista
		List<KartDTO> karts = new ArrayList<>();
		
		KartDAO kartOperation = new KartDAO();
		PistaDAO pistaOperation = new PistaDAO();
		
		List<KartDTO> kartList = new ArrayList<>();
		List<PistaDTO> pistaList = new ArrayList<>();
		
		//Obtenemos los karts que esten disponibles para la reserva y esten sin asignar a una pista
		kartList = kartOperation.consultarKartsDisponibles();
		
		//Obtenemos las pistas disponibles
		pistaList = pistaOperation.consultarByEstado(true);
		
		//Iteramos sobre las listas de pistas y karts disponibles
		for (PistaDTO pista : pistaList) {
			for(KartDTO kart : kartList) {
				
				//Comprobamos si es posible la asociacion del kart a la pista por su dificultad y tipo de kart y cantidad maxima
				if (asociarKartAPista(pista.getNombre(), pista.getDificulty().name(), pista.getMaxAmmount(), pista.getAsocAmmount(), kart.getId(), kart.geType())) {
						
					//Si la asignacion es posible cambiamos la pista asignada del kart
					kartOperation.actualizarPistaKart(pista.getNombre(), kart.getId());
					pistaOperation.actualizarPista(pista.getNombre(), true, pista.getAsocAmmount()+1);
						
					karts.add(kart);
				}
			}
		}
		return karts;
	}
	
	/**
	 * Metodo publico que devuelve lista de todos los karts
	 * @return Listado de todos los karts
	 */
	
	public List<KartDTO> listadoKarts() {
		
		KartDAO kartDAO = new KartDAO();
		return kartDAO.listadoKarts();
	}
	
	/**
	 * Metodo publico que lista las pistas en mantenimiento
	 * @return Listado de pistas en mantenimiento
	 */
	
	public List<PistaDTO> listadoPistaMantenimiento() {
		
		PistaDAO pistaOperation = new PistaDAO();
		return pistaOperation.consultarByEstado(false);
	}
	
	/**
	 * Metodo publico que lista las pistas que esten disponibles
	 * segun la dificultad y los karts asociados
	 * @param numeroInfantiles Participantes infantiles
	 * @param numeroAdultos Participantes adultos
	 * @param dificultad Dificultad de la pista
	 * @return pistas Listado de pistas disponibles
	 */
	
	public List<PistaDTO> listadoPistaLibre(int numeroInfantiles, int numeroAdultos, Dificultad dificultad) {
		PistaDAO pistaOperation = new PistaDAO();
		KartDAO kartOperation = new KartDAO();
		
		List<PistaDTO> pistas = new ArrayList<>();
		List<KartDTO> karts = new ArrayList<>();
		
		//Iteramos sobre todas las pistas
		for(PistaDTO pista : pistaOperation.consultarByEstado(true)) {
			
			if (pista.getDificulty() == dificultad) {
				karts = kartOperation.consultarKartsPista(pista.getNombre());
				
				//Iteramos sobre los karts asociados, contando el numero de karts de cada tipo
				int contKartInfantil=0, contKartAdultos=0;
				for (KartDTO kart : karts) {
					
					if (kart.geType() == true && contKartInfantil != numeroInfantiles) {
						++contKartInfantil;
					}else if (kart.geType() == false && contKartAdultos != numeroAdultos) {
						++contKartAdultos;
					}
					
					//Nos salimos del bucle cuando ya encontramos la pista adecuada
					if (contKartInfantil == numeroInfantiles && contKartAdultos == numeroAdultos) {
						pistas.add(pista);
						break;
					}
				}
			}
		}
		return pistas;
	}
	
	/**
	 * Metodo publico que reserva karts a pistas
	 * @param numeroInfantiles Participantes infantiles
	 * @param numeroAdultos Participantes adultos
	 * @param dificultad Dificultad de la pista
	 */
	
	//Marcamos los karts tantos como reservados o disponibles (segun se necesite) para la pista dada
	public void reservarKartsPista(String nombre, int numeroInfantiles, int numeroAdultos, Estado estado) {
		KartDAO kartOperation = new KartDAO();
		
		//Recorremos la lista de los karts asociados para marcalos como reservados segun el numero de participantes dados
		int contKartInfantil=0, contKartAdultos=0;
		for (KartDTO kart : kartOperation.consultarKartsPista(nombre)) {
			
			if (kart.geType() == true && contKartInfantil != numeroInfantiles) {
				++contKartInfantil;	
				kartOperation.actualizarEstadoKart(estado.name(), kart.getId());
				
			}else if (kart.geType() == false && contKartAdultos != numeroAdultos) {
				++contKartAdultos;
				kartOperation.actualizarEstadoKart(estado.name(), kart.getId());
			}
			
			//Terminamos cuando ya se han cubierto los karts para todos los participantes
			if (contKartInfantil == numeroInfantiles && contKartAdultos == numeroAdultos) {
				return;
			}
		}
	}
	
	/**
	 * Metodo publico que comprueba si una pista existe por su nombre
	 * @return boolean
	 */
	
	public boolean comprobarPistaExiste(String nombre) {
		PistaDAO pistaOperation = new PistaDAO();
		return pistaOperation.consultarPistaExiste(nombre);
	}
}
