package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Conversion {
	
///////////////////////////// - TODOS LOS FORMATOS DE FECHA - ////////////////////////////////////////////////////////
	public static SimpleDateFormat formatoddmmyy = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat formatoddmmyyhhss = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	public static SimpleDateFormat formatter1ddmmyy = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES", "ES"));
	public static SimpleDateFormat formatterNroAMes =  new SimpleDateFormat("MMMM",  new Locale("ES", "ES"));
	public static SimpleDateFormat formatohhmm = new SimpleDateFormat("HH:mm");
	public static SimpleDateFormat formatoEEEEdeMMMMyyyyHHmm = new SimpleDateFormat("EEEEE dd 'de' MMMMM yyyy HH:mm",  new Locale("ES", "ES"));
	public static SimpleDateFormat formatohhmmFechaLarga = new SimpleDateFormat("HH:mm EEEEE dd 'de' MMMMM yyyy",  new Locale("ES", "ES"));
	public static SimpleDateFormat formatoEEEEdeMMMMyyyy = new SimpleDateFormat("EEEEE dd 'de' MMMMM yyyy");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static Integer ConvertirStringAInteger(String entero) {
		return Integer.parseInt(entero);
	}
	public static Date ConvertirStringAFecha(String fecha) {			
		try {
			Date fechaFormateada;
			fechaFormateada = formatoddmmyy.parse(fecha);
			return fechaFormateada;
			
			} catch (ParseException e) {
			
			return null;			
			}		
	}	
	public static java.sql.Date ConvertirStringAFechaSql(String fecha) {		
		
		Date parsed = ConvertirStringAFecha(fecha);
		java.sql.Date sql = new java.sql.Date(parsed.getTime());
		    
		return sql;		
	}
	public static Date ConvertirStringAFechaHora(String fechahora) {			
		try {
			Date fechaFormateada;
			fechaFormateada = formatoddmmyyhhss.parse(fechahora);
			return fechaFormateada;
			
			} catch (ParseException e) {
			
			return null;			
			}		
	}
	public static String NombreDeMes(int nroMes) throws ParseException{
		
		String strfecha = "01/"+nroMes+"/2019";
		Date date = formatter1ddmmyy.parse(strfecha);					
	   	String MESActual = formatterNroAMes.format(date);

		return MESActual;
	}
	
	public static int getNroDelMes(java.sql.Date fechaVista) {
		SimpleDateFormat formatoMES = new SimpleDateFormat("MM",  new Locale("ES", "ES"));			
	   	int MESActual = Integer.parseInt(formatoMES.format(fechaVista));
		return MESActual;
	}
	
	public static int getNroAnio(java.sql.Date fechaVista) {
		SimpleDateFormat formatoANIO = new SimpleDateFormat("yyyy",  new Locale("ES", "ES"));			
   		int ANIOActual = Integer.parseInt(formatoANIO.format(fechaVista));
   		return ANIOActual;
	}
	public static String convertirMinutosConCero(int minutos) {
		String mins = null;
		if(minutos < 10) {
			  mins = "0"+minutos;
		  }else {
			  mins = String.valueOf(minutos);
			  }
		return mins;
	}
}
