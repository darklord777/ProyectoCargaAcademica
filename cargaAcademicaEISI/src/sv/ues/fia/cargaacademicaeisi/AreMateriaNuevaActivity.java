package sv.ues.fia.cargaacademicaeisi;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AreMateriaNuevaActivity extends Activity implements
		OnItemSelectedListener {
	private ControlDB helper;
	private EditText edtIdAreaMat;
	private Spinner spnIdDeptoNuevaAreaMat;
	private Spinner spnCodMateNuevaAreMat;
	private List<String> idDeptos;
	private List<String> idMaterias;
	private ArrayAdapter<String> adapterDepto;
	private ArrayAdapter<String> adapterMat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_are_materia_nueva);
		helper = new ControlDB(this);
		edtIdAreaMat = (EditText) findViewById(R.id.edtIdAreaMat);
		spnIdDeptoNuevaAreaMat = (Spinner) findViewById(R.id.spnIdDeptoNuevaAreaMat);
		spnCodMateNuevaAreMat = (Spinner) findViewById(R.id.spnCodMateNuevaAreMat);

		helper.abrir();
		idDeptos = helper.getAllIdDeptos();
		idMaterias = helper.getAllIdMaterias();
		helper.cerrar();

		adapterDepto = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idDeptos);
		adapterMat = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idMaterias);

		adapterDepto
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapterMat
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnIdDeptoNuevaAreaMat.setAdapter(adapterDepto);
		spnCodMateNuevaAreMat.setAdapter(adapterMat);

		spnIdDeptoNuevaAreaMat.setOnItemSelectedListener(this);
		spnCodMateNuevaAreMat.setOnItemSelectedListener(this);
	}

	public void guardarAreaMateria(View v) {
		if (!edtIdAreaMat.getText().toString().trim().equals("")) {
			AreaMateria areaMateria = new AreaMateria();
			areaMateria.setIdareamat(edtIdAreaMat.getText().toString().trim());
			areaMateria.setIddepartamento(spnIdDeptoNuevaAreaMat
					.getSelectedItem().toString());
			areaMateria.setCodigomateria(spnCodMateNuevaAreMat
					.getSelectedItem().toString());
			helper.abrir();
			String estado = helper.insertar(areaMateria);
			helper.cerrar();
			Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
		} else {
			edtIdAreaMat.setText("");
			Toast.makeText(this, "Codigo de area materia es obligatorio.",
					Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.are_materia_nueva, menu);
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
