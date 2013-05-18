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

public class ConsultarDeptoActivity extends Activity implements
		OnItemSelectedListener {
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
		edtNomDepto = (EditText) findViewById(R.id.edtNomDepto);
		spnListaDeptos = (Spinner) findViewById(R.id.spnListaDeptos);

		helper.abrir();
		idDeptos = helper.getAllIdDeptos();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idDeptos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnListaDeptos.setAdapter(adapter);
		spnListaDeptos.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.consultar_depto, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
		helper.abrir();
		String idDepto = parent.getItemAtPosition(pos).toString();
		Departamento departamento = helper.consultarDepto(idDepto);
		helper.cerrar();
		if (departamento == null) {
			Toast.makeText(
					this,
					"Identificador de departemento: " + idDepto
							+ ". No existe.", Toast.LENGTH_LONG).show();
		} else {
			edtNomDepto.setText(departamento.getNom_depto());
			Toast.makeText(this, "Valor de item=" + idDepto, Toast.LENGTH_LONG)
					.show();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

}
