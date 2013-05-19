package sv.ues.fia.cargaacademicaeisi;

public class Modalidad_Act_Acad {
	public String idmodalidad;
	public String nom_modalidad;
	public int descuento_horas;
	
	public Modalidad_Act_Acad(){
	}

	public Modalidad_Act_Acad(String idmodalidad, String nom_modalidad,int descuento_horas) {		
		this.idmodalidad = idmodalidad;
		this.nom_modalidad = nom_modalidad;
		this.descuento_horas = descuento_horas;
	}

	public String getIdmodalidad() {
		return idmodalidad;
	}

	public void setIdmodalidad(String idmodalidad) {
		this.idmodalidad = idmodalidad;
	}

	public String getNom_modalidad() {
		return nom_modalidad;
	}

	public void setNom_modalidad(String nom_modalidad) {
		this.nom_modalidad = nom_modalidad;
	}

	public int getDescuento_horas() {
		return descuento_horas;
	}

	public void setDescuento_horas(int descuento_horas) {
		this.descuento_horas = descuento_horas;
	}
}
