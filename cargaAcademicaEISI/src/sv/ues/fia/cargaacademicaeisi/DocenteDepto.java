package sv.ues.fia.cargaacademicaeisi;

public class DocenteDepto {
	public String IdDocente;
	public String IdDepartamento;
	
	public DocenteDepto() {
		// TODO Auto-generated constructor stub
	}

	public DocenteDepto(String idDocente, String idDepartamento) {
		this.IdDocente = idDocente;
		this.IdDepartamento = idDepartamento;
	}

	public String getIdDocente() {
		return IdDocente;
	}

	public void setIdDocente(String idDocente) {
		this.IdDocente = idDocente;
	}

	public String getIdDepartamento() {
		return IdDepartamento;
	}

	public void setIdDepartamento(String idDepartamento) {
		this.IdDepartamento = idDepartamento;
	}
	
}
