package data.DAO;

import data.common.DBConnection;

import business.kart.KartDTO;
import business.kart.Estado;

import java.sql.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
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
 * Implementacion de la clase KartDAO
 */

public class KartDAO {

	private Connection con;
	private Properties prop;
	
	public KartDAO() {
		
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
	 * Crear kart 
	 * @param kart KartDTO
	 */
	
	public void crearKart(KartDTO kart) {
		
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("crearKartSTM"));
			ps.setBoolean(1, kart.geType());
			ps.setString(2,kart.getStatus().name());
			ps.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		connection.closeConnection();
	}
	
	/**
	 * Actualizar kart con su pista asociada 
	 * @param pista Pista asociada
	 * @param id Id del kart
	 */
	
	public void actualizarPistaKart(String pista, int id) {
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("actualizarPistaKartSTM"));
			ps.setString(1,pista);
			ps.setInt(2,id);
			ps.executeUpdate();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		connection.closeConnection();
	}
	
	/**
	 * Actualizar estado del kart 
	 * @param estado Estado del kart
	 * @param id Id del kart
	 */
	
	public void actualizarEstadoKart(String estado, int id) {
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("actualizarEstadoKartSTM"));
			ps.setString(1,estado);
			ps.setInt(2,id);
			ps.executeUpdate();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		connection.closeConnection();
	}
	
	/**
	 * Consultar karts disponibles de una pista por su tipo
	 * @param nombre Nombre de una pista
	 * @return karts Listado de karts
	 */
	
	public List<KartDTO> consultarKartsPista(String nombre) {
		List<KartDTO> karts = new ArrayList<>();
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(prop.getProperty("obtenerKartsPistaSTM")+ String.format("'%s'", nombre));
			
			while (rs.next()) {
				int id = rs.getInt("id");
				Boolean type = rs.getBoolean("tipo");
				KartDTO kartToPush = new KartDTO();
				kartToPush.setId(id);
				kartToPush.seType(type);
				karts.add(kartToPush);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		
		connection.closeConnection();
		return karts;
	}
	
	/**
	 * Consultar karts sin asignar a pistas y que esten disponibles
	 * @return karts Listado de karts
	 */
	
	public List<KartDTO> consultarKartsDisponibles() {
		List<KartDTO> karts = new ArrayList<>();
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(prop.getProperty("obtenerKartDisponibleSTM"));
			while (rs.next()) {
				
				int id = rs.getInt("id");
				Boolean type = rs.getBoolean("tipo");
				String state = rs.getString("estado");
				KartDTO kartToPush = new KartDTO();
				kartToPush.setId(id);
				kartToPush.setStatus(Estado.valueOf(state.toUpperCase()));
				kartToPush.seType(type);
				karts.add(kartToPush);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		
		connection.closeConnection();
		return karts;
	}
	
	/**
	 * Obtener listado de todos los karts
	 * @return karts Listado de todos los karts
	 */
	
	public List<KartDTO> listadoKarts() {
		List<KartDTO> karts = new ArrayList<>();
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(prop.getProperty("obtenerTodosKartsSTM"));
			while (rs.next()) {
				
				int id = rs.getInt("id");
				Boolean type = rs.getBoolean("tipo");
				String state = rs.getString("estado");
				String pista = rs.getString("pista_asociada");
				KartDTO kartToPush = new KartDTO();
				kartToPush.setId(id);
				kartToPush.setStatus(Estado.valueOf(state.toUpperCase()));
				kartToPush.seType(type);
				kartToPush.setPista(pista);
				karts.add(kartToPush);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		connection.closeConnection();
		return karts;
	}
}
