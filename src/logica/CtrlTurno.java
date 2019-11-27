package logica;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.ConsultorioDatos;
import datos.TurnoDatos;
import datos.UsuarioDatos;
import entidades.*;

public class CtrlTurno {

	private TurnoDatos turnoData;
	public CtrlTurno()
	{
		turnoData = new TurnoDatos();
	}
	
	public Turno getOne(int idTurno) throws SQLException {
		return turnoData.getOne(idTurno);
	}
	
	public ArrayList<Turno> getProximosDeEspecialista(Usuario especialista,Date fecha,int estado) throws SQLException{		
		
		return turnoData.getProximosDeEspecialista(especialista,fecha,estado);
	}

	public ArrayList<Turno> getProximosDeEspecialista(Usuario especialista,Date fecha) throws SQLException{		
		
		return turnoData.getProximosDeEspecialista(especialista,fecha);
	}
	public ArrayList<Turno> getTurnosPendientesPaciente(Usuario paciente) throws SQLException{		
		
		return turnoData.getTurnosPendientesPaciente(paciente);
	}
	
	public boolean AgregarNuevoTurno(String strFechaHora,String strConsultorio,int dniEspecialista) {
		
		if(ValidacionNegocio.ValidarFecha(strFechaHora) && ValidacionNegocio.ValidarInteger(strConsultorio)) {
			
			java.util.Date fecha = Conversion.ConvertirStringAFechaHora(strFechaHora);
			int idConsultorio = Conversion.ConvertirStringAInteger(strConsultorio);	
			
			if(this.VerificarDisponibilidadConsultorio(idConsultorio, fecha)) {
			try {
				turnoData.AgregarNuevoTurno(fecha,idConsultorio,dniEspecialista);
				return true;
				
			} catch (SQLException e) {	
				return false;
			}
		}else {		
			return false;
		}
		}
		return false;			
	}
		
	public boolean FinalizarTurno(String observacion, int idTurno) {//,String duracion) {
		
		try {
			Turno t = this.getOne(idTurno);
			t.setObservacion(observacion);
			t.setEstado(Turno.terminado);
			//t.setDuracion(duracion);
			
			turnoData.UpdateTurno(t);			
			
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}
	public boolean EliminarTurno(int idTurno){		
		try {
			
			turnoData.EliminarTurno(idTurno);			
			
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}
	public boolean CancelarTurno(int id) {

		try {
			Turno t = this.getOne(id);
			t.setEstado(Turno.cancelado);
			
			//Actualizo la BD poniendole al turno el estado "cancelado"
			//Despues agrego un nuevo turno para ese mismo horario
			
			turnoData.UpdateTurno(t);			
			turnoData.AgregarNuevoTurno(t.getFechahora(),t.getConsultorio().getIdconsultorio(),t.getEspecialista().getDni());	
			
		} catch (SQLException e) {
			return false;
		}	
		return true;
	}
	public ArrayList<Turno> getAtencionesPaciente(Usuario pac, Usuario esp) throws SQLException{
		return turnoData.getTurnosPaciente(pac,esp);
	}
	public boolean VerificarDisponibilidadConsultorio(int idConsultorio, java.util.Date fechayhora) {
		//Por una cuestion de que no se sabe las practicas que tendra un turno al crearse, todos los turnos
		//tienen una duracion de media hora
		ConsultorioDatos cd = new ConsultorioDatos();
		try {
			return cd.ComprobarDisponibilidadConsultorio(idConsultorio, fechayhora);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
