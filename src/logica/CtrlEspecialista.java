package logica;

import datos.EspecialistaDatos;

public class CtrlEspecialista {

	private EspecialistaDatos espDat;		
	public CtrlEspecialista()
	{
		espDat = new EspecialistaDatos();
	}

	public String getEspecialidad(int dni){
		return espDat.getEspecialidad(dni);
	}
	
	
}
