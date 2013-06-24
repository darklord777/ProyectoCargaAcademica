package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MateriaEliminarWebServiceActivity extends Activity  implements OnClickListener  {
	private EditText codigo;
	private final String urlHostingGratuito = "http://etapa2pdm115.netai.net/ws_db_tarea_materia_eliminar.php";
	private final String urlLocal = "http://192.168.56.1:8080/MateriaWebApplication/webresources/sv.ues.fia.tablamateria.entidad.materia/by";
	
	private Button BOTONLOCAL;
	private Button BOTONEXTERNO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materia_eliminar_web_service);
		codigo = (EditText) findViewById(R.id.editText_codigo2);
		BOTONLOCAL = (Button) findViewById(R.id.btnLocal23);
		BOTONLOCAL.setOnClickListener(this);
		BOTONEXTERNO = (Button) findViewById(R.id.btnExterno23);
		BOTONEXTERNO.setOnClickListener(this);
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.materia_eliminar_web, menu);
		return true;
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String codigomateria = codigo.getText().toString();
		switch (v.getId()) {
		case R.id.btnLocal23:
			if(codigomateria.equalsIgnoreCase("") ){
				String msj = "Importante: Debe ingresar Codigo de Materia!";
				Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
			}else{
				
			}
			break;
		case R.id.btnExterno23:
			if(codigomateria.equalsIgnoreCase("") ){
				String msj = "Importante: Debe ingresar Codigo de Materia!";
				Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
			}else{
				String url = urlHostingGratuito + "?codigomateria=" + codigomateria;
				ControladorServicio.eliminarMateriaExterno(url, this);
			}
			

			
			break;
		}
	}//FIN CLASE PRINCIPAL
	
	
	

}
