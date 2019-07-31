package datos;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entidades.Consultorio;

public class ConsultorioDatos extends Conexion{
	public Consultorio getOne(int id) throws SQLException {
		
		Consultorio con = new Consultorio();
	    getConnection();
	    try {
	    	
	        Statement stmt = miCon.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Consultorio WHERE id = " + id);
	        if(rs.next())
	        {
	        	con.setIdconsultorio(rs.getInt("id"));
	        	con.setDireccion(rs.getString("Direccion"));
	        	con.setDesc(rs.getString("Desc"));
	            
	        }
	        
	        rs.close();
	        stmt.close();
	        closeConnection();
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return con;
	}
}
