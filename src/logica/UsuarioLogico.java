package logica;
import java.util.Date;

import datos.*;
import entidades.*;

public class UsuarioLogico {
	
	private UsuarioDatos userDat;
	public UsuarioLogico()
	{
		userDat = new UsuarioDatos();
	}

	public Usuario Logear(Integer dni, String psw)
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
	public boolean ExisteUsuario(int dni) {
		
		Usuario us = userDat.getOne(dni);
		
		if (us.getDni()>0) {
			return true;
		}
		else {
			return false;
		}
	}
}
