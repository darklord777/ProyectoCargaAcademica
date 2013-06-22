package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuServiciosWebActivity extends Activity {

	//private Button btnServiciosWeb;
	private ImageButton botonwebservice;
	private ControlDB helper;
	private ImageButton botoncamara = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_servicios_web);
		helper = new ControlDB(this);
		//btnServiciosWeb = (Button) findViewById(R.id.btnServiciosWeb);
		botoncamara = (ImageButton) findViewById(R.id.boton_camara);
		botonwebservice = (ImageButton) findViewById(R.id.btnWebService);
		//btnServiciosWeb.setOnClickListener(this);
		
		
		botoncamara.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(),CamaraActivity.class));
				
			}
		});
		botonwebservice.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(),ServiciosWebActivity.class));
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_servicios_web, menu);
		return true;
	}

	/*@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnServiciosWeb:
			startActivity(new Intent(getApplicationContext(),
					ServiciosWebActivity.class));
			break;
		}
	}*/

}
