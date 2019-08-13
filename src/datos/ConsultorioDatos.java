package datos;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Consultorio;
import entidades.Turno;

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
public ArrayList<Consultorio> getAll() throws SQLException{
		
		ArrayList<Consultorio> consultorios = new ArrayList<Consultorio>();
		
		getConnection();
		
		try {
			Statement stm = miCon.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM Consultorio"); 
			while(rs.next())
			{
				Consultorio con = new Consultorio();
				
				
				con.setDesc(rs.getString("Desc"));
				con.setDireccion(rs.getString("Direccion"));
				con.setIdconsultorio(rs.getInt("id"));
	            
	            consultorios.add(con);
			}
			
			rs.close();
			stm.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {closeConnection();}
		
		return consultorios;
	}
}
