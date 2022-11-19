package data.DAO;

import data.common.DBConnection;
import business.usuario.UsuarioDTO;

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
 * Implementación de la clase usuarioDAO
 *
 */

public class UsuarioDAO{
	
	private Connection con;
	private Properties prop;
	
	public UsuarioDAO(){
		
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
	 * Dar de alta a un usuario
	 * @param usuarioDTO
	 */
	
	public void altaUsuario(UsuarioDTO usuario) {
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("altaUsuarioSTM"));
			ps.setString(1, usuario.getCorreo());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getApellidos());
			ps.setString(4, usuario.getFechaNacimiento());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.closeConnection();
	}
	
	/**
	 * Modificar todos los campos de usuario salvo correo
	 * puesto que correo es PK y si tiene reservas hechas salta error
	 * @param usuarioDTO
	 */
	
	public void modificarUsuario(UsuarioDTO usuario) {
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("modificarUsuarioSTM"));
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApellidos());
			ps.setString(3, usuario.getFechaNacimiento());
			ps.setString(4, usuario.getCorreo());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connection.closeConnection();
	}
	
	
	/**
	 * Modificar correo de usuario
	 * @param correo Correo del usuario
	 * @param correo_nuevo Nuevo correo del usuario
	 * 
	 * Funciona pero puesto que correo es PK, si el usuario tiene una reserva hecha,
	 * al cambiar el correo salta error ya que deja de existir
	 */
	
	public void modificarCorreoUsuario(String correo, String correo_nuevo) {
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("modificarCorreoUsuarioSTM"));
			ps.setString(1, correo_nuevo);
			ps.setString(2, correo);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connection.closeConnection();
	}
	
	/**
	 * Modificar nombre de usuario
	 * @param correo Correo del usuario
	 * @param nombre Nombre del usuario
	 */
	
	public void modificarNombreUsuario(String correo, String nombre) {
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("modificarNombreUsuarioSTM"));
			ps.setString(1, nombre);
			ps.setString(2, correo);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connection.closeConnection();
	}
	
	/**
	 * Modificar apellidos de usuario
	 * @param correo Correo del usuario
	 * @param apellidos Apellidos del usuario
	 */
	
	public void modificarApellidosUsuario(String correo, String apellidos) {
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("modificarApellidosUsuarioSTM"));
			ps.setString(1, apellidos);
			ps.setString(2, correo);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connection.closeConnection();
	}
	
	/**
	 * Modificar fecha nacimiento de usuario
	 * @param correo Correo del usuario
	 * @param fechaNacimiento Fecha de nacimiento del usuario
	 */
	
	public void modificarFechaNacimientoUsuario(String correo, String fechaNacimiento) {
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("modificarFechaNacimientoUsuarioSTM"));
			ps.setString(1, fechaNacimiento);
			ps.setString(2, correo);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connection.closeConnection();
	}
	
	/**
	 * Modificar fecha inscripcion de usuario
	 * @param correo Correo del usuario
	 * @param fechaInscripcion Fecha de inscripcion del usuario
	 */
	
	public void modificarFechaInscripcionUsuario(String correo, String fechaInscripcion) {
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("modificarFechaInscripcionUsuarioSTM"));
			ps.setString(1, fechaInscripcion);
			ps.setString(2, correo);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connection.closeConnection();
	}
	
	/**
	 * Eliminar usuario, solo se necesita su correo para eliminarlo
	 * @param correo Correo del usuario
	 */
	
	public void eliminarUsuario(String correo) {
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(prop.getProperty("eliminarUsuarioSTM"));
			ps.setString(1, correo);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connection.closeConnection();
	}

	/**
	 * Obtener usuario por su correo
	 * @param correo Correo del usuario
	 * @return usuario Usuario buscado
	 */
	
	public UsuarioDTO queryByEmail(String correo){
		UsuarioDTO usuario = null;
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(prop.getProperty("obtenerUsuariobyEmailSTM")+String.format("'%s'", correo));
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String fechaNacimiento = rs.getString("fecha_Nacimiento");
				String fechaInscripcion = rs.getString("fecha_Inscripcion");
				
				usuario = new UsuarioDTO();
				usuario.setCorreo(correo);
				usuario.setNombre(nombre);
				usuario.setApellidos(apellidos);
				usuario.setFechaNacimiento(fechaNacimiento);
				usuario.setFechaInscripcion(fechaInscripcion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		
		connection.closeConnection();
		return usuario;
	}
	
	/**
	 * Obtener listado de todos los usuarios
	 * @return List<usuarioDTO> Lista de usuarios
	 */
	
	public List<UsuarioDTO> obtenerUsuarios(){
		List<UsuarioDTO> usuarios = new ArrayList<>();
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(prop.getProperty("obtenerUsuariosSTM"));
			while (rs.next()) {
				String correo = rs.getString("correo");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String fechaNacimiento = rs.getString("fecha_Nacimiento");
				String fechaInscripcion = rs.getString("fecha_Inscripcion");
				
				UsuarioDTO usuarioToPush = new UsuarioDTO();
				usuarioToPush.setCorreo(correo);
				usuarioToPush.setNombre(nombre);
				usuarioToPush.setApellidos(apellidos);
				usuarioToPush.setFechaNacimiento(fechaNacimiento);
				usuarioToPush.setFechaInscripcion(fechaInscripcion);
				usuarios.add(usuarioToPush);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		
		connection.closeConnection();
		return usuarios;
	}
	
	/**
	 * Obtiene la fecha de inscripcion de un usuario para el calculo de la antiguedad
	 * @param correo Correo del usuario
	 * @return fechaInscripcion Fecha de inscripcion del usuario
	 */
	
	public String calcularAntiguedad(String correo) {
		DBConnection connection = new DBConnection();
		con = connection.getConnection();
		
		String fechaInscripcion = null;
		try {		
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(prop.getProperty("calcularAntiguedadSTM")+String.format("'%s'", correo));
			while (rs.next()) {
				fechaInscripcion = rs.getString("fecha_Inscripcion");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		connection.closeConnection();
		return fechaInscripcion;
	}
}