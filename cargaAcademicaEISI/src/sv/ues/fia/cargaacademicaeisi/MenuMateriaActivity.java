package sv.ues.fia.cargaacademicaeisi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuMateriaActivity extends Activity implements OnClickListener {
	private Button btnAdmonMateria;
	private Button btnAdmonAreaMateria;
	private Button btnAdmonDetGpoAsig;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_materia);
		btnAdmonMateria = (Button) findViewById(R.id.btnAdmonMateria);
		btnAdmonMateria.setOnClickListener(this);
		btnAdmonAreaMateria = (Button) findViewById(R.id.btnAdmonAreaMateria);
		btnAdmonAreaMateria.setOnClickListener(this);
		btnAdmonDetGpoAsig = (Button) findViewById(R.id.btnAdmonDetGpoAsig);
		btnAdmonDetGpoAsig.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_materia, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAdmonMateria:
			startActivity(new Intent(getApplicationContext(),
					AdmonMateriaActivity.class));
			break;
		case R.id.btnAdmonAreaMateria:
			startActivity(new Intent(getApplicationContext(),
					AdmonAreaMatActivity.class));
			break;
		case R.id.btnAdmonDetGpoAsig:
			startActivity(new Intent(getApplicationContext(),
					AdminDetGpoAsigActivity.class));
			break;
		default:
			break;
		}
	}

}
