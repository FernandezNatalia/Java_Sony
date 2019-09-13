package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Practica;

public class PracticaDatos extends Conexion{

	public ArrayList<Practica> getPracticasDeTurno(int idTurno) throws SQLException{
		ArrayList<Practica> practicas = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String cadena = "SELECT tp.cod_practica, descripcion FROM turnos_practicas tp INNER JOIN "
				+ "practicas p ON tp.cod_practica = p.cod_practica WHERE tp.id_turno = ?";
		
	    try {
	    	getConnection();
	    	pst = miCon.prepareStatement(cadena);
	    	pst.setInt(1,idTurno);
	        rs = pst.executeQuery();
	        practicas = new ArrayList<Practica>();
	        
	        while(rs.next())
	        {
	        	Practica pr = new Practica();
	        	pr.setId(rs.getInt("cod_practica"));
	        	pr.setDesc(rs.getString("descripcion"));    
	        	practicas.add(pr);
	        }
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    finally {
	    	if(rs!=null) rs.close();
			if(pst!=null) pst.close();
			if(miCon!=null)closeConnection();
	    }

		return practicas;
	}
	
	
	/*public Practica getOne(int id) throws SQLException {
		
		Practica pr = null;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String cadena = "SELECT * FROM practicas WHERE cod_practica = ?";
		
	    try {
	    	getConnection();
	    	pst = miCon.prepareStatement(cadena);
	    	pst.setInt(1,id);
	        rs = pst.executeQuery();
	        
	        if(rs.next())
	        {
	        	pr = new Practica();
	        	pr.setId(rs.getInt("cod_practica"));
	        	pr.setDesc(rs.getString("descripcion"));        
	        }
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    finally {
	    	if(rs!=null) rs.close();
			if(pst!=null) pst.close();
			if(miCon!=null)closeConnection();
	    }
	    return pr;
	}
	*/
	
	
	
	
	
	
	
	
/*	public double getValorPlan(int idpr, int idpl) {
		double valor = 0;
		//Aca hay que poner una consulta que traiga el precio de mayor fecha menor a la actual
		//correspondiente a la practica y plan enviados por parametro
		
		return valor;
	}
	
	public Practica getOne(int id) throws SQLException {
		Practica pr = new Practica();
	    getConnection();
	    try {
	    	
	        Statement stmt = miCon.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Practicas WHERE codPractica = " + id);
	        if(rs.next())
	        {
	        	pr.setDesc(rs.getString("Descripcion"));
	        	pr.setId(rs.getInt("codPractica"));
	            
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return pr;
	}
	public double getValorPlan(int idpr, int idpl) {
		double valor = 0;
		//Aca hay que poner una consulta que traiga el precio de mayor fecha menor a la actual
		//correspondiente a la practica y plan enviados por parametro
		
		return valor;
	}

	*/
	
}
