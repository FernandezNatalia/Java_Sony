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
		PacienteDatos ud = new PacienteDatos();
		EspecialistaDatos es = new EspecialistaDatos();
		ConsultorioDatos cd = new ConsultorioDatos();
		PracticaDatos p = new PracticaDatos(); //Podria no estar todavia aca	
		
		
		turno.setIdturno(rs.getInt("id_turno"));			
		turno.setFechahora(rs.getTimestamp("fecha_hora"));
		turno.setDuracion(rs.getInt("duracion"));			
		turno.setEstado(rs.getInt("estado"));				
		turno.setEspecialista(es.getEspecialista(rs.getInt("dni_especialista")));
		turno.setPaciente(ud.getPaciente(rs.getInt("dni_paciente")));
		turno.setObservacion(rs.getString("observacion"));		
		turno.setConsultorio(cd.getOne(rs.getInt("id_consultorio")));	
		turno.setPracticas(p.getPracticasDeTurno(turno.getIdturno()));

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
	public ArrayList<Turno> getTurnosDisponiblesAFecha(Usuario especialista,java.sql.Date fechaDate) throws SQLException{
		ArrayList<Turno> turnos = new ArrayList<Turno>();		
		PreparedStatement pst = null;
		ResultSet rs = null;
		String cadena="";
		
		cadena = "SELECT * FROM turnos WHERE dni_especialista = ? AND date(fecha_hora) = ? AND estado = 1 ORDER BY fecha_hora";
		
		getConnection();
		
		try {			
			pst = miCon.prepareStatement(cadena);
			
			pst.setInt(1,especialista.getDni());
			pst.setDate(2,fechaDate); 
			
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
	public ArrayList<Turno> getProximosDeEspecialista(Usuario especialista,java.sql.Date fechaDate) throws SQLException{
		
		ArrayList<Turno> turnos = new ArrayList<Turno>();		
		PreparedStatement pst = null;
		ResultSet rs = null;
		String cadena="";
		
		cadena = "SELECT * FROM turnos WHERE dni_especialista = ? AND date(fecha_hora) >= ? ORDER BY fecha_hora;";
		
		getConnection();
		
		try {			
			pst = miCon.prepareStatement(cadena);
			
			pst.setInt(1,especialista.getDni());
			pst.setDate(2,fechaDate); 
			
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
	public ArrayList<Turno> getProximosDeEspecialista(Usuario especialista,java.sql.Date fechaDate,int estado) throws SQLException{
		
		ArrayList<Turno> turnos = new ArrayList<Turno>();		
		PreparedStatement pst = null;
		ResultSet rs = null;
		//Si el turno fue reservado tiene que mostrarse aunque sea viejo, porque sino no se lo puede 
		//finalizar ya que el especialista nunca lo veria
		//Ademas le da la opcion de no tener que poner observaciones o similar inmediatamente antes
		String cadena="";
		if(estado==Turno.disponible) {
		cadena = "SELECT * FROM turnos WHERE turnos.estado = ? AND turnos.dni_especialista = ? AND date(fecha_hora) between current_date() and ? ORDER BY fecha_hora";
		}
		if(estado==Turno.reservado) {
			cadena = "SELECT * FROM turnos WHERE turnos.estado = ? AND turnos.dni_especialista = ? AND (fecha_hora <= ? OR fecha_hora<=(CURDATE() + INTERVAL 1 DAY)) ORDER BY fecha_hora";
		}
		getConnection();
		
		try {			
			pst = miCon.prepareStatement(cadena);
			
			pst.setInt(1,estado);
			pst.setInt(2,especialista.getDni());
			pst.setDate(3,fechaDate); 
			
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
	public ArrayList<Turno> getTurnosPendientesPaciente(Usuario paciente) throws SQLException{
		
		ArrayList<Turno> turnos = new ArrayList<Turno>();		
		PreparedStatement pst = null;
		ResultSet rs = null;
		String cadena = "SELECT * FROM turnos WHERE turnos.estado = 2 AND turnos.dni_paciente = ? AND fecha_hora >= current_date() ORDER BY fecha_hora";
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
	public void UpdateTurno(Turno t) throws SQLException {
		
		String consulta = "UPDATE turnos SET fecha_hora = ?, estado = ?, dni_especialista = ?, id_consultorio = ?, dni_paciente = ?, observacion = ? WHERE (id_turno = ?)";
		PreparedStatement pst = null;
		
		try {
			getConnection();
			pst = miCon.prepareStatement(consulta);
			
			pst.setTimestamp(1, new java.sql.Timestamp(t.getFechahora().getTime()));	
			pst.setInt(2, t.getEstado());
			pst.setInt(3,t.getEspecialista().getDni());
			pst.setInt(4, t.getConsultorio().getIdconsultorio());
			pst.setInt(5, t.getPaciente().getDni());		
			pst.setString(6, t.getObservacion());
			pst.setInt(7, t.getIdturno());			

			pst.executeUpdate();
			
		}finally {
			
			if(pst!=null)pst.close();
			if(miCon!= null) miCon.close();
			
		}	
	}		
	public void AgregarNuevoTurno(java.util.Date fechaHora,int idConsultorio,int dniEspecialista) throws SQLException {

		String cadena = "INSERT INTO turnos (fecha_hora,estado,dni_especialista,id_consultorio) VALUES (?,1,?,?)";
		PreparedStatement pst = null;
		
		try {
			getConnection();
			pst = miCon.prepareStatement(cadena);

			pst.setTimestamp(1, new java.sql.Timestamp(fechaHora.getTime()));			
			pst.setInt(2,dniEspecialista);
			pst.setInt(3, idConsultorio);

			pst.executeUpdate();
			
		}finally {
			
			if(pst!=null)pst.close();
			if(miCon!= null) miCon.close();
			
		}			
	}		
	public void EliminarTurno(int id) throws SQLException {
		String cadena = "DELETE FROM turnos WHERE (id_turno = ?)";
		PreparedStatement pst = null;		
		try {
			getConnection();
			pst = miCon.prepareStatement(cadena);			
			pst.setInt(1,id);
			pst.executeUpdate();			
		}finally {			
			if(pst!=null)pst.close();
			if(miCon!= null) miCon.close();			
		}	
		
	}
public ArrayList<Turno> getTurnosPaciente(Usuario paciente, Usuario especialista) throws SQLException{
		
		ArrayList<Turno> turnos = new ArrayList<Turno>();		
		PreparedStatement pst = null;
		ResultSet rs = null;
		String cadena = "SELECT * FROM turnos WHERE turnos.estado = 3 AND turnos.dni_paciente = ? AND turnos.dni_especialista=? AND fecha_hora > current_date() ORDER BY fecha_hora";
		getConnection();
		
		try {			
			pst = miCon.prepareStatement(cadena);
			pst.setInt(1,paciente.getDni());
			pst.setInt(2,especialista.getDni());
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
	
}

