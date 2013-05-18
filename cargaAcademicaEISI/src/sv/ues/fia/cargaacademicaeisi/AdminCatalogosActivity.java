package sv.ues.fia.cargaacademicaeisi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class AdminCatalogosActivity extends Activity implements
		android.view.View.OnClickListener {
	private Button btnAdmonDepto;
	private Button btnAdmonMateria;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_catalogos);
		btnAdmonDepto = (Button) findViewById(R.id.btnAdmonDepto);
		btnAdmonDepto.setOnClickListener(this);
		btnAdmonMateria = (Button) findViewById(R.id.btnAdmonMateria);
		btnAdmonMateria.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.admin_catalogos, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAdmonDepto:
			startActivity(new Intent(getApplicationContext(),
					AdmonDeptoActivity.class));
			break;
		case R.id.btnAdmonMateria:
			startActivity(new Intent(getApplicationContext(),
					AdmonMateriaActivity.class));
		default:
			break;
		}
	}

}
