package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminMateriasImpartirActivity extends Activity implements OnClickListener {
	private Button btnInsertar;
	private Button btnConsultar;
	private Button btnEliminar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_materias_impartir);
		btnInsertar = (Button) findViewById(R.id.btnAsigMatDoc);
		btnInsertar.setOnClickListener(this);
		btnConsultar = (Button) findViewById(R.id.btnConsultarMatDoc);
		btnConsultar.setOnClickListener(this);
		btnEliminar = (Button) findViewById(R.id.btnEliminarMatDoc);
		btnEliminar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_materias_impartir, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnAsigMatDoc:
			startActivity(new Intent(getApplicationContext(),
					InsertarMateriasImpartirActivity.class));
			break;
		case R.id.btnConsultarMatDoc:
			startActivity(new Intent(getApplicationContext(),
					ConsultarMateriasImpartirActivity.class));
			break;
		case R.id.btnEliminarMatDoc:
			startActivity(new Intent(getApplicationContext(),
					EliminarMateriasImpartirActivity.class));
			break;
		default:
			break;
		}
	}

}
