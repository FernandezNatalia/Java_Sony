package logica;
import datos.EspecialistaDatos;
import entidades.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class EspecialistaLogico {

	private EspecialistaDatos espDat;		
	public EspecialistaLogico()
	{
		espDat = new EspecialistaDatos();
	}

	public String getEspecialidad(int dni){
		return espDat.getEspecialidad(dni);
	}
	
	
}