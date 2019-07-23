package entidades;

import java.util.Date;

public class Turno {
	

	private int idturno;
	private Date fechahora;
	private String estado;
	private Usuario paciente;
	private Usuario especialista;
	private String observacion;
	private Consultorio consultorio;
	private Plan plan;

	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public int getIdturno() {
		return idturno;
	}
	public Consultorio getConsultorio() {
		return consultorio;
	}
	public void setConsultorio(Consultorio consultorio) {
		this.consultorio = consultorio;
	}
	public void setIdturno(int idturno) {
		this.idturno = idturno;
	}
	public Date getFechahora() {
		return fechahora;
	}
	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
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

	
	
	
}