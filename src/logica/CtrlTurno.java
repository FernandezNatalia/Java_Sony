package logica;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public ArrayList<Turno> getTurnosPendientesPaciente(Usuario paciente) throws SQLException{		
		
		return turnoData.getTurnosPendientesPaciente(paciente);
	}
	
	public boolean AgregarNuevoTurno(String strFechaHora,String strConsultorio,int dniEspecialista) {
		
		if(ValidacionNegocio.ValidarFecha(strFechaHora) && ValidacionNegocio.ValidarInteger(strConsultorio)) {
			
			java.util.Date fecha = ValidacionNegocio.ConvertirStringAFechaHora(strFechaHora);
			int idConsultorio = Conversion.ConvertirStringAInteger(strConsultorio);			
			
			try {
				turnoData.AgregarNuevoTurno(fecha,idConsultorio,dniEspecialista);
				return true;
				
			} catch (SQLException e) {	
				return false;
			}
		}		
		return false;			
	}
		
	public boolean FinalizarTurno(String observacion, int idTurno) {//,String duracion) {
		
		try {
			Turno t = this.getOne(idTurno);
			t.setObservacion(observacion);
			//t.setDuracion(duracion);
			
			turnoData.UpdateTurno(t);			
			
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean EliminarTurno(int id) {

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

}
