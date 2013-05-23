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

public class ActividadAcademicaConsultarActivity extends Activity implements OnItemSelectedListener {

	private ControlDB helper;
	private EditText idModalAA;
	private EditText NombreActAcad;
	private EditText cargo;
	private Spinner spnListaActAcad;
	private List<String> idActA;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actividad_academica_consultar);
		
		helper = new ControlDB(this);
		idModalAA = (EditText) findViewById(R.id.idModActA);
		NombreActAcad = (EditText) findViewById(R.id.SelNombreActAcad);
		cargo = (EditText) findViewById(R.id.CargoActAcad);
		spnListaActAcad = (Spinner) findViewById(R.id.spin_Select_ActAcademica);
		
		helper.abrir();
		idActA = helper.getAll_IdActA();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, idActA);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnListaActAcad.setAdapter(adapter);
		spnListaActAcad.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actividad_academica_consultar, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
		// TODO Auto-generated method stub
		String idActAcademica = parent.getItemAtPosition(pos).toString();
		helper.abrir();		
		Actividad_Academica ActiAcad = helper.consultarActAcademica(idActAcademica);
		helper.cerrar();
		if (ActiAcad == null) {
			Toast.makeText(this,"Identificador de Actividad: " + idActAcademica
							+ ". No existe.", Toast.LENGTH_LONG).show();
		} else {
			idModalAA.setText(ActiAcad.getIdmodalidad());
			NombreActAcad.setText(ActiAcad.getNom_act_acad());
			cargo.setText(ActiAcad.getCargo());
			Toast.makeText(this, "Valor de item=" + idActAcademica, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
