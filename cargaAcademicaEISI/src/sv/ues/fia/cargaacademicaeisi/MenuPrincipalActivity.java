package sv.ues.fia.cargaacademicaeisi;

import sv.ues.fia.cargaacademicaeisi.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuPrincipalActivity extends Activity implements OnClickListener {
	private Button btnAdmonCat;
	private Button btnA_CargaAcademica;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		btnAdmonCat = (Button) findViewById(R.id.btnAdmonCat);
		btnAdmonCat.setOnClickListener(this);
		btnA_CargaAcademica = (Button) findViewById(R.id.btn5A_CargaAcademica);
		btnA_CargaAcademica.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAdmonCat:
			startActivity(new Intent(getApplicationContext(),
					AdminCatalogosActivity.class));
			break;
		case R.id.btn5A_CargaAcademica:
			startActivity(new Intent(getApplicationContext(),
					GestionCargaAcadActivity.class));
			break;
		default:
			break;
		}
	}

}
