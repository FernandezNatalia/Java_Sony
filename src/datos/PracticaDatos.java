package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Practica;
import entidades.Turno;

public class PracticaDatos extends Conexion{

	public Practica getOne(int idPractica) throws SQLException {
		Practica p = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String cadena = "SELECT * FROM practicas WHERE cod_practica = ?";
		
	    try {
	    	getConnection();
	    	pst = miCon.prepareStatement(cadena);
	    	pst.setInt(1,idPractica);
	        rs = pst.executeQuery();
	        
	        if(rs.next())
	        {
	        	p = new Practica();
	        	p.setId(rs.getInt("cod_practica"));
	        	p.setDesc(rs.getString("descripcion"));    
	        	p.setValor(rs.getDouble("costo"));
	        }
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    finally {
	    	if(rs!=null) rs.close();
			if(pst!=null) pst.close();
			if(miCon!=null)closeConnection();
	    }

		return p;
	}
	public ArrayList<Practica> getPracticasDeTurno(int idTurno) throws SQLException{
		ArrayList<Practica> practicas = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String cadena = "SELECT tp.cod_practica, descripcion, costo FROM turnos_practicas tp INNER JOIN "
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
	        	pr.setValor(rs.getDouble("costo"));
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
	
	public ArrayList<Practica> getPracticasDeEspecialista(int dni) throws SQLException{
		ArrayList<Practica> practicas = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String cadena = "SELECT p.cod_practica, p.descripcion, p.costo from usuarios u " + 
				"inner join especialidades_practicas ep on ep.cod_especialidad = u.cod_especialidad " + 
				"inner join practicas p on p.cod_practica = ep.cod_practica where u.dni = ?";
		
	    try {
	    	getConnection();
	    	pst = miCon.prepareStatement(cadena);
	    	pst.setInt(1,dni);
	        rs = pst.executeQuery();
	        practicas = new ArrayList<Practica>();
	        
	        while(rs.next())
	        {
	        	Practica pr = new Practica();
	        	pr.setId(rs.getInt("cod_practica"));
	        	pr.setDesc(rs.getString("descripcion"));   
	        	pr.setValor(rs.getDouble("costo"));
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
	
	public double getValorPractica(int idPractica,int idPlan) {
		
		String consulta = "SELECT descuento FROM practicas_planes where id_plan = ? and cod_practica=?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		double valor = 0;
		
	    try {	    	
	    	getConnection();
	    	pst = miCon.prepareStatement(consulta);
	    	pst.setInt(1,idPlan);
	    	pst.setInt(2, idPractica);
	    	
	    	rs = pst.executeQuery();
	        
	        if(rs.next())
	        {
	            valor = rs.getDouble("descuento");	                        
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
	    return valor;
	}
	
	
	public void agregarPracticaTurno(Practica practica, Turno turno) throws SQLException {		
		PreparedStatement st = null;
		String consulta = "INSERT INTO turnos_practicas (id_turno, cod_practica) VALUES (?,?)";
		try {
			getConnection();
			st = miCon.prepareStatement(consulta);
			st.setInt(1, turno.getIdturno());
			st.setInt(2, practica.getId());
			
			st.executeUpdate();
			
		}
		finally {		
			if(st!= null) st.close();
			if(miCon!=null)closeConnection();
		}		
	}
	
	public void eliminarPracticaTurno(Practica practica, Turno turno) throws SQLException {		
		PreparedStatement st = null;
		String consulta = "DELETE FROM turnos_practicas WHERE (id_turno = ?) and (cod_practica = ?)";
		try {
			getConnection();
			st = miCon.prepareStatement(consulta);
			st.setInt(1, turno.getIdturno());
			st.setInt(2, practica.getId());
			
			st.executeUpdate();
			
		}
		finally {		
			if(st!= null) st.close();
			if(miCon!=null)closeConnection();
		}		
	}
}
