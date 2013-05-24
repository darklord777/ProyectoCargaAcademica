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

public class DocenteCargoEliminarActivity extends Activity implements OnItemSelectedListener {

	private ControlDB helper;
	private Spinner spnIdDC;
	private EditText edtIdDocnt;
	private EditText edtIdPriod;
	private EditText edtIdCrgo;
	private List<String> idDoccar;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_docente_cargo_eliminar);
		helper = new ControlDB(this);
		spnIdDC = (Spinner) findViewById(R.id.spnIdDOC);
		edtIdDocnt = (EditText) findViewById(R.id.edtIdDOCENT);
		edtIdPriod = (EditText) findViewById(R.id.edtIdPERIOD);
		edtIdCrgo = (EditText) findViewById(R.id.edtIDCARG);
		
		helper.abrir();
		idDoccar = helper.getAllIdDocCar();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idDoccar);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnIdDC.setAdapter(adapter);
		spnIdDC.setOnItemSelectedListener(this);

	}
	public void eliminarDocenteCargo(View v) {
		DOCENTE_CARGO docente_cargo = new DOCENTE_CARGO();
		docente_cargo.setIdDocCar(spnIdDC.getSelectedItem()
				.toString());
		helper.abrir();
		String estado = helper.eliminar(docente_cargo);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.docente_cargo_eliminar, menu);
		return true;
	}
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		String idDoccar = arg0.getItemAtPosition(arg2).toString();
		DOCENTE_CARGO cargoAsignado = new DOCENTE_CARGO();
		helper.abrir();
		cargoAsignado = helper.consultarDocenteCargo(idDoccar);
		helper.cerrar();
		edtIdDocnt.setText(cargoAsignado.getIdDocente());
		edtIdPriod.setText(cargoAsignado.getIdPeriodo());
		edtIdCrgo.setText(cargoAsignado.getIdCargo());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}


}