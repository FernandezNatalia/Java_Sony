package logica;
import datos.TurnoDatos;
import entidades.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TurnoLogico {

	private TurnoDatos turDat;		
	public TurnoLogico()
	{
		turDat = new TurnoDatos();
	}
	
	/*public ArrayList<Turno> getAll() throws SQLException{				
		return turDat.getAll();
	}
	*/
	public ArrayList<Turno> getProximosDeEspecialista(Usuario especialista,Date fecha) throws SQLException{		
		
		return turDat.getProximosDeEspecialista(especialista,fecha);
	}
	
	/*public ArrayList<Turno> getPacienteTurnos(int dni){
		return turDat.getPacienteTurnos(dni);
	}*/
}
