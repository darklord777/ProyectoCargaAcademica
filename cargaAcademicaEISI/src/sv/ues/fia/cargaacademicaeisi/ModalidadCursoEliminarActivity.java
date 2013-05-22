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

public class ModalidadCursoEliminarActivity extends Activity implements OnItemSelectedListener {

	private ControlDB helper;
	private Spinner spnEliminarModC;
	private EditText NombreModC_Elim;
	private List<String> idLocal;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modalidad_curso_eliminar);
		
		helper = new ControlDB(this);
		spnEliminarModC = (Spinner) findViewById(R.id.spn_Select_ModC);
		NombreModC_Elim = (EditText) findViewById(R.id.ModCursoN_Elim);
		helper.abrir();
		idLocal = helper.getAllIdModCurso();
		helper.cerrar();		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, idLocal);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnEliminarModC.setAdapter(adapter);
		spnEliminarModC.setOnItemSelectedListener(this);
	}
	
	public void eliminarModC(View v) {
		String estado;
		Modalidad_Curso ModCurso = new Modalidad_Curso();
		ModCurso.setIdmodalidadCurso(spnEliminarModC.getSelectedItem().toString());
		helper.abrir();
		estado = helper.eliminarModCurso(ModCurso);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modalidad_curso_eliminar, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		String idModCurso = arg0.getItemAtPosition(arg2).toString();
		Modalidad_Curso MC = new Modalidad_Curso();
		helper.abrir();
		MC = helper.consultarModCurso(idModCurso);
		helper.cerrar();
		NombreModC_Elim.setText(MC.getNom_modalidad());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
