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

public class DetGpoAsigNuevoActivity extends Activity implements
		OnItemSelectedListener {
	private ControlDB helper;
	private EditText edtIdDetGpoAsig;
	private Spinner spnIdModNuevoDetGpoAsig;
	private Spinner spnIdLocNuevoDetGpoAsig;
	private Spinner spnCodMateNuevoDetGpoAsig;
	private List<String> idMats;
	private List<String> idMods;
	private List<String> idLocs;
	private ArrayAdapter<String> adapterMats;
	private ArrayAdapter<String> adapterMods;
	private ArrayAdapter<String> adapterLocs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_det_gpo_asig_nuevo);
		helper = new ControlDB(this);
		edtIdDetGpoAsig = (EditText) findViewById(R.id.edtIdDetGpoAsig);
		spnCodMateNuevoDetGpoAsig = (Spinner) findViewById(R.id.spnCodMateNuevoDetGpoAsig);
		spnIdModNuevoDetGpoAsig = (Spinner) findViewById(R.id.spnIdModNuevoDetGpoAsig);
		spnIdLocNuevoDetGpoAsig = (Spinner) findViewById(R.id.spnIdLocNuevoDetGpoAsig);

		helper.abrir();
		idMats = helper.getAllIdMaterias();
		idMods = helper.getAllIdModCurso();
		idLocs = helper.getAll_IdLocales();
		helper.cerrar();

		adapterMats = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idMats);
		adapterMods = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idMods);
		adapterLocs = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idLocs);

		adapterMats
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapterMods
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapterLocs
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnCodMateNuevoDetGpoAsig.setAdapter(adapterMats);
		spnIdModNuevoDetGpoAsig.setAdapter(adapterMods);
		spnIdLocNuevoDetGpoAsig.setAdapter(adapterLocs);

		spnCodMateNuevoDetGpoAsig.setOnItemSelectedListener(this);
		spnIdModNuevoDetGpoAsig.setOnItemSelectedListener(this);
		spnIdLocNuevoDetGpoAsig.setOnItemSelectedListener(this);
	}

	public void guardarDetGpoAsig(View v) {
		DetalleGrupoAsignado grupoAsignado = new DetalleGrupoAsignado();
		grupoAsignado.setIddetallecurso(edtIdDetGpoAsig.getText().toString());
		grupoAsignado.setCodigomateria(spnCodMateNuevoDetGpoAsig
				.getSelectedItem().toString());
		grupoAsignado.setIdmodalidad(spnIdModNuevoDetGpoAsig.getSelectedItem()
				.toString());
		grupoAsignado.setIdlocal(spnIdLocNuevoDetGpoAsig.getSelectedItem()
				.toString());
		helper.abrir();
		String estado = helper.insertar(grupoAsignado);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.det_gpo_asig_nuevo, menu);
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
