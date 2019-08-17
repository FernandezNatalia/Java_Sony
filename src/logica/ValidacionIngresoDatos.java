package logica;

public class ValidacionIngresoDatos {
	public static boolean EsNro(String nrostr) {
		try {
		int intnro = Integer.parseInt(nrostr);
		return true;
		}
		catch(Exception pe) {
			return false;
		}
		
	}
	public static boolean EsMail(String mail) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	    return mail.matches(regex);
		
	}
	public static boolean EsFecha(String fechastr) {
		return false;
	}
}
