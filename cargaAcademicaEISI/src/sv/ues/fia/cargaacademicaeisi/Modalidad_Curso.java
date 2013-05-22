package sv.ues.fia.cargaacademicaeisi;

public class Modalidad_Curso {
	public String idmodalidadCurso;
	public String nom_modalidad;
	public int descuento_horas;

	public Modalidad_Curso(){
	}
	
	public Modalidad_Curso(String idmodalidadCurso, String nom_modalidad, int descuento_horas){
		this.idmodalidadCurso = idmodalidadCurso;
		this.nom_modalidad = nom_modalidad;
		this.descuento_horas = descuento_horas;
	}

	public String getIdmodalidadCurso() {
		return idmodalidadCurso;
	}

	public void setIdmodalidadCurso(String idmodalidadCurso) {
		this.idmodalidadCurso = idmodalidadCurso;
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