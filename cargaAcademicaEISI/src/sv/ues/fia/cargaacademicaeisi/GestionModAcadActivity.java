package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GestionModAcadActivity extends Activity implements OnClickListener{

	private Button Btn_tabla_modalidadActAcad;
	private Button Btn_tabla_ActividadAcad;
	private Button Btn_tabla_locales;
	private Button Btn_tabla_modalidadCurso;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gestion_mod_acad);
		Btn_tabla_modalidadActAcad = (Button) findViewById(R.id.btnModActAcad);
		Btn_tabla_modalidadActAcad.setOnClickListener(this);
		Btn_tabla_ActividadAcad = (Button) findViewById(R.id.BtnActAcad);
		Btn_tabla_ActividadAcad.setOnClickListener(this);
		Btn_tabla_locales = (Button) findViewById(R.id.BtnLocal);
		Btn_tabla_locales.setOnClickListener(this);
		Btn_tabla_modalidadCurso = (Button) findViewById(R.id.BtnModCurso);
		Btn_tabla_modalidadCurso.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.btnModActAcad:
			startActivity(new Intent(getApplicationContext(),
					 ModalidadAAMenuActivity.class));
			break;
			
		case R.id.BtnActAcad:
			startActivity(new Intent(getApplicationContext(),
					 ActividadAcademicaMenuActivity.class));
			break;
			
		case R.id.BtnLocal:
			startActivity(new Intent(getApplicationContext(),
					 LocalesMenuActivity.class));
			break;
		
		case R.id.BtnModCurso:
			startActivity(new Intent(getApplicationContext(),
					 ModalidadCursoMenuActivity.class));
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gestion_mod_acad, menu);
		return true;
	}

}
