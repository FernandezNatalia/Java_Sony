package logica;
import java.sql.SQLException;
import java.util.Date;

import datos.*;
import entidades.*;

public class UsuarioLogico {

	private UsuarioDatos userDat;
	public UsuarioLogico()
	{
		userDat = new UsuarioDatos();
	}

	public Usuario Logear(Integer dni, String psw) throws SQLException
	{
		return userDat.Logear(dni, psw);
	}
	public void Registro(Usuario us ) throws Exception {
		try{int errn = 0;
		if(ExisteUsuario(us.getDni())) {
			errn = 1;
			throw new Exception("Ya existe un usuario con este dni");
		}
		else {
			
			userDat.add(us);
		}
		}
		catch(Exception ex) {
			 ex.printStackTrace();
		}
		
		
	}
	public boolean ExisteUsuario(int dni) throws SQLException {
		
		Usuario us = userDat.getOne(dni);
		
		if (us.getDni()>0) {
			return true;
		}
		else {
			return false;
		}
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
		else if(us.getTipousuario() == Usuario.paciente) {
			return "/menuPaciente.jsp";
			//request.getRequestDispatcher("WEB-INF/menuPaciente.html").forward(request, response);
		}
		else if (us.getTipousuario() == Usuario.admin) {
			
			return "WEB-INF/menuAdmin.html";
		}
		
		return ""; //Pagina de error
	}
	

}
