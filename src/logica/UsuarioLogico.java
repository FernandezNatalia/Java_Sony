package logica;
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
	
	
}
