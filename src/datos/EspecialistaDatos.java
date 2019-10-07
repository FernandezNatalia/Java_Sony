package datos;
import entidades.Especialista;
import entidades.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	
}
