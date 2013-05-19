package sv.ues.fia.cargaacademicaeisi;

public class Actividad_Academica {
	public String idactacad;
	public String idmodalidad;
	public String nom_act_acad;
	public String cargo;
	
	public Actividad_Academica() {		
		// TODO Auto-generated constructor stub
	}

	public Actividad_Academica(String idactacad, String idmodalidad,String nom_act_acad, String cargo) {		
		this.idactacad = idactacad;
		this.idmodalidad = idmodalidad;
		this.nom_act_acad = nom_act_acad;
		this.cargo = cargo;
	}

	public String getIdactacad() {
		return idactacad;
	}

	public void setIdactacad(String idactacad) {
		this.idactacad = idactacad;
	}

	public String getIdmodalidad() {
		return idmodalidad;
	}

	public void setIdmodalidad(String idmodalidad) {
		this.idmodalidad = idmodalidad;
	}

	public String getNom_act_acad() {
		return nom_act_acad;
	}

	public void setNom_act_acad(String nom_act_acad) {
		this.nom_act_acad = nom_act_acad;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
