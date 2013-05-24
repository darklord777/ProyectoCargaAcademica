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

public class CargoConsultarActivity extends Activity implements OnItemSelectedListener{

	private ControlDB helper;
	private EditText edtNomCargo;
	private Spinner spnListaCargos;
	private List<String> idCargos;
	private ArrayAdapter<String> adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cargo_consultar);
		helper = new ControlDB(this);
		edtNomCargo = (EditText) findViewById(R.id.edtNomCargo);
		spnListaCargos = (Spinner) findViewById(R.id.spnListaCargos);
		
		

		helper.abrir();
		idCargos = helper.getAllIdCargos();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, idCargos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnListaCargos.setAdapter(adapter);
		spnListaCargos.setOnItemSelectedListener(this);
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cargo_consultar, menu);
		return true;
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
		String idCargo = parent.getItemAtPosition(pos).toString();
		helper.abrir();		
		CARGO cargo = helper.consultarCargo(idCargo);
		helper.cerrar();
		if ( cargo == null) {
			Toast.makeText(
					this,
					"Identificador de cargo: " + idCargo
							+ ". No existe.", Toast.LENGTH_LONG).show();
		} else {
			edtNomCargo.setText(cargo.getNomCargo());
			Toast.makeText(this, "Valor de item=" + idCargo, Toast.LENGTH_LONG)
					.show();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

}
