package logica;

import java.sql.SQLException;

import datos.EspecialistaDatos;
import entidades.Especialista;

public class CtrlEspecialista {

	private EspecialistaDatos espDat;		
	public CtrlEspecialista()
	{
		espDat = new EspecialistaDatos();
	}

	public String getEspecialidad(int dni){
		return espDat.getEspecialidad(dni);
	}
	
	public Especialista getOneEspecialista(int dni) throws SQLException {
		return espDat.getEspecialista(dni);
	}
}
