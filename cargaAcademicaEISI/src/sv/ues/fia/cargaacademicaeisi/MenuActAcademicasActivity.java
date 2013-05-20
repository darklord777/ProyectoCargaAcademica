package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActAcademicasActivity extends Activity implements OnClickListener{
	
	private Button Btn_tabla_modalidadActAcad;
	private Button Btn_tabla_ActividadAcad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_act_academicas);
		Btn_tabla_modalidadActAcad = (Button) findViewById(R.id.btnModActAcad);
		Btn_tabla_modalidadActAcad.setOnClickListener(this);
		Btn_tabla_ActividadAcad = (Button) findViewById(R.id.BtnActAcad);
		Btn_tabla_ActividadAcad.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		
		case R.id.btnModActAcad:
			startActivity(new Intent(getApplicationContext(),
					 ModalidadAAMenuActivity.class));
			break;
			
		case R.id.BtnActAcad:
			startActivity(new Intent(getApplicationContext(),
					 ActividadAcademicaMenuActivity.class));
			break;
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_act_academicas, menu);
		return true;
	}

	
}
