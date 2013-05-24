package sv.ues.fia.cargaacademicaeisi;

public class CARGO {
	private String IdCargo;
	private String NomCargo;

	public CARGO() {
	}

	public CARGO(String IdCargo, String NomCargo) {
		this.IdCargo = IdCargo;
		this.NomCargo = NomCargo;
	}

	public String getIdCargo() {
		return IdCargo;
	}

	public void setIdCargo(String IdCargo) {
		this.IdCargo = IdCargo;
	}

	public String getNomCargo() {
		return NomCargo;
	}

	public void setNomCargo(String NomCargo) {
		this.NomCargo = NomCargo;
	}
}