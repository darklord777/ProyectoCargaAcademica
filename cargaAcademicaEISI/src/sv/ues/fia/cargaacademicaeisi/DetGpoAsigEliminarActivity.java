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

public class DetGpoAsigEliminarActivity extends Activity implements
		OnItemSelectedListener {
	private ControlDB helper;
	private Spinner spnIdDetGpoAsig;
	private EditText edtCodMatDetGpoAsigs;
	private EditText edtIdModDetGpoAsig;
	private EditText edtIdLocalDetGpoAsig;
	private List<String> idDetGpos;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_det_gpo_asig_eliminar);
		helper = new ControlDB(this);
		spnIdDetGpoAsig = (Spinner) findViewById(R.id.spnIdDetGpoAsig);
		edtCodMatDetGpoAsigs = (EditText) findViewById(R.id.edtCodMatDetGpoAsigs);
		edtIdModDetGpoAsig = (EditText) findViewById(R.id.edtIdModDetGpoAsig);
		edtIdLocalDetGpoAsig = (EditText) findViewById(R.id.edtIdLocalDetGpoAsig);

		helper.abrir();
		idDetGpos = helper.getAllIdDetGpoAsig();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idDetGpos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnIdDetGpoAsig.setAdapter(adapter);
		spnIdDetGpoAsig.setOnItemSelectedListener(this);
	}

	public void eliminarDetGpoAsignado(View v) {
		DetalleGrupoAsignado grupoAsignado = new DetalleGrupoAsignado();
		grupoAsignado.setIddetallecurso(spnIdDetGpoAsig.getSelectedItem()
				.toString());
		helper.abrir();
		String estado = helper.eliminar(grupoAsignado);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.det_gpo_asig_eliminar, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		String idDetGpo = arg0.getItemAtPosition(arg2).toString();
		DetalleGrupoAsignado grupoAsignado = new DetalleGrupoAsignado();
		helper.abrir();
		grupoAsignado = helper.consultarDetGpoAsig(idDetGpo);
		helper.cerrar();
		edtCodMatDetGpoAsigs.setText(grupoAsignado.getCodigomateria());
		edtIdModDetGpoAsig.setText(grupoAsignado.getIdmodalidad());
		edtIdLocalDetGpoAsig.setText(grupoAsignado.getIdlocal());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
