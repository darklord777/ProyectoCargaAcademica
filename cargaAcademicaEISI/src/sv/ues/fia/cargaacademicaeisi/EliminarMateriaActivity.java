package sv.ues.fia.cargaacademicaeisi;

import java.util.List;

import org.xml.sax.DTDHandler;

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

public class EliminarMateriaActivity extends Activity implements
		OnItemSelectedListener {
	private ControlDB helper;
	private Spinner spnElimMateria;
	private EditText edtNomMateria;
	private List<String> idMaterias;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_materia);
		helper = new ControlDB(this);
		spnElimMateria = (Spinner) findViewById(R.id.spnElimMateria);
		edtNomMateria = (EditText) findViewById(R.id.edtNomMateria);
		
		helper.abrir();
		idMaterias = helper.getAllIdMaterias();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idMaterias);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnElimMateria.setAdapter(adapter);
		spnElimMateria.setOnItemSelectedListener(this);
	}


	public void eliminarMateria(View v) {
		Materia materia = new Materia();
		materia.setCodigomateria(spnElimMateria.getSelectedItem().toString());
		materia.setNom_materia(edtNomMateria.getText().toString());
		helper.abrir();
		String estado = helper.eliminar(materia);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eliminar_materia, menu);
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
