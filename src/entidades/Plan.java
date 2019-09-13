package entidades;

public class Plan {
	
	private int id;
	private ObraSocial obs;	
	private String nomplan;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ObraSocial getObs() {
		return obs;
	}
	public void setObs(ObraSocial obs) {
		this.obs = obs;
	}
	public String getNomplan() {
		return nomplan;
	}
	public void setNomplan(String nomplan) {
		this.nomplan = nomplan;
	}

}
