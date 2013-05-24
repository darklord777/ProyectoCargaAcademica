package sv.ues.fia.cargaacademicaeisi;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class DocenteCargoConsultarActivity extends Activity implements OnItemSelectedListener{

	private ControlDB helper;
	private Spinner spnIDdoccarg;
	private EditText edtIDdocente;
	private EditText edtIDperiodo;
	private EditText edtIDcargo;
	private List<String> idDocCargos;
	private ArrayAdapter<String> adt;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_docente_cargo_consultar);
		helper = new ControlDB(this);
		spnIDdoccarg = (Spinner) findViewById(R.id.spnQueIdDocar);
		edtIDdocente = (EditText) findViewById(R.id.edtQueIdDocen);
		edtIDperiodo = (EditText) findViewById(R.id.edtQueIdPer);
		edtIDcargo = (EditText) findViewById(R.id.edtQueIdCarg);
		
		helper.abrir();
		idDocCargos = helper.getAllIdDocCar();
		helper.cerrar();
		adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idDocCargos);
		adt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnIDdoccarg.setAdapter(adt);
		spnIDdoccarg.setOnItemSelectedListener(this);
	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.docente_cargo_consultar, menu);
		return true;
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		String idDocCargo = arg0.getItemAtPosition(arg2).toString();
		DOCENTE_CARGO cargoAsignado = new DOCENTE_CARGO();
		helper.abrir();
		cargoAsignado = helper.consultarDocenteCargo(idDocCargo);
		helper.cerrar();
		edtIDdocente.setText(cargoAsignado.getIdDocente());
		edtIDperiodo.setText(cargoAsignado.getIdPeriodo());
		edtIDcargo.setText(cargoAsignado.getIdCargo());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
