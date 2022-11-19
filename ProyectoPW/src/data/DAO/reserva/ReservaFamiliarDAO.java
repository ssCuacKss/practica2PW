package data.DAO.reserva;

import data.common.DBConnection;

import business.reserva.ReservaFamiliarDTO;

import java.sql.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/**
 * @author Carlos Lucena Robles.
 * @author Miguel Raigón Jiménez.
 * @author Pablo Roldán Puebla.
 * @author Paloma Romero Delgado.
 * @author Kamal Abdelkader Haddu.
 * @version 1.0.0
 */

/**
 * DAO correspondiente a las operaciones necesarias para la manipulación de Reservas Familiares
 *
 */

public class ReservaFamiliarDAO {

	private Connection con;
	private Properties prop;
	
	/**
	 * Constructor sin parametros del DAO 
	 */
	
	public ReservaFamiliarDAO() {
		
		prop = new Properties();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("sql.properties")));
			prop.load(reader);
			reader.close();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para incluir una nueva reserva de tipo Familiar en la base de datos
	 * 
	 * @param reservaFamiliar reserva de tipo Familiar a incluir en la base de datos
	 */
	
	public void crearReservaFamiliar(ReservaFamiliarDTO reservaFamiliar) {
		
		DBConnection connection = new DBConnection();
		con = connection.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("crearReservaFamiliarSTM"));
			ps.setString(1, reservaFamiliar.getIdUsuario());
			ps.setInt(2,reservaFamiliar.getParticipantesInfantiles());
			ps.setInt(3,reservaFamiliar.getParticipantesAdultos());
			ps.setString(4, reservaFamiliar.getFecha());
			ps.setInt(5, reservaFamiliar.getDuracion());
			ps.setFloat(6, reservaFamiliar.getDescuento());
			ps.setFloat(7, reservaFamiliar.getPrecio());
			ps.setString(8, reservaFamiliar.getIdPista());
			
			ps.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		connection.closeConnection();
			
	}
	
	/**
	 * Metodo que crea una reserva de tipo Familiar asociada a un bono
	 * 
	 * @param reservaAFamiliar Reserva de tipo Familiar a incluir en la base de datos, asociada a un bono
	 * @param idBono Identificador del bono al que asociar a la reserva
	 */
	
	public void crearReservaFamiliarBono(ReservaFamiliarDTO reservaFamiliar, int idBono) {
		
		DBConnection connection = new DBConnection();
		con = connection.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("crearReservaFamiliarBonoSTM"));
			ps.setString(1, reservaFamiliar.getIdUsuario());
			ps.setInt(2, idBono);
			ps.setInt(3,reservaFamiliar.getParticipantesInfantiles());
			ps.setInt(4,reservaFamiliar.getParticipantesAdultos());
			ps.setString(5, reservaFamiliar.getFecha());
			ps.setInt(6, reservaFamiliar.getDuracion());
			ps.setFloat(7, reservaFamiliar.getDescuento());
			ps.setFloat(8, reservaFamiliar.getPrecio());
			ps.setString(9, reservaFamiliar.getIdPista());
			
			ps.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		connection.closeConnection();
			
	}
	
	/**
	 * Modifica una entrada de reserva de tipo Familiar de la base de datos
	 * 
	 * @param reservaFamiliar reserva de tipo Familiar existente en la base de datos a modificar
	 */
	
	public void modificarReservaFamiliar(ReservaFamiliarDTO reservaFamiliar) {
		
		DBConnection connection = new DBConnection();
		con = connection.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("crearReservaFamiliarSTM"));
			ps.setInt(2,reservaFamiliar.getParticipantesInfantiles());
			ps.setInt(3,reservaFamiliar.getParticipantesAdultos());
			ps.setString(4, reservaFamiliar.getFecha());
			ps.setInt(5, reservaFamiliar.getDuracion());
			ps.setFloat(7, reservaFamiliar.getPrecio());
			ps.setString(8, reservaFamiliar.getIdPista());
			
			ps.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		connection.closeConnection();
			
	}
	
	/**
	 * Método que obtiene las reservas de tipo Familiar cuya fecha aun no esté cumplida
	 * @return Una lista de Reservas de tipo Familiar con fecha superior a la actual
	 */
	
	public List<ReservaFamiliarDTO> consultarReservasFamiliar(){
		List<ReservaFamiliarDTO> reservas = new ArrayList<>();
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		
		try {
			Statement stmt = con.createStatement();
			String query = prop.getProperty("obtenerReservasFamiliarbyFechaSTM");
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String usuario = rs.getString("usuario");
				int participantes_infantiles = rs.getInt("participantes_infantiles");
				int participantes_adultos = rs.getInt("participantes_adultos");
				int duracion = rs.getInt("duracion");
				float descuento = rs.getFloat("descuento");
				float precio = rs.getFloat("precio");
				String pista = rs.getString("pista");
				
				ReservaFamiliarDTO reservafamiliar = new ReservaFamiliarDTO();
				
				reservafamiliar.setIdUsuario(usuario);
				reservafamiliar.setParticipantesInfantiles(participantes_infantiles);
				reservafamiliar.setParticipantesAdultos(participantes_adultos);
				reservafamiliar.setDuracion(duracion);
				reservafamiliar.setDescuento(descuento);
				reservafamiliar.setPrecio(precio);
				reservafamiliar.setIdPista(pista);
				reservas.add(reservafamiliar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		
		connection.closeConnection();
		return reservas;
	}
	
	/**
	 * 
	 * @param fecha Fecha que deben tener las reservas de tipo Familiar
	 * @param pista Pista a la que deben estar asociadas las reservas de tipo Familiar
	 * @return Lista de reservas de tipo Familiar con una fecha y pista concretas
	 */
	
	public List<ReservaFamiliarDTO> consultarReservasFamiliarbyFechaPista(String fecha, String pista){
		List<ReservaFamiliarDTO> reservas = new ArrayList<>();
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		
		try {
			Statement stmt = con.createStatement();
			String query = prop.getProperty("obtenerReservasFamiliarbyFechaPistaSTM")+String.format("'%s' AND pista='%s'", fecha, pista);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String usuario = rs.getString("usuario");
				int participantes_infantiles = rs.getInt("participantes_infantiles");
				int participantes_adultos = rs.getInt("participantes_adultos");
				int duracion = rs.getInt("duracion");
				float descuento = rs.getFloat("descuento");
				float precio = rs.getFloat("precio");
				
				ReservaFamiliarDTO reservafamiliar = new ReservaFamiliarDTO();
				
				reservafamiliar.setIdUsuario(usuario);
				reservafamiliar.setParticipantesInfantiles(participantes_infantiles);
				reservafamiliar.setParticipantesAdultos(participantes_adultos);
				reservafamiliar.setDuracion(duracion);
				reservafamiliar.setDescuento(descuento);
				reservafamiliar.setPrecio(precio);
				reservafamiliar.setIdPista(pista);
				reservas.add(reservafamiliar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		
		connection.closeConnection();
		return reservas;
	}
	
	
}
