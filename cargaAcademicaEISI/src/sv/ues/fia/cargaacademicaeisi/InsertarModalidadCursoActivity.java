package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarModalidadCursoActivity extends Activity {

	private ControlDB helper;
	private EditText idModCurso;
	private EditText NomModCurso;
	private EditText DesHorasModCurso;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_modalidad_curso);
		helper = new ControlDB(this);
		idModCurso = (EditText) findViewById(R.id.idModalidadCurso);
		NomModCurso = (EditText) findViewById(R.id.NomModCurso);
		DesHorasModCurso = (EditText) findViewById(R.id.Desc_Horas_MCurs);
	}
	
	public void insertarModalidadCurso(View v) {
		String regInsertados;
		Modalidad_Curso modalidadcurso = new Modalidad_Curso();
		modalidadcurso.setIdmodalidadCurso(idModCurso.getText().toString());
		modalidadcurso.setNom_modalidad(NomModCurso.getText().toString());
		modalidadcurso.setDescuento_horas(Integer.valueOf(DesHorasModCurso.getText().toString()));
		
		helper.abrir();
		regInsertados = helper.insertar(modalidadcurso);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
	}
	
	public void limpiarModalidadCurso(View v) {
		idModCurso.setText("");
		NomModCurso.setText("");
		DesHorasModCurso.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_modalidad_curso, menu);
		return true;
	}

}
