package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminDocenteDeptoActivity extends Activity implements OnClickListener {
	private Button btnInsertar;
	private Button btnConsultar;
	private Button btnEliminar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_docente_depto);
		btnInsertar = (Button) findViewById(R.id.btnAsigDocDepto);
		btnInsertar.setOnClickListener(this);
		btnConsultar = (Button) findViewById(R.id.btnConsultarDocDepto);
		btnConsultar.setOnClickListener(this);
		btnEliminar = (Button) findViewById(R.id.btnEliminarDocDepto);
		btnEliminar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_docente_depto, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnAsigDocDepto:
			startActivity(new Intent(getApplicationContext(),
					InsertarDocenteDeptoActivity.class));
			break;
		case R.id.btnConsultarDocDepto:
			startActivity(new Intent(getApplicationContext(),
					ConsultarDocenteDeptoActivity.class));
			break;
		case R.id.btnEliminarDocDepto:
			startActivity(new Intent(getApplicationContext(),
					EliminarDocenteDeptoActivity.class));
			break;
		default:
			break;
		}
	}

}
