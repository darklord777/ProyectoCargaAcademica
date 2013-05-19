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

public class ActualizarMateriaActivity extends Activity implements
		OnItemSelectedListener {
	private ControlDB helper;
	private Spinner spnActMateria;
	private EditText edtNomMateria;
	private List<String> idMaterias;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actualizar_materia);
		helper = new ControlDB(this);
		spnActMateria = (Spinner) findViewById(R.id.spnActMateria);
		edtNomMateria = (EditText) findViewById(R.id.edtNomMateria);

		helper.abrir();
		idMaterias = helper.getAllIdMaterias();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idMaterias);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnActMateria.setAdapter(adapter);
		spnActMateria.setOnItemSelectedListener(this);
	}

	public void guardarMateria(View v) {
		String regInsertados;
		Materia materia = new Materia();
		materia.setCodigomateria(spnActMateria.getSelectedItem().toString());
		helper.abrir();
		regInsertados = helper.actualizar(materia);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actualizar_materia, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		String idMateria = arg0.getItemAtPosition(arg2).toString();
		Materia materia = new Materia();
		helper.abrir();
		materia = helper.consultarMateria(idMateria);
		helper.cerrar();
		edtNomMateria.setText(materia.getNom_materia());

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
