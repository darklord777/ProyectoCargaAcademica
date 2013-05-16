package sv.ues.fia.cargaacademicaeisi;

import com.example.cargaacademicaeisi.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdmonDeptoActivity extends Activity implements OnClickListener {
	private Button btnNuevoDepto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admon_depto);
		btnNuevoDepto = (Button) findViewById(R.id.btnNuevoDepto);
		btnNuevoDepto.setOnClickListener(this);
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

		default:
			break;
		}
	}

}
