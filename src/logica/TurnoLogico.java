package logica;
import datos.TurnoDatos;
import entidades.Turno;
import java.util.ArrayList;

public class TurnoLogico {

	private TurnoDatos turDat;		
	public TurnoLogico()
	{
		turDat = new TurnoDatos();
	}
	
	public ArrayList<Turno> getAll(){				
		return turDat.getAll();
	}
}
