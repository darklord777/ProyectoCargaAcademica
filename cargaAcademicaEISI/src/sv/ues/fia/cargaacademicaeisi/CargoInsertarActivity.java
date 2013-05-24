package sv.ues.fia.cargaacademicaeisi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CargoInsertarActivity extends Activity {

	ControlDB helper;
	EditText IdCargo;
	EditText NomCargo;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cargo_insertar);
		helper = new ControlDB(this);
		IdCargo = (EditText) findViewById(R.id.editText1IdCArgo);
		NomCargo = (EditText) findViewById(R.id.editTextNomCArgO);
	}
	public void insertarCargo(View v) {
		String regInsertados;
		CARGO cargo = new CARGO();
		cargo.setIdCargo(IdCargo.getText().toString());
		cargo.setNomCargo(NomCargo.getText().toString());
		helper.abrir();
		regInsertados = helper.insertar(cargo);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
	}
	public void limpiarCargo(View v) {
		IdCargo.setText("");
		NomCargo.setText("");
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cargo_insertar, menu);
		return true;
	}

}
