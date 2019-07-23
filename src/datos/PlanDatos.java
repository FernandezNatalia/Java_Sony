package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entidades.Plan;

public class PlanDatos extends Conexion{
	
	public Plan getOne(int id) {
		Plan pl = new Plan();
	    getConnection();
	    
	    try {
	    	
	        Statement stmt = miCon.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Planes WHERE idPlan = " + id);
	        if(rs.next())
	        {
	        	pl.setId(rs.getInt("idPlan"));
	        	pl.setId_os(rs.getInt("CUIT_os"));
	        	pl.setNomplan(rs.getString("Nombre"));
	            
	            
	        }
	        
	        rs.close();
	        stmt.close();
	        closeConnection();
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return pl;
	}
}