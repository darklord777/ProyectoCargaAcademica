package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DetalleCargaActAcadMenuActivity extends Activity implements OnClickListener {
	private Button btnA_detallecargaacad_insertar;
	private Button btnA_detallecargaacad_eliminar;
	private Button btnA_detallecargaacad_consultar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_carga_act_acad_menu);
		btnA_detallecargaacad_insertar = (Button) findViewById(R.id.button1_MenuDETALLEACTIVIDADACADEMICA_Insert);
		btnA_detallecargaacad_insertar.setOnClickListener(this);
		btnA_detallecargaacad_eliminar = (Button) findViewById(R.id.button2_MenuDETALLEACTIVIDADACADEMICA_Delete);
		btnA_detallecargaacad_eliminar.setOnClickListener(this);
		btnA_detallecargaacad_consultar = (Button) findViewById(R.id.button3_MenuDETALLEACTIVIDADACADEMICA_Consult);
		btnA_detallecargaacad_consultar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1_MenuDETALLEACTIVIDADACADEMICA_Insert:
			startActivity(new Intent(getApplicationContext(),
					DetalleCargaActAcadInsertarActivity.class));
			break;
		case R.id.button2_MenuDETALLEACTIVIDADACADEMICA_Delete:
			startActivity(new Intent(getApplicationContext(),
					DetalleCargaActAcadEliminarActivity.class));
			break;
		case R.id.button3_MenuDETALLEACTIVIDADACADEMICA_Consult:
			startActivity(new Intent(getApplicationContext(),
					DetalleCargaActAcadConsultarActivity.class));
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_carga_act_acad_menu, menu);
		return true;
	}

}