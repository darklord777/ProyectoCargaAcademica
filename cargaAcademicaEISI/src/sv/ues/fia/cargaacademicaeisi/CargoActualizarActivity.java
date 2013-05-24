package sv.ues.fia.cargaacademicaeisi;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CargoActualizarActivity extends Activity implements OnItemSelectedListener {
	private ControlDB helper;
	private Spinner spnActIdCargo;
	private EditText edtActNomCargo;
	private List<String> idCargos;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cargo_actualizar);
		helper = new ControlDB(this);
		spnActIdCargo = (Spinner) findViewById(R.id.spnQurIdCargo);
		edtActNomCargo = (EditText) findViewById(R.id.editNvoNombre);
		
		helper.abrir();
		idCargos = helper.getAllIdCargos();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, idCargos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnActIdCargo.setAdapter(adapter);
		spnActIdCargo.setOnItemSelectedListener(this);
	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cargo_actualizar, menu);
		return true;
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2 ,long arg3) {
		String idCargo = arg0.getItemAtPosition(arg2).toString();
		helper.abrir();
		CARGO cargo = helper.consultarCargo(idCargo);
		helper.cerrar();
		if (cargo == null) {
			Toast.makeText(
					this,
					"Identificador de cargo: " + idCargo
							+ ". No existe.", Toast.LENGTH_LONG).show();
		} else {
			edtActNomCargo.setText(cargo.getNomCargo());
		}
 }
	
    public void actualizarCargo(View v) {
		CARGO cargo = new CARGO();
		cargo.setIdCargo(spnActIdCargo.getSelectedItem()
				.toString());
		cargo.setNomCargo(edtActNomCargo.getText().toString());
		helper.abrir();
		String estado = helper.actualizar(cargo);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}
    @Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}
	
}
