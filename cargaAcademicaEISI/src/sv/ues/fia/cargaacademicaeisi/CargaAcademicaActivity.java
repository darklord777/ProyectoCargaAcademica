package sv.ues.fia.cargaacademicaeisi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class CargaAcademicaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carga_academica);

		// Vamos a declarar un nuevo thread
		Thread timer = new Thread() {
			// El nuevo Thread exige el metodo run
			public void run() {
				try {
					sleep(5000);

				} catch (InterruptedException e) {
					// Si no puedo ejecutar el sleep muestro el error
					e.printStackTrace();
				} finally {
						startActivity(new Intent(getApplicationContext(),MenuPrincipalActivity.class));
				}
			}
		};
		// ejecuto el thread
		timer.start();

	}

}
