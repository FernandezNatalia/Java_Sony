package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.PracticaDatos;
import datos.TurnoDatos;
import entidades.Plan;
import entidades.Practica;
import entidades.Turno;

public class CtrlDetalleTurno {

	private TurnoDatos turDat;
	private PracticaDatos pracDat;
	
	public CtrlDetalleTurno()
	{
		turDat = new TurnoDatos();
		pracDat = new PracticaDatos();
	}	
	
	public Turno getOneTurno(int idTurno) throws SQLException {
		return turDat.getOne(idTurno);
	}
	
	public double getValorPractica(Practica pr ,Plan p) {
		
		double porcentaje = pracDat.getValorPractica(pr.getId(),p.getId());
		double valor = pr.getValor() - (porcentaje*100);
		return valor;
	}
	
	
	
	/*public ArrayList<Practica> getPracticasDeEspecialista(int dni){
		return pracDat.getPracticasDeEspecialista(dni);
	}
	
	public ArrayList<Practica> getPracticasDeTurno(Turno t) throws SQLException{
		return pracDat.getPracticasDeTurno(t.getIdturno());
	}
	*/
	
}
