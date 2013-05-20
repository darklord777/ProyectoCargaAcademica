package sv.ues.fia.cargaacademicaeisi;

import java.util.List;

import sv.ues.fia.cargaacademicaeisi.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class AreMateriaConsultarActivity extends Activity implements
		OnItemSelectedListener {
	private ControlDB helper;
	private Spinner spnConsultAreaMat;
	private TextView tvConsultDeptoAreMat;
	private TextView tvCodMatAreaMat;
	private List<String> idAreMats;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_are_materia_consultar);
		helper = new ControlDB(this);
		spnConsultAreaMat = (Spinner) findViewById(R.id.spnConsultAreaMat);
		tvConsultDeptoAreMat = (TextView) findViewById(R.id.tvConsultDeptoAreMat);
		tvCodMatAreaMat = (TextView) findViewById(R.id.tvCodMatAreaMat);

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
		helper.cerrar();
		tvConsultDeptoAreMat.setText(areaMateria.getIddepartamento());
		tvCodMatAreaMat.setText(areaMateria.getCodigomateria());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
