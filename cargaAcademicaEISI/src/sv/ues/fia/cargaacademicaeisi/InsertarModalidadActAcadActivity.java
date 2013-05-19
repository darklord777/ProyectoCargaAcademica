package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarModalidadActAcadActivity extends Activity {
	private ControlDB helper;
	private EditText idModalidadAA;
	private EditText nombreModalidad;
	private EditText descHoras;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_modalidad_act_acad);
		helper = new ControlDB(this);
		idModalidadAA = (EditText) findViewById(R.id.idModalidadAA);
		nombreModalidad = (EditText) findViewById(R.id.NombreModAA);
		descHoras = (EditText) findViewById(R.id.DesHoras);
	}
	public void insertarModalidadAA(View v) {
		String regInsertados;
		Modalidad_Act_Acad modalidadAA = new Modalidad_Act_Acad();
		modalidadAA.setIdmodalidad(idModalidadAA.getText().toString());
		modalidadAA.setNom_modalidad(nombreModalidad.getText().toString());
		modalidadAA.setDescuento_horas(Integer.valueOf(descHoras.getText().toString()));
		helper.abrir();
		regInsertados = helper.insertar(modalidadAA);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
	}
	
	public void limpiarModalidadAA(View v) {
		idModalidadAA.setText("");
		nombreModalidad.setText("");
		descHoras.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_modalidad_act_acad, menu);
		return true;
	}

}
