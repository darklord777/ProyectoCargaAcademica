package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;

public class AdmonAreaMatActivity extends Activity implements OnClickListener {
	private Button btnNuevaAreaMateria;
	private Button btnConsultarAreaMat;
	private Button btnEliminarAreaMat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admon_area_mat);
		btnNuevaAreaMateria = (Button) findViewById(R.id.btnNuevaAreaMateria);
		btnConsultarAreaMat = (Button) findViewById(R.id.btnConsultarAreaMat);
		btnEliminarAreaMat = (Button) findViewById(R.id.btnEliminarAreaMat);
		btnConsultarAreaMat.setOnClickListener(this);
		btnNuevaAreaMateria.setOnClickListener(this);
		btnEliminarAreaMat.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.admon_area_mat, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnNuevaAreaMateria:
			startActivity(new Intent(getApplicationContext(),
					AreMateriaNuevaActivity.class));
			break;
		case R.id.btnConsultarAreaMat:
			startActivity(new Intent(getApplicationContext(),
					AreMateriaConsultarActivity.class));
			break;
		case R.id.btnEliminarAreaMat:
			startActivity(new Intent(getApplicationContext(),
					AreMateriaEliminarActivity.class));
			break;
		default:
			break;
		}
	}

}
