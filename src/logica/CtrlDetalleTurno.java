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
		//Calculo el valor de la practica restandole el porcentaje que cubre la Obra Social.
		
		double porcentaje = pracDat.getValorPractica(pr.getId(),p.getId());
		double valor = porcentaje*pr.getValor();
		return valor;
	}
	
	public boolean EliminarPracticaDeTurno(int idTurno, int idPractica) {
		try {			
			Practica p = pracDat.getOne(idPractica);
			Turno t = turDat.getOne(idTurno);
			
			//Elimino la practica del turno		
			pracDat.eliminarPracticaTurno(p, t);
			
		} catch (SQLException e) {
			return false;				
		}			
	return true;
	}
	
	public boolean AgregarPractica(int idTurno, int idPractica) {
		
		try {			
			Practica p = pracDat.getOne(idPractica);
			Turno t = turDat.getOne(idTurno);

			//Verifica que la practica no este agregada al turno
			if(t.getPracticas().contains(p)) {
				return false;
			}
		
			//Agrego la nueva practica al turno		
			pracDat.agregarPracticaTurno(p, t);
			
			} catch (SQLException e) {
				return false;				
			}				
		return true;		
	}
}
