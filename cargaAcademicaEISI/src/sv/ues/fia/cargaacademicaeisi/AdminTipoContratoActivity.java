package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminTipoContratoActivity extends Activity implements OnClickListener {
	private Button btnInsertar;
	private Button btnActualizar;
	private Button btnConsultar;
	private Button btnEliminar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_tipo_contrato);
		btnInsertar = (Button) findViewById(R.id.btnNuevoContrato);
		btnInsertar.setOnClickListener(this);
		btnActualizar = (Button) findViewById(R.id.btnActualizarContrato);
		btnActualizar.setOnClickListener(this);
		btnConsultar = (Button) findViewById(R.id.btnConsultarContrato);
		btnConsultar.setOnClickListener(this);
		btnEliminar = (Button) findViewById(R.id.btnEliminarContrato);
		btnEliminar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_tipo_contrato, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnNuevoContrato:
			startActivity(new Intent(getApplicationContext(),
					InsertarContratoActivity.class));
			break;
		case R.id.btnActualizarContrato:
			startActivity(new Intent(getApplicationContext(),
				    ActualizarContratoActivity.class));
			break;
		case R.id.btnConsultarContrato:
			startActivity(new Intent(getApplicationContext(),
					ConsultarContratoActivity.class));
			break;
		case R.id.btnEliminarContrato:
			startActivity(new Intent(getApplicationContext(),
					EliminarContratoActivity.class));
			break;
		default:
			break;
	   }
		
	}

}
