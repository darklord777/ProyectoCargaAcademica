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

public class AreMateriaConsultarActivity extends Activity implements
		OnItemSelectedListener {
	private ControlDB helper;
	private Spinner spnConsultAreaMat;
	private EditText edtConsultDeptoAreMat;
	private EditText edtCodMatAreaMat;
	private List<String> idAreMats;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_are_materia_consultar);
		helper = new ControlDB(this);
		spnConsultAreaMat = (Spinner) findViewById(R.id.spnConsultAreaMat);
		edtConsultDeptoAreMat = (EditText) findViewById(R.id.edtConsultDeptoAreMat);
		edtCodMatAreaMat = (EditText) findViewById(R.id.edtCodMatAreaMat);

		helper.abrir();
		idAreMats = helper.getAllIdAreaMats();
		helper.cerrar();

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idAreMats);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnConsultAreaMat.setAdapter(adapter);

		spnConsultAreaMat.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.are_materia_consultar, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		String idAreMat = arg0.getItemAtPosition(arg2).toString();
		AreaMateria areaMateria = new AreaMateria();
		helper.abrir();
		areaMateria = helper.consultarAreaMateria(idAreMat);
		edtConsultDeptoAreMat
				.setText(helper.getAsociado("DEPARTAMENTO", "NOM_DEPTO",
						"IDDEPARTAMENTO", areaMateria.getIddepartamento()));
		edtCodMatAreaMat.setText(helper.getAsociado("materia", "NOM_MATERIA",
				"CODIGOMATERIA", areaMateria.getCodigomateria()));
		helper.cerrar();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
