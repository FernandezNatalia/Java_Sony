package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.ObraSocial;

public class ObraSocialDatos extends Conexion {

	public ObraSocial getOne(String cuit) throws SQLException {
		ObraSocial ob = null;
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		String cadena = "SELECT * FROM obras_sociales WHERE cuit = ?";
		
		try {
			getConnection();
			
			 pst = miCon.prepareStatement(cadena);
		     pst.setString(1,cuit);
		     rs = pst.executeQuery(cadena);
			
		     if(rs.next())
		        {
		        	ob = new ObraSocial();
		        	ob.setCuit(rs.getString("cuit"));
		        	ob.setRazonSocial(rs.getString("razon_social"));
		        }	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
	    	if(rs!=null) rs.close();
			if(pst!=null) pst.close();
			if(miCon!=null)closeConnection();
	    }
		return ob;
	}
	
}
