package logica;

import java.text.Collator;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
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
	public static int numeroDeDiasMes(String mes) {
		 
	    int numeroDias = -1;
	    switch (mes.toLowerCase().trim()) {
	        case "enero":
	        case "marzo":
	        case "mayo":
	        case "julio":
	        case "agosto":
	        case "octubre":
	        case "diciembre":
	            numeroDias = 31;
	            break;
	        case "abril":
	        case "junio":
	        case "septiembre":
	        case "noviembre":
	            numeroDias = 30;
	            break;
	        case "febrero":	 
	        	 Date anioActual=new Date();
	                if(esBisiesto(1900 + anioActual.getYear())){
	                    numeroDias=29;
	                }else{
	                    numeroDias=28;
	                }
	                break;
	 
	    }
	 
	    return numeroDias;
	}
	public static int numeroDeDiasMes(int mes, int anioActual) {		 
		int numeroDias=-1;
		 
        switch(mes){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                numeroDias=31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                numeroDias=30;
                break;
            case 2:
 
                //Date anioActual=new Date();
                if(esBisiesto(1900 + anioActual)) {//.getYear())){
                    numeroDias=29;
                }else{
                    numeroDias=28;
                }
                break;
 
        }
	    return numeroDias;
	}
	public static boolean esBisiesto(int anio) {
		 
	    GregorianCalendar calendar = new GregorianCalendar();
	    boolean esBisiesto = false;
	    if (calendar.isLeapYear(anio)) {
	        esBisiesto = true;
	    }
	    return esBisiesto;
	 
	}
	public static int diasDiferencia(String DIA) {
		int numeroDias = -1;
		
		//Elimino el acento de las palabras miercoles y sabado
		String DIAsinAcento = Normalizer.normalize(DIA, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
         
	    switch (DIAsinAcento.toLowerCase().trim()) {
	        case "lunes":
	        	numeroDias = 0;
	            break;
	        case "martes":
	        	numeroDias = 1;
	            break;
	        case "miercoles":
	        	numeroDias = 2;
	            break;
	        case "jueves":
	        	numeroDias = 3;
	            break;
	        case "viernes":
	        	numeroDias = 4;
	            break;
	        case "sabado":
	        	numeroDias = 5;
	            break;
	        case "domingo":
	        	numeroDias = 6;
	            break;
	    }
	    return numeroDias;
	}	
	public static int primerDia(int MESActual, int ANIOActual) throws ParseException {
		//Me devuelve la cantidad de dias que hay de diferencia con el lunes. Para saber desde donde
		//empiezan las tarjetas
		
        String dateInString = "01/"+MESActual+"/"+ANIOActual;

        Date date = Conversion.formatter1ddmmyy.parse(dateInString);
   		
   		SimpleDateFormat formatoDIA = new SimpleDateFormat("EEEEE",  new Locale("ES", "ES"));			
   		String DIA = formatoDIA.format(date);
   		
   		return diasDiferencia(DIA);
	}
	public static boolean EsMenorAFechaActual(String d1) throws ParseException
    {
		java.util.Date fecha = new Date();
   		String d2 = Conversion.formatter1ddmmyy.format(fecha);

   		Date date2 = Conversion.formatter1ddmmyy.parse(d2);
        Date date1 = Conversion.formatter1ddmmyy.parse(d1);
            
            if(date1.equals(date2)){
                return false; //SON IGUALES
            }
            
            if(date1.after(date2)){
                return false; //ES MAYOR A LA FECHA ACTUAL
            }
            
            if(date1.before(date2)){
                return true; //ES MENOR A LA FECHA ACTUAL
            }
        return false;
    }
	public static boolean EsMayorAFechaActual(String d1) throws ParseException
    {
		java.util.Date fecha = new Date();
   		String d2 = Conversion.formatter1ddmmyy.format(fecha);

   		Date date2 = Conversion.formatter1ddmmyy.parse(d2);
        Date date1 = Conversion.formatter1ddmmyy.parse(d1);
            
            if(date1.equals(date2)){
                return false; //SON IGUALES
            }
            
            if(date1.after(date2)){
                return true; //ES MAYOR A LA FECHA ACTUAL
            }
            
            if(date1.before(date2)){
                return false; //ES MENOR A LA FECHA ACTUAL
            }
        return false;
    }
	
}
