package entidades;

import java.util.ArrayList;
import java.util.Date;

public class Turno {
	
	public static final int disponible = 1;
	public static final int reservado = 2;
	public static final int terminado = 3;

	private int idturno;
	private Date fecha;
	private Date hora;
	private int duracion;
	private int estado;
	private Usuario paciente;
	private Usuario especialista;	
	private String observacion;
	private Consultorio consultorio;
	private Plan plan;
	private ArrayList<Practica> Practicas;
	
	
	public int getIdturno() {
		return idturno;
	}
	public void setIdturno(int idturno) {
		this.idturno = idturno;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
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
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public ArrayList<Practica> getPracticas() {
		return Practicas;
	}
	public void setPracticas(ArrayList<Practica> practicas) {
		Practicas = practicas;
	}
	
	
	
	
	
}