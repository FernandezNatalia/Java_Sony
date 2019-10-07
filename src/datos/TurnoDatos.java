package datos;

import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import entidades.*;

public class TurnoDatos extends Conexion {
	
	private Turno readTurno(ResultSet rs) throws SQLException {
		
		Turno turno = new Turno();
		UsuarioDatos ud = new UsuarioDatos();
		PlanDatos pd = new PlanDatos();;
		ConsultorioDatos cd = new ConsultorioDatos();
		//PracticaDatos p = new PracticaDatos(); //Podria no estar todavia aca
		
		turno.setIdturno(rs.getInt("id_turno"));			
		//turno.setFecha(rs.getDate("fecha"));
		//turno.setHora(rs.getTime("hora"));
		
		turno.setFechahora(rs.getTimestamp("fecha_hora"));
		
		
		turno.setDuracion(rs.getInt("duracion"));			
		turno.setEstado(rs.getInt("estado"));		
		
		turno.setEspecialista(ud.getOne(rs.getInt("dni_especialista")));
		turno.setPaciente(ud.getOne(rs.getInt("dni_paciente")));
		turno.setObservacion(rs.getString("observacion"));		
		turno.setConsultorio(cd.getOne(rs.getInt("id_consultorio")));			
		turno.setPlan(pd.getOne(rs.getInt("id_plan")));
		//turno.setPracticas(p.getPracticasDeTurno(turno.getIdturno()));

	return turno;
}

	public Turno getOne(int id) throws SQLException{				
		
		Turno turno = null;	
		PreparedStatement pst = null;
		ResultSet rs = null;
		String cadena = "SELECT * FROM turnos WHERE id_turno = ?";
	
		try {
			getConnection();
	        pst = miCon.prepareStatement(cadena);
	        pst.setInt(1,id);
	        rs = pst.executeQuery();
	        
	        if(rs.next())
	        {	
	        	turno = readTurno(rs);	            
	        }
	        
    }catch (Exception e) {e.printStackTrace();}
	 finally {
		 if(rs != null) rs.close();
		 if(pst != null) pst.close();
	     if(miCon != null) closeConnection();
	 }
		return turno;
	}

	
	public ArrayList<Turno> getProximosDeEspecialista(Usuario especialista,java.sql.Date fechaDate) throws SQLException{
		
		ArrayList<Turno> turnos = new ArrayList<Turno>();		
		PreparedStatement pst = null;
		ResultSet rs = null;
		String cadena = "SELECT * FROM turnos WHERE turnos.estado = 2 AND turnos.dni_especialista = ? AND date(fecha_hora)=? ORDER BY fecha_hora";
		getConnection();
		
		try {			
			pst = miCon.prepareStatement(cadena);
			pst.setInt(1,especialista.getDni());
				/*SimpleDateFormat formatoddmmyy = new SimpleDateFormat("yyyy-MM-dd");
				String fecha = formatoddmmyy.format(fechaDate);
			pst.setString(2,fecha);*/
			pst.setDate(2,fechaDate); 
			rs = pst.executeQuery(); //Modificar para traer los turnos del dia actual.
			
			while(rs.next())
			{
	            turnos.add(readTurno(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			if(rs != null) rs.close();
			if(pst != null) pst.close();
			if(miCon != null) closeConnection();
		}
		
		return turnos;
	}
	
	public ArrayList<Turno> getTurnosPendientesPaciente(Usuario paciente) throws SQLException{
		
		ArrayList<Turno> turnos = new ArrayList<Turno>();		
		PreparedStatement pst = null;
		ResultSet rs = null;
		String cadena = "SELECT * FROM turnos WHERE turnos.estado = 2 AND turnos.dni_paciente = ? ORDER BY hora";
		getConnection();
		
		try {			
			pst = miCon.prepareStatement(cadena);
			pst.setInt(1,paciente.getDni());
			rs = pst.executeQuery();
			
			while(rs.next())
			{
	            turnos.add(readTurno(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			if(rs != null) rs.close();
			if(pst != null) pst.close();
			if(miCon != null) closeConnection();
		}
		
		return turnos;
	}
	
/*	
  		//==============================================
		//VER SI FUNCIONA, POR LA BASE DE DATOS
		
	public void CancelarTurno(Turno tur){
	    try {	   
	    	getConnection();
	        Statement stmt = miCon.createStatement();
	        stmt.executeUpdate("call Turnos.CancelarTurno(" + Integer.toString(tur.getIdturno()) + ");");
        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
		
		//==============================================
	public ArrayList<Turno> getPacienteTurnos(int dni){
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String consulta = "SELECT * FROM turnos WHERE dni_paciente=?";
		
		try {
			getConnection();
			pst = miCon.prepareStatement(consulta);
			pst.setInt(1,dni);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				
	            turnos.add(readTurno(rs));
			}
		
			} catch (SQLException ex) {
	        ex.printStackTrace();
			}
	    	finally {
	    		try {
	    			if(rs!=null) rs.close();
	    			if(pst!=null) pst.close();
	    			if(miCon!=null)closeConnection();
	    			
	    			} catch (SQLException e) 
	    			 { e.printStackTrace();}	        
	    }		
		return turnos;
	}
	*/
	
	
	
	
	
	
	
	
	
/*	
	public ArrayList<Turno> getAll() throws SQLException{
		
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		
		Statement stm = null;
		ResultSet rs = null;
		getConnection();
		
		try {
			stm = miCon.createStatement();
			rs = stm.executeQuery("SELECT * FROM turnos"); //Modificar para traer los turnos del dia actual.
			
			while(rs.next())
			{
				turnos.add(readTurno(rs));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			if(rs != null) rs.close();
			if(stm != null) stm.close();
		    if(miCon != null) closeConnection();
		}
		
		return turnos;
	}
	
*/
	
	
	/*	
	public void add(Turno turno) throws SQLException {
	
	PreparedStatement pst = null;
	String cadena = "insert into java_turnos.turnos(fecha,hora,estado,id_consultorio,dni_especialista) VALUES(?,?,?,?)";
	getConnection();
	try {		
		pst = miCon.prepareStatement(cadena);
        
        pst.setDate(1,turno.getFecha());
        pst.setTime(2,turno.getHora());
        pst.setInt(3,turno.getEstado());
        pst.setInt(4,turno.getConsultorio().getIdconsultorio());
        pst.setInt(5,turno.getEspecialista().getDni());
       
        pst.executeUpdate();
        
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally {
		if(pst != null) pst.close();
		if(miCon != null) closeConnection();
	}
}

*/	


	
}
