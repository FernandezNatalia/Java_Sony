package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionNegocio {
	
	public static boolean ValidarDatosDeRegistro(String dni,String nombre,String apellido,String email,String fechaNacimiento,String password) {
		
		if(ValidarEmail(email) 
		   && ValidarFecha(fechaNacimiento)
		   && ValidarInteger(dni) 
		   && ValidarString(nombre) 
		   && ValidarString(apellido)) 
		return true;
		
		return false;
	}
	
	public static boolean ValidarString(String cadena) {
		
		if(cadena=="" || cadena==null) return false;
		
		for (int i = 0; i < cadena.length(); i++)
		{
			char caracter = cadena.toUpperCase().charAt(i);
			int valorASCII = (int)caracter;
			
			if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90))
				return false; //Se ha encontrado un caracter que no es letra
		}
		return true;
	}	
	public static boolean ValidarInteger(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}	
	public static boolean ValidarFecha(String fecha) {
		try {
			Conversion.formatoddmmyy.parse(fecha);
			return true;
		} catch (ParseException pe){
			return false;
		}
	}	
	public static boolean ValidarEmail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        Matcher mather = pattern.matcher(email);
        if (mather.find() == true) 
        	return true;
        else
        	return false;       
	}

}
