package entidades;

public class Especialista extends Usuario {

	private Especialidad especialidad;
	
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
}
