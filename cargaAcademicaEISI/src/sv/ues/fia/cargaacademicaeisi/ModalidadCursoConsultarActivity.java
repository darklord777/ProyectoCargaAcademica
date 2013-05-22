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

public class ModalidadCursoConsultarActivity extends Activity implements OnItemSelectedListener{
 
	private ControlDB helper;
	private EditText NombreModCurso;
	private EditText DesHrsModCurso;
	private Spinner spnListaModalidadCurso;
	private List<String> idModalidadC;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modalidad_curso_consultar);
		
		helper = new ControlDB(this);
		NombreModCurso = (EditText) findViewById(R.id.NombreModalCurso);
		DesHrsModCurso = (EditText) findViewById(R.id.DescHorasMC);
		spnListaModalidadCurso = (Spinner) findViewById(R.id.spn_Select_ModCurso);
		
		helper.abrir();
		idModalidadC = helper.getAllIdModCurso();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, idModalidadC);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnListaModalidadCurso.setAdapter(adapter);
		spnListaModalidadCurso.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modalidad_curso_consultar, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
		// TODO Auto-generated method stub
		String idModCurso = parent.getItemAtPosition(pos).toString();
		helper.abrir();		
		Modalidad_Curso ModCurso = helper.consultarModCurso(idModCurso);
		helper.cerrar();
		if (ModCurso == null) {
			Toast.makeText(this,"Identificador de departemento: " + idModCurso
							+ ". No existe.", Toast.LENGTH_LONG).show();
		} else {
			NombreModCurso.setText(ModCurso.getNom_modalidad());
			Toast.makeText(this, "Valor de item=" + idModCurso, Toast.LENGTH_LONG).show();
			DesHrsModCurso.setText(ModCurso.getDescuento_horas());
			Toast.makeText(this, "Valor de item=" + idModCurso, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
