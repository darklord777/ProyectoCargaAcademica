package sv.ues.fia.cargaacademicaeisi;

import sv.ues.fia.cargaacademicaeisi.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class CargaAcademicaActivity extends Activity implements OnClickListener {
	private Button btnEntrarSistema;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carga_academica);
		btnEntrarSistema = (Button) findViewById(R.id.btnEntrarSistema);
		btnEntrarSistema.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.carga_academica, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnEntrarSistema:
			startActivity(new Intent(getApplicationContext(),
					MenuPrincipalActivity.class));
			break;

		default:
			break;
		}
	}

}
