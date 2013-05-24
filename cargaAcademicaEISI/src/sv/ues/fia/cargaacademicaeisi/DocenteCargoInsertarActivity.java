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

public class DocenteCargoInsertarActivity extends Activity implements OnItemSelectedListener {

	private ControlDB helper;
	private Spinner spnNvoIdPeriodo;
	private Spinner spnNvoIdCargo;
	private Spinner spnNvoIdDocente;
	private EditText edtNvoIdDocCargo;
	private List<String> idPeriodos;
    private List<String> idDocentes;
    private List<String> idCargos;
	private ArrayAdapter<String> adtIdDocente;
	private ArrayAdapter<String> adtIdPeriodo;
	private ArrayAdapter<String> adtIdCargo;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_docente_cargo_insertar);
		
		helper = new ControlDB(this);
		edtNvoIdDocCargo = (EditText) findViewById(R.id.edtNvoDocCargo);
		spnNvoIdDocente = (Spinner) findViewById(R.id.spnIdDoc);
		spnNvoIdPeriodo= (Spinner) findViewById(R.id.spnIdPer);
		spnNvoIdCargo= (Spinner) findViewById(R.id.spnIdCar);
		
		helper.abrir();
		idCargos = helper.getAllIdCargos();
		idPeriodos = helper.getAllIdPeriodos();
		idDocentes = helper.getAllIdDocentes();
		helper.cerrar();
		
		adtIdDocente = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, idDocentes);
		adtIdPeriodo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, idPeriodos);
		adtIdCargo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, idCargos);

		adtIdDocente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adtIdPeriodo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adtIdCargo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnNvoIdPeriodo.setAdapter(adtIdPeriodo);
		spnNvoIdCargo.setAdapter(adtIdCargo);
		spnNvoIdDocente.setAdapter(adtIdDocente);
        
		spnNvoIdPeriodo.setOnItemSelectedListener(this);
		spnNvoIdCargo.setOnItemSelectedListener(this);
		spnNvoIdDocente.setOnItemSelectedListener(this);


	}
	public void insertarDocenteCargo(View v) {
		
		DOCENTE_CARGO cargoAsignado = new DOCENTE_CARGO();
		cargoAsignado.setIdDocCar(edtNvoIdDocCargo.getText().toString());
		cargoAsignado.setIdPeriodo(spnNvoIdPeriodo.getSelectedItem().toString());
		cargoAsignado.setIdDocente(spnNvoIdDocente.getSelectedItem().toString());
		cargoAsignado.setIdCargo(spnNvoIdCargo.getSelectedItem().toString());
		
		helper.abrir();
		String estado = helper.insertar(cargoAsignado);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.docente_cargo_insertar, menu);
		return true;
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
	

}