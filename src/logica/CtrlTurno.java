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
	
	
	public ArrayList<Turno> getProximosDeEspecialista(Usuario especialista,Date fecha,int estado) throws SQLException{		
		
		return turnoData.getProximosDeEspecialista(especialista,fecha,estado);
	}

	public ArrayList<Turno> getTurnosPendientesPaciente(Usuario paciente) throws SQLException{		
		
		return turnoData.getTurnosPendientesPaciente(paciente);
	}
	
	public boolean AgregarNuevoTurno(String strFechaHora,String strConsultorio,int dniEspecialista) {
		
		if(ValidacionNegocio.ValidarFecha(strFechaHora) && ValidacionNegocio.ValidarInteger(strConsultorio)) {
			
			java.util.Date fecha = ValidacionNegocio.ConvertirStringAFechaHora(strFechaHora);
			int idConsultorio = Integer.parseInt(strConsultorio);			
			
			try {
				turnoData.AgregarNuevoTurno(fecha,idConsultorio,dniEspecialista);
				return true;
				
			} catch (SQLException e) {	
				return false;
			}
		}		
		return false;			
	}
		
	
	
	
	
	
}
