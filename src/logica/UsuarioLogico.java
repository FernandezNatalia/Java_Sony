package logica;
import datos.*;
import entidades.*;

public class UsuarioLogico {
	
	private UsuarioDatos userDat;
	public UsuarioLogico()
	{
		userDat = new UsuarioDatos();
	}

	public Usuario Logear(String user, String psw)
	{
		return userDat.Logear(user, psw);
	}
	
	
}
