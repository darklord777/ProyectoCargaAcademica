package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdmonDeptoActivity extends Activity implements OnClickListener {
	private Button btnNuevoDepto;
	private Button btnConsultarDepto;
	private Button btnActualizarDepto;
	private Button btnEliminarDepto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admon_depto);
		btnNuevoDepto = (Button) findViewById(R.id.btnNuevoDepto);
		btnNuevoDepto.setOnClickListener(this);
		btnConsultarDepto = (Button) findViewById(R.id.btnConsultarDepto);
		btnConsultarDepto.setOnClickListener(this);
		btnActualizarDepto = (Button) findViewById(R.id.btnActualizarDepto);
		btnActualizarDepto.setOnClickListener(this);
		btnEliminarDepto = (Button) findViewById(R.id.btnEliminarDepto);
		btnEliminarDepto.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.admon_depto, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnNuevoDepto:
			startActivity(new Intent(getApplicationContext(),
					NuevoDeptoActivity.class));
			break;
		case R.id.btnConsultarDepto:
			startActivity(new Intent(getApplicationContext(),
					ConsultarDeptoActivity.class));
			break;
		case R.id.btnActualizarDepto:
			startActivity(new Intent(getApplicationContext(),
					ActualizarDeptoActivity.class));
			break;
		case R.id.btnEliminarDepto:
			startActivity(new Intent(getApplicationContext(),
					EliminarDeptoActivity.class));
			break;
		default:
			break;
		}
	}

}
