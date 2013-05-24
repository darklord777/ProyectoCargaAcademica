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

public class PeridoConsultarActivity extends Activity implements OnItemSelectedListener{

	private ControlDB helper;
	private EditText edtFechaIni;
	private EditText edtFechaFin;
	private Spinner spnListaPeriodos;
	private List<String> idPeriodos;
	private ArrayAdapter<String> adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perido_consultar);
		helper = new ControlDB(this);
		edtFechaIni = (EditText) findViewById(R.id.edtfechainiUp);
		edtFechaFin = (EditText) findViewById(R.id.editfechafinUp);
		spnListaPeriodos = (Spinner) findViewById(R.id.spn_Cons_Pero);
		spnListaPeriodos.setOnItemSelectedListener(this);
		
		helper.abrir();
		idPeriodos = helper.getAllIdPeriodos();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, idPeriodos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnListaPeriodos.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.perido_consultar, menu);
		return true;
	}
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
		String idPeriodo = parent.getItemAtPosition(pos).toString();
		PERIODO periodo = new PERIODO ();
		helper.abrir();		
		 periodo = helper.consultarPeriodo(idPeriodo);
		helper.cerrar();
			edtFechaIni.setText(periodo.getFechaIni());
			edtFechaFin.setText(periodo.getFechaFin());
		}
	

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

}
