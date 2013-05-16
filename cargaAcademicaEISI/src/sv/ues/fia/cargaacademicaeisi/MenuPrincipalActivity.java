package sv.ues.fia.cargaacademicaeisi;

import com.example.cargaacademicaeisi.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuPrincipalActivity extends Activity implements OnClickListener {
	private Button btnAdmonCat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		btnAdmonCat = (Button) findViewById(R.id.btnAdmonCat);
		btnAdmonCat.setOnClickListener(this);
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
					AdmonDeptoActivity.class));
			break;

		default:
			break;
		}
	}

}
