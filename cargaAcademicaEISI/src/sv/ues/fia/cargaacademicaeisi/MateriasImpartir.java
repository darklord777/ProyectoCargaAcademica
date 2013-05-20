package sv.ues.fia.cargaacademicaeisi;

public class MateriasImpartir {
	public String IdDocente;
	public String IdAreaMat;
	
	public MateriasImpartir() {
		// TODO Auto-generated constructor stub
	}

	public MateriasImpartir(String idDocente, String idAreaMat) {
		this.IdDocente = idDocente;
		this.IdAreaMat = idAreaMat;
	}

	public String getIdDocente() {
		return IdDocente;
	}

	public void setIdDocente(String idDocente) {
		this.IdDocente = idDocente;
	}

	public String getIdAreaMat() {
		return IdAreaMat;
	}

	public void setIdAreaMat(String idAreaMat) {
		this.IdAreaMat = idAreaMat;
	}
}
