package sv.ues.fia.cargaacademicaeisi;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EliminarDeptoActivity extends Activity implements
		OnItemSelectedListener {
	private ControlDB helper;
	private Spinner spnElimDepto;
	private EditText edtNomDepto;
	private List<String> idDeptos;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_depto);
		helper = new ControlDB(this);
		spnElimDepto = (Spinner) findViewById(R.id.spnElimDepto);
		edtNomDepto = (EditText) findViewById(R.id.edtNomDepto);

		helper.abrir();
		idDeptos = helper.getAllIdDeptos();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idDeptos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnElimDepto.setAdapter(adapter);
		spnElimDepto.setOnItemSelectedListener(this);
	}

	public void eliminarDepto(View v) {
		Departamento departamento = new Departamento();
		departamento.setIddepartamento(spnElimDepto.getSelectedItem()
				.toString());
		helper.abrir();
		String estado = helper.eliminar(departamento);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eliminar_depto, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		String idDepto = arg0.getItemAtPosition(arg2).toString();
		helper.abrir();
		Departamento departamento = helper.consultarDepto(idDepto);
		helper.cerrar();
		if (departamento == null) {
			Toast.makeText(
					this,
					"Identificador de departemento: " + idDepto
							+ ". No existe.", Toast.LENGTH_LONG).show();
		} else {
			edtNomDepto.setText(departamento.getNom_depto());
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
