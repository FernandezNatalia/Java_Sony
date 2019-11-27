package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversion {
	public static SimpleDateFormat formatoddmmyy = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat formatoddmmyyhhss = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	public static SimpleDateFormat formatter1ddmmyy = new SimpleDateFormat("dd/MM/yyyy");
	
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
}
