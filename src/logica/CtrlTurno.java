package logica;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
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
	
	public Turno getOne(int idTurno) {
		try {
			return turnoData.getOne(idTurno);
		} catch (SQLException e) {
			return null;
		}
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
	public ArrayList<Turno> getTurnosDisponiblesAFecha(Usuario us,Date sqlFechaDispo) throws SQLException{
		return turnoData.getTurnosDisponiblesAFecha(us,sqlFechaDispo);
	}
	public boolean AgregarNuevoTurno(String fecha,String strConsultorio,int dniEspecialista) throws ParseException, SQLException {
		
		if(ValidacionNegocio.ValidarFecha(fecha) && ValidacionNegocio.ValidarInteger(strConsultorio)) {
			
			//java.util.Date fecha = Conversion.ConvertirStringAFechaHora(strFechaHora);
			
			
			java.util.Date fechaFormateada = Conversion.formatoddmmyyhhss.parse(fecha);
			java.util.Date parsed = fechaFormateada;
			java.sql.Date sql = new java.sql.Date(parsed.getTime());
				
			int idConsultorio = Conversion.ConvertirStringAInteger(strConsultorio);	
			ArrayList<Turno> turnos = turnoData.getAllDispOcup(idConsultorio,sql);

			if(VerificarDisponibilidadConsultorio(fechaFormateada,turnos)) {
			try {
				turnoData.AgregarNuevoTurno(parsed,idConsultorio,dniEspecialista);
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
			//PERO SI ESTO YA ESTABA COMO STORED PROCEDURE EN LA BASE DE DATOS
			
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
	/*public boolean VerificarDisponibilidadConsultorio(int idConsultorio, java.util.Date fechayhora) {
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
	}*/
	
	public boolean VerificarDisponibilidadConsultorio(java.util.Date fechayhora, ArrayList<Turno> turnos) {
		try {	
				for(Turno t : turnos) {
					int MINturno = t.getFechahora().getMinutes();
					int MINfecha = fechayhora.getMinutes();
					
					int Hturno = t.getFechahora().getHours();
					int Hfecha = fechayhora.getHours();
			
				//LAS HORAS SON IGUALES
					if(Hturno == Hfecha) {		
						if(MINturno > MINfecha) {
							//LA DIFERENCIA ES MAYOR A 30 MINUTOS
							if((MINturno - MINfecha) >= 30) {
								//SE PUEDE RESERVAR
								
							}else {
								//NO SE PUEDE RESERVAR
								return false;
							}
							//LA DIFERENCIA ES MENOR A 30 MINUTOS
						}else if(MINturno < MINfecha) {
							
							if((MINfecha - MINturno) >= 30) {
								//SE PUEDE RESERVAR
								
							}else {
								//NO SE PUEDE RESERVAR
								return false;
							}
							
						}else {
							//MINUTOS IGUALES 
							//NO SE PUEDE RESERVAR
							return false;
						}
					}else {
						//LAS HORAS SON DIFERENTES
						//ME FIJO SI ESTAN CERCA 
						if((Hturno - 1) == Hfecha) {
							
							if((MINturno < 30) && (MINfecha > 30)) {
								if((MINfecha- MINturno) >= 30){
									//NO SE PUEDE
									return false;
								}else {
									//SI SE PUEDE
								}					
							}else {
								//MINUTOS LEJANOS
								//SI SE PUEDE
							}
						}else					
						if((Hturno + 1) == Hfecha) {
							
							if((MINturno > 30) && (MINfecha < 30)) {
								if((MINturno - MINfecha) >= 30){
									//NO SE PUEDE
									return false;
								}else {
									//SI SE PUEDE
								}						
							}else {
								//MINUTOS LEJANOS
								//SI SE PUEDE					
							}
						}else {
							//LAS HORAS ESTAN LEJANAS
							//SI SE PUEDE
						}
					}
				}

			}catch(Exception e) {
				return false;
			}
		return true;
		}

}
