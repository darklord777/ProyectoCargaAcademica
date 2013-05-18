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

public class ActualizarDeptoActivity extends Activity implements
		OnItemSelectedListener {
	private ControlDB helper;
	private Spinner spnActIdDepto;
	private EditText edtActNomDepto;
	private List<String> idDeptos;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actualizar_depto);
		helper = new ControlDB(this);
		spnActIdDepto = (Spinner) findViewById(R.id.spnActIdDepto);
		edtActNomDepto = (EditText) findViewById(R.id.edtActNomDepto);

		helper.abrir();
		idDeptos = helper.getAllIdDeptos();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idDeptos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnActIdDepto.setAdapter(adapter);
		spnActIdDepto.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actualizar_depto, menu);
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
			edtActNomDepto.setText(departamento.getNom_depto());
		}
	}

	public void actualizarDepto(View v) {
		Departamento departamento = new Departamento();
		departamento.setIddepartamento(spnActIdDepto.getSelectedItem()
				.toString());
		departamento.setNom_depto(edtActNomDepto.getText().toString());
		helper.abrir();
		String estado = helper.actualizar(departamento);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

}
