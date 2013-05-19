package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AdmonAreaMatActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admon_area_mat);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admon_area_mat, menu);
		return true;
	}

}
