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

public class AreMateriaEliminarActivity extends Activity implements
		OnItemSelectedListener {
	private ControlDB helper;
	private Spinner spnElimAreaMateria;
	private EditText edtIdDeptoAreMat;
	private EditText edtCodMateAreaMat;
	private List<String> idAreaMats;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_are_materia_eliminar);
		helper = new ControlDB(this);
		spnElimAreaMateria = (Spinner) findViewById(R.id.spnElimAreaMateria);
		edtIdDeptoAreMat = (EditText) findViewById(R.id.edtIdDeptoAreMat);
		edtCodMateAreaMat = (EditText) findViewById(R.id.edtCodMateAreaMat);

		helper.abrir();
		idAreaMats = helper.getAllIdAreaMats();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idAreaMats);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnElimAreaMateria.setAdapter(adapter);
		spnElimAreaMateria.setOnItemSelectedListener(this);
	}

	public void eliminarDetalleAreaMat(View v) {
		AreaMateria areaMateria = new AreaMateria();
		areaMateria.setIdareamat(spnElimAreaMateria.getSelectedItem()
				.toString());
		helper.abrir();
		String estado = helper.eliminar(areaMateria);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.are_materia_eliminar, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		String idAreMat = arg0.getItemAtPosition(arg2).toString();
		AreaMateria areaMateria = new AreaMateria();
		helper.abrir();
		areaMateria = helper.consultarAreaMateria(idAreMat);
		helper.cerrar();
		edtIdDeptoAreMat.setText(areaMateria.getIddepartamento());
		edtCodMateAreaMat.setText(areaMateria.getCodigomateria());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
