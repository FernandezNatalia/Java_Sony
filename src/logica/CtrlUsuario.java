package logica;

import datos.UsuarioDatos;
import entidades.Usuario;

public class CtrlUsuario {

	private UsuarioDatos userDat;
	public CtrlUsuario()
	{
		userDat = new UsuarioDatos();
	}

	public boolean Autenticacion(int dni,String contraseña) {
		return userDat.Autenticacion(dni, contraseña);
	}
	public Usuario getOne(int dni) {
		return userDat.getOne(dni);
	}

	public String getPathMenuUsuario(Usuario us)
	{		
		if(us.getTipousuario() == Usuario.especialista) {
			
			return"WEB-INF/menuEspecialista.html";				
		}
		if(us.getTipousuario() == Usuario.paciente) {
			return "WEB-INF/menuPaciente.jsp";
		}
		if (us.getTipousuario() == Usuario.admin) {
			
			return "WEB-INF/menuAdmin.html";
		}
		
		return "err.html"; //Pagina de error CON PARAMETROS
	}	
	public boolean RegistrarNuevoUsuario(Usuario us) {
		
		Usuario usExiste = null;
		usExiste = userDat.getOne(us.getDni());		
		
		 if(usExiste == null) {
			userDat.RegistrarNuevoUsuario(us);
			return true;
		}
		 
		 return false;
	}

}
