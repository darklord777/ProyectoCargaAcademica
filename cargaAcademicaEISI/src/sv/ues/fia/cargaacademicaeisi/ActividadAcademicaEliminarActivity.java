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

public class ActividadAcademicaEliminarActivity extends Activity implements OnItemSelectedListener{

	private ControlDB helper;
	private Spinner spnEliminarActA;
	private EditText NombreActA_Elim;
	private List<String> idActA;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actividad_academica_eliminar);
	/*	
		helper = new ControlDB(this);
		spnEliminarActA = (Spinner) findViewById(R.id.spin_Eliminar_ActAcademica);
		NombreActA_Elim = (EditText) findViewById(R.id.SelNombreActAcad);
		helper.abrir();
		idActA = helper.getAll_IdActA();
		helper.cerrar();		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, idActA);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnEliminarActA.setAdapter(adapter);
		spnEliminarActA.setOnItemSelectedListener(this);		*/
	}
	public void eliminarActAcad(View v) {
		String estado;
		Actividad_Academica ActAcademica = new Actividad_Academica();
		ActAcademica.setIdactacad(spnEliminarActA.getSelectedItem().toString());
		helper.abrir();
		estado = helper.eliminarActAcad(ActAcademica);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actividad_academica_eliminar, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
/*		String idActAcademica = arg0.getItemAtPosition(arg2).toString();
		Actividad_Academica AC = new Actividad_Academica();
		helper.abrir();
		AC = helper.consultarActAcademica(idActAcademica);
		helper.cerrar();
		NombreActA_Elim.setText(AC.getNom_act_acad());*/
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
