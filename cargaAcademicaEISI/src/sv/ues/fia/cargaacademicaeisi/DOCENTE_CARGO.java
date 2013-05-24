package sv.ues.fia.cargaacademicaeisi;

public class DOCENTE_CARGO {
	private String IdDocCar;
	private String IdDocente;
	private String IdPeriodo;
	private String IdCargo;

	public DOCENTE_CARGO() {
	}

	public DOCENTE_CARGO(String IdDocCar, String IdDocente, String IdPeriodo,
			String IdCargo) {
		this.IdDocCar = IdDocCar;
		this.IdDocente = IdDocente;
		this.IdPeriodo = IdPeriodo;
		this.IdCargo = IdCargo;
	}

	public String getIdDocCar() {
		return IdDocCar;
	}

	public void setIdDocCar(String IdDocCar) {
		this.IdDocCar = IdDocCar;
	}

	public String getIdDocente() {
		return IdDocente;
	}

	public void setIdDocente(String IdDocente) {
		this.IdDocente = IdDocente;
	}

	public String getIdPeriodo() {
		return IdPeriodo;
	}

	public void setIdPeriodo(String IdPeriodo) {
		this.IdPeriodo = IdPeriodo;
	}

	public String getIdCargo() {
		return IdCargo;
	}

	public void setIdCargo(String IdCargo) {
		this.IdCargo = IdCargo;
	}
}
