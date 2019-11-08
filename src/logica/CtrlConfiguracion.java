package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import datos.PacienteDatos;
import datos.PlanDatos;
import datos.UsuarioDatos;
import entidades.Paciente;
import entidades.Plan;
import entidades.Usuario;

public class CtrlConfiguracion {

	private PacienteDatos pacDat;
	private PlanDatos planDat;
	public CtrlConfiguracion()
	{
		pacDat = new PacienteDatos();
		planDat = new PlanDatos();
	}	
	
	public Paciente getPaciente(int dni) throws SQLException {
		return pacDat.getPaciente(dni);
	}
	
	public void eliminarPlan(int dni) throws SQLException {
		pacDat.eliminarPlan(dni);
	}
		
	public void agregarPlan(int dni,int idplan,int nroafiliado) throws SQLException {
		pacDat.agregarPlan(dni,idplan,nroafiliado);
	}
	
	public ArrayList<Plan> getAllPlanes() throws SQLException{
		return planDat.getAll();
	}
	
	public boolean CambioMail(String mail, Usuario us) {
		
		if(ValidacionNegocio.ValidarEmail(mail)) {
			
			UsuarioDatos usDatos = new UsuarioDatos();
			try {				
				usDatos.CambioMail(mail,us.getDni());
				us.setEmail(mail);;
				
			} catch (SQLException e) {
				return false;
			}
			
			return true;
		}else {
			return false;
		}
	}
	
	public boolean CambioClave(String oldpass,String newpass,String repnewpass,Usuario us){
		
		if(oldpass.contentEquals(us.getPassword()) && newpass.contentEquals(repnewpass)) {
			
			UsuarioDatos usDatos = new UsuarioDatos();
			try {				
				usDatos.CambioClave(newpass,us.getDni());
				us.setPassword(newpass);
				
			} catch (SQLException e) {
				return false;
			}
			
			return true;
		}else {
			return false;
		}
	}
	
}
