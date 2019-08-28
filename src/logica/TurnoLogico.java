package logica;
import datos.TurnoDatos;
import entidades.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class TurnoLogico {

	private TurnoDatos turDat;		
	public TurnoLogico()
	{
		turDat = new TurnoDatos();
	}
	
	public ArrayList<Turno> getAll() throws SQLException{				
		return turDat.getAll();
	}
	public ArrayList<Turno> getProximosDeEspecialista(Usuario especialista){
		try {
			ArrayList<Turno> turnos;
			turnos = turDat.getProximosDeEspecialista(especialista);
			return turnos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
	public ArrayList<Turno> getPacienteTurnos(int dni){
		return turDat.getPacienteTurnos(dni);
	}
}
