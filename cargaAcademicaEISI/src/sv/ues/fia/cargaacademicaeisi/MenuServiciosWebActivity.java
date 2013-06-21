package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuServiciosWebActivity extends Activity implements OnClickListener {

	private Button btnServiciosWeb;
	private ControlDB helper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_servicios_web);
		helper = new ControlDB(this);
		btnServiciosWeb = (Button) findViewById(R.id.btnServiciosWeb);
		btnServiciosWeb.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_servicios_web, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnServiciosWeb:
			startActivity(new Intent(getApplicationContext(),
					ServiciosWebActivity.class));
			break;
		}
	}

}
