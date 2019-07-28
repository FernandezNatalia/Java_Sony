package datos;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.*;

public class TurnoDatos extends Conexion {
	
	public void CancelarTurno(Turno tur) {
		
	    getConnection();
	    try {
	    	
	        Statement stmt = miCon.createStatement();
	        ResultSet rs = stmt.executeQuery("call Turnos.CancelarTurno(" + Integer.toString(tur.getIdturno()) + ");");
	        
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	}
	public Turno getOne(int id) {
		Turno turno = new Turno();
		UsuarioDatos ud = new UsuarioDatos();
		PlanDatos pd = new PlanDatos();
		getConnection();
    try {
	    	
	        Statement stmt = miCon.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Turnos WHERE idTurnos = " + id);
	        if(rs.next())
	        {
	        	turno.setEspecialista(ud.getOne(rs.getInt("dni")));
	        	turno.setFechahora(rs.getTimestamp("fecha"));
	        	turno.setIdturno(rs.getInt("idTurnos"));
	            turno.setPaciente(ud.getOne(rs.getInt("dni")));
	            turno.setObservacion(rs.getString("observacion"));
	            turno.setPlan(pd.getOne(rs.getInt("idPlan")));
	            //Falta el consultorio?
	            
	        }
	        
	        rs.close();
	        stmt.close();
	        closeConnection();
	        
    } catch (SQLException ex) { ex.printStackTrace();} 
      catch (Exception e) {e.printStackTrace();}
	  
		return turno;
	}
	public ArrayList<Turno> getAll(){
		
		ArrayList<Turno> turnos = new ArrayList<Turno>();		
		getConnection();
		
		try {
			Statement stm = miCon.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM Turnos"); //Modificar para traer los turnos del dia actual.
			
			while(rs.next())
			{
				Turno tur = new Turno();
				UsuarioDatos ud = new UsuarioDatos();
				PlanDatos pd = new PlanDatos();
				tur.setEspecialista(ud.getOne(rs.getInt("dni")));
	        	tur.setFechahora(rs.getTimestamp("fecha"));
	        	tur.setIdturno(rs.getInt("idTurnos"));
	            tur.setPaciente(ud.getOne(rs.getInt("dni")));
	            tur.setObservacion(rs.getString("observacion"));
	            tur.setPlan(pd.getOne(rs.getInt("idPlan")));
	            
	            turnos.add(tur);
			}
			
			rs.close();
			stm.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {closeConnection();}
		
		return turnos;
	}
}
