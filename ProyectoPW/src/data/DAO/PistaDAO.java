package data.DAO;

import data.common.DBConnection;
import business.pista.Dificultad;
import business.pista.PistaDTO;

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
 * Implementación de la clase pistaDAO
 */

public class PistaDAO{
	
	private Connection con;
	private Properties prop;
	
	public PistaDAO(){
		
		prop = new Properties();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("sql.properties")));
			prop.load(reader);
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Crear pista
	 * @param pista PistaDTO
	 */
	
	public void CrearPista(PistaDTO pista) {
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("crearPistaSTM"));
			ps.setString(1, pista.getNombre());
			ps.setBoolean(2, pista.getEstado());
			ps.setString(3, pista.getDificulty().name());
			ps.setInt(4, pista.getMaxAmmount());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.closeConnection();
	}
	
	/**
	 * Actualizar pista
	 * @param nombre Nombre de la pista
	 * @param estado Estado de la pista
	 * @param asocKart Numero de karts asociados a la pista
	 */
	
	public void actualizarPista(String nombre, boolean estado, int asocKart) {
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("actualizarPistaSTM") + String.format("'%s'", nombre));
			ps.setBoolean(1, estado);
			ps.setInt(2, asocKart);
			ps.setString(3, nombre);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.closeConnection();
	}
	
	/**
	 * Obtener pistas por su estado
	 * @param estado Estado de la pista
	 * @return pistas Listado de pistas
	 */
	
	public List<PistaDTO> consultarByEstado(Boolean estado){
		List<PistaDTO> pistas = new ArrayList<>();
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = prop.getProperty("obtenerPistasbyStateSTM")+estado;
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String dificultad = rs.getString("dificultad");
				int max_karts = rs.getInt("max_karts");
				int asoc_karts = rs.getInt("asoc_karts");
				PistaDTO pistaToPush = new PistaDTO();
				pistaToPush.setNombre(nombre);
				pistaToPush.setDificulty(Dificultad.valueOf(dificultad.toUpperCase()));
				pistaToPush.setMaxAmmount(max_karts);
				pistaToPush.setAsocAmmount(asoc_karts);
				pistaToPush.setEstado(estado);
				pistas.add(pistaToPush);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		
		connection.closeConnection();
		return pistas;
	}
	
	/**
	 * Comprobar si una pista existe
	 * @param nombre Nombre de la pista
	 * @return boolean
	 */
	
	public boolean consultarPistaExiste(String nombre){
		boolean flag = false;
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = prop.getProperty("checkPistabyNombreSTM")+String.format("'%s')", nombre);
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				flag = rs.getBoolean(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		
		connection.closeConnection();
		return flag;
	}	
}