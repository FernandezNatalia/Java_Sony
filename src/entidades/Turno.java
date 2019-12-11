package entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import logica.Conversion;

public class Turno {
	
	public static final int disponible = 1;
	public static final int reservado = 2;
	public static final int terminado = 3;
	public static final int cancelado= 4;

	private int idturno;
	private Date fechahora;
	private int duracion;
	private int estado;
	private Usuario paciente;
	private Usuario especialista;	
	private String observacion;
	private Consultorio consultorio;
	private ArrayList<Practica> Practicas;
	
	public int getIdturno() {
		return idturno;
	}
	public void setIdturno(int idturno) {
		this.idturno = idturno;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Usuario getPaciente() {
		return paciente;
	}
	public void setPaciente(Usuario paciente) {
		this.paciente = paciente;
	}
	public Usuario getEspecialista() {
		return especialista;
	}
	public void setEspecialista(Usuario especialista) {
		this.especialista = especialista;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Consultorio getConsultorio() {
		return consultorio;
	}
	public void setConsultorio(Consultorio consultorio) {
		this.consultorio = consultorio;
	}
	public ArrayList<Practica> getPracticas() {
		return Practicas;
	}
	public void setPracticas(ArrayList<Practica> practicas) {
		Practicas = practicas;
	}
	public Date getFechahora() {
		return fechahora;
	}
	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechahora == null) ? 0 : fechahora.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Turno other = (Turno) obj;
		if (fechahora == null) {
			if (other.fechahora != null) {
				return false;
			}
		} else {
			
			SimpleDateFormat formatohhmm = new SimpleDateFormat("dd/MM/yyyy",  new Locale("ES", "ES"));
			
			String d1 = formatohhmm.format(other.fechahora);
	   		String d2 = formatohhmm.format(fechahora);

	   		try {
	   			Date date1 = Conversion.formatter1ddmmyy.parse(d1);
				Date date2 = Conversion.formatter1ddmmyy.parse(d2);
				
				if (!date2.equals(date1)) {
					return false;
				}
			} catch (ParseException e) {
				return false;
			}
		}
		return true;
	}
}