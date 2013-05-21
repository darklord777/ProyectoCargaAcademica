package sv.ues.fia.cargaacademicaeisi;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class InsertarActividadAcademicaActivity extends Activity implements OnItemSelectedListener {

	private ControlDB helper;
	private EditText idActAcad;
	private Spinner spnIdModalAA;
	private EditText nombreActAcad;
	private EditText cargoActAcad;	
	private List<String> idModalAA;
	private ArrayAdapter<String> adapterModalAA;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_actividad_academica);
		helper = new ControlDB(this);
		idActAcad = (EditText) findViewById(R.id.idActAcademica);
		spnIdModalAA = (Spinner) findViewById(R.id.spin_Select_ModalAA);
		nombreActAcad = (EditText) findViewById(R.id.NombreActAcad);
		cargoActAcad = (EditText) findViewById(R.id.CargoActAcad);
		
		helper.abrir();
		idModalAA = helper.getAll_IdModAA();
		helper.cerrar();
		adapterModalAA = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, idModalAA);
		adapterModalAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spnIdModalAA.setAdapter(adapterModalAA);
		spnIdModalAA.setOnItemSelectedListener(this);
	}
	
	public void insertarActAcademica(View v) {
		String regInsertados;
		Actividad_Academica ActAcademica = new Actividad_Academica();
		ActAcademica.setIdactacad(idActAcad.getText().toString());
		ActAcademica.setIdmodalidad(spnIdModalAA.getSelectedItem().toString());
		ActAcademica.setNom_act_acad(nombreActAcad.getText().toString());
		ActAcademica.setCargo(cargoActAcad.getText().toString());
		helper.abrir();
		regInsertados = helper.insertar(ActAcademica);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
	}
	
	public void limpiarActAcad(View v) {
		idActAcad.setText("");
		nombreActAcad.setText("");
		cargoActAcad.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_actividad_academica, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
