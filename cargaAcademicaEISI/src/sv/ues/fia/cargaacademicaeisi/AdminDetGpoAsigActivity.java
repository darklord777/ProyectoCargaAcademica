package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminDetGpoAsigActivity extends Activity implements
		OnClickListener {
	private Button btnNuevoDetGopAsig;
	private Button btnConsultarDetGpoAsig;
	private Button btnEliminarDetGpoAsig;
	private Button btnActualizarDetGpoAsig;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_det_gpo_asig);
		btnNuevoDetGopAsig = (Button) findViewById(R.id.btnNuevoDetGopAsig);
		btnNuevoDetGopAsig.setOnClickListener(this);
		btnConsultarDetGpoAsig = (Button) findViewById(R.id.btnConsultarDetGpoAsig);
		btnConsultarDetGpoAsig.setOnClickListener(this);
		btnEliminarDetGpoAsig = (Button) findViewById(R.id.btnEliminarDetGpoAsig);
		btnEliminarDetGpoAsig.setOnClickListener(this);
		btnActualizarDetGpoAsig = (Button) findViewById(R.id.btnActualizarDetGpoAsig);
		btnActualizarDetGpoAsig.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_det_gpo_asig, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnNuevoDetGopAsig:
			startActivity(new Intent(getApplicationContext(),
					DetGpoAsigNuevoActivity.class));
			break;
		case R.id.btnConsultarDetGpoAsig:
			startActivity(new Intent(getApplicationContext(),
					DetGpoAsigConsultarActivity.class));
			break;
		case R.id.btnEliminarDetGpoAsig:
			startActivity(new Intent(getApplicationContext(),
					DetGpoAsigEliminarActivity.class));
			break;
		case R.id.btnActualizarDetGpoAsig:
			startActivity(new Intent(getApplicationContext(),
					DetGpoAsigActualizarActivity.class));
			break;
		default:
			break;
		}
	}

}
