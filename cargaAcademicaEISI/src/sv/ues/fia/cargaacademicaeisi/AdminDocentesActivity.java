package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminDocentesActivity extends Activity implements OnClickListener {
	private Button btnInsertar;
	private Button btnActualizar;
	private Button btnConsultar;
	private Button btnEliminar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_docentes);
		btnInsertar = (Button) findViewById(R.id.btnNuevoDocente);
		btnInsertar.setOnClickListener(this);
		btnActualizar = (Button) findViewById(R.id.btnActualizarDocente);
		btnActualizar.setOnClickListener(this);
		btnConsultar = (Button) findViewById(R.id.btnConsultarDocente);
		btnConsultar.setOnClickListener(this);
		btnEliminar = (Button) findViewById(R.id.btnEliminarDocente);
		btnEliminar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_docentes, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnNuevoDocente:
			startActivity(new Intent(getApplicationContext(),
					InsertarDocenteActivity.class));
			break;
		case R.id.btnActualizarDocente:
			startActivity(new Intent(getApplicationContext(),
				    ActualizarDocenteActivity.class));
			break;
		case R.id.btnConsultarDocente:
			startActivity(new Intent(getApplicationContext(),
					ConsultarDocenteActivity.class));
			break;
		case R.id.btnEliminarDocente:
			startActivity(new Intent(getApplicationContext(),
					EliminarDocenteActivity.class));
			break;
		default:
			break;
		}
	}

}
