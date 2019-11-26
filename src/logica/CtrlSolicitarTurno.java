package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.EspecialidadDatos;
import entidades.Especialidad;

public class CtrlSolicitarTurno {

	private EspecialidadDatos espDat;		
	public CtrlSolicitarTurno()
	{
		espDat = new EspecialidadDatos();
	}
	
	public ArrayList<Especialidad> getAllEspecialidades() throws SQLException{
		return espDat.getAll();
	}
	
	
}
