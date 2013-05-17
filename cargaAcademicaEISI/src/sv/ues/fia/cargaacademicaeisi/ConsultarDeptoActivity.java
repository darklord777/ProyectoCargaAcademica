package sv.ues.fia.cargaacademicaeisi;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class ConsultarDeptoActivity extends Activity {
	private ControlDB helper;
	private EditText edtNomDepto;
	private Spinner spnListaDeptos;
	private List<String> idDeptos;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_depto);
		helper = new ControlDB(this);
		edtNomDepto=(EditText)findViewById(R.id.edtNomDepto);
		spnListaDeptos=(Spinner)findViewById(R.id.spnListaDeptos);
				
		helper.abrir();
		idDeptos = helper.getAllIdDeptos();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idDeptos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnListaDeptos.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consultar_depto, menu);
		return true;
	}

}
