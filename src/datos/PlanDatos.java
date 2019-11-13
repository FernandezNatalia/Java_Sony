package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.ObraSocial;
import entidades.Plan;

public class PlanDatos extends Conexion{

	public ArrayList<Plan> getAll() throws SQLException{
		
		ObraSocialDatos ob = null;		
		ArrayList<Plan> planes = new ArrayList<Plan>();
		
		Statement stm = null;
		ResultSet rs = null;
		String cadena = "SELECT * FROM planes";
		
	    try {
	    	getConnection();
			stm = miCon.createStatement();
	        rs = stm.executeQuery(cadena);
	        	        
	        while(rs.next())
	        {
	        	Plan pl = new Plan();
	        	ob = new ObraSocialDatos();
	        	
	        	pl.setId(rs.getInt("id_plan"));
	        	pl.setObs(ob.getOne(rs.getString("cuit_os")));
	        	pl.setNomplan(rs.getString("nombre")); 
	        	
	        	planes.add(pl);
	        }
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    finally {
	    	if(rs!=null) rs.close();
			if(stm!=null) stm.close();
			if(miCon!=null)closeConnection();
	    }
	    return planes;
	    
	}
}