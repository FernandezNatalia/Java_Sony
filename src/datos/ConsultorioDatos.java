package datos;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import entidades.Consultorio;
import entidades.Turno;

public class ConsultorioDatos extends Conexion{

	private Consultorio readConsultorio(ResultSet rs) throws SQLException {
		
		Consultorio con = new Consultorio();				
		con.setIdconsultorio(rs.getInt("id"));
		con.setDesc(rs.getString("descripcion"));	
		return con;
		
	}
	public Consultorio getOne(int id) throws SQLException {
		
		Consultorio con = null;		
		PreparedStatement pst = null;		
		ResultSet rs = null;
		String cadena = "SELECT * FROM consultorios WHERE id = ?";
	    
	    try {
	    	getConnection();
	    	
	    	pst = miCon.prepareStatement(cadena);
	    	pst.setInt(1,id);
	        rs = pst.executeQuery();
	        
	        if(rs.next())
	        {
	        	con = readConsultorio(rs);       		            
	        }	        
        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    finally {
	        if(rs!=null) rs.close();
	        if(pst!=null) pst.close();
	        if(miCon!=null) closeConnection();
	    }
	    return con;
	}
	
	
	public ArrayList<Consultorio> getAll() throws SQLException{
		
		ArrayList<Consultorio> consultorios = new ArrayList<Consultorio>();;		
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stm = miCon.createStatement();
			rs = stm.executeQuery("SELECT * FROM consultorios"); 			

			while(rs.next())
			{
	            consultorios.add(readConsultorio(rs));
			}
			
			rs.close();
			stm.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			if(rs!=null) rs.close();
			if(stm!=null) stm.close();
			if(miCon!=null)closeConnection();
		}
		
		return consultorios;
	}	

	/*public boolean ComprobarDisponibilidadConsultorio(int idConsultorio, java.util.Date fechayhora) throws Exception {
		boolean disponible = true;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			
			String consulta = "SELECT * FROM turnos WHERE fecha_hora<=? AND fecha_hora>DATE_SUB(?, Interval 30 minute) AND id_consultorio=?;";
			stm = miCon.prepareStatement(consulta);
			stm.setTimestamp(1, new java.sql.Timestamp(fechayhora.getTime()));
			stm.setTimestamp(2, new java.sql.Timestamp(fechayhora.getTime()));
			stm.setInt(3, idConsultorio);
			rs = stm.executeQuery(); 			

			if(rs.next())
			{
	            disponible=false;
				throw new Exception();
				
			}
			
			rs.close();
			stm.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			if(rs!=null) rs.close();
			if(stm!=null) stm.close();
			if(miCon!=null)closeConnection();
		}
		return disponible;
	}*/
}
