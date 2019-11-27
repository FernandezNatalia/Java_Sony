package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.EspecialidadDatos;
import entidades.Especialidad;
import entidades.Turno;

public class CtrlSolicitarTurno {

	private EspecialidadDatos espDat;		
	public CtrlSolicitarTurno()
	{
		espDat = new EspecialidadDatos();
	}
	
	public ArrayList<Especialidad> getAllEspecialidades() throws SQLException{
		return espDat.getAll();
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
