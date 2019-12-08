package datos;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Especialista;
import entidades.Paciente;
import entidades.Plan;
import entidades.Usuario;
public class EspecialistaDatos extends Conexion{

	public String getEspecialidad(int dni){ 
		
	    String espe= null;	
		PreparedStatement pst = null;
		ResultSet rs = null;
		String consulta = "SELECT e.nombre FROM usuarios u " + 
				"inner join especialidades e on e.cod_especialidad = u.cod_especialidad WHERE dni =?";
		
	    try {	    	
	    	getConnection();
	    	pst = miCon.prepareStatement(consulta);
	    	pst.setInt(1,dni);
	    	rs = pst.executeQuery();
	        
	        if(rs.next())
	        {
	            espe = rs.getString("nombre");	                        
	        }	 
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    finally {
	    	try {
				if(rs!=null) rs.close();
				if(pst!=null) pst.close();
				if(miCon!=null)closeConnection();
			} catch (SQLException e) { e.printStackTrace();}	        
	    }
	    return espe;
	}
	
	public ArrayList<Especialista> getAllPorEspecialidad(int cod_espe) throws SQLException{
		
		ArrayList<Especialista> especialistas = new ArrayList<Especialista>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String cadena = "select u.dni,u.nombre,u.apellido,u.fecha_nacimiento,u.tipo_usuario,u.password,u.email,u.cod_especialidad from usuarios u " + 
				"where u.cod_especialidad=?";		
	    try {
	    	getConnection();
	        pst = miCon.prepareStatement(cadena);
	        pst.setInt(1, cod_espe);
	        rs = pst.executeQuery();
	        
	        while(rs.next())
	        {
	        	Usuario espe = null;
	    		PracticaDatos practicas = new PracticaDatos();       	
	        	UsuarioDatos udat = new UsuarioDatos();
		    	espe = new Especialista();
		    	udat.readUsuario(rs,espe);
	        	
	        	((Especialista)espe).setCodEspecialidad(rs.getInt("cod_especialidad"));
	        	((Especialista)espe).setPracticas(practicas.getPracticasDeEspecialista(espe.getDni()));
	        
	        	especialistas.add(((Especialista)espe));
	        }
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    finally {
	    	if(rs!=null) rs.close();
			if(pst!=null) pst.close();
			if(miCon!=null)closeConnection();
	    }
	    return especialistas;
	}
	
	public Especialista getEspecialista(int dni) throws SQLException {

		
		Usuario espe = null;
		PracticaDatos practicas = new PracticaDatos();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String cadena = "select u.dni,u.nombre,u.apellido,u.fecha_nacimiento,u.tipo_usuario,u.password,u.email,u.cod_especialidad,e.nombre as nomb_espe from usuarios u " + 
				"inner join especialidades e on e.cod_especialidad = u.cod_especialidad where dni = ?";		
	    try {
	    	getConnection();
	        pst = miCon.prepareStatement(cadena);
	        pst.setInt(1, dni);
	        rs = pst.executeQuery();
	        
	        if(rs.next())
	        {
	        	        	
	        	UsuarioDatos udat = new UsuarioDatos();
		    	espe = new Especialista();
		    	udat.readUsuario(rs,espe);
	        	
	        	((Especialista)espe).setCodEspecialidad(rs.getInt("cod_especialidad"));
	        	((Especialista)espe).setEspecialidad(rs.getString("nomb_espe"));
	        	((Especialista)espe).setPracticas(practicas.getPracticasDeEspecialista(espe.getDni()));
	        
	        }
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    finally {
	    	if(rs!=null) rs.close();
			if(pst!=null) pst.close();
			if(miCon!=null)closeConnection();
	    }
	    return ((Especialista)espe);
	}
}
