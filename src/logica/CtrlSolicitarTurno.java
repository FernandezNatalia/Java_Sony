package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.EspecialidadDatos;
import datos.EspecialistaDatos;
import datos.TurnoDatos;
import entidades.Especialidad;
import entidades.Especialista;
import entidades.Turno;

public class CtrlSolicitarTurno {

	private EspecialidadDatos espDat;		
	private EspecialistaDatos esplistaDat;
	public CtrlSolicitarTurno()
	{
		espDat = new EspecialidadDatos();
		esplistaDat = new EspecialistaDatos();
	}
	
	public ArrayList<Especialidad> getAllEspecialidades() throws SQLException{
		return espDat.getAll();
	}
	public Especialidad getOneEspecialidad(int codigo){
		try {
			return espDat.getOne(codigo);
		} catch (SQLException e) {
			return null;
		}
	}
	public boolean reservarTurno(Turno t){
		TurnoDatos tDat = new TurnoDatos();
		try {
			tDat.UpdateTurno(t);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	public ArrayList<Especialista> getAllEspecialistas(Especialidad eActual) throws SQLException {
		return esplistaDat.getAllPorEspecialidad(eActual.getCodEspecialidad());
		
	}
	public static boolean contieneFechaTurno(ArrayList<Turno> turnos, Turno tu) {
		
		for(Turno t : turnos) {
			if(t.equals(tu)) return true;
		}
		
		return false;
	}
	
	public static boolean existenTurnoDisponible(ArrayList<Turno> turnos,Turno tu) {
		
		for(Turno t : turnos) {
			if(t.equals(tu) && t.getEstado()==Turno.disponible) return true;
		}	
		return false;
	}
	
	
}
