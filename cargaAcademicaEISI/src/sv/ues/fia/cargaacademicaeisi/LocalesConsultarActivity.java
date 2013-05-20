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
import android.widget.AdapterView.OnItemSelectedListener;

public class LocalesConsultarActivity extends Activity implements OnItemSelectedListener {
	
	private ControlDB helper;
	private EditText CapLocales;
	private Spinner spnListaLocales;
	private List<String> idLocales;
	private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locales_consultar);
		
		helper = new ControlDB(this);
		CapLocales = (EditText) findViewById(R.id.edtNomDepto);
		spnListaLocales = (Spinner) findViewById(R.id.spnListaDeptos);

		helper.abrir();
		idLocales = helper.getAllIdDeptos();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idLocales);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnListaLocales.setAdapter(adapter);
		spnListaLocales.setOnItemSelectedListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.locales_consultar, menu);
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
