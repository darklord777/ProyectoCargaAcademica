package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarActividadAcademicaActivity extends Activity {

	private ControlDB helper;
	private EditText idActAcad;
	private EditText nombreActAcad;
	private EditText cargoActAcad;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_actividad_academica);
		helper = new ControlDB(this);
		idActAcad = (EditText) findViewById(R.id.idActAcademica);
		nombreActAcad = (EditText) findViewById(R.id.NombreActAcad);
		cargoActAcad = (EditText) findViewById(R.id.CargoActAcad);	
	}
	
	public void insertarActAcademica(View v) {
		String regInsertados;
		Actividad_Academica ActAcademica = new Actividad_Academica();
		ActAcademica.setIdactacad(idActAcad.getText().toString());
		ActAcademica.setNom_act_acad(nombreActAcad.getText().toString());
		ActAcademica.setCargo(cargoActAcad.getText().toString());
		helper.abrir();
		regInsertados = helper.insertar(ActAcademica);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
	}
	
	public void limpiarActAcad(View v) {
		idActAcad.setText("");
		nombreActAcad.setText("");
		cargoActAcad.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_actividad_academica, menu);
		return true;
	}

}
