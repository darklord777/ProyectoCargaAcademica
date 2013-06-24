package sv.ues.fia.cargaacademicaeisi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MateriaActualizarWebServiceActivity extends Activity {

	ControlDB db;

	static List<Materia> listaMaterias;
	static List<String> nombreMaterias;

	EditText fechaTxt;
	ListView listViewMaterias;
	
	private final String urlHostingGratuito = "http://etapa2pdm115.netai.net/ws_db_tarea_materia_fecha.php";
	private final String urlLocal = "http://192.168.56.1:8080/MateriaWebApplication/webresources/sv.ues.fia.tablamateria.entidad.materia/by";
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materia_actualizar_web_service);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		db = new ControlDB(this);

		listaMaterias = new ArrayList<Materia>();
		nombreMaterias = new ArrayList<String>();

		fechaTxt = (EditText) findViewById(R.id.editText1);
		listViewMaterias = (ListView) findViewById(R.id.listView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.materia_actualizar_web, menu);
		return true;
	}
	
	public void servicioLocal(View v) {
		try {
			String fechaprueba = fechaTxt.getText().toString();
			if(fechaprueba.equalsIgnoreCase("") ){
				String msj = "Importante: Debe ingresar fecha valida!";
				Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
			}else{
				
			listaMaterias.removeAll(listaMaterias);
			String[] fecha = fechaTxt.getText().toString().split("/");
			String url = urlLocal+"?fecha="+fecha[2]+"-"+fecha[1]+"-"+fecha[0];

			String materiasLocales = ControladorServicio.obtenerRespuestaPeticion(url, this);
			listaMaterias.addAll(ControladorServicio.obtenerMateriasLocal(materiasLocales, this));
			actualizarListView();
			}
			
		} catch (Exception e) {
		}
	}

	public void servicioExterno(View v) {
		
		String fechaprueba = fechaTxt.getText().toString();
		if(fechaprueba.equalsIgnoreCase("") ){
			String msj = "Importante: Debe ingresar fecha valida!";
			Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
		}else{
		
		listaMaterias.removeAll(listaMaterias);
		String[] fecha = fechaTxt.getText().toString().split("/");
		String url = urlHostingGratuito + "?day=" + fecha[0] + "&month="+ fecha[1] + "&year=" + fecha[2];
		//Toast.makeText(this, url, Toast.LENGTH_LONG).show();
		String materiasExternas = ControladorServicio.obtenerRespuestaPeticion(url, this);
		try {
			listaMaterias.addAll(ControladorServicio.obtenerMateriasExterno(materiasExternas, this));
			actualizarListView();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	  }
	}

	public void guardar(View v) {
		String fechaprueba = fechaTxt.getText().toString();
		if(fechaprueba.equalsIgnoreCase("") ){
			String msj = "Importante: Debe ingresar fecha valida y consultar antes de guardar!";
			Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
			}else{
		
		db.abrir();
		for(int i=0; i < listaMaterias.size();i++){
			Log.v("guardar",db.insertar(listaMaterias.get(i)));
		}
		db.cerrar();
		Toast.makeText(this, "Guardado con exito", Toast.LENGTH_LONG).show();
		listaMaterias.removeAll(listaMaterias);
		actualizarListView();
	 }
	}

	private void actualizarListView() {
		String dato = "";
		nombreMaterias.clear();
		for (int i = 0; i < listaMaterias.size(); i++) {
			dato = listaMaterias.get(i).getCodigomateria() + "    "
					+ listaMaterias.get(i).getNom_materia();
			nombreMaterias.add(dato);
		}
		eliminarElementosDuplicados();
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, nombreMaterias);
		listViewMaterias.setAdapter(adaptador);
	}

	private void eliminarElementosDuplicados() {
		HashSet<Materia> conjuntoMateria = new HashSet<Materia>();
		conjuntoMateria.addAll(listaMaterias);
		listaMaterias.clear();
		listaMaterias.addAll(conjuntoMateria);

		HashSet<String> conjuntoNombre = new HashSet<String>();
		conjuntoNombre.addAll(nombreMaterias);
		nombreMaterias.clear();
		nombreMaterias.addAll(conjuntoNombre);
	}

}
